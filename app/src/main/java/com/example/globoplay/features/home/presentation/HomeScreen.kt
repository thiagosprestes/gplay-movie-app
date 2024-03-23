package com.example.globoplay.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.globoplay.R
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.presentation.components.ItemsList
import com.example.globoplay.core.presentation.components.Loading
import com.example.globoplay.ui.theme.Black
import com.example.globoplay.ui.theme.White

@Composable
fun HomeScreen(
    onGoToMovieDetail: (movieId: Int) -> Unit,
    paddingValues: PaddingValues,
    uiState: States,
    popularMovies: List<Movie>?,
    upcomingMovies: List<Movie>?
) {
    when (uiState) {
        States.LOADING -> Loading()
        States.DEFAULT -> {
            if (popularMovies != null && upcomingMovies != null)
                Default(onGoToMovieDetail, paddingValues, popularMovies, upcomingMovies)
        }

        States.GENERIC_ERROR -> Text("Generic error")
        States.NETWORK_ERROR -> Text("Network error")
    }
}

@Composable
private fun Default(
    onGoToMovieDetail: (movieId: Int) -> Unit,
    paddingValues: PaddingValues,
    popularMovies: List<Movie>,
    upcomingMovies: List<Movie>
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Black)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.globoplay), contentDescription = null)
        }
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 20.dp)
        ) {
            ItemsList("Filmes populares", popularMovies, onGoToMovieDetail)
            ItemsList("Novos lançamentos", upcomingMovies, onGoToMovieDetail)
//            ItemsList("Cinema")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onGoToMovieDetail = {},
        paddingValues = PaddingValues(),
        uiState = States.LOADING,
        popularMovies = emptyList(),
        upcomingMovies = emptyList()
    )
}