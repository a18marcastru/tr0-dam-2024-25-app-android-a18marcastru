package com.example.autoescola.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.autoescola.R
import com.example.autoescola.model.ResultResponse

@Composable
fun ResultScreen(totalTimeSpent: Int, result: ResultResponse?, onRestart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Mostrar el tiempo total transcurrido
        Text(
            text = "Tiempo total: ${String.format("%02d:%02d", totalTimeSpent / 60, totalTimeSpent % 60)}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar resultados correctos e incorrectos
        if (result != null) {
            Text(text = "Correctas: ${result.correctes}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Incorrectas: ${result.incorrectes}", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRestart) {
            Text(text = stringResource(R.string.reiniciar))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewResultQuizScreen() {
    val result = ResultResponse(correctes = 3, incorrectes = 5)
    ResultScreen(totalTimeSpent = 120, onRestart = {}, result = result)
}