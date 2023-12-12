package com.example.myica.ui.search


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myica.data.api.model.Crime
import com.example.myica.ui.MainDestinations
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel) {

    var text by remember { mutableStateOf("") } // Query for SearchBar
    var active by remember { mutableStateOf(false) } // Active state for SearchBar

    val state by searchViewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(state){crime: Crime ->
                PoliceCrimesCard(crime = crime)
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

    Spacer(Modifier.height(20.dp))

}

@Composable
fun PoliceCrimesCard(crime: Crime) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    )
    {
        Column(
            modifier = Modifier.padding(15.dp)
        )
        {
            Text(text = "Id: ${crime.id}",
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(text = "category:${crime.category}")
            Text(text = "month:${crime.month}")
        }
    }
}

