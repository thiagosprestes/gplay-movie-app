package com.example.globoplay.features.favorites.data.source

import com.example.globoplay.core.data.local.dao.MovieDao
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.features.favorites.data.mappers.toMovieEntity
import com.example.globoplay.features.favorites.data.mappers.toMovies
import com.example.globoplay.features.favorites.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieFavoriteLocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : MovieFavoriteLocalDataSource {
    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies().map {
            it.toMovies()
        }
    }

    override suspend fun insert(movie: Movie) {
        dao.insertMovie(movie.toMovieEntity())
    }

    override suspend fun delete(movie: Movie) {
        dao.deleteMovie(movie.toMovieEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return dao.isFavorite(movieId) != null
    }
}