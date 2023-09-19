package com.example.quizapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.data.Topic
import com.example.quizapp.model.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicSelectionViewModel @Inject constructor(
    private val repository: TopicRepository
): ViewModel() {
    private val _topics: MutableLiveData<List<Topic>> = MutableLiveData(repository.getAll())
    val topics: LiveData<List<Topic>> get() = _topics
}