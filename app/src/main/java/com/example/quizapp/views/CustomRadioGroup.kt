package com.example.quizapp.views

import android.content.Context
import android.util.AttributeSet
import android.widget.RadioGroup

class CustomRadioGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RadioGroup(context, attrs) {
    var changeWithAnimation = true
}