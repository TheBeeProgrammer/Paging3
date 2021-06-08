package com.example.presentation.view.fragments

import com.example.data.entities.moviedetail.MovieDetailResponse
import com.example.data.network.interfaces.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val movieDetailMock: MovieDetailResponse = MovieDetailResponse(
    1,
    "loren ipsum",
    "123.png",
    "2021/06/08",
    "movieTest",
    5.9,
    1000
)

class FakeMovieDetailApi : RemoteDataSource {
    override suspend fun getDetailMovie(movieId: Int): Flow<MovieDetailResponse> {
        return flowOf(movieDetailMock)
    }
}