package com.example.quizapp.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

@Parcelize
class Answers(
    private val answers: MutableList<String>,
    private var _answerOption: Option?,
    private var _correctIndex: Int
    ) : Parcelable {

    val answerOption: Option? get() = _answerOption
    val correctOption: Option get() = Option.getOption(_correctIndex)!!

    fun answer(option: Option?) {
        _answerOption = option
    }

    fun getAnswerText(option: Option): String {
        return answers[option.value]
    }

    companion object {
        fun new(fakeAnswers: List<String>, correctAnswer: String): Answers{
            if (fakeAnswers.size >= 3) {
                val newAnswers = mutableListOf<String>()
                    .apply {
                        addAll(fakeAnswers.shuffled().subList(0, 3))
                    }

                val correctIndex = (0..3).random()
                newAnswers.add(correctIndex, correctAnswer)
                return Answers(newAnswers, null, correctIndex)
            } else {
                throw ExceptionInInitializerError("Answers: few fake answers for init")
            }
        }
    }

}