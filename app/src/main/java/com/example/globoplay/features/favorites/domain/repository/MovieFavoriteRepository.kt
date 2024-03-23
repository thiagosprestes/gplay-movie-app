package com.example.globoplay.features.favorites.domain.repository

import com.example.globoplay.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteRepository {
    fun getMovies(): Flow<List<Movie>>

    suspend fun insert(movie: Movie)

    suspend fun delete(movie: Movie)

    suspend fun isFavorite(movieId: Int): Boolean
}