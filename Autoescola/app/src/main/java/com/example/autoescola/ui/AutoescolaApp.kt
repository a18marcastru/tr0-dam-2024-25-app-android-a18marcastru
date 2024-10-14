package com.example.autoescola.ui

import androidx.annotation.StringRes
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.autoescola.R
import com.example.autoescola.ui.screens.QuestionsScreen
import com.example.autoescola.ui.screens.ResultScreen
import com.example.autoescola.ui.screens.StartScreen
import com.example.autoescola.ui.screens.TimeTimerScreen

enum class AutoescolaApp(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Game(title = R.string.preguntes),
    Result(title = R.string.resultats)
}

@Composable
fun AutoescolaApp(navController: NavHostController, quizViewModel: AutoescolaViewModel = viewModel()) {
    val questions by quizViewModel.questions
    val currentQuestionIndex by quizViewModel.currentQuestionIndex
    val isQuizFinished by quizViewModel.isQuizFinished
    val totalTimeSpent by quizViewModel.totalTimeSpent
    val result by quizViewModel.result

    // Cargar preguntas desde la API cuando se monta el Composable
    LaunchedEffect(Unit) {
        quizViewModel.loadQuestions()
    }

    // Configurar el NavHost
    NavHost(navController, startDestination = AutoescolaApp.Start.name) {
        composable(route = AutoescolaApp.Start.name) {
            StartScreen(onStartClicked = {
                navController.navigate(AutoescolaApp.Game.name) // Navega a la pantalla del quiz
            })
        }
        composable(route = AutoescolaApp.Game.name) {
            if (questions == null) {
                CircularProgressIndicator() // Indicador de carga
            } else if (isQuizFinished) {
                // Solo enviar respuestas una vez cuando se completa el quiz
                LaunchedEffect(Unit) {
                    quizViewModel.sendAnswers()
                    navController.navigate(AutoescolaApp.Result.name) // Navega a la pantalla de resultados
                }
            } else {
                // Temporizador global del cuestionario
                TimeTimerScreen(onSecondPassed = { quizViewModel.incrementTime() })
                // Mostrar la pregunta actual
                val currentQuestion = questions!![currentQuestionIndex]
                QuestionsScreen(
                    question = currentQuestion.pregunta,
                    imageUrl = currentQuestion.imatge,
                    answers = currentQuestion.respostes,
                    onAnswerSelected = { selectedAnswer ->
                        quizViewModel.onAnswerSelected(selectedAnswer)
                    }
                )
            }
        }
        composable(route = AutoescolaApp.Result.name) {
            ResultScreen(
                totalTimeSpent = totalTimeSpent,
                result = result,
                onRestart = {
                    quizViewModel.restartQuiz()
                    navController.navigate(AutoescolaApp.Start.name) // Regresa a la pantalla de inicio
                }
            )
        }
    }
}