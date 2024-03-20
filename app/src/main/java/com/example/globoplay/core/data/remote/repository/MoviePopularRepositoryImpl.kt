package com.example.globoplay.core.data.remote.repository

import com.example.globoplay.core.data.remote.MovieDataSource
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.features.home.domain.repository.MoviePopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviePopularRepositoryImpl(
    private val remoteDataSource: MovieDataSource
) : MoviePopularRepository {
    override fun getPopularMovies(): Flow<Movie> {
        return flow { remoteDataSource.getPopularMovies() }
    }
}