package com.example.aplicacionentrenos.ui.screens.registro

import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO

interface RegistroContract {

    sealed class Eventos {
        object NavigateToLogin : Eventos()
        data class Registrarse(val clienteDTO: ClienteDTO) : Eventos()
        data class OnNombreChange(val nombre: String) : Eventos()
        data class OnApellidosChange(val apellidos: String) : Eventos()
        data class OnUsernameChange(val username: String) : Eventos()
        data class OnPasswordChange(val password: String) : Eventos()
    }

}