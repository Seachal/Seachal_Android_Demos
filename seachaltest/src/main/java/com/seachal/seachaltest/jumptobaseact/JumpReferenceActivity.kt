package com.seachal.seachaltest.jumptobaseact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.Activity.FourthActivity
import com.seachal.seachaltest.R

class JumpReferenceActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jump)
        val tv_reslut = findViewById<TextView>(R.id.tv_one)
        tv_reslut.setOnClickListener {
            BaseJumpBaseReferenceActivity.startMeForResult(this@JumpReferenceActivity,true)
        }
        val tv_two = findViewById<TextView>(R.id.tv_two)
        tv_two.setOnClickListener {
            BaseJumpBaseReferenceActivity.startMeForResult(this@JumpReferenceActivity,false)
        }
    }
}