package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.network.entities.moviedetail.MovieDetailResponse
import com.example.domain.usecases.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun fetchData() = movieUseCase.getMovieList()
    suspend fun getMovieDetail(movieId: Int) = movieUseCase.getMovie(837007)

    fun getVoteAverage(movieDetailResponse: MovieDetailResponse) =
        ( movieDetailResponse.voteAverage.div(10) * 5).toFloat()
}