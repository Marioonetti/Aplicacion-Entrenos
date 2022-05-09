package com.example.aplicacionentrenos.domain.model

data class ClienteDTO (

    val username : String,
    val password : String,
    val nombre : String,
    val apellidos : String,
    val id : Int? = null,
    val idEntrenador : Int? = null
)