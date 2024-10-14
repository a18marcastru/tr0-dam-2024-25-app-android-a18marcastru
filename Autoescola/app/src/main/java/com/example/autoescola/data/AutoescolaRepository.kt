package com.example.autoescola.data

import android.util.Log
import com.example.autoescola.model.AnswerSubmission
import com.example.autoescola.model.QuestionsResponse
import com.example.autoescola.model.ResultResponse
import com.example.autoescola.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun loadQuestionsFromApi(onQuestionsLoaded: (QuestionsResponse?) -> Unit) {
    val call = RetrofitInstance.api.getQuestions()
    call.enqueue(object : Callback<QuestionsResponse> {
        override fun onResponse(call: Call<QuestionsResponse>, response: Response<QuestionsResponse>) {
            if (response.isSuccessful) {
                onQuestionsLoaded(response.body()) // Aquí devolvemos las preguntas
            } else {
                Log.e("QuizApp", "Error en la respuesta: ${response.code()}")
                onQuestionsLoaded(null) // En caso de error, devolvemos null
            }
        }
        override fun onFailure(call: Call<QuestionsResponse>, t: Throwable) {
            Log.e("QuizApp", "Error de conexión: ${t.message}")
            onQuestionsLoaded(null) // En caso de fallo, devolvemos null
        }
    })
}

fun sendAnswersToApi(token: String, answers: List<String>, onResult: (ResultResponse?) -> Unit) {
    val api = RetrofitInstance.api // Instancia de la API

    val request = AnswerSubmission(token, answers)

    api.submitAnswers(request).enqueue(object : Callback<ResultResponse> {
        override fun onResponse(call: Call<ResultResponse>, response: Response<ResultResponse>) {
            if (response.isSuccessful) {
                onResult(response.body()) // Llama al callback con el resultado
            } else {
                Log.e("QuizApp", "Error al enviar respuestas: ${response.message()}")
                onResult(null) // En caso de error
            }
        }

        override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
            Log.e("QuizApp", "Fallo en la llamada a la API: ${t.message}")
            onResult(null) // En caso de fallo
        }
    })
}