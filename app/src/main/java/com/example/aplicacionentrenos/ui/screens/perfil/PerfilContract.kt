package com.example.aplicacionentrenos.ui.screens.perfil

import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO

interface PerfilContract {


    sealed class Eventos {
        data class GetClienteByID(val id: Int) : Eventos()
        data class GetEntrenadorById(val idEntrenador: Int) : Eventos()
        data class BajaEntrenador(val cliente: ClienteDTO) : Eventos()

    }

    data class PerfilState(
        val cliente: ClienteDTO? = null,
        val entrenador: Entrenador? = null
    )

}