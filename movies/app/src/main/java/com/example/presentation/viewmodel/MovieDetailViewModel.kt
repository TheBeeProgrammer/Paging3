package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.entities.moviedetail.MovieDetailResponse
import com.example.domain.usecases.MovieDetailUseCase

class MovieDetailViewModel(private val usecase: MovieDetailUseCase) : ViewModel() {

    suspend fun getMovieDetail(movieId: Int) = usecase.getMovie(movieId)

    fun getVoteAverage(movieDetailResponse: MovieDetailResponse) =
        (movieDetailResponse.voteAverage.div(10) * 5).toFloat()
}