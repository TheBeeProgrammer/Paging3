package com.example.domain.usecases

import com.example.domain.repository.MovieDeatilRepository

class MovieDetailUseCase(private val repositoryDetail: MovieDeatilRepository) {
    suspend fun getMovie(movieId: Int) = repositoryDetail.getMovie(movieId)
}
