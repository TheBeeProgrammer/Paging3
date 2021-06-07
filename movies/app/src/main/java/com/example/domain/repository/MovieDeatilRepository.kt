package com.example.domain.repository

import com.example.data.network.api.MovieDetailApi
import com.example.data.network.api.TheMovieDbService

class MovieDeatilRepository(private val theMovieDbService: TheMovieDbService) {
    suspend fun getMovie(movieId: Int) = MovieDetailApi(theMovieDbService).fetchMovies(movieId)
}