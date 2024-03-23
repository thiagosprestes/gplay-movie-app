package com.example.globoplay.features.favorites.data.mappers

import com.example.globoplay.core.data.local.entity.MovieEntity
import com.example.globoplay.core.domain.model.Movie

fun List<MovieEntity>.toMovies() = map {
    Movie(
        id = it.movieId,
        title = it.title,
        imageUrl = it.imageUrl
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        movieId = id,
        title = title,
        imageUrl = imageUrl
    )
}