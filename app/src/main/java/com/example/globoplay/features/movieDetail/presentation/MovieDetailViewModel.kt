package com.example.globoplay.features.movieDetail.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.domain.model.MovieCredits
import com.example.globoplay.core.domain.model.MovieDetails
import com.example.globoplay.core.util.ErrorType
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.home.presentation.States
import com.example.globoplay.features.movieDetail.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    var uiState = mutableStateOf(States.LOADING)
    var movieDetails = mutableStateOf<MovieDetails?>(null)
    var movieCredits = mutableStateOf<MovieCredits?>(null)
    private var selectedOption = mutableStateOf(OptionName.WATCH)
    val options = mutableStateOf(
        listOf(
            MovieDetailOptions(
                label = "ASSISTA TAMBÉM",
                optionName = OptionName.WATCH
            ), MovieDetailOptions(
                label = "DETALHES",
                optionName = OptionName.DETAILS
            )
        )
    )

    fun isSelectedOption(option: OptionName) = option == selectedOption.value

    fun onSelectOption(option: OptionName) {
        selectedOption.value = option
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: String): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate: Date? = simpleDateFormat.parse(date)
        return SimpleDateFormat("dd/MM/yyyy").format(parsedDate)
    }

    fun getMovieDetails(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    private fun event(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    val response = getMovieDetailsUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    )
                    response.collect {
                        when (it) {
                            is ResultData.Success -> {
                                movieDetails.value = it.data?.first
                                movieCredits.value = it.data?.second
                                uiState.value = States.DEFAULT
                            }

                            is ResultData.Failure -> {
                                if (it.type == ErrorType.GENERIC) {
                                    uiState.value = States.GENERIC_ERROR
                                    return@collect
                                }
                                uiState.value = States.NETWORK_ERROR
                            }
                        }
                    }
                }
            }
        }
    }
}