package com.example.aplicacionentrenos.ui.screens.entrenador.detalles

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import com.example.aplicacionentrenos.ui.screens.shared.ImagenCompleta
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.utils.UiEvents
import com.example.aplicacionentrenos.utils.UserCache
import kotlinx.coroutines.flow.collect


@Composable
fun EntrenadorDetallesScreen(
    onPopBackStack: () -> Unit,
    id: Int?,
    viewModel: DetallesEntrenadorViewModel = hiltViewModel()
) {
    val entrenador = viewModel.uiState.collectAsState().value.entrenador

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {

        id?.let {
            viewModel.handleEvent(DetallesEntrenadorContract.Eventos.PedirEntrenador(it))
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

    entrenador?.let {
        EntrenadorOverView(entrenador = it, viewModel)

    }

}


@Composable
private fun EntrenadorOverView(
    entrenador: Entrenador,
    viewModel: DetallesEntrenadorViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BackArrow(viewModel)
        ImagenCompleta(ruta = entrenador.imagen)
        EntrenadorInfo(entrenador = entrenador)
        Spacer(modifier = Modifier.padding(top = 10.dp))
        DescripcionBoton(entrenador = entrenador, viewModel = viewModel)
        LoadProgressBar(viewModel.loading)


    }
}

@Composable
private fun EntrenadorInfo(
    entrenador: Entrenador
){
    Column(
        modifier = Modifier.height(150.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = entrenador.nombre,
                style = MaterialTheme.typography.h4
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = entrenador.apellidos,
                style = MaterialTheme.typography.h5
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Edad : ${entrenador.edad}",
                style = MaterialTheme.typography.h5
            )
        }
    }
}


@Composable
private fun DescripcionBoton(
    entrenador: Entrenador,
    viewModel: DetallesEntrenadorViewModel
){
    Column {
        Text(
            text = "Descripción: ",
            style = MaterialTheme.typography.h5
        )

        Text(
            text = entrenador.descripcion,
            style = MaterialTheme.typography.body1
        )
    }

    Button(onClick = {
        viewModel.handleEvent(
            DetallesEntrenadorContract.Eventos.AltaEntrenador(
                ClienteDTO("", "", "", "", UserCache.id, entrenador.id)
            )
        )
    }) {
        Text(text = "Contratar")
    }

}

@Composable
private fun BackArrow(viewModel: DetallesEntrenadorViewModel) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start) {
        IconButton(onClick = {
            viewModel.handleEvent(DetallesEntrenadorContract.Eventos.Volver)
        }) {
            Icon(imageVector = Icons.Default.ArrowBack ,
                contentDescription = "Volver")
        }
    }

}