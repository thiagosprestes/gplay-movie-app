package com.example.globoplay.features.movieDetail.presentation

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent()
}