package com.example.inversionestrack.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary =  Green500,
    onPrimary = TextLight,
    primaryContainer = Green900,
    onPrimaryContainer = TextLight,
    secondary = Green700,
    onSecondary = TextLight,
    background = Cream,
    onBackground = TextDark,
    surface = Cream,
    onSurface = TextDark,
    surfaceVariant = CreamDark,
    onSurfaceVariant = TextDark,


)

@Composable
fun InversionesTrackTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}