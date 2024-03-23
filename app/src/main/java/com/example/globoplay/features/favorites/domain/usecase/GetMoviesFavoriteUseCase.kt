package com.example.globoplay.features.favorites.domain.usecase

import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.features.favorites.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMovieFavoriteUseCase {
    suspend operator fun invoke(): Flow<List<Movie>>
}

class GetMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : GetMovieFavoriteUseCase {
    override suspend fun invoke(): Flow<List<Movie>> {
        return repository.getMovies()
    }
}