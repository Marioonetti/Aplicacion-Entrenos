package com.example.aplicacionentrenos.utils

sealed class UiEvents {
    data class Navigate(val route: String): UiEvents()
    object PopBackStack: UiEvents()
    data class ShowSnackBar(
        val mensaje :String
    ) : UiEvents()

}
