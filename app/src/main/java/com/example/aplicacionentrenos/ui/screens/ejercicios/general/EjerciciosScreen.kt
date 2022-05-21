package com.example.aplicacionentrenos.ui.screens.ejercicios.general

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.dto.EjercicioDTO
import com.example.aplicacionentrenos.ui.screens.shared.ImagenHorizontalPeque
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.ui.theme.GrisFondo
import com.example.aplicacionentrenos.ui.theme.amarilloApagado
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect

@Composable
fun EjerciciosScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: EjerciciosViewModel = hiltViewModel()
){

    val listaEjercicios = viewModel.ejercicios.
    collectAsState().
    value.
    ejercicios

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.handleEvent(EjerciciosContract.Eventos.GetAll)
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
    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            SearchTextField(viewModel)

            LazyColumn{
                items(listaEjercicios){
                        ejercicio ->
                    EjercicioItem(ejercicio, viewModel::handleEvent)
                }

            }

            LoadProgressBar(viewModel.loading)
        }
    }
}

@Composable
fun EjercicioItem(
    ejercicio: EjercicioDTO,
    handleEvent: (EjerciciosContract.Eventos) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .height(250.dp)
            .clickable {
                handleEvent(EjerciciosContract.Eventos.IrDetallesEjercicio(ejercicio.id))
            },
        elevation = 5.dp
    ) {
        Column(modifier = Modifier.background(color = GrisFondo)) {
            ImagenHorizontalPeque(ejercicio.img, 200)
            OverviewEjercicio(ejercicio)
        }

    }
}


@Composable
private fun OverviewEjercicio(ejercicio: EjercicioDTO) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
            Text(
                text = ejercicio.nombre,
                style = MaterialTheme.typography.h5,
            )
        Text(
            text = ejercicio.intensidad,
            style = MaterialTheme.typography.h5,
            color = amarilloApagado
        )

    }
}



@Composable
private fun SearchTextField(viewModel: EjerciciosViewModel) {

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            modifier =
            Modifier
                .padding(5.dp)
                .width(300.dp),
            value = viewModel.searchText,
            onValueChange = {
                viewModel.handleEvent(EjerciciosContract.Eventos.OnSearchChange(it))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(
                    onClick = { viewModel.handleEvent(EjerciciosContract.Eventos.BuscarEjercicios(viewModel.searchText)) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search")
                }
            },
            label = {
                Text(text = "Nombre del ejercicio")
            }
        )
    }


}
