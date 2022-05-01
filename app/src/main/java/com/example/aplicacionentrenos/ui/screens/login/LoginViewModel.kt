package com.example.aplicacionentrenos.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class LoginViewModel : ViewModel() {

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set


    fun handleEvent(event : LoginContract.Eventos){

        when(event){
            is LoginContract.Eventos.onUsernameChange -> {
                username = event.username
            }

            is LoginContract.Eventos.onPasswordChange -> {
                password = event.passw
            }

        }

    }


}