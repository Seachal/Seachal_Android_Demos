package com.seachal.seachaltest.webview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PerformanceOptimizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).apply {
            text = "性能优化示例\n\n功能开发中，敬请期待！"
            textSize = 16f
            setPadding(32, 32, 32, 32)
        }
        setContentView(textView)
        title = "性能优化"
    }
} 