package com.example.aplicacionentrenos.ui.screens.ejercicios.detalles

import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun EjercicioDetalleScreen(
    id : Int?
) {
    Text(text = "La id del ejercicio es  ${id}")

}