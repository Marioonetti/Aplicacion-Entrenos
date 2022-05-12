package com.example.aplicacionentrenos.ui.screens.entrenador.detalles

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.ui.screens.shared.ImagenFull
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun EntrenadorDetallesScreen(
    id: Int?,
    viewModel: DetallesEntrenadorViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {

        id?.let{
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

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        viewModel.uiState.value.entrenador?.let {
            ImagenFull(ruta = it.imagen)

            Column(
                modifier = Modifier.height(30.dp),
                verticalArrangement = Arrangement.SpaceEvenly) {

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = it.nombre)
                    Text(text = it.apellidos)
                }

                Text(text = "Edad : ${it.edad}")
            }

            Box(modifier = Modifier.padding(5.dp)){
                TextField(value = it.descripcion,
                    onValueChange ={},
                )

            }

        }


    }

}