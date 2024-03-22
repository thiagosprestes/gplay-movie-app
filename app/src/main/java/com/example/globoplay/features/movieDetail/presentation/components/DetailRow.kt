package com.example.globoplay.features.movieDetail.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.globoplay.ui.theme.MediumGrey
import com.example.globoplay.ui.theme.circularFontFamily

@Composable
fun DetailRow(title: String, value: String) {
    Row {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = MediumGrey,
                        fontFamily = circularFontFamily
                    )
                ) {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append("$title: ")
                    }
                    withStyle(
                        style = ParagraphStyle(
                            lineHeight = 18.sp
                        )
                    ) {
                        append(value)
                    }
                }
            }
        )
    }
}