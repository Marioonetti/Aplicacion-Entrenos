package com.example.aplicacionentrenos.ui.screens.principal

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Train
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.example.aplicacionentrenos.domain.model.BottomBarItem
import com.example.aplicacionentrenos.ui.screens.shared.BottomBarNavigation
import com.example.aplicacionentrenos.utils.NavigationConstants
import com.example.aplicacionentrenos.utils.UiEvents

@Composable

fun PrincipalScreen(
    onNavigate: (UiEvents.Navigate) -> Unit,
    navController: NavHostController,
) {
    Scaffold(

    ) {

    }

    Text(text = "Principal Screen")

}