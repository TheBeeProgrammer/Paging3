package com.example.data.network.api

import com.example.data.entities.moviedetail.MovieDetailResponse
import com.example.data.network.interfaces.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieDetailApi(private var theMovieDbService: TheMovieDbService) : RemoteDataSource {

    override suspend fun getDetailMovie(movieId: Int): Flow<MovieDetailResponse> {
        return flow {
            emit(theMovieDbService.fetchMovieDetail(movieId))
        }.flowOn(Dispatchers.IO)
    }

}