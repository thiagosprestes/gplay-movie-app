package com.example.globoplay.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.globoplay.R
import com.example.globoplay.ui.theme.Black
import com.example.globoplay.ui.theme.White
import com.example.globoplay.ui.theme.circularFontFamily

@Composable
fun HomeScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Black)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.globoplay), contentDescription = null)
        }
        Row(Modifier.padding(20.dp)) {
            Column {
                Text(
                    text = "Novelas",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = circularFontFamily,
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}