package com.example.quizapp

import android.app.Application
import com.example.quizapp.model.TopicRepository
import com.example.quizapp.utils.UtilsEn
import com.example.quizapp.utils.UtilsRu
import com.example.quizapp.utils.UtilsRu.langKeyRu
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class QuizApp: Application()