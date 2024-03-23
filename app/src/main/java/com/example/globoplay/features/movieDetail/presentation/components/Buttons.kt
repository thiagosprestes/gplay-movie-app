package com.example.globoplay.features.movieDetail.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.globoplay.ui.theme.Black
import com.example.globoplay.ui.theme.White
import com.example.globoplay.ui.theme.circularFontFamily

@Composable
fun Buttons(onAddFavorite: () -> Unit, isFavorited: Boolean) {
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
            onClick = { onAddFavorite() },
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
                imageVector = if (isFavorited) Icons.Default.Close else Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.padding(end = 5.dp),
                tint = White
            )
            Text(
                text = if (isFavorited) "Remover" else "Minha lista",
                fontFamily = circularFontFamily,
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                letterSpacing = 0.sp
            )
        }
    }
}