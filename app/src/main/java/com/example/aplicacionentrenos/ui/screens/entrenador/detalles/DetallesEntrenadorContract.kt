package com.example.aplicacionentrenos.ui.screens.entrenador.detalles

import com.example.aplicacionentrenos.domain.model.bo.Entrenador

interface DetallesEntrenadorContract {

    sealed class Eventos{
        data class AltaEntrenador(val id:Int) : Eventos()
        data class PedirEntrenador(val id: Int) : Eventos()
    }

    data class EntrenadorState(
        val entrenador: Entrenador? = null
    )

}