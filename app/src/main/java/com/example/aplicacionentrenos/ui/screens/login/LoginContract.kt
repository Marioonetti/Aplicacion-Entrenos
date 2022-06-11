package com.example.aplicacionentrenos.ui.screens.login

import com.example.aplicacionentrenos.domain.model.dto.UserDTO

interface LoginContract {

    sealed class Eventos {
        object navToRegistro : Eventos()
        data class doLogin(val userDTO: UserDTO) : Eventos()
        data class onUsernameChange(val username: String) : Eventos()
        data class onPasswordChange(val passw: String) : Eventos()

    }


}