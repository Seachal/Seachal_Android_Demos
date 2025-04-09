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
//           为什么在设置count == 10时，并没有成功取消 timer？
            if (count > 10){
                uploadLearnTimer?.cancel()
                uploadLearnTask?.cancel()
//                 uploadLearnTimer = null 是为了在uploadLearnTimer取消之后，避免被再次调用， 出现异常的情况。
                uploadLearnTimer = null
                uploadLearnTask = null
                LogUtils.d("定时器取消了")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        tv_timer.text = "定时器:在启动第 10次，在任务里取消 timer"
        tv_timer.setOnClickListener {
            uploadLearnTimer = Timer()
            uploadLearnTask = UploadLearnTask()
            uploadLearnTimer?.schedule(uploadLearnTask, 1000, 1000)
        }
    }

}