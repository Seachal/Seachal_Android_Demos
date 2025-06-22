package com.seachal.seachaltest.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R

/**
 * HTML文件测试示例 - 验证JavaScript调用Android方法返回值问题
 * 
 * 测试目标：
 * 1. 从assets加载HTML文件
 * 2. 验证JS能否获取Android方法的返回值
 * 3. 测试同步返回值 vs 异步回调的区别
 * 
 * 关于返回值的真相：
 * - ✅ JavaScript **可以**获取Android方法的返回值
 * - ✅ @JavascriptInterface方法的返回值会自动转换为JS类型
 * - ⚠️ 但有一些限制和最佳实践需要了解
 * 
 * @author Seachal
 * @since 2025-01-27
 */
class HtmlFileTestActivity : AppCompatActivity() {
    
    companion object {
        private const val TAG = "HtmlFileTestActivity"
    }
    
    private lateinit var webView: WebView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html_file_test)
        
        setupActionBar()
        initViews()
        setupWebView()
        loadHtmlFromAssets()
    }
    
    /**
     * 设置标题栏
     */
    private fun setupActionBar() {
        supportActionBar?.apply {
            title = "HTML文件测试 - JS返回值验证"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
    
    /**
     * 初始化视图
     */
    private fun initViews() {
        webView = findViewById(R.id.webview_html_test)
    }
    
    /**
     * 设置WebView配置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                allowFileAccess = true
                allowContentAccess = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                useWideViewPort = true
                loadWithOverviewMode = true
                
                // 允许加载本地文件
                allowFileAccessFromFileURLs = true
                allowUniversalAccessFromFileURLs = true
            }
            
            // 添加JavaScript接口 - 专门用于测试返回值
            addJavascriptInterface(ReturnValueTestInterface(this@HtmlFileTestActivity), "ReturnTestAPI")
            
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Log.d(TAG, "HTML文件加载完成: $url")
                    
                    // 页面加载完成后，主动调用JS函数测试双向通信
                    view?.evaluateJavascript("window.onAndroidReady && window.onAndroidReady();") { result ->
                        Log.d(TAG, "调用JS onAndroidReady结果: $result")
                    }
                }
                
                override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                    super.onReceivedError(view, errorCode, description, failingUrl)
                    Log.e(TAG, "页面加载错误: $description, URL: $failingUrl")
                }
            }
            
            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    consoleMessage?.let { msg ->
                        val level = when (msg.messageLevel()) {
                            ConsoleMessage.MessageLevel.ERROR -> "ERROR"
                            ConsoleMessage.MessageLevel.WARNING -> "WARN"
                            ConsoleMessage.MessageLevel.DEBUG -> "DEBUG"
                            else -> "INFO"
                        }
                        Log.d(TAG, "JS控制台[$level]: ${msg.message()} (${msg.sourceId()}:${msg.lineNumber()})")
                    }
                    return true
                }
                
                override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                    Log.d(TAG, "JS Alert: $message")
                    result?.confirm()
                    return true
                }
            }
        }
    }
    
    /**
     * 从assets加载HTML文件
     */
    private fun loadHtmlFromAssets() {
        try {
            // 加载assets中的HTML文件
            webView.loadUrl("file:///android_asset/return_value_test.html")
            Log.d(TAG, "开始加载assets中的HTML文件")
        } catch (e: Exception) {
            Log.e(TAG, "加载HTML文件失败: ${e.message}", e)
            Toast.makeText(this, "加载HTML文件失败: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
    /**
     * 返回值测试专用JavaScript接口
     * 
     * 重要说明：
     * - 所有@JavascriptInterface方法的返回值都可以被JavaScript获取
     * - 基本类型（int, boolean, String等）会自动转换
     * - 复杂对象需要转换为JSON字符串
     * - 方法调用是同步的，会阻塞JS线程
     */
    inner class ReturnValueTestInterface(private val context: Context) {
        
        /**
         * 测试1: 返回布尔值
         * @return 布尔值测试
         */
        @JavascriptInterface
        fun testBooleanReturn(): Boolean {
            val result = true
            Log.d(TAG, "[测试1] JS调用 testBooleanReturn(), 返回: $result")
            return result
        }
        
        /**
         * 测试2: 返回整数
         * @return 整数测试  
         */
        @JavascriptInterface
        fun testIntReturn(): Int {
            val result = 42
            Log.d(TAG, "[测试2] JS调用 testIntReturn(), 返回: $result")
            return result
        }
        
        /**
         * 测试3: 返回字符串
         * @return 字符串测试
         */
        @JavascriptInterface
        fun testStringReturn(): String {
            val result = "Hello from Android! 🚀"
            Log.d(TAG, "[测试3] JS调用 testStringReturn(), 返回: $result")
            return result
        }
        
        /**
         * 测试4: 返回JSON字符串（模拟复杂对象）
         * @return JSON格式的字符串
         */
        @JavascriptInterface
        fun testJsonReturn(): String {
            val result = """
                {
                    "success": true,
                    "message": "这是从Android返回的JSON数据",
                    "data": {
                        "timestamp": ${System.currentTimeMillis()},
                        "device": "Android Device",
                        "version": "1.0.0"
                    },
                    "list": ["item1", "item2", "item3"]
                }
            """.trimIndent()
            Log.d(TAG, "[测试4] JS调用 testJsonReturn(), 返回JSON")
            return result
        }
        
        /**
         * 测试5: 带参数并返回处理结果
         * @param input 输入参数
         * @return 处理后的结果
         */
        @JavascriptInterface
        fun testParameterAndReturn(input: String): String {
            val result = "Android处理结果: [$input] -> ${input.toUpperCase()} (长度: ${input.length})"
            Log.d(TAG, "[测试5] JS调用 testParameterAndReturn('$input'), 返回: $result")
            return result
        }
        
        /**
         * 测试6: 应用安装检查（实际业务场景）
         * @param packageName 包名
         * @return 是否已安装
         */
        @JavascriptInterface
        fun checkAppInstalled(packageName: String): Boolean {
            val result = try {
                context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
            Log.d(TAG, "[测试6] JS调用 checkAppInstalled('$packageName'), 返回: $result")
            return result
        }
        
        /**
         * 测试7: 获取设备信息
         * @return 设备信息JSON
         */
        @JavascriptInterface
        fun getDeviceInfo(): String {
            val result = """
                {
                    "manufacturer": "${android.os.Build.MANUFACTURER}",
                    "model": "${android.os.Build.MODEL}",
                    "version": "${android.os.Build.VERSION.RELEASE}",
                    "sdk": ${android.os.Build.VERSION.SDK_INT},
                    "timestamp": ${System.currentTimeMillis()}
                }
            """.trimIndent()
            Log.d(TAG, "[测试7] JS调用 getDeviceInfo(), 返回设备信息")
            return result
        }
        
        /**
         * 测试8: 数学计算
         * @param a 第一个数
         * @param b 第二个数  
         * @param operation 运算符
         * @return 计算结果
         */
        @JavascriptInterface
        fun calculate(a: Int, b: Int, operation: String): String {
            val result = when (operation) {
                "+" -> (a + b).toString()
                "-" -> (a - b).toString()
                "*" -> (a * b).toString()
                "/" -> if (b != 0) (a.toDouble() / b).toString() else "Error: Division by zero"
                else -> "Error: Unknown operation"
            }
            Log.d(TAG, "[测试8] JS调用 calculate($a, $b, '$operation'), 返回: $result")
            return result
        }
        
        /**
         * 测试9: 显示Toast（无返回值方法对比）
         * @param message 消息内容
         */
        @JavascriptInterface
        fun showToast(message: String) {
            Log.d(TAG, "[测试9] JS调用 showToast('$message'), 无返回值")
            runOnUiThread {
                Toast.makeText(context, "JS调用: $message", Toast.LENGTH_SHORT).show()
            }
        }
        
        /**
         * 测试10: 模拟耗时操作（同步执行，会阻塞）
         * @param seconds 模拟耗时秒数
         * @return 执行结果
         */
        @JavascriptInterface
        fun simulateTimeConsumingTask(seconds: Int): String {
            Log.d(TAG, "[测试10] JS调用 simulateTimeConsumingTask($seconds), 开始执行...")
            
            val startTime = System.currentTimeMillis()
            
            // 模拟耗时操作（注意：这会阻塞JS线程！）
            Thread.sleep((seconds * 1000).toLong())
            
            val endTime = System.currentTimeMillis()
            val actualTime = endTime - startTime
            
            val result = "耗时操作完成！预期: ${seconds}秒, 实际: ${actualTime}ms"
            Log.d(TAG, "[测试10] 耗时操作完成: $result")
            return result
        }
    }
    
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
} 