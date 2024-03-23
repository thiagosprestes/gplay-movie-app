package com.example.globoplay.features.favorites.domain.usecase

import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.domain.model.MovieDetails
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.favorites.data.mappers.toMovieEntity
import com.example.globoplay.features.favorites.domain.repository.MovieFavoriteRepository
import com.example.globoplay.features.movieDetail.data.mapper.toMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DeleteMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>>
    data class Params(val movie: MovieDetails)
}

class DeleteMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : DeleteMovieFavoriteUseCase {
    override suspend fun invoke(params: DeleteMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            val movie = params.movie.toMovie()
            val insert = repository.delete(movie)
            emit(ResultData.Success(insert))
        }.flowOn(Dispatchers.IO)
    }
}