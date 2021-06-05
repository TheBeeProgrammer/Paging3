package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.repository.TheMovieDbRepository

class MovieViewModel(private val repository: TheMovieDbRepository) : ViewModel() {
    fun fetchData() = repository.getResult()
}