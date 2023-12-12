package com.example.myica.ui.detail


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Details() {

    val detailViewModel = viewModel(modelClass = DetailViewModel::class.java)
    val state by detailViewModel.state.collectAsState()
}


