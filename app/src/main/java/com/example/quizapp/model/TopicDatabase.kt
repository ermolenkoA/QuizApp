package com.example.quizapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quizapp.data.Topic

@Database(entities = [Topic::class], version = 1)
@TypeConverters(Converters::class)
abstract class TopicDatabase: RoomDatabase() {
    abstract fun topicDao(): TopicDao
}