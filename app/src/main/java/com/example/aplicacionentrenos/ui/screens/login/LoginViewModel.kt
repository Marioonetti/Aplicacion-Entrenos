package com.example.aplicacionentrenos.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionentrenos.data.repository.AuthRepository
import com.example.aplicacionentrenos.data.sources.remote.utils.NetworkResult
import com.example.aplicacionentrenos.utils.NavigationConstants
import com.example.aplicacionentrenos.utils.UiEvents
import com.example.aplicacionentrenos.utils.UserCache
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var hidden by mutableStateOf(true)

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var loading by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun handleEvent(event: LoginContract.Eventos) {

        when (event) {
            is LoginContract.Eventos.onUsernameChange -> {
                username = event.username
            }

            is LoginContract.Eventos.onPasswordChange -> {
                password = event.passw
            }

            is LoginContract.Eventos.navToRegistro -> {
                sendUiEvent(UiEvents.Navigate(NavigationConstants.REGISTRO_ROUTE))
            }

            is LoginContract.Eventos.doLogin -> {
                viewModelScope.launch {
                    authRepository.login(event.userDTO)
                        .catch(action = { error ->
                            sendUiEvent(
                                UiEvents.ShowSnackBar(error.message ?: "error")
                            )
                        })
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Success -> {
                                    UserCache.username = username
                                    UserCache.password = password
                                    loading = false
                                    sendUiEvent(UiEvents.Navigate(NavigationConstants.PRINCIPAL_SCREEN_ROUTE))
                                }
                                is NetworkResult.Error -> {
                                    sendUiEvent(
                                        UiEvents.ShowSnackBar(
                                            result.message ?: "Fallo"
                                        )
                                    )
                                    sendUiEvent(UiEvents.Navigate(NavigationConstants.PRINCIPAL_SCREEN_ROUTE))
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