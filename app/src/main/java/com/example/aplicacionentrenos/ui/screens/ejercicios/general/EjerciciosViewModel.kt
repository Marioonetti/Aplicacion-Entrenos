package com.example.aplicacionentrenos.ui.screens.ejercicios.general

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionentrenos.data.repository.EjericiciosRepository
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.ui.screens.entrenador.general.EntrenadoresContract
import com.example.aplicacionentrenos.utils.NavigationConstants
import com.example.aplicacionentrenos.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EjerciciosViewModel @Inject constructor(
    private val ejerciciosRepository: EjericiciosRepository
) : ViewModel(){

    var loading by mutableStateOf(false)
        private set

    var searchText by mutableStateOf("")
        private set

    private val _ejercicios: MutableStateFlow<EjerciciosContract.EjerciciosState> by lazy {
        MutableStateFlow(EjerciciosContract.EjerciciosState())
    }
    val ejercicios: StateFlow<EjerciciosContract.EjerciciosState> = _ejercicios

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        handleEvent(EjerciciosContract.Eventos.GetAll)
    }


    fun handleEvent(event : EjerciciosContract.Eventos){
        when(event){

            is EjerciciosContract.Eventos.IrDetallesEjercicio -> {
                sendUiEvent(
                    UiEvents.Navigate(NavigationConstants.NAVIGATE_TO_DETALLES_EJERCICIO + event.ejercicioId)
                )
            }
            is EjerciciosContract.Eventos.OnSearchChange -> {
               searchText = event.value
            }

            is EjerciciosContract.Eventos.GetAll -> {
                viewModelScope.launch {
                    ejerciciosRepository.getAll()
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _ejercicios.update {
                                        it.copy(
                                            ejercicios = result.data ?: emptyList()
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
            is EjerciciosContract.Eventos.BuscarEjercicios -> {
                viewModelScope.launch {
                    ejerciciosRepository.getByName(event.nombre)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _ejercicios.update {
                                        it.copy(
                                            ejercicios = result.data ?: emptyList()
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