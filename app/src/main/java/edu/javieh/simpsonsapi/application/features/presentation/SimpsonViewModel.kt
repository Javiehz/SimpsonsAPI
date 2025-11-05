package edu.javieh.simpsonsapi.application.features.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.javieh.simpsonsapi.application.features.domain.ErrorApp
import edu.javieh.simpsonsapi.application.features.domain.GetByIdSimpsonUseCase
import edu.javieh.simpsonsapi.application.features.domain.Simpson
import kotlinx.coroutines.launch

class SimpsonViewModel(private val getByIdSimpsonUseCase: GetByIdSimpsonUseCase) : ViewModel() {

        private val _uiState = MutableLiveData<UiState>()
        val uiState : LiveData<UiState> = _uiState

    fun loadById(id: Int){
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            getByIdSimpsonUseCase(id).fold({onSuccess(it)}, {onFailure(it as ErrorApp)})
        }
    }


    private fun onSuccess(simpson: Simpson){
        _uiState.value = UiState(simpson = simpson)
    }

    private fun onFailure(error: ErrorApp){
        _uiState.value = UiState(error = error)
    }

    data class UiState(
        val error : ErrorApp?=null,
        val isLoading : Boolean = false,
        val simpson : Simpson?=null
    )
}