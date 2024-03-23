package com.example.globoplay.features.home.domain.source

import com.example.globoplay.core.data.remote.model.response.MovieResponse

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): MovieResponse

    suspend fun getUpcomingMovies(): MovieResponse
}