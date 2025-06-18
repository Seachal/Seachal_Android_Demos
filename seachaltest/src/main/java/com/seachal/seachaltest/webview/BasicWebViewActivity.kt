package com.seachal.seachaltest.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.webkit.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R

/**
 * 基础 WebView 使用示例
 * 
 * 本示例演示了 WebView 的基础功能：
 * 1. WebView 基本设置和配置
 * 2. 网页加载（URL、本地文件）
 * 3. 导航控制（前进、后退、刷新、停止）
 * 4. 页面加载状态监听
 * 5. URL 拦截和处理
 * 6. 错误处理
 * 7. 自定义 User-Agent
 * 8. 缓存策略
 * 
 * 遵循阿里巴巴 Android 开发规范：
 * - 禁用不安全的 JavaScript 接口
 * - 设置安全的 WebView 配置
 * - 正确处理生命周期
 * 
 * @author Seachal
 * @since 2025-01-27
 */
class BasicWebViewActivity : AppCompatActivity() {
    
    companion object {
        private const val TAG = "BasicWebViewActivity"
        
        // 测试网址列表
        private val TEST_URLS = arrayOf(
            "https://www.baidu.com",
            "https://m.taobao.com",
            "https://github.com",
            "https://developer.android.com",
            "file:///android_asset/demo.html"
        )
    }
    
