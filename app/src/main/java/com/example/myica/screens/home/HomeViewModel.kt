package com.example.myica.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myica.data.DataOrException
import com.example.myica.models.Article
import com.example.myica.models.Force
import com.example.myica.models.ForceResponse
import com.example.myica.models.NewsResponse
import com.example.myica.repository.NewsRepository
import com.example.myica.repository.PoliceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PoliceRepository) : ViewModel() {

    var result: MutableStateFlow<DataOrException<List<Force>, Boolean, Exception>> =
        MutableStateFlow(
            DataOrException(
                emptyList(),false,null
            )
        )

    init {
        viewModelScope.launch {
            result.emit(DataOrException(result.value.data, true, null))
            val response = repository.getForces()
            result.emit(response)
        }
    }
    val list = mutableStateOf<List<Force>>(emptyList())
    fun getForces() =
        viewModelScope.launch {
            result.emit(DataOrException(result.value.data, true, null))
            val response = repository.getForces()
            list.value = response.data?: emptyList()
            result.emit(response)
        }


}

