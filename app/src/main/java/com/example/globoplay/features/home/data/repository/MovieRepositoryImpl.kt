package com.example.globoplay.features.home.data.repository

import com.example.globoplay.core.data.remote.model.response.MovieResponse
import com.example.globoplay.features.home.domain.repository.MovieRepository
import com.example.globoplay.features.home.domain.source.MovieRemoteDataSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getPopularMovies(): MovieResponse {
        return remoteDataSource.getPopularMovies()
    }

    override suspend fun getUpcomingMovies(): MovieResponse {
        return remoteDataSource.getUpcomingMovies()
    }
}
