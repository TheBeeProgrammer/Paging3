package com.example

import androidx.lifecycle.ViewModelProvider
import com.example.data.network.api.MovieDetailApi
import com.example.data.network.api.TheMovieDbService
import com.example.domain.repository.MovieDeatilRepository
import com.example.domain.repository.TheMovieDbRepository
import com.example.domain.usecases.MovieUseCase
import com.example.presentation.viewmodel.ViewModelFactory

object Injector {

    fun provideTheMovieDbRepository() = TheMovieDbRepository(TheMovieDbService.create())
    fun provideTheMovieDetailRepository() = MovieDeatilRepository(TheMovieDbService.create())
    fun provideMovieUseCase() = MovieUseCase(provideTheMovieDbRepository(), provideTheMovieDetailRepository())
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideMovieUseCase())
    }
}