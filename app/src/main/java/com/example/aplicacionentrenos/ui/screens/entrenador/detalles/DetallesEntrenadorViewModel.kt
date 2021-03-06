package com.example.aplicacionentrenos.ui.screens.entrenador.detalles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionentrenos.data.repository.EntrenadorRepository
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.utils.Constantes
import com.example.aplicacionentrenos.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallesEntrenadorViewModel @Inject constructor(
    private val entrenadorRepository: EntrenadorRepository
) : ViewModel() {


    var loading by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState:
            MutableStateFlow<DetallesEntrenadorContract.EntrenadorState> by lazy {
        MutableStateFlow(DetallesEntrenadorContract.EntrenadorState())
    }
    val uiState: StateFlow<DetallesEntrenadorContract.EntrenadorState> = _uiState


    fun handleEvent(event: DetallesEntrenadorContract.Eventos) {

        when (event) {

            is DetallesEntrenadorContract.Eventos.Volver -> {
                sendUiEvent(UiEvents.PopBackStack)
            }
            is DetallesEntrenadorContract.Eventos.PedirEntrenador -> {

                viewModelScope.launch {
                    entrenadorRepository.getById(event.id)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: Constantes.ERROR)
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _uiState.update {
                                        it.copy(
                                            entrenador = result.data
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

            is DetallesEntrenadorContract.Eventos.AltaEntrenador -> {
                viewModelScope.launch {
                    entrenadorRepository.altaEnrenador(event.clienteDTO)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: Constantes.ERROR)
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    sendUiEvent(UiEvents.ShowSnackBar(Constantes.ENTRENADOR_ALTA_MSG))

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