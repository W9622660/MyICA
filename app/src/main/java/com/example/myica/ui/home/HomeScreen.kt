package com.example.myica.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myica.data.api.model.Force
import com.example.myica.ui.MainDestinations
import com.example.myica.ui.login.loginScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {

    val state by homeViewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(state){force: Force ->
                PoliceForceCard(force = force)
            }
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            coroutineScope.launch {
                FirebaseAuth.getInstance().signOut()
                navController?.navigate(MainDestinations.LOGIN_ROUTE)
            }
        },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)) { // Add some padding at the bottom
            Text("Logout")
        }
    }



  /*  Button(
        onClick = {
            FirebaseAuth.getInstance().signOut()
           // loginScreen(auth = Firebase.auth, logining = logining)
        },
        modifier = Modifier.fillMaxWidth()

    ){
        Text(text = "Logout from app")
    }*/
    Spacer(Modifier.height(20.dp))
/*    LazyColumn {
        if (state.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }

        items(state){force: Force ->
            PoliceForceCard(force = force)
        }

    }*/
}


@Composable
fun PoliceForceCard(force: Force) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    )

    {
        Column(
            modifier = Modifier.padding(15.dp)
        )

        {
            Text(text = "Id: ${force.id}",
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Text(text = "Force Name:${force.name}")
        }
    }
}




