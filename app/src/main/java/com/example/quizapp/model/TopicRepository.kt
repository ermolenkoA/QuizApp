package com.example.quizapp.model

import com.example.quizapp.data.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    fun add(vararg topic: Topic)
    suspend fun getById(id: Long): Topic?
    fun remove(topic: Topic)
    fun clear()
    suspend fun getAll(): Flow<List<Topic>>
}