package com.example.globoplay.core.presentation.navigation

sealed class MovieDetailsNavigation(val route: String) {
    object MovieDetailsScreen : MovieDetailsNavigation(
        route = "MovieDetailsScreen/{movieId}"
    ) {
        fun passMovieId(movieId: Int) = "MovieDetailsScreen/${movieId}"
    }
}