    // UI 组件
    private lateinit var webView: WebView
    private lateinit var etUrl: EditText
    private lateinit var btnGo: Button
    private lateinit var btnBack: Button
    private lateinit var btnForward: Button
    private lateinit var btnRefresh: Button
    private lateinit var btnStop: Button
    private lateinit var tvStatus: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinnerUrls: Spinner
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_webview)
        
        setupActionBar()
        initViews()
        setupWebView()
        setupUrlSpinner()
        setupClickListeners()
        
        // 加载默认页面
        loadUrl(TEST_URLS[0])
    }
    
    /**
     * 设置标题栏
     */
    private fun setupActionBar() {
        supportActionBar?.apply {
            title = "基础 WebView 使用"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
    
    /**
     * 初始化视图组件
     */
    private fun initViews() {
        webView = findViewById(R.id.webview)
        etUrl = findViewById(R.id.et_url)
        btnGo = findViewById(R.id.btn_go)
        btnBack = findViewById(R.id.btn_back)
        btnForward = findViewById(R.id.btn_forward)
        btnRefresh = findViewById(R.id.btn_refresh)
        btnStop = findViewById(R.id.btn_stop)
        tvStatus = findViewById(R.id.tv_status)
        progressBar = findViewById(R.id.progress_bar)
        spinnerUrls = findViewById(R.id.spinner_urls)
    }
    
    /**
     * 设置 WebView 配置
     * 遵循阿里巴巴 Android 开发规范的安全配置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val webSettings = webView.settings
        
        // 基础设置
        webSettings.apply {
            // 启用 JavaScript（谨慎使用，确保安全）
            javaScriptEnabled = true
            
            // 启用 DOM Storage
            domStorageEnabled = true
            
            // 设置缓存策略
            cacheMode = WebSettings.LOAD_DEFAULT
            
            // 启用缓存（注意：setAppCacheEnabled 在 API 33+ 中已废弃）
            // setAppCacheEnabled(true) // 已废弃，使用 cacheMode 替代
            
            // 支持缩放
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            
            // 自适应屏幕
            useWideViewPort = true
            loadWithOverviewMode = true
            
            // 支持多窗口
            setSupportMultipleWindows(false)
            
            // 禁用文件访问（安全考虑）
            allowFileAccess = false
            allowFileAccessFromFileURLs = false
            allowUniversalAccessFromFileURLs = false
            
            // 设置 User-Agent
            userAgentString = "$userAgentString BasicWebViewDemo/1.0"
            
            // 设置文本编码
            defaultTextEncodingName = "UTF-8"
            
            // 设置最小字体大小
            minimumFontSize = 12
        }
        
        // 设置 WebViewClient
        webView.webViewClient = CustomWebViewClient()
        
        // 设置 WebChromeClient（处理 JavaScript 对话框等）
        webView.webChromeClient = CustomWebChromeClient()
    }
    
    /**
     * 设置 URL 下拉选择器
     */
    private fun setupUrlSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            TEST_URLS
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUrls.adapter = adapter
        
        spinnerUrls.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                etUrl.setText(TEST_URLS[position])
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    
    /**
     * 设置点击事件监听器
     */
    private fun setupClickListeners() {
        // 访问按钮
        btnGo.setOnClickListener {
            val url = etUrl.text.toString().trim()
            if (url.isNotEmpty()) {
                loadUrl(url)
            } else {
                Toast.makeText(this, "请输入网址", Toast.LENGTH_SHORT).show()
            }
        }
        
        // 后退按钮
        btnBack.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                Toast.makeText(this, "无法后退", Toast.LENGTH_SHORT).show()
            }
        }
        
        // 前进按钮
        btnForward.setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
            } else {
                Toast.makeText(this, "无法前进", Toast.LENGTH_SHORT).show()
            }
        }
        
        // 刷新按钮
        btnRefresh.setOnClickListener {
            webView.reload()
        }
        
        // 停止按钮
        btnStop.setOnClickListener {
            webView.stopLoading()
            updateStatus("已停止加载")
        }
    }
    
    /**
     * 加载指定 URL
     */
    private fun loadUrl(url: String) {
        try {
            Log.d(TAG, "Loading URL: $url")
            updateStatus("正在加载...")
            webView.loadUrl(url)
            etUrl.setText(url)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading URL: $url", e)
            updateStatus("加载失败: ${e.message}")
        }
    }
    
    /**
     * 更新状态文本
     */
    private fun updateStatus(status: String) {
        tvStatus.text = status
        Log.d(TAG, "Status: $status")
    }
    
    /**
     * 更新导航按钮状态
     */
    private fun updateNavigationButtons() {
        btnBack.isEnabled = webView.canGoBack()
        btnForward.isEnabled = webView.canGoForward()
    }
    
    /**
     * 自定义 WebViewClient
     * 处理页面加载事件和 URL 拦截
     */
    private inner class CustomWebViewClient : WebViewClient() {
        
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url = request?.url?.toString() ?: return false
            
            Log.d(TAG, "shouldOverrideUrlLoading: $url")
            
            // 处理特殊协议
            when {
                // 电话协议
                url.startsWith("tel:") -> {
                    try {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                        startActivity(intent)
                        return true
                    } catch (e: Exception) {
                        Log.e(TAG, "Error handling tel protocol", e)
                    }
                }
                
                // 邮件协议
                url.startsWith("mailto:") -> {
                    try {
                        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(url))
                        startActivity(intent)
                        return true
                    } catch (e: Exception) {
                        Log.e(TAG, "Error handling mailto protocol", e)
                    }
                }
                
                // 限制访问某些域名（示例）
                url.contains("example-blocked-domain.com") -> {
                    showBlockedDomainDialog(url)
                    return true
                }
            }
            
            return false
        }
        
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Log.d(TAG, "onPageStarted: $url")
            
            updateStatus("开始加载: $url")
            progressBar.visibility = View.VISIBLE
            btnStop.isEnabled = true
            updateNavigationButtons()
        }
        
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.d(TAG, "onPageFinished: $url")
            
            updateStatus("加载完成: $url")
            progressBar.visibility = View.GONE
            btnStop.isEnabled = false
            updateNavigationButtons()
            
            // 更新地址栏
            etUrl.setText(url)
        }
        
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            
            val errorCode = error?.errorCode ?: -1
            val description = error?.description?.toString() ?: "未知错误"
            val failingUrl = request?.url?.toString() ?: "未知地址"
            
            Log.e(TAG, "onReceivedError: $errorCode - $description for $failingUrl")
            
            updateStatus("加载错误: $description")
            progressBar.visibility = View.GONE
            
            // 显示错误页面
            showErrorPage(errorCode, description, failingUrl)
        }
        
        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse?
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
            
            val statusCode = errorResponse?.statusCode ?: -1
            val reasonPhrase = errorResponse?.reasonPhrase ?: "未知错误"
            val failingUrl = request?.url?.toString() ?: "未知地址"
            
            Log.e(TAG, "onReceivedHttpError: $statusCode - $reasonPhrase for $failingUrl")
            
            if (request?.isForMainFrame == true) {
                updateStatus("HTTP错误: $statusCode - $reasonPhrase")
            }
        }
    }
    
    /**
     * 自定义 WebChromeClient
     * 处理 JavaScript 对话框和进度更新
     */
    private inner class CustomWebChromeClient : WebChromeClient() {
        
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            
            progressBar.progress = newProgress
            
            if (newProgress < 100) {
                updateStatus("加载中... $newProgress%")
            }
        }
        
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            
            if (!title.isNullOrEmpty()) {
                supportActionBar?.subtitle = title
                Log.d(TAG, "Page title: $title")
            }
        }
        
        override fun onJsAlert(
            view: WebView?,
            url: String?,
            message: String?,
            result: JsResult?
        ): Boolean {
            AlertDialog.Builder(this@BasicWebViewActivity)
                .setTitle("网页提示")
                .setMessage(message)
                .setPositiveButton("确定") { _, _ ->
                    result?.confirm()
                }
                .setOnCancelListener {
                    result?.cancel()
                }
                .show()
            
            return true
        }
        
        override fun onJsConfirm(
            view: WebView?,
            url: String?,
            message: String?,
            result: JsResult?
        ): Boolean {
            AlertDialog.Builder(this@BasicWebViewActivity)
                .setTitle("网页确认")
                .setMessage(message)
                .setPositiveButton("确定") { _, _ ->
                    result?.confirm()
                }
                .setNegativeButton("取消") { _, _ ->
                    result?.cancel()
                }
                .setOnCancelListener {
                    result?.cancel()
                }
                .show()
            
            return true
        }
    }
    
    /**
     * 显示被阻止域名的对话框
     */
    private fun showBlockedDomainDialog(url: String) {
        AlertDialog.Builder(this)
            .setTitle("访问被阻止")
            .setMessage("出于安全考虑，不允许访问此域名：\n$url")
            .setPositiveButton("确定", null)
            .show()
    }
    
    /**
     * 显示错误页面
     */
    private fun showErrorPage(errorCode: Int, description: String, failingUrl: String) {
        val errorHtml = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>页面加载失败</title>
                <style>
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
                        margin: 0;
                        padding: 20px;
                        background-color: #f5f5f5;
                        color: #333;
                        text-align: center;
                    }
                    .error-container {
                        background: white;
                        border-radius: 8px;
                        padding: 40px 20px;
                        margin: 20px auto;
                        max-width: 500px;
                        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                    }
                    .error-icon {
                        font-size: 64px;
                        color: #ff6b6b;
                        margin-bottom: 20px;
                    }
                    .error-title {
                        font-size: 24px;
                        font-weight: bold;
                        margin-bottom: 10px;
                        color: #2c3e50;
                    }
                    .error-message {
                        font-size: 16px;
                        color: #7f8c8d;
                        margin-bottom: 20px;
                        line-height: 1.5;
                    }
                    .error-details {
                        background: #f8f9fa;
                        border-radius: 4px;
                        padding: 15px;
                        margin: 20px 0;
                        font-size: 14px;
                        color: #6c757d;
                        text-align: left;
                    }
                    .retry-button {
                        background: #3498db;
                        color: white;
                        border: none;
                        padding: 12px 24px;
                        border-radius: 4px;
                        font-size: 16px;
                        cursor: pointer;
                        margin-top: 20px;
                    }
                </style>
            </head>
            <body>
                <div class="error-container">
                    <div class="error-icon">⚠️</div>
                    <div class="error-title">页面加载失败</div>
                    <div class="error-message">抱歉，无法加载此页面。请检查网络连接或稍后重试。</div>
                    <div class="error-details">
                        <strong>错误代码：</strong> $errorCode<br>
                        <strong>错误描述：</strong> $description<br>
                        <strong>失败地址：</strong> $failingUrl
                    </div>
                    <button class="retry-button" onclick="window.location.reload()">重新加载</button>
                </div>
            </body>
            </html>
        """.trimIndent()
        
        webView.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null)
    }
    
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 处理返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        
        // 清理 WebView 资源
        try {
            webView.apply {
                loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
                clearHistory()
                clearCache(true)
                onPause()
                removeAllViews()
                destroy()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error destroying WebView", e)
        }
    }
} 