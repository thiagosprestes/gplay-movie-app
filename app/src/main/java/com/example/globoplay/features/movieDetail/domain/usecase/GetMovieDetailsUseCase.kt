package com.example.globoplay.features.movieDetail.domain.usecase

import com.example.globoplay.core.domain.model.MovieCredits
import com.example.globoplay.core.domain.model.MovieDetails
import com.example.globoplay.core.util.ErrorType
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.movieDetail.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Pair<MovieDetails, MovieCredits>>>
    data class Params(val movieId: Int)
}

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MovieDetailsRepository
) : GetMovieDetailsUseCase {
    override suspend fun invoke(params: GetMovieDetailsUseCase.Params): Flow<ResultData<Pair<MovieDetails, MovieCredits>>> =
        flow {
            try {
                val detailsResponse = repository.getMovieDetails(params.movieId)
                val creditsResponse = repository.getMovieCredits(params.movieId)

                emit(ResultData.Success(detailsResponse to creditsResponse))
            } catch (exception: IOException) {
                exception.printStackTrace()
                emit(ResultData.Failure(exception, ErrorType.GENERIC))
            } catch (exception: HttpException) {
                exception.printStackTrace()
                emit(ResultData.Failure(exception, ErrorType.NETWORK))
            }
        }
}