package com.example.quizapp.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.quizapp.R

class CustomRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatRadioButton(context, attrs, defStyleAttr) {

    private val appearAnimation: AnimatedVectorDrawableCompat?
    private val disappearAnimation: AnimatedVectorDrawableCompat?
    private val checkedDrawable: Drawable?
    private val uncheckedDrawable: Drawable?

    init {
        appearAnimation = AnimatedVectorDrawableCompat.create(context, R.drawable.rb_appear)
        disappearAnimation = AnimatedVectorDrawableCompat.create(context, R.drawable.rb_disappear)
        checkedDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_checked)
        uncheckedDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_unchecked)
        setButton()
    }


    override fun setChecked(checked: Boolean) {
        if(checked != isChecked) {
            super.setChecked(checked)
            if((parent as? CustomRadioGroup)?.changeWithAnimation == true) {
                setButtonAnimated()
            } else {
                setButton()
            }
            isClickable = true
        }
    }

    private fun setButtonAnimated() {
        if (isChecked) {
            buttonDrawable = appearAnimation
            appearAnimation?.start()
        } else {
            buttonDrawable = disappearAnimation
            disappearAnimation?.start()
        }
    }

    private fun setButton() {
        buttonDrawable = if (isChecked) {
            checkedDrawable
        } else {
            uncheckedDrawable
        }
    }

}