package com.example.globoplay.features.home.domain.usecase

import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.util.ErrorType
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.home.data.mapper.toMovie
import com.example.globoplay.features.home.domain.repository.MoviePopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetPopularMoviesUseCase {
    suspend operator fun invoke(): Flow<ResultData<List<Movie>>>
}

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviePopularRepository
) : GetPopularMoviesUseCase {
    override suspend fun invoke(): Flow<ResultData<List<Movie>>> = flow {
        try {
            val response = repository.getPopularMovies()
            emit(ResultData.Success(response.results.toMovie()))
        } catch (exception: IOException) {
            exception.printStackTrace()
            emit(ResultData.Failure(exception, ErrorType.GENERIC))
        } catch (exception: HttpException) {
            exception.printStackTrace()
            emit(ResultData.Failure(exception, ErrorType.NETWORK))
        }
    }
}