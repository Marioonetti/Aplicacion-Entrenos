package com.example.aplicacionentrenos.domain.model.dto

data class EntrenoDTO(
    val comentario: String,
    val fecha: String,
    val id: Int,
    val idCliente: Int,
    val series: List<SerieDTO>,
    val titulo: String
)