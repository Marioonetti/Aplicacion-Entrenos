package com.example.aplicacionentrenos.ui.screens.login

import com.example.aplicacionentrenos.domain.model.UserDTO

interface LoginContract {

    sealed class Eventos{

        data class doLogin(val userDTO: UserDTO) : Eventos()
        data class onUsernameChange(val username : String) : Eventos()
        data class onPasswordChange(val passw : String) : Eventos()

    }





}