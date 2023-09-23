package com.example.quizapp.domain

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.data.GameQuestion
import com.example.quizapp.utils.Keys.TOPIC_ID_KEY
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.util.Calendar
import java.util.Date

class ResultsViewModel @AssistedInject constructor(
    @Assisted val gameQuestions: List<GameQuestion>,
    @Assisted("topicId") val topicId: Long,
    @Assisted("spentTime") spentTime: Long
) : ViewModel() {
    val totalTime: Calendar = Calendar.getInstance().apply {
        time = Date(spentTime)
    }

    val numberOfQuestions = gameQuestions.size

    val correctAnswersCount = gameQuestions.filter { gameQuestion ->
        with(gameQuestion.answers){
            answerOption == correctOption
        }
    }.size

    fun createBundleForGameFragment(): Bundle = Bundle().apply {
        putLong(TOPIC_ID_KEY, topicId)
    }

    @AssistedFactory
    interface Factory {
        fun create(gameQuestions: List<GameQuestion>,
                   @Assisted("topicId")topicId: Long,
                   @Assisted("spentTime")spentTime: Long,
        ): ResultsViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            gameQuestions: List<GameQuestion>,
            topicId: Long,
            spentTime: Long,
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(gameQuestions, topicId, spentTime) as T
            }
        }
    }
}