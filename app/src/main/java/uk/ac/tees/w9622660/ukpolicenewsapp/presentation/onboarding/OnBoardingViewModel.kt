package uk.ac.tees.w9622660.ukpolicenewsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uk.ac.tees.w9622660.ukpolicenewsapp.domain.usecases.appEntry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(    //when we use inject constructor, if all the arguements to the constructor can be 'provided' by dagger, then dagger can provide this class also, no need to write a provides function for this class, its automatic
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {

    fun onEvent(event : OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry ->{
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry(){
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}