package com.example.globoplay.core.data.remote.model.response


import com.example.globoplay.core.data.remote.model.MovieResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<MovieResult?>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
)