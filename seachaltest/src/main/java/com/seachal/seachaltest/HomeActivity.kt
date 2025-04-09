package com.seachal.seachaltest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_home.iv_icon1
import kotlinx.android.synthetic.main.activity_home.iv_icon2
import kotlinx.android.synthetic.main.activity_home.iv_icon3
import kotlinx.android.synthetic.main.activity_home.tv_text
import kotlinx.android.synthetic.main.activity_home.view.iv_icon3

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // 跳转三个界面
        iv_icon1.setOnClickListener {
//            上层 View没有遮挡住事件
            Log.e("HomeActivity", "iv_icon1")
        }

        iv_icon2.setOnClickListener {
//                 上层 View没有遮挡住事件
            Log.e("HomeActivity", "iv_icon2")

        }
//             tv_text

        tv_text.setOnClickListener {
             Log.e("HomeActivity", "tv_text")

        }

        iv_icon3.setOnClickListener {
            Log.e("HomeActivity", "iv_icon3")
        }

    }
}