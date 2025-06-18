package com.seachal.seachaltest.webview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * WebView 示例基础 Activity
 * 
 * 为其他 WebView 示例提供基础功能
 * 遵循阿里巴巴 Android 开发手册规范
 */
abstract class BaseWebViewActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "BaseWebViewActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 显示开发中提示
        showDevelopmentToast()
    }

    /**
     * 显示功能开发中的提示
     */
    private fun showDevelopmentToast() {
        Toast.makeText(this, "该功能正在开发中，敬请期待！", Toast.LENGTH_LONG).show()
    }

    /**
     * 子类需要实现的初始化方法
     */
    protected abstract fun initializeViews()

    /**
     * 子类需要实现的 WebView 设置方法
     */
    protected abstract fun setupWebView()
} 