package com.example.globoplay.core.domain.model

class MovieFactory {
    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> {
            Movie(
                id = 1,
                title = "Avengers",
                imageUrl = "url"
            )
        }

        Poster.SpiderMan -> {
            Movie(
                id = 2,
                title = "SpiderMan",
                imageUrl = "url"
            )
        }
    }

    sealed class Poster {
        object Avengers : Poster()
        object SpiderMan : Poster()
    }
}