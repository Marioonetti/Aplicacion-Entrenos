package com.example.aplicacionentrenos.ui.screens.entrenador.detalles

import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO

interface DetallesEntrenadorContract {

    sealed class Eventos{
        data class AltaEntrenador(val clienteDTO: ClienteDTO) : Eventos()
        data class PedirEntrenador(val id: Int) : Eventos()
        object Volver : Eventos()
    }

    data class EntrenadorState(
        val entrenador: Entrenador? = null
    )

}