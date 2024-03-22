package com.example.globoplay.features.movieDetail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.globoplay.ui.theme.Black
import com.example.globoplay.ui.theme.LightGrey
import com.example.globoplay.ui.theme.Transparent
import com.example.globoplay.ui.theme.White
import com.example.globoplay.ui.theme.circularFontFamily

@Composable
fun Main(imageUrl: String, title: String, genres: List<String>, overview:String) {
    Box {
        AsyncImage(
            model = imageUrl,
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
                .fillMaxWidth()
                .matchParentSize()
                .blur(10.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Transparent, Black),
                        0f,
                        900f,
                    )
                )
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = White,
                    contentDescription = null
                )
            }
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                placeholder = BrushPainter(
                    Brush.linearGradient(
                        listOf(
                            Color(color = 0xFFCCCCCC),
                            Color(color = 0xFF000000),
                        )
                    )
                ),
                modifier = Modifier
                    .width(140.dp)
                    .height(210.dp)
            )
            Text(
                text = title,
                fontFamily = circularFontFamily,
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Text(
                text = genres[0],
                fontFamily = circularFontFamily,
                color = LightGrey,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = overview,
                fontFamily = circularFontFamily,
                color = LightGrey,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}