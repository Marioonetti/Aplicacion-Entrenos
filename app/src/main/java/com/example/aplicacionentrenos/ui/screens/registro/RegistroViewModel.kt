package com.example.aplicacionentrenos.ui.screens.registro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionentrenos.data.repository.AuthRepository
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.utils.Constantes
import com.example.aplicacionentrenos.utils.NavigationConstants
import com.example.aplicacionentrenos.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


    var hidden by mutableStateOf(true)

    var taNombre by mutableStateOf("")
        private set

    var taApellidos by mutableStateOf("")
        private set

    var taUsername by mutableStateOf("")
        private set

    var taPassw by mutableStateOf("")
        private set

    var loading by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun handleEvent(event: RegistroContract.Eventos) {
        when (event) {
            is RegistroContract.Eventos.NavigateToLogin -> {
                sendUiEvent(UiEvents.Navigate(NavigationConstants.MAIN_ROUTE))
            }
            is RegistroContract.Eventos.OnNombreChange -> {
                taNombre = event.nombre
            }
            is RegistroContract.Eventos.OnApellidosChange -> {
                taApellidos = event.apellidos
            }
            is RegistroContract.Eventos.OnUsernameChange -> {
                taUsername = event.username
            }
            is RegistroContract.Eventos.OnPasswordChange -> {
                taPassw = event.password
            }
            is RegistroContract.Eventos.Registrarse -> {
                viewModelScope.launch {
                    authRepository.registro(event.clienteDTO)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: Constantes.ERROR)
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    loading = false
                                    sendUiEvent(UiEvents.ShowSnackBar(Constantes.USER_REGISTRADO))
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