package com.example.domain.repository

import com.example.data.network.api.MovieDetailApi
import com.example.data.network.interfaces.RemoteDataSource

class MovieDeatilRepository(private var remoteDataSource: RemoteDataSource) {
    suspend fun getMovie(movieId: Int) = remoteDataSource.getDetailMovie(movieId)

}