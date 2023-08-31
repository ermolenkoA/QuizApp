package com.example.quizapp.data

data class Topic(
    val id: Long,
    val title: String,
    val image: String,
    val about: String,
    val questions: ArrayList<Question>
)