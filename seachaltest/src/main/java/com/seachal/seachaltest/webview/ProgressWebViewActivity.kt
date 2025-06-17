package com.seachal.seachaltest.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R

/**
 * 带进度条的 WebView 示例
 * 
 * 功能包括：
 * 1. 显示页面加载进度条
 * 2. 自定义进度条样式和状态文本
 * 3. 处理页面标题变化
 * 4. 显示加载错误状态
 * 5. 多个测试网址切换功能
 * 6. 自定义错误页面
 * 
 * @author Seachal
 * @since 2025-01-27
 */
class ProgressWebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var statusText: TextView
    private lateinit var titleText: TextView
    private lateinit var urlSpinner: Spinner
    private lateinit var btnLoad: Button
    private lateinit var btnStop: Button
    private lateinit var loadingLayout: LinearLayout
    
    // 测试网址列表
    private val testUrls = arrayOf(
        "https://www.baidu.com" to "百度首页",
        "https://www.github.com" to "GitHub",
        "https://www.stackoverflow.com" to "Stack Overflow",
        "https://developer.android.com" to "Android 开发者",
        "https://www.google.com" to "Google",
        "https://httpbin.org/delay/3" to "延迟加载测试",
        "https://httpbin.org/status/404" to "404 错误测试",
        "https://invalid-url-test.com" to "无效URL测试"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_webview)
        
        title = "进度条 WebView 示例"
        
        initViews()
        setupSpinner()
        setupWebView()
        setupButtons()
        
        // 加载默认页面
        loadUrl(testUrls[0].first)
    }

    /**
     * 初始化视图组件
     */
    private fun initViews() {
        webView = findViewById(R.id.webview)
        progressBar = findViewById(R.id.progress_bar)
        progressText = findViewById(R.id.tv_progress)
        statusText = findViewById(R.id.tv_status)
        titleText = findViewById(R.id.tv_title)
        urlSpinner = findViewById(R.id.spinner_urls)
        btnLoad = findViewById(R.id.btn_load)
        btnStop = findViewById(R.id.btn_stop)
        loadingLayout = findViewById(R.id.loading_layout)
    }

    /**
     * 设置 URL 选择器
     */
    private fun setupSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            testUrls.map { it.second }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        urlSpinner.adapter = adapter
    }

    /**
     * 设置 WebView 配置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                cacheMode = WebSettings.LOAD_DEFAULT
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                useWideViewPort = true
                loadWithOverviewMode = true
                
                // 设置 User-Agent
                userAgentString = "$userAgentString SeachalProgressDemo/1.0"
            }
            
            // 设置 WebChromeClient 处理进度
            webChromeClient = CustomWebChromeClient()
            
            // 设置 WebViewClient 处理页面事件
            webViewClient = CustomWebViewClient()
        }
    }

    /**
     * 设置按钮事件
     */
    private fun setupButtons() {
        btnLoad.setOnClickListener {
            val selectedIndex = urlSpinner.selectedItemPosition
            val selectedUrl = testUrls[selectedIndex].first
            loadUrl(selectedUrl)
        }
        
        btnStop.setOnClickListener {
            webView.stopLoading()
            updateStatus("加载已停止", false)
            hideProgress()
        }
    }

    /**
     * 加载指定 URL
     */
    private fun loadUrl(url: String) {
        updateStatus("准备加载: $url", true)
        showProgress()
        webView.loadUrl(url)
    }

    /**
     * 显示进度条
     */
    private fun showProgress() {
        loadingLayout.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE
        progressText.visibility = View.VISIBLE
        btnStop.isEnabled = true
        btnLoad.isEnabled = false
    }

    /**
     * 隐藏进度条
     */
    private fun hideProgress() {
        loadingLayout.visibility = View.GONE
        progressBar.visibility = View.GONE
        progressText.visibility = View.GONE
        btnStop.isEnabled = false
        btnLoad.isEnabled = true
    }

    /**
     * 更新状态信息
     */
    private fun updateStatus(message: String, isLoading: Boolean = false) {
        statusText.text = message
        
        // 根据状态设置不同的颜色
        val colorRes = when {
            isLoading -> android.R.color.holo_blue_dark
            message.contains("完成") -> android.R.color.holo_green_dark
            message.contains("错误") || message.contains("失败") -> android.R.color.holo_red_dark
            else -> android.R.color.darker_gray
        }
        statusText.setTextColor(resources.getColor(colorRes, null))
    }

    /**
     * 更新进度信息
     */
    private fun updateProgress(progress: Int) {
        progressBar.progress = progress
        progressText.text = "加载进度: $progress%"
        
        // 根据进度设置不同的状态文本
        val statusMessage = when {
            progress < 20 -> "正在连接服务器..."
            progress < 40 -> "正在下载页面内容..."
            progress < 60 -> "正在解析页面结构..."
            progress < 80 -> "正在加载资源文件..."
            progress < 95 -> "正在渲染页面..."
            else -> "即将完成加载..."
        }
        
        if (progress < 100) {
            updateStatus(statusMessage, true)
        }
    }

    /**
     * 自定义 WebChromeClient
     */
    private inner class CustomWebChromeClient : WebChromeClient() {
        
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            
            updateProgress(newProgress)
            
            if (newProgress == 100) {
                // 延迟隐藏进度条，让用户看到100%的状态
                android.os.Handler().postDelayed({
                    hideProgress()
                }, 500)
            }
        }
        
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            title?.let {
                titleText.text = "页面标题: $it"
                // 同时更新 Activity 标题
                this@ProgressWebViewActivity.title = "WebView - $it"
            }
        }
        
        override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
            super.onReceivedIcon(view, icon)
            // 可以在这里处理网站图标
            updateStatus("已获取网站图标", false)
        }
        
        override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            // 处理 JavaScript 警告框
            android.app.AlertDialog.Builder(this@ProgressWebViewActivity)
                .setTitle("页面提示")
                .setMessage(message)
                .setPositiveButton("确定") { _, _ -> result?.confirm() }
                .setOnCancelListener { result?.cancel() }
                .show()
            return true
        }
        
        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            consoleMessage?.let {
                // 显示控制台消息（用于调试）
                val logLevel = when (it.messageLevel()) {
                    ConsoleMessage.MessageLevel.ERROR -> "错误"
                    ConsoleMessage.MessageLevel.WARNING -> "警告"
                    ConsoleMessage.MessageLevel.DEBUG -> "调试"
                    else -> "信息"
                }
                updateStatus("控制台[$logLevel]: ${it.message()}", false)
            }
            return true
        }
    }

    /**
     * 自定义 WebViewClient
     */
    private inner class CustomWebViewClient : WebViewClient() {
        
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            showProgress()
            updateStatus("开始加载页面: $url", true)
            titleText.text = "页面标题: 加载中..."
        }
        
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            updateStatus("页面加载完成: $url", false)
            
            // 注入 JavaScript 获取页面信息
            val jsCode = """
                (function() {
                    return {
                        title: document.title,
                        url: window.location.href,
                        loadTime: performance.timing.loadEventEnd - performance.timing.navigationStart,
                        resources: document.querySelectorAll('img, script, link[rel="stylesheet"]').length
                    };
                })();
            """.trimIndent()
            
            view?.evaluateJavascript(jsCode) { result ->
                try {
                    // 解析返回的 JSON 信息
                    updateStatus("页面加载完成，正在分析页面信息...", false)
                } catch (e: Exception) {
                    updateStatus("页面加载完成", false)
                }
            }
        }
        
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            
            hideProgress()
            val errorMessage = "加载错误: ${error?.description}"
            updateStatus(errorMessage, false)
            
            // 加载自定义错误页面
            val errorHtml = createErrorPage(
                error?.description?.toString() ?: "未知错误",
                request?.url?.toString() ?: "未知URL",
                error?.errorCode ?: -1
            )
            
            view?.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null)
        }
        
        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
            
            val statusCode = errorResponse?.statusCode ?: 0
            val reasonPhrase = errorResponse?.reasonPhrase ?: "未知错误"
            
            updateStatus("HTTP 错误: $statusCode $reasonPhrase", false)
        }
        
        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            super.onReceivedSslError(view, handler, error)
            
            hideProgress()
            updateStatus("SSL 证书错误: ${error?.toString()}", false)
            
            // 显示 SSL 错误对话框
            android.app.AlertDialog.Builder(this@ProgressWebViewActivity)
                .setTitle("SSL 证书错误")
                .setMessage("此网站的安全证书不受信任，是否继续访问？\n\n错误详情：${error?.toString()}")
                .setPositiveButton("继续") { _, _ -> handler?.proceed() }
                .setNegativeButton("取消") { _, _ -> handler?.cancel() }
                .show()
        }
        
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url = request?.url?.toString() ?: return false
            
            // 记录 URL 跳转
            updateStatus("准备跳转到: $url", true)
            
            // 在当前 WebView 中加载
            return false
        }
    }

    /**
     * 创建错误页面 HTML
     */
    private fun createErrorPage(errorDescription: String, failingUrl: String, errorCode: Int): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>页面加载失败</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        text-align: center;
                        padding: 50px 20px;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        color: white;
                        margin: 0;
                        min-height: 100vh;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                    }
                    .error-container {
                        background: rgba(255,255,255,0.1);
                        padding: 40px;
                        border-radius: 15px;
                        backdrop-filter: blur(10px);
                        border: 1px solid rgba(255,255,255,0.2);
                        max-width: 500px;
                    }
                    .error-icon {
                        font-size: 80px;
                        margin-bottom: 20px;
                    }
                    .error-title {
                        font-size: 24px;
                        margin-bottom: 20px;
                        color: #FFE082;
                    }
                    .error-description {
                        font-size: 16px;
                        margin-bottom: 30px;
                        line-height: 1.5;
                    }
                    .error-details {
                        background: rgba(0,0,0,0.3);
                        padding: 20px;
                        border-radius: 10px;
                        margin: 20px 0;
                        text-align: left;
                        font-family: 'Courier New', monospace;
                        font-size: 14px;
                    }
                    .btn {
                        background: linear-gradient(45deg, #4CAF50, #45a049);
                        color: white;
                        padding: 12px 25px;
                        border: none;
                        border-radius: 25px;
                        cursor: pointer;
                        margin: 10px;
                        font-size: 16px;
                        transition: all 0.3s ease;
                        text-decoration: none;
                        display: inline-block;
                    }
                    .btn:hover {
                        transform: translateY(-2px);
                        box-shadow: 0 6px 20px rgba(0,0,0,0.3);
                    }
                    .btn-secondary {
                        background: linear-gradient(45deg, #2196F3, #1976D2);
                    }
                </style>
            </head>
            <body>
                <div class="error-container">
                    <div class="error-icon">⚠️</div>
                    <h1 class="error-title">页面加载失败</h1>
                    <div class="error-description">
                        很抱歉，无法加载请求的页面。请检查网络连接或稍后重试。
                    </div>
                    
                    <div class="error-details">
                        <strong>错误详情：</strong><br>
                        错误代码: $errorCode<br>
                        错误描述: $errorDescription<br>
                        请求URL: $failingUrl<br>
                        时间: ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date())}
                    </div>
                    
                    <button class="btn" onclick="location.reload()">🔄 重新加载</button>
                    <button class="btn btn-secondary" onclick="history.back()">⬅️ 返回上页</button>
                </div>
            </body>
            </html>
        """.trimIndent()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }
} 