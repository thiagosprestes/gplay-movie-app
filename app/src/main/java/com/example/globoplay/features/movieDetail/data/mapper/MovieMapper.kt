package com.example.globoplay.features.movieDetail.data.mapper

import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.domain.model.MovieDetails

fun MovieDetails.toMovie(): Movie = Movie(
    id = this.id,
    title = this.title,
    imageUrl = this.imageUrl
)