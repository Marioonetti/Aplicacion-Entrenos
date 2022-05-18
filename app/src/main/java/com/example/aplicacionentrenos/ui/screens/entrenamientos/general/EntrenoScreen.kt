package com.example.aplicacionentrenos.ui.screens.entrenamientos.general

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.aplicacionentrenos.domain.model.bo.Entreno
import com.example.aplicacionentrenos.ui.screens.shared.LoadProgressBar
import com.example.aplicacionentrenos.utils.UiEvents
import com.example.aplicacionentrenos.utils.UserCache
import kotlinx.coroutines.flow.collect
import kotlin.reflect.KFunction1

@Composable
fun EntrenosScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: EntrenamientosViewModel = hiltViewModel()
) {

    val listaEntrenos = viewModel.entrenos.collectAsState().value.entrenamientos

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.handleEvent(EntrenoContract.Eventos.GetAllDesc(UserCache.id!!))
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (listaEntrenos.isEmpty()) {
                NoEntrenos()
            } else {
                DropDownMenu(lista = listOf("Fecha Descendente", "Fecha Ascendente"), viewModel)
                LazyColumn {
                    items(listaEntrenos) { entreno ->
                        EntrenoItem(entreno, viewModel::handleEvent)
                    }

                }
            }
            LoadProgressBar(viewModel.loading)
        }
    }
}

@Composable
private fun EntrenoItem(
    entreno: Entreno,
    handleEvent: KFunction1<EntrenoContract.Eventos, Unit>
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .height(250.dp)
            .clickable {
                handleEvent(EntrenoContract.Eventos.IrDetallesEntrenamiento(entreno.id))
            },
        elevation = 5.dp
    ) {
        EntrenoElements(entreno = entreno)
    }


}

@Composable
private fun EntrenoElements(entreno: Entreno) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = entreno.titulo)

        Box(
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
                .padding(8.dp)
        )
        {
            Text(
                text = entreno.descripcion,
                textAlign = TextAlign.Justify
            )
        }

        Text(text = entreno.fecha.toString())

    }
}


@Composable
private fun NoEntrenos() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No tienes Entrenos aún disponibles",
            style = MaterialTheme.typography.h4
        )
    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenu(
    lista: List<String>,
    viewModel: EntrenamientosViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(lista[0]) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                lista.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                            if (selectionOption == lista[0]){
                                viewModel.handleEvent(EntrenoContract.Eventos.GetAllDesc(UserCache.id!!))
                            }
                            else{
                                viewModel.handleEvent(EntrenoContract.Eventos.GetAllAsc(UserCache.id!!))
                            }
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }

}