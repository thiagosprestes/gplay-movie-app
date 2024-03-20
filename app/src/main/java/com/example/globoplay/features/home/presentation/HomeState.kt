package com.example.globoplay.features.home.presentation

import androidx.paging.PagingData
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.util.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

enum class States {
    DEFAULT, LOADING, GENERIC_ERROR, NETWORK_ERROR
}

data class HomeState(
    val state: States = States.DEFAULT,
    val movies: Flow<ResultData<List<Movie>>> = emptyFlow()
)