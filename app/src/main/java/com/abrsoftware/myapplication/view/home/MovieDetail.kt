package com.abrsoftware.myapplication.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abrsoftware.myapplication.R
import com.abrsoftware.myapplication.data.Movie
import com.abrsoftware.myapplication.view.BaseView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.moview_detail.*

class MovieDetail : BaseView() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            rootView = inflater.inflate(R.layout.moview_detail, container, false)
        return rootView
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments!= null && arguments!!.getSerializable("singlemovie") != null){
            val singleMovie =  arguments!!.getSerializable("singlemovie") as Movie
            title.text = singleMovie.title
            releaseDate.text = singleMovie.release_date
            description.text = singleMovie.overview
            if (!TextUtils.isEmpty(singleMovie.poster_path)) {
                val urlImg =
                    context!!.getString(R.string.img_end_point) + singleMovie.poster_path
                Glide.with(context)
                    .load(urlImg)
                    .centerCrop().crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imgMovie)
            }
        }
    }

}