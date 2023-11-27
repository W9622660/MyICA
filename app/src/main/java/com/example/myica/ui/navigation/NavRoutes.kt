package com.example.myica.ui.navigation


sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("Home_screen")
    object Details: NavRoutes("Detail_screen")
}
