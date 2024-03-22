package com.example.globoplay.features.movieDetail.data.source

import com.example.globoplay.core.data.remote.datasource.MovieService
import com.example.globoplay.core.domain.model.MovieCredits
import com.example.globoplay.core.domain.model.MovieDetails
import com.example.globoplay.core.util.toPosterUrl
import com.example.globoplay.features.movieDetail.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovieDetails(movieId)
        val genres = response.genres?.map {
            it?.name
        }
        val productionCountries = response.productionCountries?.map {
            it?.name
        }

        return MovieDetails(
            id = response.id!!,
            title = response.title!!,
            overview = response.overview!!,
            imageUrl = response.posterPath.toPosterUrl(),
            originalTitle = response.originalTitle!!,
            releaseDate = response.releaseDate!!,
            genres = genres as List<String>,
            productionCountries = productionCountries as List<String>
        )
    }

    override suspend fun getMovieCredits(movieId: Int): MovieCredits {
        val response = service.getMovieCredits(movieId)
        val cast = response.cast?.map {
            it?.name
        }
        val director = response.crew?.find {
            it?.job == "Director"
        }

        return MovieCredits(
            cast = cast,
            director = director?.name
        )
    }
}