package com.example.aplicacionentrenos.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplicacionentrenos.domain.model.dto.UserDTO
import com.example.aplicacionentrenos.utils.UiEvents
import kotlinx.coroutines.flow.collect

@Composable
fun LoginBox(
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
//Snackbar

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
    ) {}


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {


        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Nombre de Usuario: ",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = viewModel.username,
                onValueChange = {
                    viewModel.handleEvent(LoginContract.Eventos.onUsernameChange(it))
                },
                singleLine = true
            )
        }

        Column {
            Text(
                text = "Contraseña: ",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = {
                    viewModel.handleEvent(LoginContract.Eventos.onPasswordChange(it))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                visualTransformation = if (viewModel.hidden)
                    PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = { viewModel.hidden = !viewModel.hidden }) {
                        val vector: ImageVector =
                            if (viewModel.hidden) Icons.Default.VisibilityOff
                            else Icons.Default.Visibility
                        Icon(imageVector = vector, contentDescription = "Ojo")
                    }
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(onClick = {
                viewModel.handleEvent(LoginContract.Eventos.navToRegistro)
            }) {
                Text(text = "Registrarse")
            }

            Button(onClick = {
                viewModel.handleEvent(
                    LoginContract.Eventos.doLogin(
                        UserDTO(viewModel.username, viewModel.password)
                    )
                )
            }) {
                Text(text = "Login")
            }
        }

        if (viewModel.loading) {
            CircularProgressIndicator()
        }

    }


}