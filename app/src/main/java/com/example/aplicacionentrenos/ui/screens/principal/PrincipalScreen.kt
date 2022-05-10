package com.example.aplicacionentrenos.ui.screens.principal

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.aplicacionentrenos.utils.UiEvents

@Composable
fun PrincipalScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    navController: NavHostController,
) {

    Text(text = "Principal Screen")

}