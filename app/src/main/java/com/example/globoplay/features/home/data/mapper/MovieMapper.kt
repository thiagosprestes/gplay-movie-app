package com.example.globoplay.features.home.data.mapper

import com.example.globoplay.core.data.remote.model.MovieResult
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.util.toPosterUrl

fun List<MovieResult>.toMovie() = this.map {
    Movie(
        id = it.id,
        title = it.title,
        imageUrl = it.posterPath.toPosterUrl() ?: ""
    )
}