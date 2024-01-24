package com.seachal.seachaltest.Activity

import android.app.ActivityManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.seachal.seachaltest.BaseApp
import com.seachal.seachaltest.R
import com.seachal.seachaltest.utils.ActivityUtils.allActivitys

/**
 * @Author zhangxc
 * @Description //TODO
 * @Date 15:11 2023/5/30
 *
 * @return * @return null
 *
 * 获取栈内所有的 TimerActivity。
 *
 * [获取栈中所有activity的方法 - 简书](https://www.jianshu.com/p/3f74a06a71a1)
 *
 * 总结，
 * 通过 registerActivityLifecycleCallbacks  获取到的生命周期没有问题。
 * 通过反射获取到的栈的顺序是有问题的。
 */
class StackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack)
        val textView = findViewById<TextView>(R.id.textView)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView3 = findViewById<TextView>(R.id.textView3)
        textView.setOnClickListener { //获取activity任务栈
            val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            //参数1是指最大任务栈数，一般APP也就只有一个任务栈
            val runningTaskInfo = activityManager.getRunningTasks(1)[0]
            textView.text = runningTaskInfo.toString()
            LogUtils.e("runningTaskInfo", runningTaskInfo.toString())
        }
        textView2.setOnClickListener {
            val stack = BaseApp.stack
            LogUtils.e("runningTaskInfo",  stack[stack.size-2 ].toString())
            textView2.text = stack.toString()
        }
        textView3.setOnClickListener {
            val list = allActivitys
            textView3.text = list.toString()
            LogUtils.e("runningTaskInfo", list.toString())
        }
    }
}