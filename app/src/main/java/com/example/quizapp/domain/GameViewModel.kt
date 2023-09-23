package com.example.quizapp.domain


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.data.Answers
import com.example.quizapp.data.GameQuestion
import com.example.quizapp.data.Topic
import com.example.quizapp.model.TopicRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class GameViewModel @AssistedInject constructor(
    repository: TopicRepository,
    @Assisted topicId: Long)
    : ViewModel() {

    private val topic = repository.getById(topicId)
    private val allQuestions = createGameQuestionList(topic)
    private val remainingQuestions = mutableListOf<Pair<GameQuestion, Int>>()
        .apply {
            allQuestions.forEachIndexed { index, question ->
                add(Pair(question, index))
            }
        }
    private val answers = List(allQuestions.size) {""}
    private val _currentQuestion = MutableLiveData<GameQuestion?>(
        if (remainingQuestions.isNotEmpty()) {
            remainingQuestions.first().first
        } else {
            null
        }
    )
    private var _currentQuestionIndex =
        if (currentQuestion.value != null) {
            0
        } else {
            -1
        }


    private val _isGameEnded = MutableLiveData(false)

    val numberOfQuestions = allQuestions.size
    val currentQuestion: LiveData<GameQuestion?> get() = _currentQuestion
    val currentQuestionIndex: Int get() = _currentQuestionIndex
    var baseTime = 0L
    var withoutAnimation = false
    val isGameEnded: LiveData<Boolean> get() = _isGameEnded

    fun toNextQuestion() {
        if (remainingQuestions.size > 1) {
            withoutAnimation = true
            val iterator = remainingQuestions.listIterator(
                remainingQuestions.indexOfFirst { pair ->
                    pair.second == currentQuestionIndex
                }
            )
            iterator.next()
            if (iterator.hasNext()) {
                val nextPair = iterator.next()
                _currentQuestionIndex = nextPair.second
                _currentQuestion.value = nextPair.first
            } else {
                val nextPair = remainingQuestions.first()
                _currentQuestionIndex = nextPair.second
                _currentQuestion.value = nextPair.first
            }
        }
    }

    fun toPreviousQuestion() {
        if (remainingQuestions.size > 1) {
            withoutAnimation = true
            val iterator = remainingQuestions.listIterator(
                remainingQuestions.indexOfFirst { pair ->
                    pair.second == currentQuestionIndex
                }
            )

            if (iterator.hasPrevious()) {
                val previousPair = iterator.previous()
                _currentQuestionIndex = previousPair.second
                _currentQuestion.value = previousPair.first
            } else {
                val previousPair = remainingQuestions.last()
                _currentQuestionIndex = previousPair.second
                _currentQuestion.value = previousPair.first
            }
        }
    }

    fun confirmAnswer() {
        allQuestions[currentQuestionIndex].answers.answer(
            currentQuestion.value?.answers?.getCurrentAnswer()
        )
        if (numberOfQuestions == 1) {
            endGame()
            return
        }
        val currentIndex = currentQuestionIndex
        toNextQuestion()
        remainingQuestions.removeIf {pair ->
            pair.second == currentIndex
        }
    }

    fun choseOption(option: Answers.Option?) {
        _currentQuestion.value?.answers?.answer(option)
    }

    private fun endGame() {

    }

    private fun createGameQuestionList(topic: Topic?): List<GameQuestion> {
        var newQuestionList = listOf<GameQuestion>()
        topic?.questions?.let {questions ->
            newQuestionList = questions.filter { question ->
                question.fakeAnswers.size >= 2
            }.map{ question ->
                GameQuestion(
                    question.title,
                    question.image,
                    Answers(question.fakeAnswers, question.realAnswer)
                )
            }
        }
        return newQuestionList
    }

    @AssistedFactory
    interface Factory {
        fun create(topicId: Long): GameViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory( assistedFactory: Factory,
            topicId: Long
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(topicId) as T
            }
        }
    }
}
