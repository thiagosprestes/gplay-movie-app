package com.example.globoplay.features.movieDetail.data.repository

import com.example.globoplay.core.domain.model.MovieCredits
import com.example.globoplay.core.domain.model.MovieDetails
import com.example.globoplay.features.movieDetail.domain.repository.MovieDetailsRepository
import com.example.globoplay.features.movieDetail.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteDataSource.getMovieDetails(movieId)
    }

    override suspend fun getMovieCredits(movieId: Int): MovieCredits {
        return remoteDataSource.getMovieCredits(movieId)
    }
}