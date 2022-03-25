package com.abrsoftware.myapplication.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abrsoftware.myapplication.MainActivity
import com.abrsoftware.myapplication.R
import com.abrsoftware.myapplication.adapters.AdapterMovies
import com.abrsoftware.myapplication.data.Movie
import com.abrsoftware.myapplication.utils.Connectivity
import com.abrsoftware.myapplication.view.BaseView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.include_recommendation.*
import kotlinx.android.synthetic.main.view_home.*

class ViewHome : BaseView(), HomeMvp.View, AdapterMovies.onItemClickListener, View.OnClickListener {

    lateinit var presenter: HomePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = HomePresenter(this)
        presenter.oncreate()
        rootView = inflater.inflate(R.layout.view_home, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retryBtn.setOnClickListener(this)
        if(Connectivity.isOnline(context)){
            showContets(true)
            getInfoService()
        }else{
            showContets(false)
        }
    }

    private fun showContets(isOnline: Boolean){
        contenPrincipal.visibility = if (isOnline)  View.VISIBLE else View.GONE
        contentRetry.visibility = if (isOnline)  View.GONE else View.VISIBLE
    }

    private fun getInfoService(){
        presenter.getLastest("3")
        presenter.getPolular("3")
        presenter.getUpcomming("3")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun showLoading(show: Boolean) {

    }

    override fun showMsj(msj: String?) {

    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun <T : Any?> successResponce(responce: T) {
        when (responce) {
            is Movie -> {
                val movie = responce
                title.text = movie.title
                overview.text = movie.overview
                if (!TextUtils.isEmpty(movie.poster_path)) {
                    val urlImg =
                        context!!.getString(R.string.img_end_point) + movie.poster_path
                    Glide.with(context)
                        .load(urlImg)
                        .centerCrop().crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imgMovie)
                }
            }
            is ListMoviesUpcomming -> {
                initAdapter(responce.results, upcommingRecycler)
            }

            is ListMoviesPopular -> {
                initAdapter(responce.results, popularRecycler)
            }
        }
    }

    override fun onClickItem(holder: AdapterMovies.MoviewHolder?, view: View?) {
        val bundle = Bundle()
        bundle.putSerializable("singlemovie", holder!!.movie)
        (activity as MainActivity).changeFragment(MovieDetail::class.java, bundle)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    fun initAdapter(movies: List<Movie>, recycler: RecyclerView){
        val adapterBranch = AdapterMovies(this, movies, context!!)
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        recycler.layoutManager = linearLayout
        recycler.adapter = adapterBranch
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.retryBtn->{
                if(Connectivity.isOnline(context)){
                    showContets(true)
                    getInfoService()
                }else{
                    Toast.makeText(context, "NO INTERNET",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}