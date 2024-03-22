package com.example.globoplay.features.home.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.util.ErrorType
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.home.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    var uiState = mutableStateOf(States.LOADING)
    var popularMovies = mutableStateOf<List<Movie>>(emptyList())

    init {
        viewModelScope.launch {
            getPopularMovies()
        }
    }

    private suspend fun getPopularMovies() {
        val movies = getPopularMoviesUseCase.invoke()

        movies.collect {
            when (it) {
                is ResultData.Success -> {
                    popularMovies.value = it.data!!
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