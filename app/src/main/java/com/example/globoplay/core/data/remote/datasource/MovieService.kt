package com.example.globoplay.core.data.remote.datasource

import com.example.globoplay.core.data.remote.model.response.MovieCreditsResponse
import com.example.globoplay.core.data.remote.model.response.MovieDetailsResponse
import com.example.globoplay.core.data.remote.model.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
    ): MovieCreditsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(): MovieResponse
}