package com.example.aplicacionentrenos.ui.screens.entrenador.general

import com.example.aplicacionentrenos.domain.model.bo.Entrenador

interface EntrenadoresContract {


    sealed class Eventos{
        object GetAll : Eventos()
        data class NavToInformacion(val id: Int) : Eventos()
    }

    data class EntrenadoresState(
        val entrenadores: List<Entrenador> = emptyList()
    )

}