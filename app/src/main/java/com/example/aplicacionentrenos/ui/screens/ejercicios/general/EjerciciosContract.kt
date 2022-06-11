package com.example.aplicacionentrenos.ui.screens.ejercicios.general

import com.example.aplicacionentrenos.domain.model.dto.EjercicioDTO

interface EjerciciosContract {

    sealed class Eventos {
        data class IrDetallesEjercicio(val ejercicioId: Int) : Eventos()
        data class OnSearchChange(val value: String) : Eventos()
        object GetAll : Eventos()
        data class BuscarEjercicios(val nombre: String) : Eventos()
    }

    data class EjerciciosState(
        val ejercicios: List<EjercicioDTO> = emptyList()
    )


}