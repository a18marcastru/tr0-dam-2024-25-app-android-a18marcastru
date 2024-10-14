package com.example.autoescola.network

import com.example.autoescola.model.AnswerSubmission
import com.example.autoescola.model.QuestionsResponse
import com.example.autoescola.model.ResultResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object RetrofitInstance {
    private const val BASE_URL = "http://dam.inspedralbes.cat:20999/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: QuizApi by lazy {
        retrofit.create(QuizApi::class.java)
    }
}

interface QuizApi {
    @GET("start")
    fun getQuestions(): Call<QuestionsResponse>

    @POST("over")
    fun submitAnswers(@Body answers: AnswerSubmission): Call<ResultResponse>
}