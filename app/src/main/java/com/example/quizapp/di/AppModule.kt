package com.example.quizapp.di

import com.example.quizapp.data.Topic
import com.example.quizapp.model.TopicRepository
import com.example.quizapp.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTopicRepository(): TopicRepository {
        return object: TopicRepository {

            private var dataset = Utils.dataset

            override fun add(topic: Topic) {
                dataset.add(topic)
            }

            override fun getById(id: Long): Topic? {
                return dataset.find { it.id == id }
            }

            override fun update(topic: Topic) {
                val index = dataset.indexOfFirst { it.id == topic.id }
                if (index != -1) {
                    dataset[index] = topic
                }
            }

            override fun remove(topic: Topic) {
                dataset.removeIf { topic.id == it.id }
            }

            override fun clear() {
                dataset.clear()
            }

            override fun getAll(): List<Topic> = dataset
        }
    }
}