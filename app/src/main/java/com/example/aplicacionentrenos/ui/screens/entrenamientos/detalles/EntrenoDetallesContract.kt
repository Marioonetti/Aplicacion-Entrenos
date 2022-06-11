package com.example.aplicacionentrenos.ui.screens.entrenamientos.detalles

import com.example.aplicacionentrenos.domain.model.dto.EntrenoDTO

interface EntrenoDetallesContract {

    sealed class Eventos {
        data class IrDetalleEjercicio(val ejercicioId: Int) : Eventos()
        data class GetEntrenoById(val id: Int) : Eventos()
    }

    data class EntrenoState(
        val entreno: EntrenoDTO? = null
    )

}