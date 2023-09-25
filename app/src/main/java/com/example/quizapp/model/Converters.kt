package com.example.quizapp.model

import androidx.room.TypeConverter
import com.example.quizapp.data.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromStringToQuestionList(value: String): List<Question>  {
        val listType: Type = object : TypeToken<List<Question>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Question>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}