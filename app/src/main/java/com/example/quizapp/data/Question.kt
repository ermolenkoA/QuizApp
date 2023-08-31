package com.example.quizapp.data

data class Question(
    val id: Long,
    val title: String,
    val image: String?,
    val fakeAnswers: ArrayList<String>,
    val realAnswer: String
)