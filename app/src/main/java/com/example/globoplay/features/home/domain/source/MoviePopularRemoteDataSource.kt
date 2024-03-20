package com.example.globoplay.features.home.domain.source

import com.example.globoplay.core.data.remote.model.response.MovieResponse

interface MoviePopularRemoteDataSource {
    suspend fun getPopularMovies(): MovieResponse
}