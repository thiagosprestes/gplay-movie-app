package com.example.globoplay.core.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val genres: List<String>,
    val overview: String,
    val imageUrl: String,
    val originalTitle: String,
    val releaseDate: String,
    val productionCountries: List<String>,
)
