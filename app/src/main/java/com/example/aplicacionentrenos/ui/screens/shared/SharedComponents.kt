package com.example.aplicacionentrenos.ui.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Imagen(ruta: String) {

    Box {
        val painter = rememberImagePainter(
            data = ruta,
            builder = {
            size(OriginalSize)
                scale(Scale.FILL)
                crossfade(true)
        })
//        Estado de la imagen
        val painterState = painter.state

        Image(
            modifier = Modifier.width(210.dp).fillMaxHeight(),
            painter = painter,
            contentDescription = "Imagen",
            contentScale = ContentScale.Fit,
        )
//        Si su estado es cargando, ponemos la ciruclar progress bar
        if (painterState is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }

    }


}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImagenFull(ruta: String) {

    Box {
        val painter = rememberImagePainter(
            data = ruta,
            builder = {
                size(OriginalSize)
                scale(Scale.FILL)
                crossfade(true)
            })
//        Estado de la imagen
        val painterState = painter.state

        Image(
            painter = painter,
            contentDescription = "Imagen",
            contentScale = ContentScale.Fit,
        )
//        Si su estado es cargando, ponemos la ciruclar progress bar
        if (painterState is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }

    }


}