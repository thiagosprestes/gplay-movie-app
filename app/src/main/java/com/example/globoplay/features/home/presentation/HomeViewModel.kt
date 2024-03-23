package com.example.globoplay.features.home.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.util.ErrorType
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.home.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    var uiState = mutableStateOf(States.LOADING)
    var popularMovies = mutableStateOf<List<Movie>?>(emptyList())
    var upcomingMovies = mutableStateOf<List<Movie>?>(emptyList())

    init {
        viewModelScope.launch {
            getPopularMovies()
        }
    }

    private suspend fun getPopularMovies() {
        val movies = getMoviesUseCase.invoke()

        movies.collect {
            when (it) {
                is ResultData.Success -> {
                    popularMovies.value = it.data?.first
                    upcomingMovies.value = it.data?.second
                    uiState.value = States.DEFAULT
                }

                is ResultData.Failure -> {
                    if (it.type == ErrorType.GENERIC) {
                        uiState.value = States.GENERIC_ERROR
                        return@collect
                    }
                    uiState.value = States.NETWORK_ERROR
                }
            }
        }
    }
}