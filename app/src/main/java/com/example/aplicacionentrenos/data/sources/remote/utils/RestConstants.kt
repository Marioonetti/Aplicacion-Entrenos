package com.example.aplicacionentrenos.data.sources.remote.utils

object RestConstants {
//    const val BASE_URL = "http://192.168.1.133:8080/ServidorEntrenos-1.0-SNAPSHOT/api/"
    const val BASE_URL = "http://informatica.iesquevedo.es:2326/EntrenosApp/api/"

    const val LOGIN_PATH = "login/cliente"
    const val REGISTER_PATH = "registro/cliente"


    const val EJERCICIOS_PATH = "ejercicios"
    const val EJERCICIO_PATH_PARAM = "/{id}"
    const val EJERCICIO_BY_NAME_PATH = "/nombre"

    const val ENTRENADORES_PATH = "entrenador"
    const val ENTRENADOR_PATH_PARAM = "/{id}"


    const val CLIENTE_PATH = "cliente"
    const val CLIENTE_ALTA_PATH = "/alta"

    const val ENTRENAMIENTOS_PATH = "/entrenamientos"

}