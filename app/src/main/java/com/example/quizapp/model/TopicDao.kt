package com.example.quizapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quizapp.data.Topic
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao {
    @Query("SELECT * FROM topics")
    fun getAll(): Flow<List<Topic>>

    @Query("SELECT * FROM topics WHERE id = :inputID")
    fun findByID(inputID: Long): Topic

    @Insert
    fun insertTopic(vararg topics: Topic)

    @Delete
    fun delete(topic: Topic)

    @Query("DELETE FROM topics")
    fun clear()
}