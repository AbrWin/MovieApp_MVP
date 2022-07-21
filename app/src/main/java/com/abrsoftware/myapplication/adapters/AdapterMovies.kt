package com.abrsoftware.myapplication.adapters

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abrsoftware.myapplication.R
import com.abrsoftware.myapplication.data.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_movie.view.*

class AdapterMovies(
    private val listener: onItemClickListener,
    private val movies: List<Movie>,
    private val context: Context
) : RecyclerView.Adapter<AdapterMovies.MoviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviewHolder, position: Int) {
        holder.movie = movies.get(position)
        if (!TextUtils.isEmpty(holder.movie!!.title)) {
            val urlImg = context.getString(R.string.img_end_point) + holder.movie!!.poster_path
            Glide.with(context)
                .load(urlImg)
                .centerCrop().crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.itemView.imgMoview)
        }
    }

    override fun getItemCount(): Int {
        return if (movies.size > 0) movies.size else 0
    }

    inner class MoviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var movie: Movie? = null
        override fun onClick(view: View?) {
            listener.onClickItem(this, view)
        }

        init {
            itemView.setOnClickListener(this)
        }


    }

    interface onItemClickListener {
        fun onClickItem(holder: MoviewHolder?, view: View?)
    }
}

