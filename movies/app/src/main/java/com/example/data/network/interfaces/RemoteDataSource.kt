package com.example.data.network.interfaces

import com.example.data.entities.moviedetail.MovieDetailResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getDetailMovie(movieId: Int): Flow<MovieDetailResponse>
}