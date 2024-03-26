package com.example.globoplay.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.globoplay.core.presentation.navigation.BottomNavItem
import com.example.globoplay.core.presentation.navigation.Route
import com.example.globoplay.ui.theme.Black
import com.example.globoplay.ui.theme.White
import com.example.globoplay.ui.theme.circularFontFamily

@Composable
fun BottomNavigationBar(
    onNavigate: (route: BottomNavItem) -> Unit,
    destination: String? = null
) {
    val navItems = listOf(
        BottomNavItem("Início", Route.HomeScreen.routeName, Icons.Default.Home),
        BottomNavItem("Minha lista", Route.FavoritesScreen.routeName, Icons.Default.Star)
    )

    NavigationBar(containerColor = Black) {
        navItems.forEach {
            val isSelected = it.route == destination
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(it) },
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

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackEntry by navController.currentBackStackEntryAsState()
    return navBackEntry?.destination?.route
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar({}, Route.HomeScreen.routeName)
}