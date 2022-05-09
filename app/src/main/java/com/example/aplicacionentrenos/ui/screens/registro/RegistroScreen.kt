package com.example.aplicacionentrenos.ui.screens.registro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.aplicacionentrenos.domain.model.ClienteDTO
import com.example.aplicacionentrenos.ui.screens.login.LoginContract
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect

@Composable
fun RegistroScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: RegistroViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
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

    }


    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .width(300.dp)
                .height(500.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            OutlinedTextField(
                value = viewModel.taNombre,
                onValueChange = {
                    viewModel.handleEvent(RegistroContract.Eventos.OnNombreChange(it))
                },
                label = { Text(text = "Nombre") },
                singleLine = true
            )

            OutlinedTextField(
                value = viewModel.taApellidos,
                onValueChange = {
                    viewModel.handleEvent(RegistroContract.Eventos.OnApellidosChange(it))
                },
                label = { Text(text = "Apellidos") },
                singleLine = true
            )

            OutlinedTextField(
                value = viewModel.taUsername,
                onValueChange = {
                    viewModel.handleEvent(RegistroContract.Eventos.OnUsernameChange(it))
                },
                label = { Text(text = "Usuario") },
                singleLine = true
            )

            OutlinedTextField(
                value = viewModel.taPassw,
                onValueChange = {
                    viewModel.handleEvent(RegistroContract.Eventos.OnPasswordChange(it))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                label = { Text(text = "Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    viewModel.handleEvent(RegistroContract.Eventos.NavigateToLogin)
                }) {
                    Text(text = "Login")
                }

                Button(onClick = {
                    viewModel.handleEvent(RegistroContract.Eventos.Registrarse(
                        ClienteDTO(viewModel.taUsername, viewModel.taPassw, viewModel.taNombre, viewModel.taApellidos)
                    ))
                }) {
                    Text(text = "Registrarse")
                }
            }


        }
    }







}