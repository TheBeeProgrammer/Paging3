package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.MovieDetailUseCase
import com.example.domain.usecases.MovieUseCase

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val movieUseCase: MovieUseCase,
    private val movieDetailUseCase: MovieDetailUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(movieUseCase) as T
        }
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieDetailUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}