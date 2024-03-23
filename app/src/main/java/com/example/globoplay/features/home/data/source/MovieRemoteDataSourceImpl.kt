package com.example.globoplay.features.home.data.source

import com.example.globoplay.core.data.remote.datasource.MovieService
import com.example.globoplay.core.data.remote.model.response.MovieResponse
import com.example.globoplay.features.home.domain.source.MovieRemoteDataSource
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieRemoteDataSource {
    override suspend fun getPopularMovies(): MovieResponse {
        return service.getPopularMovies()
    }

    override suspend fun getUpcomingMovies(): MovieResponse {
        return service.getUpcoming()
    }
}