package com.example.globoplay.features.favorites.presentation

import com.example.globoplay.TestDispatcherRule
import com.example.globoplay.core.domain.model.MovieFactory
import com.example.globoplay.features.favorites.domain.usecase.GetMovieFavoriteUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoritesViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMovieFavoriteUseCase: GetMovieFavoriteUseCase

    private val viewModel by lazy {
        FavoritesViewModel(getMovieFavoriteUseCase)
    }

    private val movies = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.SpiderMan)
    )

    @Test
    fun `must validate get favorite movies`() = runTest {
        whenever(getMovieFavoriteUseCase.invoke()).thenReturn(
            flowOf(movies)
        )

        val result = viewModel.movies.value

        assertThat(result).isNotEmpty()
        assertThat(result).contains(movies[0])
    }
}