package com.example.myica.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myica.data.api.model.Crime
import com.example.myica.data.repository.PoliceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    private val policeRepo: PoliceRepo
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<(Crime)>())
    val state: StateFlow<List<Crime>>
        get() = _state


    init {
        viewModelScope.launch {
            val crimes = policeRepo.getAllCrimes()
            _state.value = crimes
        }
    }


}