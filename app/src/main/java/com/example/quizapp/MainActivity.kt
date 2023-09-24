package com.example.quizapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.quizapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var onGameFragment = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (onGameFragment) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.you_shure)
                .setPositiveButton(R.string.cancel){ _, _ -> }
                .setNegativeButton(R.string.leave) { _, _ ->
                    viewModelStore.clear()
                    super.onBackPressed()
                }
                .create()
                .show()
        } else {
            super.onBackPressed()
        }
    }
}