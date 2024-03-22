package com.example.globoplay.features.movieDetail.presentation

enum class OptionName {
    WATCH, DETAILS
}

data class MovieDetailOptions(
    val optionName: OptionName,
    val label: String
)