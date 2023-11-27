package com.example.myica.screens.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myica.components.NewsAppBar
import com.example.myica.navigation.AppGraphs
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingScreen(navController: NavHostController, mainNavController: NavHostController) {


    Scaffold(
        topBar = {
            Surface(shadowElevation = 1.dp) {

                NewsAppBar(
                    title = "Setting",
                    isMainScreen = false,
                    navController = navController
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(10.dp)
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        FirebaseAuth
                            .getInstance()
                            .signOut()

                        mainNavController.navigate(AppGraphs.Auth.name) {
                            popUpTo(AppGraphs.Main.name) {
                                inclusive = true
                            }
                        }

                    }
            ) {
                Text(
                    "Sign Out", modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(vertical = 15.dp),
                    fontSize = 18.sp,
                    color = Color.Red
                )
            }

        }
    }
}