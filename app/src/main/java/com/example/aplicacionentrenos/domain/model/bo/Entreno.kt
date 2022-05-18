package com.example.aplicacionentrenos.domain.model.bo

import java.time.LocalDate

data class Entreno (
    val id : Int,
    val titulo : String,
    val descripcion : String,
    val fecha : LocalDate
        )