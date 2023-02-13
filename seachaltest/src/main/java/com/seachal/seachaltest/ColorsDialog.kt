package com.seachal.seachaltest

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Gravity
import com.seachal.seachaltest.R
import android.view.WindowManager
import android.view.MotionEvent
import android.view.View

class ColorsDialog(  context: Context) : Dialog(context), View.OnClickListener {
    private val isSize = false
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.colors_dialog)
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
        window.setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH)
        window.attributes.windowAnimations  R.style.train_search_filter_anim


    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (MotionEvent.ACTION_OUTSIDE == event.action) {
            dismiss()
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onClick(v: View) {}
}