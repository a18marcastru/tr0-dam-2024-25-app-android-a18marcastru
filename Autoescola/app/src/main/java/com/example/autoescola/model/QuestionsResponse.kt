package com.example.autoescola.model

import com.example.autoescola.model.Question

data class QuestionsResponse(
    val token: String,
    val preguntes: List<Question>
)