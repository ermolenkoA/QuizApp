package com.example.quizapp.domain


import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.data.Answers
import com.example.quizapp.data.GameQuestion
import com.example.quizapp.data.Option
import com.example.quizapp.data.Topic
import com.example.quizapp.model.TopicRepository
import com.example.quizapp.utils.Keys.GAME_QUESTION_KEY
import com.example.quizapp.utils.Keys.SPENT_TIME_KEY
import com.example.quizapp.utils.Keys.TOPIC_ID_KEY
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GameViewModel @AssistedInject constructor(
    repository: TopicRepository,
    coroutineScope: CoroutineScope,
    @Assisted gameQuestions: List<GameQuestion>?,
    @Assisted private val topicId: Long?,
    ) : ViewModel() {

    private val _isLoading = MutableLiveData(false)

    private var allQuestions = if(gameQuestions == null) {
        loadTopic(repository, coroutineScope)
        listOf()
    } else {
        gameQuestions
    }

    private val remainingQuestions = mutableListOf<Pair<GameQuestion, Int>>().apply {
        allQuestions.forEachIndexed { index, question ->
            add(Pair(question, index))
        }
    }

    private val _currentQuestion = MutableLiveData<GameQuestion?>(
        getInitialQuestion()
    )

    private var _currentQuestionIndex = 0

    val numberOfQuestions: Int get() = allQuestions.size
    val currentQuestion: LiveData<GameQuestion?> get() = _currentQuestion
    val currentQuestionIndex: Int get() = _currentQuestionIndex
    var baseTime = 0L
    val isGameEnded = gameQuestions != null
    val isLoading: LiveData<Boolean> get() = _isLoading
    val withAnimation = MutableLiveData(true)
    fun toNextQuestion() {
        if (remainingQuestions.size > 1) {
            withAnimation.value = false
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
            withAnimation.value = false
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
            currentQuestion.value?.answers?.answerOption
        )
        if (remainingQuestions.size == 1) {
            _currentQuestion.value = null
            return
        }
        val currentIndex = currentQuestionIndex
        toNextQuestion()
        remainingQuestions.removeIf {pair ->
            pair.second == currentIndex
        }
    }

    fun choseOption(option: Option?) {
        _currentQuestion.value?.answers?.answer(option)
    }

    fun createEndGameBundle(finalTime: Long): Bundle =
        Bundle().apply {
            putParcelableArrayList(GAME_QUESTION_KEY, allQuestions as ArrayList)
            putLong(SPENT_TIME_KEY, finalTime - baseTime)
            putLong(TOPIC_ID_KEY, topicId!!)
        }

    private fun loadTopic(repository: TopicRepository,
                          coroutineScope: CoroutineScope) {
        _isLoading.value = true
        coroutineScope.launch {
            allQuestions = createGameQuestionList(repository.getById(topicId!!))
            allQuestions.forEachIndexed { index, question ->
                remainingQuestions.add(Pair(question, index))
            }
            _currentQuestion.postValue(getInitialQuestion())
            _currentQuestionIndex = 0
            _isLoading.postValue(false)
        }
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
                    Answers.new(question.fakeAnswers, question.realAnswer)
                )
            }
        }
        return newQuestionList.shuffled()
    }

    private fun getInitialQuestion(): GameQuestion? =
        if (remainingQuestions.isNotEmpty()) {
            remainingQuestions.first().first
        } else {
            null
        }

    @AssistedFactory
    interface Factory {
        fun create(
            gameQuestions: List<GameQuestion>?,
            topicId: Long?): GameViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            gameQuestions: List<GameQuestion>?,
            topicId: Long?
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(gameQuestions, topicId) as T
            }
        }
    }
}
