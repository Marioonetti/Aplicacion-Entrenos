package com.example.aplicacionentrenos.ui.screens.shared

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets.add
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import coil.size.Scale

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Imagen(ruta: String) {

    Box {

        val context = LocalContext.current
        val imgLoader = ImageLoader.invoke(context).newBuilder()
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(context))
                } else {
                    add(GifDecoder())
                }
            }.build()

        val painter = rememberImagePainter(
            imageLoader = imgLoader ,
            data = ruta,
            builder = {
            size(OriginalSize)
                scale(Scale.FILL)
                crossfade(true)
        })
//        Estado de la imagen
        val painterState = painter.state

        Image(
            modifier = Modifier
                .width(210.dp)
                .fillMaxHeight(),
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
fun ImagenHorizontal(ruta: String) {

    Box {

        val context = LocalContext.current
        val imgLoader = ImageLoader.invoke(context).newBuilder()
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(context))
                } else {
                    add(GifDecoder())
                }
            }.build()

        val painter = rememberImagePainter(
            imageLoader = imgLoader ,
            data = ruta,
            builder = {
                size(OriginalSize)
                scale(Scale.FILL)
                crossfade(true)
            })
//        Estado de la imagen
        val painterState = painter.state

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painter,
            contentDescription = "Imagen",
            contentScale = ContentScale.Crop,
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
                crossfade(true)
            })
//        Estado de la imagen
        val painterState = painter.state

        Image(
            painter = painter,
            contentDescription = "Imagen",
            contentScale = ContentScale.Crop,
        )
//        Si su estado es cargando, ponemos la ciruclar progress bar
        if (painterState is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }

    }


}


@Composable
fun LoadProgressBar(activacion : Boolean){

    if (activacion){
        CircularProgressIndicator()
    }

}


