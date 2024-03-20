package com.example.globoplay.core.data.remote.repository

import com.example.globoplay.core.data.remote.model.response.MovieResponse
import com.example.globoplay.features.home.domain.repository.MoviePopularRepository
import com.example.globoplay.features.home.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject

class MoviePopularRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {
    override suspend fun getPopularMovies(): MovieResponse {
        return remoteDataSource.getPopularMovies()
    }
}
