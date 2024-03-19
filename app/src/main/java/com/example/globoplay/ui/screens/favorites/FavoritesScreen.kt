package com.example.globoplay.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.globoplay.R
import com.example.globoplay.ui.screens.home.components.ItemsList
import com.example.globoplay.ui.theme.Black

@Composable
fun FavoritesScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        Text("Vrau")
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(rememberNavController())
}