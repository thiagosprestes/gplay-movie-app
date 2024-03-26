package com.example.globoplay.features.home.presentation

import com.example.globoplay.TestDispatcherRule
import com.example.globoplay.core.domain.model.MovieFactory
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.home.domain.usecase.GetMoviesUseCase
import com.google.common.truth.Truth.assertThat
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
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMoviesUseCase: GetMoviesUseCase

    private val viewModel by lazy {
        HomeViewModel(getMoviesUseCase)
    }

    private val popularMovies = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.SpiderMan)
    )

    private val nowPlaying = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.SpiderMan)
    )

    @Test
    fun `must validate usecase response`() = runTest {
        whenever(getMoviesUseCase.invoke()).thenReturn(
            flowOf(ResultData.Success(popularMovies to nowPlaying))
        )

        val result = viewModel.popularMovies.value?.first()

        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when usecase return a exception`() = runTest {
        whenever(getMoviesUseCase.invoke()).thenThrow(RuntimeException())

        val result = viewModel.popularMovies.value?.first()

        assertThat(result).isNull()
    }
}