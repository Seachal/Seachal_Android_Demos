package com.seachal.seachaltest.webview

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seachal.seachaltest.R
import com.seachal.seachaltest.bean.StartActivityBean
// MenuAdapter 在同一包中，无需导入

/**
 * WebView 示例菜单活动
 * 
 * 本活动展示了 WebView 的各种使用场景和高级功能：
 * 1. 基础 WebView 使用
 * 2. JavaScript 交互
 * 3. 文件上传下载
 * 4. 进度条显示
 * 5. 缓存管理
 * 6. 安全设置
 * 7. Cookie 管理
 * 8. 自定义 WebViewClient
 * 9. 混合开发模式
 * 10. 性能优化技巧
 * 
 * @author Seachal
 * @since 2025-01-27
 */
class WebViewMenuActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MenuAdapter
    private val menuItems = mutableListOf<StartActivityBean>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_menu)
        
        setupActionBar()
        initViews()
        initMenuData()
        setupRecyclerView()
    }
    
    /**
     * 设置标题栏
     */
    private fun setupActionBar() {
        supportActionBar?.apply {
            title = "WebView 示例集"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
    
    /**
     * 初始化视图
     */
    private fun initViews() {
        recyclerView = findViewById(R.id.recycler_view)
    }
    
    /**
     * 初始化菜单数据
     */
    private fun initMenuData() {
        menuItems.apply {
            // 基础功能示例
            add(StartActivityBean(
                "基础 WebView 使用",
                "演示 WebView 的基本配置、网页加载、导航控制等基础功能",
                BasicWebViewActivity::class.java
            ))
            
            // JavaScript 交互示例
            add(StartActivityBean(
                "JavaScript 交互",
                "演示 Android 与 JavaScript 的双向通信，包括方法调用和数据传递",
                JSInteractionActivity::class.java
            ))
            
            // 文件操作示例
            add(StartActivityBean(
                "文件上传下载",
                "演示 WebView 中的文件上传、下载功能，包括权限处理",
                FileOperationActivity::class.java
            ))
            
            // 进度条示例
            add(StartActivityBean(
                "加载进度显示",
                "演示网页加载进度条、状态提示、错误处理等用户体验优化",
                ProgressWebViewActivity::class.java
            ))
            
            // 缓存管理示例
            add(StartActivityBean(
                "缓存管理",
                "演示 WebView 的缓存策略、离线访问、缓存清理等功能",
                CacheWebViewActivity::class.java
            ))
            
            // 安全设置示例
            add(StartActivityBean(
                "安全设置",
                "演示 WebView 的安全配置、HTTPS 处理、SSL 证书验证等",
                SecuritySettingsActivity::class.java
            ))
            
            // Cookie 管理示例
            add(StartActivityBean(
                "Cookie 管理",
                "演示 Cookie 的设置、获取、同步、清理等操作",
                CookieManagementActivity::class.java
            ))
            
            // 自定义 WebViewClient 示例
            add(StartActivityBean(
                "自定义 WebViewClient",
                "演示如何自定义 WebViewClient 处理各种网页事件",
                CustomWebViewClientActivity::class.java
            ))
            
            // 混合开发示例
            add(StartActivityBean(
                "混合开发模式",
                "演示原生组件与 WebView 的混合使用，实现复杂交互",
                HybridDevelopmentActivity::class.java
            ))
            
            // 性能优化示例
            add(StartActivityBean(
                "性能优化技巧",
                "演示 WebView 的性能优化方法，包括预加载、内存管理等",
                PerformanceOptimizationActivity::class.java
            ))
            
            // 应用安装检查验证示例
            add(StartActivityBean(
                "应用安装检查验证",
                "演示通过 JavaScript 接口检查设备上应用的安装状态",
                AppInstallCheckActivity::class.java
            ))
        }
    }
    
    /**
     * 设置 RecyclerView
     */
    private fun setupRecyclerView() {
        adapter = MenuAdapter(this, menuItems)
        
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@WebViewMenuActivity)
            adapter = this@WebViewMenuActivity.adapter
        }
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
} 