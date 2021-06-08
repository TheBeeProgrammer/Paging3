package com.example

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.data.db.MovieDatabase
import com.example.data.network.api.MovieDetailApi
import com.example.data.network.api.TheMovieDbService
import com.example.domain.repository.MovieDeatilRepository
import com.example.domain.repository.TheMovieDbRepository
import com.example.domain.usecases.MovieUseCase
import com.example.presentation.viewmodel.ViewModelFactory

object Injector {

    private fun provideTheMovieDbRepository(context: Context) = TheMovieDbRepository(
        TheMovieDbService.create(),
        MovieDatabase.getInstance(context)
    )

    private fun provideTheMovieDetailRepository() =
        MovieDeatilRepository(TheMovieDbService.create())

    private fun provideMovieUseCase(context: Context) =
        MovieUseCase(provideTheMovieDbRepository(context), provideTheMovieDetailRepository())

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideMovieUseCase(context))
    }
}