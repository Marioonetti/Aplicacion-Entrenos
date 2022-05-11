package com.example.aplicacionentrenos.domain.datamappers

import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.domain.model.dto.EntrenadorDTO


fun EntrenadorDTO.toEntrenador() = Entrenador(
    id, nombre, apellidos, descripcion ?: "", imagen, edad
)