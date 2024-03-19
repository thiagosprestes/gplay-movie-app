package com.example.globoplay.ui.navigation

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.globoplay.ui.screens.favorites.FavoritesScreen
import com.example.globoplay.ui.screens.home.HomeScreen
import com.example.globoplay.ui.theme.Black
import com.example.globoplay.ui.theme.White
import com.example.globoplay.ui.theme.circularFontFamily

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun Navigation() {
    val navItems = listOf(
        BottomNavItem("Início", Route.HomeScreen, Icons.Default.Home),
        BottomNavItem("Minha lista", Route.FavoritesScreen, Icons.Default.Star)
    )

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Black) {
                navItems.forEach {
                    val isSelected = it.route == navBackStackEntry?.destination?.route
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(it.route.lowercase()) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = it.title,
                                fontFamily = circularFontFamily
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = White,
                            unselectedIconColor = White.copy(0.4f),
                            selectedTextColor = White,
                            unselectedTextColor = White.copy(0.4f),
                            indicatorColor = Black,
                        )
                    )
                }
            }

        }
    ) {
        HomeNavHost(navController, it)
    }
}


@Composable
fun HomeNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = Route.HomeScreen) {
        composable(Route.HomeScreen) {
            HomeScreen(navController, paddingValues)
        }
        composable(Route.FavoritesScreen) {
            FavoritesScreen(navController, paddingValues)
        }
    }
}
