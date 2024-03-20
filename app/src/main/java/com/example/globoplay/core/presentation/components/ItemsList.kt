package com.example.globoplay.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.globoplay.BuildConfig
import com.example.globoplay.core.domain.model.Movie
import com.example.globoplay.ui.theme.White
import com.example.globoplay.ui.theme.circularFontFamily
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ItemsList(title: String, movies: List<Movie>) {
    Column(Modifier.padding(top = 30.dp)) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 10.dp),
            fontFamily = circularFontFamily,
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(movies) {
                AsyncImage(
                    model = it.imageUrl,
                    contentDescription = null,
                    placeholder = BrushPainter(
                        Brush.linearGradient(
                            listOf(
                                Color(color = 0xFFFFFFFF),
                                Color(color = 0xFFDDDDDD),
                            )
                        )
                    ),
                    modifier = Modifier
                        .width(132.dp)
                        .height(195.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemsListPreview() {
    ItemsList("Novelas", emptyList())
}