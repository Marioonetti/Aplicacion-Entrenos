package com.example.aplicacionentrenos.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.aplicacionentrenos.ui.screens.login.LoginBox
import com.example.aplicacionentrenos.ui.theme.GrisFondo
import com.example.aplicacionentrenos.utils.UiEvents

@Composable
fun MainScreen(onNavigate: (UiEvents.Navigate) -> Unit,) {

    LoginBox(onNavigate = onNavigate)


}