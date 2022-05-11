package com.example.aplicacionentrenos.ui.screens.entrenador.general

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionentrenos.data.repository.EntrenadorRepository
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.utils.NavigationConstants
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class EntrenadoresViewModel @Inject constructor(
    private val entrenadorRepository: EntrenadorRepository
) : ViewModel() {

    var loading by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

   init {
       handleEvent(EntrenadoresContract.Eventos.GetAll)
   }

    private val _uiState:
            MutableStateFlow<EntrenadoresContract.EntrenadoresState> by lazy {
        MutableStateFlow(EntrenadoresContract.EntrenadoresState())
    }
    val uiState: StateFlow<EntrenadoresContract.EntrenadoresState> = _uiState


    fun handleEvent(event : EntrenadoresContract.Eventos){
        when(event){
            is EntrenadoresContract.Eventos.NavToInformacion -> {
                sendUiEvent(
                    UiEvents.Navigate(NavigationConstants.NAVIGATE_TO_DETALLES_ENTRENADOR + event.id)
                )
            }

            is EntrenadoresContract.Eventos.GetAll -> {
                viewModelScope.launch {
                    entrenadorRepository.getAll()
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _uiState.update {
                                        it.copy(
                                            entrenadores = result.data ?: emptyList()
                                        )
                                    }
                                }
                                is NetworkResult.Error -> {
                                    sendUiEvent(
                                        UiEvents.ShowSnackBar(
                                            result.message ?: "Fallo"
                                        )
                                    )
                                    loading = false
                                }
                                is NetworkResult.Loading -> {
                                    loading = true
                                }
                            }
                        }
                }



            }
        }
    }



    private fun sendUiEvent(event: UiEvents) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}