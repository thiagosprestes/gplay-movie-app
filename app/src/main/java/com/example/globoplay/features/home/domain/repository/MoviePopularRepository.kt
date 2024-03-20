package com.example.globoplay.features.home.domain.repository

import com.example.globoplay.core.data.remote.model.MovieResult
import com.example.globoplay.core.data.remote.model.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    suspend fun getPopularMovies(): MovieResponse
}