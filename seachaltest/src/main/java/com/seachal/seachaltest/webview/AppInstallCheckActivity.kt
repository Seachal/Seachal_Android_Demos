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
 * 应用安装检查验证示例
 * 
 * 功能包括：
 * - 检查常见应用是否已安装
 * - JavaScript 接口调用验证
 * - 前端网页交互演示
 * 
 * 遵循阿里巴巴 Android 开发手册规范
 * 
 * @author Seachal
 * @since 2025-01-27
 */
class AppInstallCheckActivity : AppCompatActivity() {
    
    companion object {
        private const val TAG = "AppInstallCheckActivity"
    }
    
    private lateinit var webView: WebView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_install_check)
        
        setupActionBar()
        initViews()
        setupWebView()
        loadDemoPage()
    }
    
    /**
     * 设置标题栏
     */
    private fun setupActionBar() {
        supportActionBar?.apply {
            title = "应用安装检查验证"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
    
    /**
     * 初始化视图组件
     */
    private fun initViews() {
        webView = findViewById(R.id.webview)
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
                allowFileAccess = true
                allowContentAccess = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                useWideViewPort = true
                loadWithOverviewMode = true
            }
            
            // 添加 JavaScript 接口
            addJavascriptInterface(AppInstallJSInterface(this@AppInstallCheckActivity), "AndroidInterface")
            
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Log.d(TAG, "页面加载完成，JavaScript 接口已就绪")
                }
            }
            
            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    consoleMessage?.let {
                        Log.d(TAG, "JS 控制台: ${it.message()}")
                    }
                    return true
                }
            }
        }
    }
    
    /**
     * 加载演示页面
     */
    private fun loadDemoPage() {
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
    }
    
    /**
     * 检查应用是否已安装
     * 
     * @param context 上下文
     * @param packageName 应用包名
     * @return 是否已安装
     */
    fun isAppInstalled(context: Context?, packageName: String): Boolean {
        return context?.let {
            val pm: PackageManager = it.packageManager
            return try {
                val packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
                Log.d(TAG, "应用 [$packageName] 检查成功，版本: ${packageInfo.versionName}, 版本码: ${packageInfo.versionCode}")
                true
            } catch (e: PackageManager.NameNotFoundException) {
                Log.w(TAG, "应用 [$packageName] 未找到: ${e.message}")
                false
            } catch (e: Exception) {
                Log.e(TAG, "检查应用 [$packageName] 时发生异常: ${e.message}", e)
                false
            }
        } ?: run {
            Log.e(TAG, "Context 为空，无法检查应用 [$packageName]")
            false
        }
    }
    
    /**
     * Android JavaScript 接口类
     * 提供给前端网页调用的应用安装检查方法
     */
    inner class AppInstallJSInterface(private val context: Context) {
        
        /**
         * 检查微信是否已安装
         * 供前端 WebView 中的网页调用
         * @return 直接返回 true 或 false
         */
        @JavascriptInterface
        fun isWeChatInstalled(): Boolean {
            Log.d(TAG, "JavaScript 接口调用: isWeChatInstalled()")
            val result = isAppInstalled(context, "com.tencent.mm")
            Log.d(TAG, "微信安装检查结果: $result")
            
            // 额外的调试信息
            try {
                val pm = context.packageManager
                val installedApps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
                val wechatApp = installedApps.find { it.packageName == "com.tencent.mm" }
                if (wechatApp != null) {
                    Log.d(TAG, "通过 getInstalledApplications 找到微信: ${wechatApp.packageName}")
                } else {
                    Log.w(TAG, "通过 getInstalledApplications 未找到微信")
                }
            } catch (e: Exception) {
                Log.e(TAG, "获取已安装应用列表时发生异常: ${e.message}", e)
            }
            
            return result
        }
        
        /**
         * 检查应用是否已安装
         * 供前端 WebView 中的网页调用
         * @param packageName 应用包名
         * @return 直接返回 true 或 false
         */
        @JavascriptInterface
        fun isAppInstalled(packageName: String): Boolean {
            val result = isAppInstalled(context, packageName)
            Log.d(TAG, "检查应用安装状态 [$packageName]: $result")
            return result
        }
        
        /**
         * 获取已安装的应用列表（部分常见应用）
         * @return JSON 格式的应用安装状态
         */
        @JavascriptInterface
        fun getCommonAppsStatus(): String {
            val commonApps = mapOf(
                "微信" to "com.tencent.mm",
                "QQ" to "com.tencent.mobileqq",
                "支付宝" to "com.eg.android.AlipayGphone",
                "淘宝" to "com.taobao.taobao",
                "抖音" to "com.ss.android.ugc.aweme",
                "YouTube" to "com.google.android.youtube",
                "Chrome" to "com.android.chrome",
                "百度" to "com.baidu.searchbox"
            )
            
            val statusMap = mutableMapOf<String, Boolean>()
            commonApps.forEach { (name, packageName) ->
                statusMap[name] = isAppInstalled(context, packageName)
            }
            
            // 转换为 JSON 字符串
            val jsonBuilder = StringBuilder("{")
            statusMap.entries.forEachIndexed { index, entry ->
                jsonBuilder.append("\"${entry.key}\": ${entry.value}")
                if (index < statusMap.size - 1) {
                    jsonBuilder.append(", ")
                }
            }
            jsonBuilder.append("}")
            
            val result = jsonBuilder.toString()
            Log.d(TAG, "常见应用安装状态: $result")
            return result
        }
        
        /**
         * 显示 Toast 消息
         * @param message 消息内容
         */
        @JavascriptInterface
        fun showToast(message: String) {
            runOnUiThread {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    // HTML 演示页面内容
    private val htmlContent = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>应用安装检查验证</title>
            <style>
                body {
                    font-family: 'Segoe UI', Arial, sans-serif;
                    margin: 0;
                    padding: 20px;
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    color: white;
                    min-height: 100vh;
                }
                .container {
                    background: rgba(255,255,255,0.1);
                    padding: 25px;
                    border-radius: 15px;
                    backdrop-filter: blur(10px);
                    margin-bottom: 20px;
                    box-shadow: 0 8px 32px rgba(0,0,0,0.1);
                }
                h1 {
                    text-align: center;
                    margin-bottom: 30px;
                    color: #fff;
                    font-size: 24px;
                }
                .section {
                    background: rgba(255,255,255,0.15);
                    margin: 20px 0;
                    padding: 20px;
                    border-radius: 12px;
                    border: 1px solid rgba(255,255,255,0.2);
                }
                .btn {
                    background: linear-gradient(45deg, #4CAF50, #45a049);
                    color: white;
                    padding: 12px 24px;
                    border: none;
                    border-radius: 25px;
                    cursor: pointer;
                    margin: 8px 4px;
                    font-size: 14px;
                    transition: all 0.3s ease;
                    box-shadow: 0 4px 15px rgba(0,0,0,0.2);
                    min-width: 120px;
                }
                .btn:hover {
                    transform: translateY(-2px);
                    box-shadow: 0 6px 20px rgba(0,0,0,0.3);
                }
                .btn-info {
                    background: linear-gradient(45deg, #2196F3, #1976D2);
                }
                .btn-warning {
                    background: linear-gradient(45deg, #ff9800, #f57c00);
                }
                input {
                    width: calc(100% - 24px);
                    padding: 12px;
                    margin: 8px 0;
                    border: none;
                    border-radius: 8px;
                    background: rgba(255,255,255,0.9);
                    color: #333;
                    font-size: 14px;
                }
                .result {
                    background: rgba(0,0,0,0.3);
                    padding: 15px;
                    border-radius: 8px;
                    margin: 15px 0;
                    font-family: 'Courier New', monospace;
                    min-height: 40px;
                    border: 1px solid rgba(255,255,255,0.3);
                    white-space: pre-wrap;
                }
                .app-status {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    padding: 8px 0;
                    border-bottom: 1px solid rgba(255,255,255,0.1);
                }
                .status-installed {
                    color: #4CAF50;
                    font-weight: bold;
                }
                .status-not-installed {
                    color: #f44336;
                    font-weight: bold;
                }
                .test-group {
                    margin: 15px 0;
                }
                .package-examples {
                    background: rgba(0,0,0,0.2);
                    padding: 10px;
                    border-radius: 6px;
                    font-size: 12px;
                    margin: 10px 0;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>📱 应用安装检查验证演示</h1>
                
                <!-- 微信检查 -->
                <div class="section">
                    <h3>🔍 微信安装检查</h3>
                    <p>检查设备上是否安装了微信应用</p>
                    
                    <div class="test-group">
                        <button class="btn" onclick="checkWeChat()">检查微信是否安装</button>
                    </div>
                    
                    <div class="result" id="wechatResult">点击按钮检查微信安装状态...</div>
                </div>
                
                <!-- 自定义应用检查 -->
                <div class="section">
                    <h3>🎯 自定义应用检查</h3>
                    <p>输入应用包名检查是否已安装</p>
                    
                    <input type="text" id="packageInput" placeholder="输入应用包名，例如: com.tencent.mm" value="com.tencent.mm">
                    
                    <div class="package-examples">
                        <strong>常见应用包名示例：</strong><br>
                        • 微信: com.tencent.mm<br>
                        • QQ: com.tencent.mobileqq<br>
                        • 支付宝: com.eg.android.AlipayGphone<br>
                        • 淘宝: com.taobao.taobao<br>
                        • 抖音: com.ss.android.ugc.aweme<br>
                        • YouTube: com.google.android.youtube
                    </div>
                    
                    <div class="test-group">
                        <button class="btn btn-info" onclick="checkCustomApp()">检查应用</button>
                        <button class="btn btn-warning" onclick="clearCustomResult()">清空结果</button>
                    </div>
                    
                    <div class="result" id="customResult">输入包名后点击检查...</div>
                </div>
                
                <!-- 批量检查 -->
                <div class="section">
                    <h3>📊 常见应用批量检查</h3>
                    <p>一键检查多个常见应用的安装状态</p>
                    
                    <div class="test-group">
                        <button class="btn btn-info" onclick="checkCommonApps()">批量检查常见应用</button>
                        <button class="btn btn-warning" onclick="refreshCommonApps()">刷新状态</button>
                    </div>
                    
                    <div class="result" id="commonAppsResult">点击按钮开始批量检查...</div>
                </div>
                
                <!-- 测试日志 -->
                <div class="section">
                    <h3>📝 测试日志</h3>
                    <div class="result" id="testLog">等待测试操作...</div>
                    <button class="btn btn-warning" onclick="clearLog()">清空日志</button>
                </div>
            </div>
            
            <script>
                let logCount = 0;
                
                // 添加日志
                function addLog(message) {
                    const logElement = document.getElementById('testLog');
                    const timestamp = new Date().toLocaleTimeString();
                    logCount++;
                    const newLog = "[" + logCount + "] " + timestamp + ": " + message + "\\n";
                    logElement.textContent = newLog + logElement.textContent;
                }
                
                // 检查微信是否安装
                function checkWeChat() {
                    addLog('开始检查微信安装状态...');
                    addLog('设备信息: ' + navigator.userAgent);
                    
                    if (window.AndroidInterface) {
                        try {
                            addLog('调用 AndroidInterface.isWeChatInstalled()...');
                            const isInstalled = window.AndroidInterface.isWeChatInstalled();
                            
                            const result = isInstalled ? 
                                '✅ 微信已安装 (包名: com.tencent.mm)' : 
                                '❌ 微信未安装 (可能原因: 1.确实未安装 2.Android 11+需要包可见性权限)';
                            
                            document.getElementById('wechatResult').textContent = result;
                            addLog('微信检查结果: ' + (isInstalled ? '已安装' : '未安装'));
                            
                            if (!isInstalled) {
                                addLog('⚠️ 如果微信确实已安装但显示未安装，请检查:');
                                addLog('1. AndroidManifest.xml 中是否添加了 <queries> 元素');
                                addLog('2. 是否声明了 com.tencent.mm 包名');
                                addLog('3. 是否重新安装了应用以应用新权限');
                            }
                            
                            // 显示 Toast
                            window.AndroidInterface.showToast('微信' + (isInstalled ? '已' : '未') + '安装');
                        } catch (error) {
                            const errorMsg = '检查微信时发生JavaScript错误: ' + error.message;
                            document.getElementById('wechatResult').textContent = errorMsg;
                            addLog(errorMsg);
                            addLog('错误堆栈: ' + error.stack);
                        }
                    } else {
                        const errorMsg = '❌ 错误: AndroidInterface 未找到，JavaScript接口未正确注入';
                        document.getElementById('wechatResult').textContent = errorMsg;
                        addLog(errorMsg);
                        addLog('请检查 WebView 设置中是否启用了 JavaScript 并正确添加了接口');
                    }
                }
                
                // 检查自定义应用
                function checkCustomApp() {
                    const packageName = document.getElementById('packageInput').value.trim();
                    
                    if (!packageName) {
                        alert('请输入应用包名');
                        return;
                    }
                    
                    addLog('开始检查应用: ' + packageName);
                    
                    if (window.AndroidInterface) {
                        try {
                            const isInstalled = window.AndroidInterface.isAppInstalled(packageName);
                            const result = '应用包名: ' + packageName + '\\n状态: ' + (isInstalled ? '✅ 已安装' : '❌ 未安装');
                            
                            document.getElementById('customResult').textContent = result;
                            addLog(packageName + ' 检查结果: ' + (isInstalled ? '已安装' : '未安装'));
                            
                            // 显示 Toast
                            window.AndroidInterface.showToast(packageName + ' ' + (isInstalled ? '已' : '未') + '安装');
                        } catch (error) {
                            const errorMsg = '检查应用时发生错误: ' + error.message;
                            document.getElementById('customResult').textContent = errorMsg;
                            addLog(errorMsg);
                        }
                    } else {
                        const errorMsg = '错误: AndroidInterface 未找到';
                        document.getElementById('customResult').textContent = errorMsg;
                        addLog(errorMsg);
                    }
                }
                
                // 批量检查常见应用
                function checkCommonApps() {
                    addLog('开始批量检查常见应用...');
                    
                    if (window.AndroidInterface) {
                        try {
                            const statusJson = window.AndroidInterface.getCommonAppsStatus();
                            const statusData = JSON.parse(statusJson);
                            
                            let resultHtml = '';
                            Object.entries(statusData).forEach(function(entry) {
                                const appName = entry[0];
                                const isInstalled = entry[1];
                                const statusClass = isInstalled ? 'status-installed' : 'status-not-installed';
                                const statusText = isInstalled ? '✅ 已安装' : '❌ 未安装';
                                resultHtml += '<div class="app-status">' +
                                    '<span>' + appName + '</span>' +
                                    '<span class="' + statusClass + '">' + statusText + '</span>' +
                                '</div>';
                            });
                            
                            document.getElementById('commonAppsResult').innerHTML = resultHtml;
                            addLog('批量检查完成，共检查 ' + Object.keys(statusData).length + ' 个应用');
                            
                            // 统计已安装应用数量
                            const installedCount = Object.values(statusData).filter(function(status) { return status; }).length;
                            window.AndroidInterface.showToast('已安装 ' + installedCount + '/' + Object.keys(statusData).length + ' 个常见应用');
                        } catch (error) {
                            const errorMsg = '批量检查时发生错误: ' + error.message;
                            document.getElementById('commonAppsResult').textContent = errorMsg;
                            addLog(errorMsg);
                        }
                    } else {
                        const errorMsg = '错误: AndroidInterface 未找到';
                        document.getElementById('commonAppsResult').textContent = errorMsg;
                        addLog(errorMsg);
                    }
                }
                
                // 刷新常见应用状态
                function refreshCommonApps() {
                    addLog('刷新常见应用状态...');
                    checkCommonApps();
                }
                
                // 清空自定义结果
                function clearCustomResult() {
                    document.getElementById('customResult').textContent = '输入包名后点击检查...';
                    addLog('清空自定义检查结果');
                }
                
                // 清空日志
                function clearLog() {
                    document.getElementById('testLog').textContent = '等待测试操作...';
                    logCount = 0;
                }
                
                // 页面加载完成
                window.onload = function() {
                    addLog('页面加载完成，JavaScript 接口已就绪');
                    
                    // 自动检查微信（演示）
                    setTimeout(function() {
                        addLog('自动执行微信检查演示...');
                        checkWeChat();
                    }, 1000);
                };
            </script>
        </body>
        </html>
    """.trimIndent()
} 