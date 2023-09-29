package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.model.TopicRepository
import com.example.quizapp.views.CustomAlertDialog
import com.example.quizapp.utils.UtilsEn
import com.example.quizapp.utils.UtilsRu
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var database: TopicRepository
    @Inject
    lateinit var coroutineScope: CoroutineScope
    var onGameFragment = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineScope.launch {
            checkDatabase()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (onGameFragment) {
            CustomAlertDialog(this) {
                viewModelStore.clear()
                super.onBackPressed()
            }.show()
        } else {
            super.onBackPressed()
        }
    }

    private suspend fun checkDatabase(){
        val isLocaleRu = applicationContext
            .resources
            .configuration
            .locales
            .get(0)
            .language == UtilsRu.langKeyRu
        database.getAll().collect {
            if (it.isEmpty()) {
                if (isLocaleRu) {
                    UtilsRu.dataset
                } else {
                    UtilsEn.dataset
                }.forEach { topic ->
                    database.add(topic)
                }
            }
        }
    }
}