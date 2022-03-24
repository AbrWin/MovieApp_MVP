package com.abrsoftware.myapplication.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abrsoftware.myapplication.R
import com.abrsoftware.myapplication.view.BaseView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.include_recommendation.*
import kotlinx.android.synthetic.main.include_recommendation.view.*
import kotlinx.android.synthetic.main.view_home.*
import java.util.*

class ViewHome : BaseView(), HomeMvp.View {

    lateinit var presenter: HomePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = HomePresenter(this)
        presenter.oncreate()
        presenter.getRecommendation("3","550")
        rootView = inflater.inflate(R.layout.view_home, container, false)
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun showLoading(show: Boolean) {

    }

    override fun showMsj(msj: String?) {

    }

    override fun <T : Any?> successResponce(responce: T) {
        if( responce is RecommendationResponce){
            val recommendation = (responce as RecommendationResponce)
            title.text = recommendation.original_title
            overview.text = recommendation.overview
            val urlImg = requireContext().getString(R.string.img_end_point)+recommendation.poster_path
            Glide.with(context)
                .load(urlImg)
                .centerCrop().crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imgMovie)
        }
    }
}