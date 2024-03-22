package com.example.globoplay.features.home.domain.repository

import com.example.globoplay.core.data.remote.model.response.MovieResponse

interface MoviePopularRepository {
    suspend fun getPopularMovies(): MovieResponse
}