package com.example.aplicacionentrenos.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.aplicacionentrenos.ui.theme.AplicacionEntrenosTheme

@Composable
fun MyEntrenosApp(content: @Composable () -> Unit) {
    AplicacionEntrenosTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }


}