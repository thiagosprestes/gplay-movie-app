package com.example.globoplay.core.presentation.navigation

sealed class Route(
    val routeName: String
) {
    object HomeScreen : Route("HomeScreen")
    object FavoritesScreen : Route("FavoritesScreen")
    object MovieDetailsScreen : Route("MovieDetailsScreen/{movieId}") {
        fun passMovieId(movieId: Int) = "MovieDetailsScreen/${movieId}"
    }
}
