package com.abrsoftware.myapplication.view.home

import android.os.AsyncTask
import com.abrsoftware.myapplication.data.AppDatabase.Companion.getInstance
import com.abrsoftware.myapplication.event.PostEvent
import com.google.gson.Gson
import com.abrsoftware.myapplication.service.ApiService
import com.abrsoftware.myapplication.data.MoviesDao
import com.abrsoftware.myapplication.utils.Connectivity
import com.abrsoftware.myapplication.MyApplication
import com.abrsoftware.myapplication.event.GeneralEvent
import com.abrsoftware.myapplication.R
import com.abrsoftware.myapplication.data.Movie
import com.abrsoftware.myapplication.service.ApiServiceSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository : PostEvent(), HomeMvp.Repository {
    private val gson: Gson
    private val apiService: ApiService
    private val db: MoviesDao

    override fun getPolular(page: String) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(
                GeneralEvent.ERROR,
                MyApplication.getContext().getString(R.string.errorConnectivity)
            )
            return
        }
        apiService.apiClient.getPopular(
            page,
            MyApplication.getContext().getString(R.string.api_key)
        ).enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                val responce = gson.fromJson(response.body(), ListMoviesPopular::class.java)
                postEvent(GeneralEvent.SUCCESS_RESPONCE, responce)
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                postEvent(
                    GeneralEvent.ERROR,
                    MyApplication.getContext().getString(R.string.errorConnectivity)
                )
            }
        })
    }

    override fun getLastest(page: String) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(
                GeneralEvent.ERROR,
                MyApplication.getContext().getString(R.string.errorConnectivity)
            )
            return
        }
        apiService.apiClient.getLastest(
            page,
            MyApplication.getContext().getString(R.string.api_key)
        ).enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                val responce = gson.fromJson(response.body(), SingleMovie::class.java)
                postEvent(GeneralEvent.SUCCESS_RESPONCE, responce)
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                postEvent(
                    GeneralEvent.ERROR,
                    MyApplication.getContext().getString(R.string.errorConnectivity)
                )
            }
        })
    }

    override fun getUpcomming(page: String) {
        if (!Connectivity.isOnline(MyApplication.getContext())) {
            postEvent(
                GeneralEvent.ERROR,
                MyApplication.getContext().getString(R.string.errorConnectivity)
            )
            return
        }
        apiService.apiClient.getUpcoming(
            page,
            MyApplication.getContext().getString(R.string.api_key)
        ).enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                val responce = gson.fromJson(response.body(), ListMoviesUpcomming::class.java)
                postEvent(GeneralEvent.SUCCESS_RESPONCE, responce)
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                postEvent(
                    GeneralEvent.ERROR,
                    MyApplication.getContext().getString(R.string.errorConnectivity)
                )
            }
        })
    }

    init {
        apiService = ApiServiceSingleton.apiServiceHolder.apiService
        gson = Gson()
        db = getInstance(MyApplication.getContext())!!.moviewsDao()
    }

    fun getAllUsers(): List<Movie> {
        return db.gelAllUsers()
    }

    // Insert new user
    fun insertUser(movie: Movie) {
        insertAsyncTask(db).execute(movie)
    }

    private class insertAsyncTask internal constructor(private val moviewDao: MoviesDao) :
        AsyncTask<Movie, Void, Void>() {

        override fun doInBackground(vararg params: Movie): Void? {
            moviewDao.insertMovies(params[0])
            return null
        }
    }
}