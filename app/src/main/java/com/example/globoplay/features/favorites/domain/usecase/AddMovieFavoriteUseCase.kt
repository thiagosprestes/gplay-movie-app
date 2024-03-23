package com.example.globoplay.features.favorites.domain.usecase

import android.content.Context
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.domain.model.MovieDetails
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.favorites.domain.repository.MovieFavoriteRepository
import com.example.globoplay.features.movieDetail.data.mapper.toMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface AddMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>>
    data class Params(val movie: MovieDetails)
}

class AddMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : AddMovieFavoriteUseCase {
    override suspend fun invoke(params: AddMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            val movie = params.movie.toMovie()
            val insert = repository.insert(movie)
            emit(ResultData.Success(insert))
        }.flowOn(Dispatchers.IO)
    }
}