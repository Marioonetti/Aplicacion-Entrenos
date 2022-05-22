package com.example.aplicacionentrenos.ui.screens.perfil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionentrenos.data.repository.EntrenadorRepository
import com.example.aplicacionentrenos.data.repository.PerfilRepository
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.ui.screens.entrenamientos.general.EntrenoContract
import com.example.aplicacionentrenos.utils.NavigationConstants
import com.example.aplicacionentrenos.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val perfilRepository: PerfilRepository,
    private val entrenadorRepository: EntrenadorRepository
) : ViewModel() {

    var loading by mutableStateOf(false)
        private set


    private val _perfilState: MutableStateFlow<PerfilContract.PerfilState> by lazy {
        MutableStateFlow(PerfilContract.PerfilState())
    }
    val perfilState: StateFlow<PerfilContract.PerfilState> = _perfilState

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun handleEvent(event : PerfilContract.Eventos){

        when(event){
            is PerfilContract.Eventos.BajaEntrenador ->{
                viewModelScope.launch {
                    entrenadorRepository.bajaEnrenador(event.cliente)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    sendUiEvent(UiEvents.ShowSnackBar("Entrenador dado de baja correctamente"))
                                    sendUiEvent(UiEvents.Navigate(NavigationConstants.PERFIL_ROUTE))


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
            is PerfilContract.Eventos.GetClienteByID -> {

                viewModelScope.launch {
                    perfilRepository.getById(event.id)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _perfilState.update {
                                        it.copy(
                                            cliente = result.data
                                        )
                                    }
                                    if (result.data?.idEntrenador !=0){
                                        handleEvent(PerfilContract.Eventos.GetEntrenadorById(result.data?.idEntrenador!!))
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
            is PerfilContract.Eventos.GetEntrenadorById -> {

                viewModelScope.launch {
                    entrenadorRepository.getById(event.idEntrenador)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    _perfilState.update {
                                        it.copy(
                                            entrenador = result.data
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