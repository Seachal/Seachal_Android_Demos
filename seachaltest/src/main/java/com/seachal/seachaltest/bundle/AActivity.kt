package com.seachal.seachaltest.bundle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import com.zhangyue.we.x2c.X2C.setContentView

/**
 **
 * *
 * Project_Name:Seachal_Android_Demos
 * @Description: TODO
 * @author zhangxc
 * @date 2024/7/18 11:05
 * @Version：1.0
 */
class AActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        // 模拟用户点击事件，启动 B Activity
        val button = findViewById<Button>(R.id.start_button)
        button.setOnClickListener {
            val intent = Intent(this, BActivity::class.java)
            intent.putExtra("data_key", "Hello from MainActivity!")
            startActivity(intent)
        }
    }
}