package com.seachal.seachaltest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.activity_timer.tv_timer
import java.util.Timer
import java.util.TimerTask

class TimerActivity : AppCompatActivity() {

    //   上传定时器
    private var uploadLearnTimer: Timer? = null
    private var uploadLearnTask: UploadLearnTask? = null
    private var count = 0

    inner class UploadLearnTask : TimerTask() {
        override fun run() {
            Log.d("TimerActivity", "定时器")
            count = count + 1
            tv_timer.text = "定时器启动了${count}次"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        tv_timer.text = "定时器"
        tv_timer.setOnClickListener {
            uploadLearnTimer = Timer()
            uploadLearnTask = UploadLearnTask()
            uploadLearnTimer?.schedule(uploadLearnTask, 10000, 1000)
        }
    }

}