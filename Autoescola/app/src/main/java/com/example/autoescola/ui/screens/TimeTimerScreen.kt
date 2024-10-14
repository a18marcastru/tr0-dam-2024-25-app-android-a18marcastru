package com.example.autoescola.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun TimeTimerScreen(onSecondPassed: () -> Unit) {
    var timeElapsed by remember { mutableIntStateOf(0) }

    LaunchedEffect(true) { // Al crear el composable
        while (true) {
            delay(1000L) // Esperar 1 segundo
            timeElapsed++
            onSecondPassed() // Llama la función para notificar el paso de un segundo
        }
    }

    // Mostrar el tiempo transcurrido en formato MM:SS
    Text(
        text = String.format("%02d:%02d", timeElapsed / 60, timeElapsed % 60),
        style = MaterialTheme.typography.titleLarge
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTotalTimeTimerScreen() {
    TimeTimerScreen(onSecondPassed = {})
}