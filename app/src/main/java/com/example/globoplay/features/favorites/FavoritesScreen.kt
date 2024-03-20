package com.example.globoplay.features.favorites

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.globoplay.ui.theme.Black
import com.example.globoplay.ui.theme.White
import com.example.globoplay.ui.theme.circularFontFamily

@Composable
fun FavoritesScreen(navController: NavController, paddingValues: PaddingValues) {
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
                items(5) {
                    AsyncImage(
                        model = "https://www.movieposters.com/cdn/shop/products/108b520c55e3c9760f77a06110d6a73b_240x360_crop_center.progressive.jpg?v=1573652543",
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
    FavoritesScreen(rememberNavController(), PaddingValues())
}