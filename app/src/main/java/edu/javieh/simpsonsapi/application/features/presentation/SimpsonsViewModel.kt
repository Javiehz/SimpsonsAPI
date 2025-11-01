package edu.javieh.simpsonsapi.application.features.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.javieh.simpsonsapi.application.features.domain.GetAllSimpsonsUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.javieh.simpsonsapi.application.features.domain.ErrorApp
import edu.javieh.simpsonsapi.application.features.domain.Simpson
import kotlinx.coroutines.launch

class SimpsonsViewModel(private val getAllSimpsonsUseCase: GetAllSimpsonsUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadAllSimpsons(){
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            getAllSimpsonsUseCase().fold({onSuccess(it)}, {onFailure(it as ErrorApp)})
        }
    }

    private fun onFailure(error: ErrorApp){
        _uiState.value = UiState(error = error)
    }
    private fun onSuccess(simpsons: List<Simpson>){
        _uiState.value = UiState(simpsons = simpsons)
    }

    data class UiState(
        val error: ErrorApp? = null,
        val isLoading: Boolean = false,
        val simpsons: List<Simpson>? = null
    )
}