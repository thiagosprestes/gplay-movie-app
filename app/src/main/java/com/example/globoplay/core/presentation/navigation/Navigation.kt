package com.example.globoplay.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.globoplay.core.presentation.components.BottomNavigationBar
import com.example.globoplay.features.favorites.presentation.FavoritesScreen
import com.example.globoplay.features.favorites.presentation.FavoritesViewModel
import com.example.globoplay.features.home.presentation.HomeScreen
import com.example.globoplay.features.home.presentation.HomeViewModel
import com.example.globoplay.features.movieDetail.presentation.MovieDetailScreen
import com.example.globoplay.features.movieDetail.presentation.MovieDetailViewModel

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
    NavHost(navController = navController, startDestination = Route.HomeScreen.routeName) {
        composable(Route.HomeScreen.routeName) {
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState = viewModel.uiState.value
            val popularMovies = viewModel.popularMovies.value
            val upcomingMovies = viewModel.upcomingMovies.value

            HomeScreen(
                onGoToMovieDetail = {
                    navController.navigate(Route.MovieDetailsScreen.passMovieId(it))
                },
                paddingValues,
                uiState,
                popularMovies,
                upcomingMovies

            )
        }
        composable(Route.MovieDetailsScreen.routeName, arguments = listOf(
            navArgument("movieId") {
                type = NavType.IntType
                defaultValue = 0
            }
        )) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val options = viewModel.options.value
            val uiState = viewModel.uiState.value


            val movieDetails = viewModel.movieDetails.value
            val movieCredits = viewModel.movieCredits.value
            val similarMovies = viewModel.similarMovies.value
            val isFavorited = viewModel.isFavorited.value

            val movieId = it.arguments?.getInt("movieId")
            val getMovieDetails = viewModel::getMovieDetails
            val checkedFavorite = viewModel::checkedFavorite
            val onAddFavorite = viewModel::onAddFavorite

            MovieDetailScreen(
                options = options,
                isSelectedOption = { option -> viewModel.isSelectedOption(option) },
                onSelectOption = { option -> viewModel.onSelectOption(option) },
                paddingValues = paddingValues,
                movieDetails = movieDetails,
                movieCredits = movieCredits,
                movieId = movieId,
                getMovieDetails = getMovieDetails,
                similarMovies = similarMovies?.take(6),
                uiState = uiState,
                formatDate = { date ->
                    viewModel.formatDate(date)
                },
                onGoToMovieDetail = { movieId ->
                    navController.navigate(Route.MovieDetailsScreen.passMovieId(movieId))
                },
                onBack = {
                    navController.popBackStack()
                },
                checkedFavorite = checkedFavorite,
                onAddFavorite = onAddFavorite,
                isFavorited = isFavorited
            )
        }
        composable(Route.FavoritesScreen.routeName) {
            val viewModel: FavoritesViewModel = hiltViewModel()
            val uiState = viewModel.uiState.value
            val movies = viewModel.movies.value

            FavoritesScreen(paddingValues, uiState, movies, onGoToMovie = {
                navController.navigate(Route.MovieDetailsScreen.passMovieId(it))
            })
        }
    }
}
