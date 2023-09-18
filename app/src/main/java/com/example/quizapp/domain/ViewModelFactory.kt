package com.example.quizapp.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.model.TopicRepository

class ViewModelFactory(private val repository: TopicRepository):
    ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == TopicSelectionViewModel::class.java) {
            @Suppress("UNCHECKED_CAST")
            return TopicSelectionViewModel(repository) as T
        }
        return super.create(modelClass)
    }
}