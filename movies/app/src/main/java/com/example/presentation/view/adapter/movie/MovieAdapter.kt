package com.example.presentation.view.adapter.movie

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.data.network.entities.movie.MovieEntity
import com.example.presentation.view.fragments.MovieFragment

class MovieAdapter : PagingDataAdapter<MovieEntity, MovieViewHolder>(Movie_COMPARATOR) {

    private var callBackAdapter: CallBackAdapter? = null

    fun setView(fragment: MovieFragment?) {
        this.callBackAdapter = fragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setCallBack(callBackAdapter)
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }

    companion object {
        private val Movie_COMPARATOR = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }

    interface CallBackAdapter {
        fun onMovieClicked(movieId: Int?)
    }

}
