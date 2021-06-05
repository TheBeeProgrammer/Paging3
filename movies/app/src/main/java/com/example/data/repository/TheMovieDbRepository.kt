package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.network.api.TheMovieDbService
import com.example.data.network.entities.MovieEntity
import com.example.data.network.paging.TheMovieDbPagingSource
import kotlinx.coroutines.flow.Flow

class TheMovieDbRepository(private val theMovieDb: TheMovieDbService) {
    fun getSearchResultStream(): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { TheMovieDbPagingSource(theMovieDb) }
        ).flow
    }
}