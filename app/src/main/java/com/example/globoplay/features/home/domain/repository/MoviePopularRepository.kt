package com.example.globoplay.features.home.domain.repository

import com.example.globoplay.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    fun getPopularMovies(): Flow<Movie>
}