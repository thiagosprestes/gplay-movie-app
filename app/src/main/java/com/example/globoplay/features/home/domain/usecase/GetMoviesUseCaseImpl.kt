package com.example.globoplay.features.home.domain.usecase

import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.util.ErrorType
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.home.data.mapper.toMovie
import com.example.globoplay.features.home.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetMoviesUseCase {
    suspend operator fun invoke(): Flow<ResultData<Pair<List<Movie>?, List<Movie>?>>>
}

class GetMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMoviesUseCase {
    override suspend fun invoke(): Flow<ResultData<Pair<List<Movie>?, List<Movie>?>>> = flow {
        try {
            val popularMoviesResponse = repository.getPopularMovies()
            val upcomingMoviesResponse = repository.getUpcomingMovies()

            val popularMovies = popularMoviesResponse.results.toMovie()
            val upcomingMovies = upcomingMoviesResponse.results.toMovie()

            emit(ResultData.Success(popularMovies to upcomingMovies))
        } catch (exception: IOException) {
            exception.printStackTrace()
            emit(ResultData.Failure(exception, ErrorType.GENERIC))
        } catch (exception: HttpException) {
            exception.printStackTrace()
            emit(ResultData.Failure(exception, ErrorType.NETWORK))
        }
    }
}