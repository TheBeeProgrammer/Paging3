package com.example

import androidx.lifecycle.ViewModelProvider
import com.example.data.network.api.TheMovieDbService
import com.example.data.repository.TheMovieDbRepository
import com.example.presentation.viewmodel.ViewModelFactory

object Injector {
    fun provideTheMovieDbRepository() = TheMovieDbRepository(TheMovieDbService.create())
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideTheMovieDbRepository())
    }
}