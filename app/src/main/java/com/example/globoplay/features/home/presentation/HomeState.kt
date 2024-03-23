package com.example.globoplay.features.home.presentation

import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.domain.model.States
import com.example.globoplay.core.util.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val state: States = States.DEFAULT,
    val movies: Flow<ResultData<List<Movie>>> = emptyFlow()
)