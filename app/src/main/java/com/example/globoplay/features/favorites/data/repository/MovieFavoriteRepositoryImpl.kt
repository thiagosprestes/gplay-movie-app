package com.example.globoplay.features.favorites.data.repository

import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.features.favorites.domain.repository.MovieFavoriteRepository
import com.example.globoplay.features.favorites.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: MovieFavoriteLocalDataSource
) : MovieFavoriteRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return localDataSource.getMovies()
    }

    override suspend fun insert(movie: Movie) {
        localDataSource.insert(movie)
    }

    override suspend fun delete(movie: Movie) {
        localDataSource.delete(movie)
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return localDataSource.isFavorite(movieId)
    }
}