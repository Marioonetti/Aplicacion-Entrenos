package com.example.aplicacionentrenos.ui.screens.perfil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aplicacionentrenos.data.repository.PerfilRepository
import com.example.aplicacionentrenos.ui.screens.entrenamientos.general.EntrenoContract
import com.example.aplicacionentrenos.utils.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val perfilRepository: PerfilRepository
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

            }
            is PerfilContract.Eventos.GetClienteByID -> {

            }
            is PerfilContract.Eventos.GetEntrenadorById -> {

            }
        }

    }
}