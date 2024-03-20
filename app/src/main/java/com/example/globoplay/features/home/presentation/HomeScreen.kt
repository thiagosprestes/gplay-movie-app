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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.globoplay.R
import com.example.globoplay.core.presentation.components.ItemsList
import com.example.globoplay.ui.theme.Black

@Composable
fun HomeScreen(navController: NavController, paddingValues: PaddingValues) {
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
            Modifier.verticalScroll(rememberScrollState()).padding(bottom = 20.dp)
        ) {
            ItemsList("Novelas")
            ItemsList("Séries")
            ItemsList("Cinema")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController(), PaddingValues())
}