package com.seachal.seachaltest.webview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.webkit.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import java.io.File

/**
 * WebView 缓存管理示例
 * 
 * 功能包括：
 * - 缓存策略设置
 * - 缓存清理
 * - 缓存大小查看
 * - 离线模式
 * 
 * 遵循阿里巴巴 Android 开发手册规范
 */
class CacheWebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var cacheInfoText: TextView
    private lateinit var btnClearCache: Button
    private lateinit var btnOnlineMode: Button
    private lateinit var btnOfflineMode: Button
    private lateinit var btnRefresh: Button

    companion object {
        private const val TAG = "CacheWebViewActivity"
        private const val TEST_URL = "https://www.baidu.com"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache_webview)

        initViews()
        setupWebView()
        setupButtons()
        updateCacheInfo()
    }

    /**
     * 初始化视图组件
     */
    private fun initViews() {
        webView = findViewById(R.id.webview)
        cacheInfoText = findViewById(R.id.tv_cache_info)
        btnClearCache = findViewById(R.id.btn_clear_cache)
        btnOnlineMode = findViewById(R.id.btn_online_mode)
        btnOfflineMode = findViewById(R.id.btn_offline_mode)
        btnRefresh = findViewById(R.id.btn_refresh)

        supportActionBar?.apply {
            title = "WebView 缓存管理"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * 配置 WebView 设置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.apply {
            settings.apply {
                // 启用 JavaScript
                javaScriptEnabled = true
                
                // 启用 DOM 存储
                domStorageEnabled = true
                
                // 启用数据库存储
                databaseEnabled = true
                
                // 设置缓存模式为默认（优先使用缓存）
                cacheMode = WebSettings.LOAD_DEFAULT
                
                // 设置缓存目录
                            // 注意：以下 API 在 Android API 33+ 中已废弃
            // setAppCacheEnabled(true) // 已废弃
            // setAppCachePath(cacheDir.absolutePath) // 已废弃  
            // setAppCacheMaxSize(50 * 1024 * 1024) // 已废弃，50MB
                
                // 允许文件访问
                allowFileAccess = true
                allowContentAccess = true
                
                // 支持缩放
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                
                // 自适应屏幕
                useWideViewPort = true
                loadWithOverviewMode = true
                
                // 设置用户代理
                userAgentString = "${userAgentString} SeachalWebViewDemo/1.0"
            }

            // 设置 WebViewClient
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    updateCacheInfo()
                    Toast.makeText(this@CacheWebViewActivity, "页面加载完成", Toast.LENGTH_SHORT).show()
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    Toast.makeText(
                        this@CacheWebViewActivity,
                        "加载错误: ${error?.description}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            // 设置 WebChromeClient
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    // 可以在这里更新进度条
                }
            }
        }

        // 加载测试页面
        webView.loadUrl(TEST_URL)
    }

    /**
     * 设置按钮点击事件
     */
    private fun setupButtons() {
        // 清除缓存
        btnClearCache.setOnClickListener {
            clearWebViewCache()
        }

        // 在线模式
        btnOnlineMode.setOnClickListener {
            setOnlineMode()
        }

        // 离线模式
        btnOfflineMode.setOnClickListener {
            setOfflineMode()
        }

        // 刷新页面
        btnRefresh.setOnClickListener {
            webView.reload()
        }
    }

    /**
     * 清除 WebView 缓存
     */
    private fun clearWebViewCache() {
        try {
            // 清除网页缓存
            webView.clearCache(true)
            
            // 清除历史记录
            webView.clearHistory()
            
            // 清除表单数据
            webView.clearFormData()
            
            // 清除 Cookie
            CookieManager.getInstance().removeAllCookies(null)
            
            // 清除数据库
            WebStorage.getInstance().deleteAllData()
            
            // 清除缓存目录
            clearCacheDirectory()
            
            updateCacheInfo()
            Toast.makeText(this, "缓存已清除", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "清除缓存失败: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * 清除缓存目录
     */
    private fun clearCacheDirectory() {
        try {
            val cacheDir = File(cacheDir, "webview")
            if (cacheDir.exists()) {
                deleteDirectory(cacheDir)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 递归删除目录
     */
    private fun deleteDirectory(dir: File): Boolean {
        if (dir.isDirectory) {
            val children = dir.list()
            children?.forEach { child ->
                val success = deleteDirectory(File(dir, child))
                if (!success) {
                    return false
                }
            }
        }
        return dir.delete()
    }

    /**
     * 设置在线模式
     */
    private fun setOnlineMode() {
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        Toast.makeText(this, "已切换到在线模式", Toast.LENGTH_SHORT).show()
        webView.reload()
    }

    /**
     * 设置离线模式
     */
    private fun setOfflineMode() {
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ONLY
        Toast.makeText(this, "已切换到离线模式", Toast.LENGTH_SHORT).show()
        webView.reload()
    }

    /**
     * 更新缓存信息显示
     */
    private fun updateCacheInfo() {
        try {
            val cacheSize = getCacheSize()
            val cacheSizeText = formatFileSize(cacheSize)
            
            val cacheMode = when (webView.settings.cacheMode) {
                WebSettings.LOAD_DEFAULT -> "默认模式"
                WebSettings.LOAD_CACHE_ELSE_NETWORK -> "缓存优先模式"
                WebSettings.LOAD_NO_CACHE -> "不使用缓存"
                WebSettings.LOAD_CACHE_ONLY -> "仅使用缓存"
                else -> "未知模式"
            }
            
            val info = """
                缓存模式: $cacheMode
                缓存大小: $cacheSizeText
                缓存目录: ${cacheDir.absolutePath}
                JavaScript: ${if (webView.settings.javaScriptEnabled) "已启用" else "已禁用"}
                DOM存储: ${if (webView.settings.domStorageEnabled) "已启用" else "已禁用"}
                数据库: ${if (webView.settings.databaseEnabled) "已启用" else "已禁用"}
            """.trimIndent()
            
            cacheInfoText.text = info
        } catch (e: Exception) {
            cacheInfoText.text = "获取缓存信息失败: ${e.message}"
        }
    }

    /**
     * 获取缓存大小
     */
    private fun getCacheSize(): Long {
        return try {
            val cacheDir = File(cacheDir, "webview")
            getDirectorySize(cacheDir)
        } catch (e: Exception) {
            0L
        }
    }

    /**
     * 获取目录大小
     */
    private fun getDirectorySize(dir: File): Long {
        var size = 0L
        if (dir.exists()) {
            if (dir.isDirectory) {
                dir.listFiles()?.forEach { file ->
                    size += if (file.isDirectory) {
                        getDirectorySize(file)
                    } else {
                        file.length()
                    }
                }
            } else {
                size = dir.length()
            }
        }
        return size
    }

    /**
     * 格式化文件大小
     */
    private fun formatFileSize(size: Long): String {
        return when {
            size < 1024 -> "${size}B"
            size < 1024 * 1024 -> "${size / 1024}KB"
            size < 1024 * 1024 * 1024 -> "${size / (1024 * 1024)}MB"
            else -> "${size / (1024 * 1024 * 1024)}GB"
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 清理 WebView
        webView.apply {
            loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            clearHistory()
            destroy()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
} 