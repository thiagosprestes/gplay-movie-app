package com.example.globoplay.features.movieDetail.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.globoplay.ui.theme.LightGrey
import com.example.globoplay.ui.theme.circularFontFamily

@Composable
fun DetailRow(title: String, value: String) {
    Row {
        Text(
            text = "$title: $value",
            fontFamily = circularFontFamily,
            color = LightGrey,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(end = 8.dp),
            lineHeight = 20.sp
        )
    }
}