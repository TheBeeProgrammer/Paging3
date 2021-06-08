package com.example.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.db.MovieDatabase
import com.example.data.db.mediator.MovieRemoteMediator
import com.example.data.entities.movie.MovieEntity
import com.example.data.network.api.TheMovieDbService
import kotlinx.coroutines.flow.Flow

class TheMovieDbRepository(
    private val service: TheMovieDbService,
    private val database: MovieDatabase
) {
    fun getResult(): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { database.movieDao().getMovies() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = MovieRemoteMediator(
                service,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}