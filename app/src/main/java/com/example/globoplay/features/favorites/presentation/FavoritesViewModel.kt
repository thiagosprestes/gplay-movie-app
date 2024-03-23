package com.example.globoplay.features.favorites.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.domain.model.States
import com.example.globoplay.features.favorites.domain.usecase.GetMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getMovieFavoriteUseCase: GetMovieFavoriteUseCase
) : ViewModel() {
    val uiState = mutableStateOf(States.LOADING)
    val movies = mutableStateOf<List<Movie>>(emptyList())

    init {
        viewModelScope.launch {
            getFavoriteMovies()
        }
    }

    private suspend fun getFavoriteMovies() {
        val response = getMovieFavoriteUseCase.invoke()

        response.collectLatest {
            movies.value = it
            uiState.value = States.DEFAULT
        }
    }
}