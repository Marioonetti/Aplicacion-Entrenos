package com.example.aplicacionentrenos.ui.screens.ejercicios.detalles

import com.example.aplicacionentrenos.domain.model.dto.EjercicioDTO

interface EjercicioDetallesContract {

    sealed class Eventos {
        data class GetEjercicioById(val id: Int) : Eventos()
        object Volver : Eventos()
    }

    data class EjercicioState(
        val ejercicio: EjercicioDTO? = null
    )


}