package com.abrsoftware.myapplication.view.home

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.abrsoftware.myapplication.MyApplication
import com.abrsoftware.myapplication.R
import com.abrsoftware.myapplication.data.AppDatabase.Companion.getInstance
import com.abrsoftware.myapplication.data.Movie
import com.abrsoftware.myapplication.data.MoviesDao
import com.abrsoftware.myapplication.event.GeneralEvent
import com.abrsoftware.myapplication.event.PostEvent
import com.abrsoftware.myapplication.service.ApiService
import com.abrsoftware.myapplication.service.ApiServiceSingleton
import com.abrsoftware.myapplication.utils.Connectivity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class HomeRepository : PostEvent(), HomeMvp.Repository {
    private val gson: Gson
    private val apiService: ApiService
    private val db: MoviesDao

    @SuppressLint("NewApi")
    override fun getPolular(page: String?) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(
                GeneralEvent.ERROR,
                MyApplication.getContext().getString(R.string.errorConnectivity)
            )
            return
        }

        if (db.gelAllMovies("popular").size > 0) {
            Log.d("MSJ", "GET popular")
            val listMoviesPopular = ListMoviesPopular()
            listMoviesPopular.results = db.gelAllMovies("popular")
            postEvent(GeneralEvent.SUCCESS_RESPONCE, listMoviesPopular)
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                val response = apiService.apiClient!!.getPopular(
                    page,
                    MyApplication.getContext().getString(R.string.api_key)
                )
                if (response!!.isSuccessful) {
                    val movies = gson.fromJson(response.body()!!, ListMoviesPopular::class.java)
                    val listPopular = movies as ListMoviesPopular
                    listPopular.results?.stream()!!.forEach { elt -> elt.type = "popular" }
                    insertMovies(listPopular.results!!)
                    postEvent(GeneralEvent.SUCCESS_RESPONCE, listPopular)
                } else {
                    postEvent(
                        GeneralEvent.ERROR,
                        MyApplication.getContext().getString(R.string.errorConnectivity)
                    )
                }
            }
        }
    }

    override fun getLastest(page: String?) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(
                GeneralEvent.ERROR,
                MyApplication.getContext().getString(R.string.errorConnectivity)
            )
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            if (db.gelAllMovies("lastest").size > 0) {
                Log.d("MSJ", "GET lastest")
                postEvent(GeneralEvent.SUCCESS_RESPONCE, db.gelAllMovies("lastest").get(0))
            } else {
                val response = apiService.apiClient!!.getLastest(
                    page, "8ac3bea5e0e63f1b4c5f608d2f31b372"
                )
                if (response!!.isSuccessful) {
                    val movie = gson.fromJson(response.body(), Movie::class.java)
                    val lastest = (movie as Movie)
                    lastest.type = "lastest"
                    insertUser(lastest)
                    postEvent(GeneralEvent.SUCCESS_RESPONCE, movie)
                } else {
                    postEvent(
                        GeneralEvent.ERROR,
                        MyApplication.getContext().getString(R.string.errorConnectivity)
                    )
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getUpcomming(page: String?) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(
                GeneralEvent.ERROR,
                MyApplication.getContext().getString(R.string.errorConnectivity)
            )
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            if (db.gelAllMovies("upcomming").size > 0) {
                Log.d("MSJ", "GET upcomming")
                val listMoviesUpcomming = ListMoviesUpcomming()
                listMoviesUpcomming.results = db.gelAllMovies("upcomming")
                postEvent(GeneralEvent.SUCCESS_RESPONCE, listMoviesUpcomming)
            } else {
                val response =
                    apiService.apiClient!!.getUpcoming(
                        page,
                        MyApplication.getContext().getString(R.string.api_key)
                    )
                if (response!!.isSuccessful) {
                    val listUpc = gson.fromJson(response.body(), ListMoviesUpcomming::class.java) as ListMoviesUpcomming
                    listUpc.results?.stream()!!.forEach { elt -> elt.type = "upcomming" }
                    insertMovies(listUpc.results!!)
                    postEvent(GeneralEvent.SUCCESS_RESPONCE, listUpc)
                } else {
                    postEvent(
                        GeneralEvent.ERROR,
                        MyApplication.getContext().getString(R.string.errorConnectivity)
                    )
                }
            }
        }
    }

    init {
        apiService = ApiServiceSingleton.instance.apiService
        gson = Gson()
        db = getInstance(MyApplication.getContext())!!.moviewsDao()
    }

    fun gelAllMovies(type: String): List<Movie> {
        return db.gelAllMovies(type)
    }

    // Insert new user
    fun insertUser(movie: Movie) {
        insertAsyncTask(db).execute(movie)
    }

    fun insertMovies(movie: List<Movie>) {
        insertAsyncMovies(db).execute(movie)
    }

    private class insertAsyncTask internal constructor(private val moviewDao: MoviesDao) :
        AsyncTask<Movie, Void, Void>() {

        override fun doInBackground(vararg params: Movie): Void? {
            moviewDao.insertMovies(params[0])
            return null
        }
    }

    private class insertAsyncMovies internal constructor(private val moviewDao: MoviesDao) :
        AsyncTask<List<Movie>, Void, Void>() {

        override fun doInBackground(vararg params: List<Movie>): Void? {
            moviewDao.insertMovies(params[0])
            return null
        }
    }
}