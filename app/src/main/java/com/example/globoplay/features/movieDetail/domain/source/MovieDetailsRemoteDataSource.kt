package com.example.globoplay.features.movieDetail.domain.source

import com.example.globoplay.core.data.remote.model.response.MovieResponse
import com.example.globoplay.core.domain.model.MovieCredits
import com.example.globoplay.core.domain.model.MovieDetails

interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMovieCredits(movieId: Int): MovieCredits

    suspend fun getSimilarMovies(movieId: Int): MovieResponse
}