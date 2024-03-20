package com.example.globoplay.core.data.remote

import com.example.globoplay.core.data.remote.model.response.MovieResponse
import retrofit2.http.GET

interface MovieDataSource {
    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse
}