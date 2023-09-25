package com.example.quizapp.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.quizapp.model.Converters

@Entity(tableName = "topics")
data class Topic(
    @PrimaryKey val id: Long,
    val title: String,
    val image: String,
    val about: String,
    val questions: List<Question>
)