package com.example.myica.ui.navigation//package com.example.myica.ui.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHost
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.compose.ComposeNavigator
//
//
//@Composable
//fun AppNavigation() {
//
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
//        Home(navController)
//    }
//    composable(NavRoutes.Details.route + "/{id}") {
//        Details(
//            onBackPressed = {
//                navController.popBackStack()
//            }
//        )
//    }
//
//}