package com.seachal.seachaltest

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewGroupAndChildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_group_and_child)
        val textViewOut1: TextView = findViewById<TextView>(R.id.textView_out1)
        val textViewOut2: TextView = findViewById<TextView>(R.id.textView_out2)
        val textViewOut3: TextView = findViewById<TextView>(R.id.textView_out3)
        val viewGroup: ViewGroup = findViewById(R.id.linearLayout_parent)
        val textView: TextView = findViewById(R.id.textView_child)

// 初始状态：ViewGroup 和 TextView 都是显示的
        viewGroup.visibility = View.VISIBLE
        textView.visibility = View.VISIBLE

        textViewOut1.setOnClickListener {
            textView.visibility = View.GONE
        }

        textViewOut2.setOnClickListener {
            // 设置 ViewGroup 隐藏
            viewGroup.visibility = View.GONE
        }

//        父布局隐藏时， 会带着子布局一块隐藏，
//        父布局显示时， 不会影响子布局的显示与隐藏值，  如果子布局之前是显示，之后任然显示，如果子布局之前是隐藏，那么子布局不会显示
        textViewOut3.setOnClickListener {
            // 再次设置 ViewGroup 显示
            viewGroup.visibility = View.VISIBLE
        }
    }
}
