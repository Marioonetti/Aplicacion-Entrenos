package com.example.aplicacionentrenos.ui.screens.ejercicios.detalles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionentrenos.data.repository.EjericiciosRepository
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.ui.screens.entrenamientos.detalles.EntrenoDetallesContract
import com.example.aplicacionentrenos.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EjercicioDetallesViewModel @Inject constructor(
    private val ejercicioRepository: EjericiciosRepository
) : ViewModel(){

    var loading by mutableStateOf(false)
        private set


    private val _ejercicioState: MutableStateFlow<EjercicioDetallesContract.EjercicioState> by lazy {
        MutableStateFlow(EjercicioDetallesContract.EjercicioState())
    }
    val ejercicioState: StateFlow<EjercicioDetallesContract.EjercicioState> = _ejercicioState

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun handleEvent(event : EjercicioDetallesContract.Eventos){

        when(event){
            is EjercicioDetallesContract.Eventos.GetEjercicioById -> {

                viewModelScope.launch {
                    ejercicioRepository.getById(event.id)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _ejercicioState.update {
                                        it.copy(
                                            ejercicio = result.data
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