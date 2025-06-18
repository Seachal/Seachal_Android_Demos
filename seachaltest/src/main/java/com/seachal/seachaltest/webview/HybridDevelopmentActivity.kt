package com.seachal.seachaltest.webview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HybridDevelopmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).apply {
            text = "混合开发示例\n\n功能开发中，敬请期待！"
            textSize = 16f
            setPadding(32, 32, 32, 32)
        }
        setContentView(textView)
        title = "混合开发"
    }
} 