package com.example.quizapp.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Option(val value: Int): Parcelable {
    A(0), B(1), C(2), D(3);
    companion object {
        fun getOption(index: Int): Option? = when (index) {
            0 -> A
            1 -> B
            2 -> C
            3 -> D
            else -> null
        }
    }
}