package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.entities.moviedetail.MovieDetailResponse
import com.example.domain.usecases.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun fetchData() = movieUseCase.getMovieList()

}