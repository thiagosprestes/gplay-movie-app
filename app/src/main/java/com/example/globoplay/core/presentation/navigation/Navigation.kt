package com.example.globoplay.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.globoplay.core.presentation.components.BottomNavigationBar
import com.example.globoplay.features.favorites.FavoritesScreen
import com.example.globoplay.features.home.presentation.HomeScreen
import com.example.globoplay.features.home.presentation.HomeViewModel

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onNavigate = {
                    navController.navigate(it.route.lowercase()) {
                        launchSingleTop = true
                    }
                },
                navBackStackEntry?.destination?.route
            )
        },
        content = {
            HomeNavHost(navController, it)
        }
    )
}


@Composable
fun HomeNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = Route.HomeScreen) {
        composable(Route.HomeScreen) {
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState = viewModel.uiState.value
            val movies = viewModel.popularMovies.value

            HomeScreen(
                onGoToMovieDetail = { },
                paddingValues,
                uiState,
                movies
            )
        }
        composable(Route.FavoritesScreen) {
            FavoritesScreen(navController, paddingValues)
        }
    }
}
