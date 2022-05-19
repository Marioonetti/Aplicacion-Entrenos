package com.example.aplicacionentrenos.ui.screens.ejercicios.detalles

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.dto.EjercicioDTO
import com.example.aplicacionentrenos.ui.screens.entrenamientos.detalles.EntrenoDetallesContract
import com.example.aplicacionentrenos.ui.screens.shared.ImagenHorizontal
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect


@Composable
fun EjercicioDetalleScreen(
    id : Int?,
    viewModel: EjercicioDetallesViewModel = hiltViewModel()
) {
    val ejercicio = viewModel.ejercicioState.collectAsState().value.ejercicio

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {

        id?.let {
            viewModel.handleEvent(EjercicioDetallesContract.Eventos.GetEjercicioById(id))
        }
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.mensaje
                    )
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {}

    Column {
        ejercicio?.let {
            EjercicioOverView(ejercicio = ejercicio)
        }

        LoadProgressBar(activacion = viewModel.loading)
    }

}


@Composable
private fun EjercicioOverView(
    ejercicio: EjercicioDTO
){

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(text = ejercicio.nombre, style = MaterialTheme.typography.h4)
        ImagenHorizontal(ruta = ejercicio.img, alto = 300)
        EjercicioElements(ejercicio = ejercicio)

    }

}

@Composable
private fun EjercicioElements(
    ejercicio: EjercicioDTO
){

    Column() {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Intensidad: ${ejercicio.intensidad}")
            Text(text = "Musculo Enfocado: ${ejercicio.grupoMuscular}")
        }
        Box(modifier = Modifier
            .height(250.dp)
            .width(300.dp)
            .padding(8.dp)
        ){
            Text(
                text = ejercicio.descripcion,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify
            )
        }

    }

}