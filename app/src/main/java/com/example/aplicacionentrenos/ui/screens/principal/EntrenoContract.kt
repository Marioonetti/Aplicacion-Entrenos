package com.example.aplicacionentrenos.ui.screens.principal

import com.example.aplicacionentrenos.domain.model.dto.EjercicioDTO
import com.example.aplicacionentrenos.domain.model.dto.EntrenoDTO

interface EntrenoContract {


    sealed class Eventos{
        data class IrDetallesEntrenamiento(val entrenoId: Int) : Eventos()
        object GetAll : Eventos()
    }

    data class EntrenosState(
        val entrenamientos: List<EntrenoDTO> = emptyList()
    )

}