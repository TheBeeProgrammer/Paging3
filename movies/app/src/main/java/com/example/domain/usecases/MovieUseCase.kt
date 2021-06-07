package com.example.domain.usecases

import com.example.domain.repository.MovieDeatilRepository
import com.example.domain.repository.TheMovieDbRepository

class MovieUseCase(
    private val repository: TheMovieDbRepository,
    private val repositoryDetail: MovieDeatilRepository
) {

    fun getMovieList() = repository.getResult()

    suspend fun getMovie(movieId: Int) = repositoryDetail.getMovie(837007)

}