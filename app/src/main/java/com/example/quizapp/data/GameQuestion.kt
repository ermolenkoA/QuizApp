package com.example.quizapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameQuestion(
    val title: String,
    val image: String?,
    val answers: Answers
): Parcelable