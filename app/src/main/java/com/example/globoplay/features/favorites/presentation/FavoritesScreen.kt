package com.example.globoplay.features.favorites.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.core.domain.model.States
import com.example.globoplay.core.presentation.components.Loading
import com.example.globoplay.ui.theme.Black
import com.example.globoplay.ui.theme.White
import com.example.globoplay.ui.theme.circularFontFamily

@Composable
fun FavoritesScreen(paddingValues: PaddingValues, uiState: States, movies: List<Movie>) {
    when (uiState) {
        States.LOADING -> Loading()
        States.DEFAULT -> Default(
            paddingValues,
            movies
        )

        States.GENERIC_ERROR -> Text("Generic error")
        States.NETWORK_ERROR -> Text("Network error")
    }
}

@Composable
fun Default(paddingValues: PaddingValues, movies: List<Movie>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row(
            Modifier
                .background(Black)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 29.dp)
        ) {
            Text(
                "Minha lista",
                fontFamily = circularFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
        }
        Column(
            Modifier.padding(
                20.dp,
            )
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(movies) {
                    AsyncImage(
                        model = it.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        placeholder = BrushPainter(
                            Brush.linearGradient(
                                listOf(
                                    Color(color = 0xFFFFFFFF),
                                    Color(color = 0xFFDDDDDD),
                                )
                            )
                        ),
                        modifier = Modifier
                            .width(120.dp)
                            .height(190.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(PaddingValues(), States.DEFAULT, emptyList())
}