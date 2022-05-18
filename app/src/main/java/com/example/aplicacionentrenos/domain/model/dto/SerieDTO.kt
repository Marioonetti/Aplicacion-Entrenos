package com.example.aplicacionentrenos.domain.model.dto


data class SerieDTO(
    val ejercicio: EjercicioDTO,
    val enfoque: String,
    val id: Int,
    val idEjercicio: Int,
    val rir: Int,
    val seriesRepeticiones: String
)