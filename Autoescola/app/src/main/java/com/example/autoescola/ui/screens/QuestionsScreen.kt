package com.example.autoescola.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.autoescola.R

@Composable
fun QuestionsScreen(
    question: String,            // Pregunta
    imageUrl: String,            // URL de la imagen
    answers: List<String>,       // Lista de respuestas
    onAnswerSelected: (String) -> Unit // Acción cuando se selecciona una respuesta
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        // Mostrar la pregunta
        Text(text = question, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la imagen de la pregunta (usando Coil para cargarla desde la URL)
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = stringResource(R.string.descripcion_imagen),
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar las opciones de respuesta
        answers.forEach { answer ->
            Button(
                onClick = { onAnswerSelected(answer) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = answer)
            }
        }
    }
}