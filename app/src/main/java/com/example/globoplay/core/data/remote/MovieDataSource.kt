package com.example.globoplay.core.data.remote

import com.example.globoplay.core.data.remote.model.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse
}