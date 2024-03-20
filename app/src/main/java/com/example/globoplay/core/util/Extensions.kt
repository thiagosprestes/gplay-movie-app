package com.example.globoplay.core.util

import com.example.globoplay.BuildConfig

fun String?.toPosterUrl() = "${BuildConfig.POSTER_BASE_URL}$this"