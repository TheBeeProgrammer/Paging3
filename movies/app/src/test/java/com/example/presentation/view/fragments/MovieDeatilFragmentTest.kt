package com.example.presentation.view.fragments

import com.example.domain.repository.MovieDeatilRepository
import com.example.domain.usecases.MovieDetailUseCase
import com.example.presentation.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDeatilFragmentTest {

    @get:Rule
    val corutineTestRule = CorutineTestRules()

    @Test
    fun `Get the movie detail from the server`() = corutineTestRule.testDispatcher.runBlockingTest {
        val repository = MovieDeatilRepository(FakeMovieDetailApi())
        val subject = MovieDetailViewModel(MovieDetailUseCase(repository))
        subject.getMovieDetail(1).collectLatest { Assert.assertEquals(movieDetailMock, it) }

    }

}


