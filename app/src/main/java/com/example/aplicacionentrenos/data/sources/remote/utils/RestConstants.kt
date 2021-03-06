package com.example.aplicacionentrenos.data.sources.remote.utils

object RestConstants {
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
    const val CLIENTE_BAJA_PATH = "/baja"
    const val CLIENTE_PATH_PARAM = "/{id}"

    const val ENTRENAMIENTOS_PATH = "entreno"
    const val ENTRENAMIENTOS_DESC_PATH = "/desc"
    const val ENTRENAMIENTOS_ASC_PATH = "/asc"
    const val ENTRENAMIENTO_PATH_PARAM = "/{id}"


    const val ID_ENTRENAMIENTO_PARAM = "id"
    const val ID_CLIENTE_PARAM ="idCliente"

    const val HEADER_AUTH = "Authorization"
    const val HEADER_EXPIRES = "Expires"

}