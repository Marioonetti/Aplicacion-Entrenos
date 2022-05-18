package com.example.aplicacionentrenos.ui.screens.entrenamientos.general

import com.example.aplicacionentrenos.domain.model.bo.Entreno

interface EntrenoContract {


    sealed class Eventos {
        data class IrDetallesEntrenamiento(val entrenoId: Int) : Eventos()
        data class GetAllDesc(val id: Int) : Eventos()
        data class GetAllAsc(val id: Int) : Eventos()
    }

    data class EntrenosState(
        val entrenamientos: List<Entreno> = emptyList()
    )

}