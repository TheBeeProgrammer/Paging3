package com.example.domain.usecases

import com.example.domain.repository.TheMovieDbRepository

class MovieUseCase(
    private val repository: TheMovieDbRepository
) {

    fun getMovieList() = repository.getResult()



}