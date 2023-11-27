package com.example.myica.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myica.data.api.model.Force
import com.example.myica.data.repository.PoliceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel  @Inject constructor(
    private val policeRepo: PoliceRepo
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<(Force)>())
    val state: StateFlow<List<Force>>
        get() = _state


    init {
        viewModelScope.launch {
            val forces = policeRepo.getForces()
            _state.value = forces
        }
    }


}