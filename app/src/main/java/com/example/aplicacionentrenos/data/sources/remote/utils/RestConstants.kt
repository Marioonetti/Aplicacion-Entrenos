package com.example.aplicacionentrenos.data.sources.remote.utils

object RestConstants {
    const val BASE_URL = "http://192.168.1.137:8080/ServidorEntrenos-1.0-SNAPSHOT/api/"


    const val LOGIN_PATH = "login/cliente"
    const val REGISTER_PATH = "registro/cliente"


    const val EJERCICIOS_PATH = "ejercicios"
    const val EJERCICIO_PATH_PARAM = "/{id}"

    const val ENTRENADORES_PATH = "entrenadores"
    const val ENTRENADOR_PATH_PARAM = "/{id}"

}