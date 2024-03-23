package com.example.globoplay.features.movieDetail.presentation

import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.domain.model.MovieDetails

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent()
    data class AddFavorite(val movie: MovieDetails) : MovieDetailEvent()
    data class CheckedFavorite(val movieId: Int) : MovieDetailEvent()
    data class RemoveFavorite(val movie: MovieDetails) : MovieDetailEvent()
}