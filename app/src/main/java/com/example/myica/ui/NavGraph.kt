package com.example.myica.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myica.ui.home.HomeScreen
import com.example.myica.ui.home.HomeViewModel
import com.example.myica.ui.login.loginScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val LOGIN_ROUTE = "login"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.LOGIN_ROUTE) {
    val navController = rememberNavController()
    val logining = remember { mutableStateOf(true) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = MainDestinations.LOGIN_ROUTE) {
            loginScreen(navController, auth = Firebase.auth, logining = logining)
        }
        composable(route = MainDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController,homeViewModel)
        }
    }
}

