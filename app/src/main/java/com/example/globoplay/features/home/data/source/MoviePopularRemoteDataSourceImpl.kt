package com.example.globoplay.features.home.data.source

import com.example.globoplay.core.data.remote.MovieService
import com.example.globoplay.core.data.remote.model.response.MovieResponse
import com.example.globoplay.features.home.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {
    override suspend fun getPopularMovies(): MovieResponse {
        return service.getPopularMovies()
    }
}