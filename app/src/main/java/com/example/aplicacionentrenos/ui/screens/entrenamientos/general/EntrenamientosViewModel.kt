package com.example.aplicacionentrenos.ui.screens.entrenamientos.general

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionentrenos.data.repository.EntrenosRepository
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.utils.NavigationConstants
import com.example.aplicacionentrenos.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntrenamientosViewModel @Inject constructor(
    private val repository: EntrenosRepository
) : ViewModel(){

    var loading by mutableStateOf(false)
        private set


    private val _entrenos: MutableStateFlow<EntrenoContract.EntrenosState> by lazy {
        MutableStateFlow(EntrenoContract.EntrenosState())
    }
    val entrenos: StateFlow<EntrenoContract.EntrenosState> = _entrenos

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun handleEvent(event : EntrenoContract.Eventos){
        when(event){

            is EntrenoContract.Eventos.IrDetallesEntrenamiento -> {
                sendUiEvent(
                    UiEvents.Navigate(NavigationConstants.NAVIGATE_TO_DETALLES_ENTRENO + event.entrenoId)
                )
            }

            is EntrenoContract.Eventos.GetAllAsc -> {
                viewModelScope.launch {
                    repository.getAllAsc(event.id)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _entrenos.update {

                                        it.copy(
                                            entrenamientos =
                                            result.data?.sortedByDescending {entreno ->  entreno.fecha }?.asReversed()
                                                ?: emptyList()
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

            is EntrenoContract.Eventos.GetAllDesc -> {
                viewModelScope.launch {
                    repository.getAllDesc(event.id)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _entrenos.update {
                                        it.copy(
                                            entrenamientos = result.data?.sortedByDescending {entreno ->  entreno.fecha } ?: emptyList()
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