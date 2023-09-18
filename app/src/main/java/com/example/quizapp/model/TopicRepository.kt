package com.example.quizapp.model

import com.example.quizapp.data.Topic

interface TopicRepository {
    fun add(topic: Topic)
    fun getById(id: Long): Topic?
    fun update(topic: Topic)
    fun remove(topic: Topic)
    fun clear()
    fun getAll(): List<Topic>
}