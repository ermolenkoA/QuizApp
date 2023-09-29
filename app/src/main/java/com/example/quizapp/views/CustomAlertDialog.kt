package com.example.quizapp.views

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import com.example.quizapp.databinding.AlertDialogBinding

class CustomAlertDialog(
    context: Context,
    private val leaveButtonCallback: () -> Unit
): Dialog(context) {
    private lateinit var binding: AlertDialogBinding
    private var isDown = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = AlertDialogBinding.inflate(layoutInflater)
        with(binding){
            cancelButton.apply {
                setOnClickListener {
                    dismiss()
                }
                setOnTouchListener { v, event ->
                    crateOnTouchAnimation(v, event)
                    false
                }
            }
            leaveButton.apply {
                setOnClickListener {
                    dismiss()
                    leaveButtonCallback()
                }
                setOnTouchListener { v, event ->
                    crateOnTouchAnimation(v, event)
                    false
                }
            }
        }
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun crateOnTouchAnimation(view: View, event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                isDown = false
            }
            MotionEvent.ACTION_DOWN -> {
                isDown = true
                startFocusedAnimation(view)
            }
            MotionEvent.ACTION_UP -> {
                if(isDown) {
                    view.performClick()
                    isDown = false
                }
                startUnfocusedAnimation(view)
            }
        }
    }


    private fun startFocusedAnimation(view: View) {
        ObjectAnimator.ofFloat(
            view,
            "alpha",
            1f,
            0.5f
        ).apply {
            duration = 100
            start()
        }
    }

    private fun startUnfocusedAnimation(view: View) {
        ObjectAnimator.ofFloat(
            view,
            "alpha",
            0.5f,
            1f
        ).apply {
            duration = 100
            start()
        }
    }

}