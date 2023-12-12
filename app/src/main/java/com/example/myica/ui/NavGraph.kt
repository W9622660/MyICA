package com.example.myica.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myica.R
import com.example.myica.ui.detail.DetailViewModel
import com.example.myica.ui.detail.Details
import com.example.myica.ui.search.SearchViewModel
import com.example.myica.ui.home.HomeScreen
import com.example.myica.ui.home.HomeViewModel
import com.example.myica.ui.login.loginScreen
import com.example.myica.ui.search.SearchScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val LOGIN_ROUTE = "login"
    const val NEIGHBOURHOODS_ROUTE = "neighbourhoods"
    const val SEARCH_ROUTE = "search"
    const val SPLASH_ROUTE = "splash"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.SPLASH_ROUTE) {
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
     /*   composable(route = MainDestinations.NEIGHBOURHOODS_ROUTE) {
            val detailViewModel: DetailViewModel = hiltViewModel()
            Details(navController,detailViewModel)
        }*/
        composable(route = MainDestinations.SEARCH_ROUTE) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(navController,searchViewModel)
        }
        composable(route = MainDestinations.SPLASH_ROUTE) {
            SplashScreen(navController)
        }
    }
}

@Composable
fun SplashScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.size(width = 100.dp, height = 200.dp),
            painter = painterResource(id = R.drawable.baseline_local_police_24),
            contentDescription = "",
        )

        LaunchedEffect(Unit) {
            delay(1000) // Delay for 1 second
            navController.popBackStack()
            navController.navigate(route = MainDestinations.LOGIN_ROUTE) {
                popUpTo(MainDestinations.SPLASH_ROUTE)
                launchSingleTop = true
            }
        }
    }
}

