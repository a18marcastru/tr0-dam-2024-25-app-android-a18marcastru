package com.example.autoescola

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.autoescola.ui.AutoescolaApp
import com.example.autoescola.ui.theme.AutoescolaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoescolaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Usar rememberNavController para manejar la navegación
                    val navController = rememberNavController()
                    AutoescolaApp(navController) // Asegúrate de que aquí se esté usando tu pantalla principal
                }
            }
        }
    }
}