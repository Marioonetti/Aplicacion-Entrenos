package com.example.aplicacionentrenos.domain.datamappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.domain.model.bo.Entreno
import com.example.aplicacionentrenos.domain.model.dto.EntrenadorDTO
import com.example.aplicacionentrenos.domain.model.dto.EntrenoDTO
import java.time.LocalDate


fun EntrenadorDTO.toEntrenador() = Entrenador(
    id, nombre, apellidos, descripcion ?: "", imagen, edad
)


fun EntrenoDTO.toEntreno() = Entreno(
    id, titulo, comentario, LocalDate.parse(fecha)
)