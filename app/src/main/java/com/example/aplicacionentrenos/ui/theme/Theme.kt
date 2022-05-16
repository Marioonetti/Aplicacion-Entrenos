package com.example.aplicacionentrenos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = primaryDarkColor,
    primaryVariant = secondaryDarkColor,
    secondary = secondaryColor
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


private val PersonalColorPalette = lightColors(
    primary = primaryColor,
    primaryVariant = primaryLightColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryLightColor,
    error = error,
    onPrimary = primaryTextColor
)


@Composable
fun AplicacionEntrenosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        PersonalColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}