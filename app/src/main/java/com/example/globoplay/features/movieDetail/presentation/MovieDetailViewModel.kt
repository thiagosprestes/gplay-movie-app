package com.example.globoplay.features.movieDetail.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.domain.model.MovieCredits
import com.example.globoplay.core.domain.model.MovieDetails
import com.example.globoplay.core.domain.model.States
import com.example.globoplay.core.util.ErrorType
import com.example.globoplay.core.util.ResultData
import com.example.globoplay.features.favorites.domain.usecase.AddMovieFavoriteUseCase
import com.example.globoplay.features.favorites.domain.usecase.DeleteMovieFavoriteUseCase
import com.example.globoplay.features.favorites.domain.usecase.IsMovieFavoriteUseCase
import com.example.globoplay.features.movieDetail.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase
) : ViewModel() {
    var uiState = mutableStateOf(States.LOADING)
    var movieDetails = mutableStateOf<MovieDetails?>(null)
    var movieCredits = mutableStateOf<MovieCredits?>(null)
    var similarMovies = mutableStateOf<List<Movie>?>(null)

    val isFavorited = mutableStateOf(false)

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

    fun onAddFavorite(movie: MovieDetails) {
        if (!isFavorited.value) {
            event(MovieDetailEvent.AddFavorite(movie))
            return
        }

        event(MovieDetailEvent.RemoveFavorite(movie))
    }

    fun checkedFavorite(movie: MovieDetailEvent.CheckedFavorite) {
        event(movie)
    }

    fun getMovieDetails(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    private fun event(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.AddFavorite -> {
                viewModelScope.launch {
                    val response =
                        addMovieFavoriteUseCase.invoke(params = AddMovieFavoriteUseCase.Params(movie = event.movie))

                    response.collectLatest {
                        when (it) {
                            is ResultData.Success -> {
                                isFavorited.value = true
                            }

                            else -> {}
                        }
                    }
                }
            }

            is MovieDetailEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    val response =
                        isMovieFavoriteUseCase.invoke(params = IsMovieFavoriteUseCase.Params(movieId = event.movieId))

                    response.collectLatest {
                        when (it) {
                            is ResultData.Success -> {
                                if (it.data == true) {
                                    isFavorited.value = true
                                    return@collectLatest
                                }

                                isFavorited.value = false
                            }

                            else -> {}
                        }
                    }
                }
            }

            is MovieDetailEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    val response =
                        deleteMovieFavoriteUseCase.invoke(
                            params = DeleteMovieFavoriteUseCase.Params(
                                movie = event.movie
                            )
                        )

                    response.collectLatest {
                        when (it) {
                            is ResultData.Success -> {
                                isFavorited.value = false
                            }

                            else -> {}
                        }
                    }
                }
            }

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
                                similarMovies.value = it.data?.third
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