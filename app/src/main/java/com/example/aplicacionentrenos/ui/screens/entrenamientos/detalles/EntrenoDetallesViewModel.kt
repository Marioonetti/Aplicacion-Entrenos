package com.example.aplicacionentrenos.ui.screens.entrenamientos.detalles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionentrenos.data.repository.EntrenosRepository
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.utils.Constantes
import com.example.aplicacionentrenos.utils.NavigationConstants
import com.example.aplicacionentrenos.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EntrenoDetallesViewModel @Inject constructor(
    private val entrenosRepository: EntrenosRepository
) : ViewModel() {
    var loading by mutableStateOf(false)
        private set


    private val _entreno: MutableStateFlow<EntrenoDetallesContract.EntrenoState> by lazy {
        MutableStateFlow(EntrenoDetallesContract.EntrenoState())
    }
    val entreno: StateFlow<EntrenoDetallesContract.EntrenoState> = _entreno

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun handleEvent(event: EntrenoDetallesContract.Eventos) {
        when (event) {
            is EntrenoDetallesContract.Eventos.IrDetalleEjercicio -> {
                sendUiEvent(UiEvents.Navigate(NavigationConstants.NAVIGATE_TO_DETALLES_EJERCICIO + event.ejercicioId))
            }
            is EntrenoDetallesContract.Eventos.GetEntrenoById -> {
                viewModelScope.launch {
                    entrenosRepository.getById(event.id)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: Constantes.ERROR)
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _entreno.update {
                                        it.copy(
                                            entreno = result.data
                                        )
                                    }
                                }
                                is NetworkResult.Error -> {
                                    sendUiEvent(
                                        UiEvents.ShowSnackBar(
                                            result.message ?: Constantes.FALLO
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