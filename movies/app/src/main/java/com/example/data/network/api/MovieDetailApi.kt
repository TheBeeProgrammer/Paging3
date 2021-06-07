package com.example.data.network.api

import com.example.data.network.entities.moviedetail.MovieDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieDetailApi(private var theMovieDbService: TheMovieDbService) {

    suspend fun fetchMovies(movieId: Int): Flow<MovieDetailResponse> {
        return flow {
            emit(theMovieDbService.fetchMovieDetail(movieId))
        }.flowOn(Dispatchers.IO) as Flow<MovieDetailResponse>
    }
}