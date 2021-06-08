package com.example.presentation.view.adapter.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.network.entities.movie.MovieEntity
import com.example.movies.R

private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val poster: ImageView = view.findViewById(R.id.ivMovies)
    private var movie: MovieEntity? = null
    private var callBackAdapter: MovieAdapter.CallBackAdapter? = null

    init {
        view.setOnClickListener {
            callBackAdapter?.onMovieClicked(this.movie?.id)
        }
    }

    fun setCallBack(callBackAdapter: MovieAdapter.CallBackAdapter?) {
        this.callBackAdapter = callBackAdapter
    }

    fun bind(movie: MovieEntity?) {
        if (movie == null) {
            // TODO: 6/6/2021 show empty image
        } else {
            showmovieData(movie)
        }
    }

    private fun showmovieData(movie: MovieEntity?) {
        this.movie = movie
        Glide.with(view.context).load(BASE_IMAGE_URL.plus(movie?.posterPath)).into(poster)
    }

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_items, parent, false)
            return MovieViewHolder(view)
        }
    }

}
