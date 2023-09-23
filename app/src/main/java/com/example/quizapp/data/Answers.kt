package com.example.quizapp.data

import android.util.Log

class Answers(fakeAnswers: List<String>, correctAnswer: String) {
    private val answers: MutableList<String>
    private var _answerOption: Option? = null

    val correctIndex: Int
    val answerIndex: Int? get() = _answerOption?.value

    enum class Option(val value: Int){
        A(0), B(1), C(2), D(3);
        companion object{
            fun getOption(index: Int): Option? = when (index) {
                0 -> A
                1 -> B
                2 -> C
                3 -> D
                else -> null
            }
        }
    }

    init {
        if (fakeAnswers.size >= 3) {
            answers = mutableListOf<String>()
                .apply {
                    addAll(fakeAnswers.shuffled().subList(0, 3))
                }
            correctIndex = (0..3).random()
            answers.add(correctIndex, correctAnswer)
        } else {
            throw ExceptionInInitializerError("Answers: few fake answers for init")
        }
    }

    fun answer(option: Option?) {
        _answerOption = option
    }

    fun getAnswerText(option: Option): String {
        return answers[option.value]
    }

    fun getCurrentAnswer(): Option? {
        answerIndex?.let {index ->
            return Option.getOption(index)
        }
        return null
    }
}