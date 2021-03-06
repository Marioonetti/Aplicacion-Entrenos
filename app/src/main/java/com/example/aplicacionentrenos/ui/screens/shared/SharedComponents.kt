package com.example.aplicacionentrenos.ui.screens.shared

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import coil.size.Scale
import com.example.aplicacionentrenos.utils.Constantes

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImagenPreview(ruta: String) {

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
            imageLoader = imgLoader,
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
            contentDescription = Constantes.CONTENT_DESCRIPTION,
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
fun ImagenHorizontalPeque(ruta: String, alto: Int) {

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
            imageLoader = imgLoader,
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
                .height(alto.dp),
            painter = painter,
            contentDescription = Constantes.CONTENT_DESCRIPTION,
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
fun GifImagenAjustadoCaja(ruta: String, alto: Int) {

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
            imageLoader = imgLoader,
            data = ruta,
            builder = {
                size(OriginalSize)
                scale(Scale.FIT)
                crossfade(true)
            })
//        Estado de la imagen
        val painterState = painter.state

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(alto.dp),
            painter = painter,
            contentDescription = Constantes.CONTENT_DESCRIPTION,
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
fun ImagenCompleta(ruta: String) {

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
            contentDescription = Constantes.CONTENT_DESCRIPTION,
            contentScale = ContentScale.Crop,
        )
//        Si su estado es cargando, ponemos la ciruclar progress bar
        if (painterState is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }

    }


}


@Composable
fun LoadProgressBar(activacion: Boolean) {

    if (activacion) {
        CircularProgressIndicator()
    }

}


