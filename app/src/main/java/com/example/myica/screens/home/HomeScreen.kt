package com.example.myica.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myica.components.NewsAppBar
import com.example.myica.components.newsrow.ForceRow
import com.example.myica.models.Force


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 0.dp) {
                NewsAppBar(
                    title = "Home",
                    isMainScreen = true,
                    navController = navController,
                )
            }
        }

    ) {
        val response = homeViewModel.result.collectAsState()
        val context = LocalContext.current
        homeViewModel.getForces();
        if (response.value.loading == true && response.value.data.isNullOrEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

        } else {

            Box(Modifier.padding(it)) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()

                ) {

                }
            }
        }


    }

}