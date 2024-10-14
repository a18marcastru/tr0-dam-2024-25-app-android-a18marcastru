package com.example.autoescola.ui

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoescola.data.loadQuestionsFromApi
import com.example.autoescola.data.sendAnswersToApi
import com.example.autoescola.model.Question
import com.example.autoescola.model.ResultResponse
import kotlinx.coroutines.launch

class AutoescolaViewModel : ViewModel() {
    var token = mutableStateOf("")
        private set

    var questions = mutableStateOf<List<Question>?>(null)
        private set

    var currentQuestionIndex = mutableIntStateOf(0)
        private set

    var isQuizFinished = mutableStateOf(false)
        private set

    var totalTimeSpent = mutableIntStateOf(0)
        private set

    var answers = mutableStateListOf<String>()
        private set

    var result = mutableStateOf<ResultResponse?>(null)
        private set

    // Cargar preguntas desde la API
    fun loadQuestions() {
        viewModelScope.launch {
            loadQuestionsFromApi { loadedQuestions ->
                loadedQuestions?.let {
                    questions.value = it.preguntes // Cargamos las preguntas
                    token.value = it.token        // Cargamos el token recibido
                }
            }
        }
    }

    // Enviar respuestas a la API cuando el cuestionario finaliza
    fun sendAnswers() {
        viewModelScope.launch {
            sendAnswersToApi(token.value, answers) { resultResponse ->
                result.value = resultResponse
            }
        }
    }

    // Reiniciar el cuestionario
    fun restartQuiz() {
        currentQuestionIndex.value = 0
        isQuizFinished.value = false
        totalTimeSpent.value = 0
        answers.clear()
        result.value = null
    }

    // Seleccionar una respuesta y avanzar a la siguiente pregunta
    fun onAnswerSelected(answer: String) {
        answers.add(answer)
        if (currentQuestionIndex.value < questions.value!!.size - 1) {
            currentQuestionIndex.value++
        } else {
            isQuizFinished.value = true
        }
    }

    // Incrementar el tiempo total transcurrido
    fun incrementTime() {
        totalTimeSpent.value++
    }
}