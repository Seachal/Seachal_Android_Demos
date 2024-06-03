package com.seachal.seachaltest.viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.seachal.seachaltest.R

class Viewpager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager2)


        // 检查是否已经有Fragment实例，如果没有则添加
            val myFragment = TrainHomeLiveMainFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container1, myFragment)
                .commit()

    }
}