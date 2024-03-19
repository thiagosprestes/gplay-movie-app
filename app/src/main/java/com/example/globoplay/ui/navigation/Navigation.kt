package com.example.globoplay.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.globoplay.ui.screens.HomeScreen

object Route {
    const val HomeScreen = "HomeScreen"
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.HomeScreen) {
        composable(route = Route.HomeScreen) {
            HomeScreen(navController)
        }
    }
}