package com.example.quizapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.Topic
import com.example.quizapp.model.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicSelectionViewModel @Inject constructor(
    _repository: TopicRepository
): ViewModel() {
    private val repository = _repository
    private val _topics: MutableLiveData<List<Topic>> = MutableLiveData()
    val topics: LiveData<List<Topic>> get() = _topics

    fun getAll() {
        viewModelScope.launch {
            repository.getAll().collect { newList ->
                _topics.value = newList
            }
        }
    }
}