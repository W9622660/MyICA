package com.example.myica.ui.detail


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController


@Composable
fun Details(navController: NavController, detailViewModel: DetailViewModel) {

    val state by detailViewModel.state.collectAsState()

    // Back arrow for navigation

}


