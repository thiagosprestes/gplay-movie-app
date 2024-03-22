package com.example.globoplay.features.movieDetail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.globoplay.core.domain.model.MovieCredits
import com.example.globoplay.core.domain.model.MovieDetails
import com.example.globoplay.core.presentation.components.Loading
import com.example.globoplay.features.home.presentation.States
import com.example.globoplay.features.movieDetail.presentation.components.DetailRow
import com.example.globoplay.features.movieDetail.presentation.components.Main
import com.example.globoplay.ui.theme.Black
import com.example.globoplay.ui.theme.DarkenGrey
import com.example.globoplay.ui.theme.White
import com.example.globoplay.ui.theme.circularFontFamily

@Composable
fun MovieDetailScreen(
    options: List<MovieDetailOptions>,
    onSelectOption: (option: OptionName) -> Unit,
    isSelectedOption: (option: OptionName) -> Boolean,
    paddingValues: PaddingValues,
    movieDetails: MovieDetails?,
    movieCredits: MovieCredits?,
    movieId: Int?,
    getMovieDetails: (MovieDetailEvent.GetMovieDetail) -> Unit,
    uiState: States,
    formatDate: (date: String) -> String
) {
    LaunchedEffect(key1 = true) {
        if (movieId != null) {
            getMovieDetails(MovieDetailEvent.GetMovieDetail(movieId))
        }
    }

    when (uiState) {
        States.LOADING -> Loading()
        States.DEFAULT -> {

            if (movieDetails != null && movieCredits != null)
                Default(
                    paddingValues,
                    options,
                    onSelectOption,
                    isSelectedOption,
                    movieDetails,
                    movieCredits,
                    formatDate
                )
        }

        States.GENERIC_ERROR -> Text("Generic error")
        States.NETWORK_ERROR -> Text("Network error")
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Default(
    paddingValues: PaddingValues,
    options: List<MovieDetailOptions>,
    onSelectOption: (option: OptionName) -> Unit,
    isSelectedOption: (option: OptionName) -> Boolean,
    movieDetail: MovieDetails,
    movieCredits: MovieCredits,
    formatDate: (date: String) -> String
) {
    Column(
        Modifier
            .padding(paddingValues)
            .background(Black)
            .verticalScroll(rememberScrollState())
    ) {
        Main(
            imageUrl = movieDetail.imageUrl,
            title = movieDetail.title,
            genres = movieDetail.genres,
            overview = movieDetail.overview
        )
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        top = 10.dp,
                        end = 20.dp,
                        bottom = 30.dp,
                    ),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(White),
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        text = "Assista",
                        fontFamily = circularFontFamily,
                        color = Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        letterSpacing = 0.sp,
                        modifier = Modifier
                            .padding(5.dp)

                    )
                }
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(Black),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = White,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 5.dp),
                        tint = White
                    )
                    Text(
                        text = "Minha lista",
                        fontFamily = circularFontFamily,
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        letterSpacing = 0.sp
                    )
                }
            }
            Row(Modifier.padding(horizontal = 20.dp)) {
                options.map {
                    Text(
                        text = it.label,
                        fontFamily = circularFontFamily,
                        color = if (isSelectedOption(it.optionName)) White else White.copy(0.5F),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(end = 40.dp)
                            .clickable { onSelectOption(it.optionName) }
                            .drawBehind {
                                val borderSize = 3.dp.toPx()
                                val y = size.height - borderSize / 2

                                if (isSelectedOption(it.optionName)) {
                                    drawLine(
                                        color = White,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth = borderSize
                                    )
                                }
                            }
                            .padding(bottom = 12.dp)
                    )
                }
            }
            if (isSelectedOption(OptionName.WATCH)) {
                Row(
                    Modifier
                        .background(DarkenGrey)
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                ) {
                    FlowRow(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        for (i in 1..6) {
                            AsyncImage(
                                model = "https://www.movieposters.com/cdn/shop/products/108b520c55e3c9760f77a06110d6a73b_240x360_crop_center.progressive.jpg?v=1573652543",
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                placeholder = BrushPainter(
                                    Brush.linearGradient(
                                        listOf(
                                            Color(color = 0xFFCCCCCC),
                                            Color(color = 0xFF000000),
                                        )
                                    )
                                ),
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(180.dp)
                            )
                        }
                    }
                }
            } else {
                Column(
                    Modifier
                        .background(DarkenGrey)
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                ) {
                    Text(
                        text = "Ficha técnica",
                        fontFamily = circularFontFamily,
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 15.dp)
                    )
                    DetailRow("Título original", movieDetail.originalTitle)
                    DetailRow("Gênero", movieDetail.genres.joinToString(", "))
                    DetailRow("País", movieDetail.productionCountries.joinToString(", "))
                    DetailRow("Direção", movieCredits.director ?: "")
                    DetailRow("Elenco", movieCredits.cast?.joinToString(", ") ?: "")
                    DetailRow("Lançamento", formatDate(movieDetail.releaseDate))
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailPreview() {
    MovieDetailScreen(
        options = listOf(
            MovieDetailOptions(
                label = "ASSISTA TAMBÉM",
                optionName = OptionName.WATCH
            ), MovieDetailOptions(
                label = "DETALHES",
                optionName = OptionName.DETAILS
            )
        ),
        isSelectedOption = { true },
        onSelectOption = { true },
        paddingValues = PaddingValues(),
        movieDetails = MovieDetails(
            id = 0,
            imageUrl = "",
            productionCountries = listOf(""),
            genres = listOf(""),
            releaseDate = "",
            originalTitle = "",
            title = "",
            overview = ""
        ),
        movieCredits = MovieCredits(
            director = "",
            cast = listOf("")
        ),
        movieId = 0,
        getMovieDetails = {},
        uiState = States.DEFAULT,
        formatDate = { "" }
    )
}