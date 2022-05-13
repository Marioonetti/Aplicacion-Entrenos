package com.example.aplicacionentrenos.ui.screens.entrenador.detalles

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import com.example.aplicacionentrenos.ui.screens.shared.ImagenFull
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.utils.UiEvents
import com.example.aplicacionentrenos.utils.UserCache
import kotlinx.coroutines.flow.collect


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun EntrenadorDetallesScreen(
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
                else -> Unit
            }
        }


    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {

    }
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            entrenador?.let {
                ImagenFull(ruta = it.imagen)

                Column(
                    modifier = Modifier.height(150.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Row(
                        modifier = Modifier.width(300.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = it.nombre,
                            style = MaterialTheme.typography.h4
                        )
                        Text(
                            text = it.apellidos,
                            style = MaterialTheme.typography.h4
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Edad : ${it.edad}",
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))

                Column {
                    Text(
                        text = "Descripción: ",
                        style = MaterialTheme.typography.h5
                    )

                    Text(
                        text = it.descripcion,
                        style = MaterialTheme.typography.h6
                    )
                }

                Button(onClick = {
                    viewModel.handleEvent(DetallesEntrenadorContract.Eventos.AltaEntrenador(
                        ClienteDTO("", "", "", "" ,UserCache.id, it.id)
                    ))
                }) {
                    Text(text = "Contratar")
                }

            }
        }

        LoadProgressBar(viewModel.loading)


    }

}