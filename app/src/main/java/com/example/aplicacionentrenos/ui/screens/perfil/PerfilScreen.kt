package com.example.aplicacionentrenos.ui.screens.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.bo.Entrenador
import com.example.aplicacionentrenos.domain.model.dto.ClienteDTO
import com.example.aplicacionentrenos.ui.screens.shared.ImagenPreview
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.utils.Constantes
import com.example.aplicacionentrenos.utils.UiEvents
import com.example.aplicacionentrenos.utils.UserCache
import kotlinx.coroutines.flow.collect


@Composable
fun PerfilScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: PerfilViewModel = hiltViewModel(),
) {
    val cliente = viewModel.perfilState.collectAsState().value.cliente
    val entrenador = viewModel.perfilState.collectAsState().value.entrenador

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {

        UserCache.id?.let {
            viewModel.handleEvent(PerfilContract.Eventos.GetClienteByID(it))
        }

        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.mensaje
                    )
                }
                is UiEvents.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {}

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
    ) {
        cliente?.let {
            ShowClientData(cliente = it)

        }

        entrenador?.let { entrenadorLet ->
            cliente?.let {
                Spacer(modifier = Modifier.padding(top = 50.dp))
                ShowTrainerData(entrenador = entrenadorLet, viewModel, it)
            }

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            LoadProgressBar(activacion = viewModel.loading)
        }


    }


}


@Composable
private fun ShowClientData(
    cliente: ClienteDTO
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = cliente.username, style = MaterialTheme.typography.h4)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = cliente.nombre, style = MaterialTheme.typography.h5)
        Text(text = cliente.apellidos, style = MaterialTheme.typography.h5)
    }


}


@Composable
private fun ShowTrainerData(
    entrenador: Entrenador,
    viewModel: PerfilViewModel,
    cliente: ClienteDTO
) {

    Card(
        modifier =
        Modifier
            .width(400.dp)
            .height(250.dp)
            .padding(5.dp),
        elevation = 5.dp
    ) {

        Row(
            modifier =
            Modifier
                .fillMaxSize()
        ) {

            ImagenPreview(ruta = entrenador.imagen)

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = entrenador.nombre)
                Text(text = entrenador.apellidos)
                Button(onClick = {
                    viewModel.handleEvent(PerfilContract.Eventos.BajaEntrenador(cliente))
                }) {
                    Text(text = Constantes.DAR_BAJA)
                }
            }
        }


    }


}