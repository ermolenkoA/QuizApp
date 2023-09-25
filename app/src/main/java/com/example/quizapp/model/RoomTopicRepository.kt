package com.example.quizapp.model

import com.example.quizapp.data.Topic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RoomTopicRepository(
    private val dao: TopicDao,
    private val coroutineScope: CoroutineScope
): TopicRepository {
    override fun add(vararg topic: Topic) {
        coroutineScope.launch {
            dao.insertTopic(*topic)
        }
    }

    override suspend fun getById(id: Long): Topic = dao.findByID(id)


    override fun remove(topic: Topic) {
        coroutineScope.launch {
            dao.delete(topic)
        }
    }

    override fun clear() {
        coroutineScope.launch{
            dao.clear()
        }
    }

    override suspend fun getAll() = dao.getAll()

}