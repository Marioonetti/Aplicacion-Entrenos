package com.example.aplicacionentrenos.ui.screens.ejercicios.detalles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.dto.EjercicioDTO
import com.example.aplicacionentrenos.ui.screens.shared.GifImagenAjustadoCaja
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.utils.Constantes
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect


@Composable
fun EjercicioDetalleScreen(
    onPopBackStack: () -> Unit,
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
                is UiEvents.PopBackStack -> onPopBackStack()
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
            EjercicioOverView(ejercicio = ejercicio, viewModel)
        }

        LoadProgressBar(activacion = viewModel.loading)
    }

}


@Composable
private fun EjercicioOverView(
    ejercicio: EjercicioDTO,
    viewModel: EjercicioDetallesViewModel
){

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Row{

                IconButton(onClick = {
                    viewModel.handleEvent(EjercicioDetallesContract.Eventos.Volver)
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack ,
                        contentDescription = Constantes.CONTENT_DESCRIPTION)
                }

                Text(
                    text = ejercicio.nombre,
                    style = MaterialTheme.typography.h4)


        }

        GifImagenAjustadoCaja(ruta = ejercicio.img, alto = 300)
        EjercicioElements(ejercicio = ejercicio)

    }

}

@Composable
private fun EjercicioElements(
    ejercicio: EjercicioDTO
){

    Column() {

        Column(
            modifier = Modifier.height(150.dp),
            verticalArrangement = Arrangement.SpaceEvenly,

        ) {
            Text(text = "Intensidad: ${ejercicio.intensidad}")
            Text(text = "Musculo Enfocado: ${ejercicio.grupoMuscular}")
        }
        Box(modifier = Modifier
            .height(250.dp)
            .width(300.dp)
            .padding(4.dp)
        ){
            Text(
                text = ejercicio.descripcion,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify
            )
        }

    }

}