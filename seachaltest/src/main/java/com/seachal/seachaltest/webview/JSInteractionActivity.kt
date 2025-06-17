package com.seachal.seachaltest.webview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.webkit.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import org.json.JSONObject

/**
 * JavaScript 交互示例
 * 
 * 功能包括：
 * 1. Android 调用 JavaScript 函数
 * 2. JavaScript 调用 Android 方法
 * 3. 双向数据传递
 * 4. 动态注入 JavaScript 代码
 * 5. 安全的 JavaScript 接口实现
 * 
 * @author Seachal
 * @since 2025-01-27
 */
class JSInteractionActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var etAndroidInput: EditText
    private lateinit var tvJsOutput: TextView
    private lateinit var btnCallJs: Button
    private lateinit var btnInjectJs: Button
    private lateinit var btnGetPageInfo: Button
    private lateinit var tvStatus: TextView

    // 本地 HTML 页面，包含 JavaScript 交互演示
    private val htmlContent = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>JavaScript 交互演示</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 20px;
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    color: white;
                    min-height: 100vh;
                }
                .container {
                    background: rgba(255,255,255,0.1);
                    padding: 20px;
                    border-radius: 15px;
                    backdrop-filter: blur(10px);
                    margin-bottom: 20px;
                }
                h1 {
                    text-align: center;
                    margin-bottom: 30px;
                    color: #fff;
                }
                .section {
                    background: rgba(255,255,255,0.15);
                    margin: 15px 0;
                    padding: 20px;
                    border-radius: 10px;
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
                }
                .btn:hover {
                    transform: translateY(-2px);
                    box-shadow: 0 6px 20px rgba(0,0,0,0.3);
                }
                .btn-danger {
                    background: linear-gradient(45deg, #f44336, #d32f2f);
                }
                .btn-info {
                    background: linear-gradient(45deg, #2196F3, #1976D2);
                }
                .btn-warning {
                    background: linear-gradient(45deg, #ff9800, #f57c00);
                }
                input, textarea {
                    width: 100%;
                    padding: 12px;
                    margin: 8px 0;
                    border: none;
                    border-radius: 8px;
                    background: rgba(255,255,255,0.9);
                    color: #333;
                    font-size: 14px;
                }
                .output {
                    background: rgba(0,0,0,0.3);
                    padding: 15px;
                    border-radius: 8px;
                    margin: 10px 0;
                    font-family: 'Courier New', monospace;
                    min-height: 50px;
                    border: 1px solid rgba(255,255,255,0.3);
                }
                .log {
                    font-size: 12px;
                    color: #E8F5E8;
                    margin: 5px 0;
                    padding: 5px;
                    background: rgba(0,0,0,0.2);
                    border-radius: 4px;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>🔄 JavaScript 交互演示</h1>
                
                <!-- JavaScript 调用 Android 区域 -->
                <div class="section">
                    <h3>📱 JavaScript → Android</h3>
                    <p>从网页调用 Android 原生功能</p>
                    
                    <input type="text" id="jsInput" placeholder="输入要发送给 Android 的消息" value="Hello from JavaScript!">
                    
                    <div style="margin: 15px 0;">
                        <button class="btn" onclick="callAndroidMethod()">发送消息到 Android</button>
                        <button class="btn btn-info" onclick="getDeviceInfo()">获取设备信息</button>
                        <button class="btn btn-warning" onclick="showAndroidToast()">显示 Toast</button>
                        <button class="btn btn-danger" onclick="callAndroidCallback()">回调测试</button>
                    </div>
                </div>
                
                <!-- Android 调用 JavaScript 区域 -->
                <div class="section">
                    <h3>🌐 Android → JavaScript</h3>
                    <p>Android 调用网页中的 JavaScript 函数</p>
                    
                    <div class="output" id="androidOutput">等待 Android 调用...</div>
                    
                    <input type="text" id="jsReceiveInput" placeholder="接收 Android 数据的输入框">
                    
                    <div style="margin: 15px 0;">
                        <button class="btn" onclick="clearOutput()">清空输出</button>
                        <button class="btn btn-info" onclick="testLocalFunction()">测试本地函数</button>
                    </div>
                </div>
                
                <!-- 数据交换演示 -->
                <div class="section">
                    <h3>🔄 数据交换演示</h3>
                    <p>复杂数据类型的双向传递</p>
                    
                    <textarea id="jsonData" rows="4" placeholder="输入 JSON 数据...">{
  "name": "测试用户",
  "age": 25,
  "city": "北京",
  "hobbies": ["编程", "音乐", "旅行"]
}</textarea>
                    
                    <div style="margin: 15px 0;">
                        <button class="btn" onclick="sendJsonToAndroid()">发送 JSON 到 Android</button>
                        <button class="btn btn-info" onclick="requestDataFromAndroid()">从 Android 获取数据</button>
                    </div>
                    
                    <div class="output" id="jsonOutput">JSON 数据交换结果将在这里显示...</div>
                </div>
                
                <!-- 页面状态信息 -->
                <div class="section">
                    <h3>📊 页面状态</h3>
                    <div class="output" id="statusInfo">
                        <div class="log">页面加载时间: <span id="loadTime"></span></div>
                        <div class="log">用户代理: <span id="userAgent"></span></div>
                        <div class="log">屏幕尺寸: <span id="screenSize"></span></div>
                        <div class="log">当前时间: <span id="currentTime"></span></div>
                    </div>
                </div>
            </div>
            
            <script>
                let loadStartTime = Date.now();
                
                // ========== JavaScript 调用 Android 方法 ==========
                
                // 发送消息到 Android
                function callAndroidMethod() {
                    const message = document.getElementById('jsInput').value;
                    if (window.AndroidInterface) {
                        window.AndroidInterface.receiveMessage(message);
                        addLog('发送消息到 Android: ' + message);
                    } else {
                        addLog('错误: AndroidInterface 未找到');
                    }
                }
                
                // 获取设备信息
                function getDeviceInfo() {
                    if (window.AndroidInterface) {
                        const deviceInfo = window.AndroidInterface.getDeviceInfo();
                        addLog('设备信息: ' + deviceInfo);
                    } else {
                        addLog('错误: AndroidInterface 未找到');
                    }
                }
                
                // 显示 Android Toast
                function showAndroidToast() {
                    if (window.AndroidInterface) {
                        window.AndroidInterface.showToast('来自 JavaScript 的问候！');
                        addLog('请求显示 Toast');
                    } else {
                        addLog('错误: AndroidInterface 未找到');
                    }
                }
                
                // 回调测试
                function callAndroidCallback() {
                    if (window.AndroidInterface) {
                        window.AndroidInterface.testCallback('JavaScript 回调测试');
                        addLog('发送回调测试请求');
                    } else {
                        addLog('错误: AndroidInterface 未找到');
                    }
                }
                
                // 发送 JSON 数据到 Android
                function sendJsonToAndroid() {
                    const jsonStr = document.getElementById('jsonData').value;
                    try {
                        const jsonObj = JSON.parse(jsonStr);
                        if (window.AndroidInterface) {
                            window.AndroidInterface.receiveJsonData(jsonStr);
                            document.getElementById('jsonOutput').innerHTML = 
                                '<div class="log">✅ JSON 数据已发送到 Android</div>' +
                                '<div class="log">数据: ' + jsonStr + '</div>';
                        }
                    } catch (e) {
                        document.getElementById('jsonOutput').innerHTML = 
                            '<div class="log">❌ JSON 格式错误: ' + e.message + '</div>';
                    }
                }
                
                // 从 Android 请求数据
                function requestDataFromAndroid() {
                    if (window.AndroidInterface) {
                        const data = window.AndroidInterface.getAppData();
                        document.getElementById('jsonOutput').innerHTML = 
                            '<div class="log">📱 从 Android 获取的数据:</div>' +
                            '<div class="log">' + data + '</div>';
                    }
                }
                
                // ========== Android 调用的 JavaScript 函数 ==========
                
                // 接收来自 Android 的消息
                function receiveFromAndroid(message) {
                    const output = document.getElementById('androidOutput');
                    output.innerHTML += '<div class="log">📱 Android 消息: ' + message + '</div>';
                    addLog('收到 Android 消息: ' + message);
                }
                
                // 更新输入框内容
                function updateInputFromAndroid(data) {
                    document.getElementById('jsReceiveInput').value = data;
                    addLog('输入框已更新: ' + data);
                }
                
                // 接收 JSON 数据
                function receiveJsonFromAndroid(jsonStr) {
                    try {
                        const jsonObj = JSON.parse(jsonStr);
                        document.getElementById('jsonOutput').innerHTML = 
                            '<div class="log">📱 Android 发送的 JSON:</div>' +
                            '<div class="log">' + JSON.stringify(jsonObj, null, 2) + '</div>';
                    } catch (e) {
                        document.getElementById('jsonOutput').innerHTML = 
                            '<div class="log">❌ 接收到无效 JSON: ' + jsonStr + '</div>';
                    }
                }
                
                // 处理 Android 回调
                function handleAndroidCallback(result) {
                    addLog('Android 回调结果: ' + result);
                    const output = document.getElementById('androidOutput');
                    output.innerHTML += '<div class="log">🔄 回调结果: ' + result + '</div>';
                }
                
                // ========== 辅助函数 ==========
                
                // 清空输出
                function clearOutput() {
                    document.getElementById('androidOutput').innerHTML = '输出已清空...';
                    document.getElementById('jsonOutput').innerHTML = 'JSON 数据交换结果将在这里显示...';
                }
                
                // 测试本地函数
                function testLocalFunction() {
                    const output = document.getElementById('androidOutput');
                    output.innerHTML += '<div class="log">🧪 本地函数测试: ' + new Date().toLocaleTimeString() + '</div>';
                }
                
                // 添加日志
                function addLog(message) {
                    console.log('[WebView] ' + message);
                }
                
                // 更新页面信息
                function updatePageInfo() {
                    document.getElementById('loadTime').textContent = (Date.now() - loadStartTime) + 'ms';
                    document.getElementById('userAgent').textContent = navigator.userAgent.substring(0, 50) + '...';
                    document.getElementById('screenSize').textContent = screen.width + 'x' + screen.height;
                    document.getElementById('currentTime').textContent = new Date().toLocaleString();
                }
                
                // 页面加载完成
                window.onload = function() {
                    updatePageInfo();
                    setInterval(function() {
                        document.getElementById('currentTime').textContent = new Date().toLocaleString();
                    }, 1000);
                    
                    addLog('页面加载完成，准备进行 JavaScript 交互');
                };
            </script>
        </body>
        </html>
    """.trimIndent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_js_interaction)
        
        title = "JavaScript 交互示例"
        
        initViews()
        setupWebView()
        setupButtons()
        
        // 加载 HTML 页面
        loadHtmlPage()
    }

    /**
     * 初始化视图组件
     */
    private fun initViews() {
        webView = findViewById(R.id.webview)
        etAndroidInput = findViewById(R.id.et_android_input)
        tvJsOutput = findViewById(R.id.tv_js_output)
        btnCallJs = findViewById(R.id.btn_call_js)
        btnInjectJs = findViewById(R.id.btn_inject_js)
        btnGetPageInfo = findViewById(R.id.btn_get_page_info)
        tvStatus = findViewById(R.id.tv_status)
        
        // 设置默认输入内容
        etAndroidInput.setText("Hello from Android!")
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
            addJavascriptInterface(AndroidJSInterface(this@JSInteractionActivity), "AndroidInterface")
            
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    updateStatus("页面加载完成，JavaScript 接口已就绪")
                }
            }
            
            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    consoleMessage?.let {
                        updateStatus("JS 控制台: ${it.message()}")
                    }
                    return true
                }
            }
        }
    }

    /**
     * 设置按钮事件
     */
    private fun setupButtons() {
        btnCallJs.setOnClickListener {
            val message = etAndroidInput.text.toString()
            callJavaScriptFunction("receiveFromAndroid", message)
        }
        
        btnInjectJs.setOnClickListener {
            injectJavaScript()
        }
        
        btnGetPageInfo.setOnClickListener {
            getPageInfo()
        }
    }

    /**
     * 加载 HTML 页面
     */
    private fun loadHtmlPage() {
        updateStatus("正在加载 JavaScript 交互演示页面...")
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
    }

    /**
     * 调用 JavaScript 函数
     */
    private fun callJavaScriptFunction(functionName: String, param: String) {
        val jsCode = "$functionName('$param')"
        webView.evaluateJavascript(jsCode) { result ->
            updateStatus("调用 JS 函数: $functionName, 返回值: $result")
        }
    }

    /**
     * 注入 JavaScript 代码
     */
    private fun injectJavaScript() {
        val jsCode = """
            (function() {
                var now = new Date().toLocaleTimeString();
                var message = 'Android 注入的代码执行于: ' + now;
                document.getElementById('androidOutput').innerHTML += 
                    '<div class="log">💉 ' + message + '</div>';
                return message;
            })();
        """.trimIndent()
        
        webView.evaluateJavascript(jsCode) { result ->
            updateStatus("JavaScript 代码注入成功: $result")
        }
    }

    /**
     * 获取页面信息
     */
    private fun getPageInfo() {
        val jsCode = """
            (function() {
                return JSON.stringify({
                    title: document.title,
                    url: window.location.href,
                    userAgent: navigator.userAgent,
                    timestamp: new Date().getTime(),
                    screenSize: screen.width + 'x' + screen.height
                });
            })();
        """.trimIndent()
        
        webView.evaluateJavascript(jsCode) { result ->
            try {
                val cleanResult = result.replace("\\", "").removeSurrounding("\"")
                val jsonObject = JSONObject(cleanResult)
                val formattedInfo = """
                    页面标题: ${jsonObject.getString("title")}
                    页面 URL: ${jsonObject.getString("url")}
                    时间戳: ${jsonObject.getLong("timestamp")}
                    屏幕尺寸: ${jsonObject.getString("screenSize")}
                """.trimIndent()
                
                tvJsOutput.text = formattedInfo
                updateStatus("页面信息获取成功")
            } catch (e: Exception) {
                tvJsOutput.text = "解析页面信息失败: ${e.message}"
                updateStatus("页面信息解析错误")
            }
        }
    }

    /**
     * 更新状态信息
     */
    private fun updateStatus(message: String) {
        tvStatus.text = message
    }

    /**
     * Android JavaScript 接口类
     * 提供给 JavaScript 调用的方法
     */
    inner class AndroidJSInterface(private val context: Context) {
        
        @JavascriptInterface
        fun receiveMessage(message: String) {
            runOnUiThread {
                tvJsOutput.text = "来自 JS 的消息: $message"
                updateStatus("收到 JavaScript 消息: $message")
            }
        }
        
        @JavascriptInterface
        fun getDeviceInfo(): String {
            val deviceInfo = """
                {
                    "brand": "${android.os.Build.BRAND}",
                    "model": "${android.os.Build.MODEL}",
                    "version": "${android.os.Build.VERSION.RELEASE}",
                    "sdk": ${android.os.Build.VERSION.SDK_INT},
                    "app": "SeachalWebViewDemo"
                }
            """.trimIndent()
            
            runOnUiThread {
                updateStatus("JavaScript 请求设备信息")
            }
            
            return deviceInfo
        }
        
        @JavascriptInterface
        fun showToast(message: String) {
            runOnUiThread {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                updateStatus("显示 Toast: $message")
            }
        }
        
        @JavascriptInterface
        fun testCallback(data: String) {
            runOnUiThread {
                // 模拟异步操作后回调 JavaScript
                android.os.Handler().postDelayed({
                    val result = "Android 处理结果: $data (${System.currentTimeMillis()})"
                    webView.evaluateJavascript("handleAndroidCallback('$result')") {
                        updateStatus("回调 JavaScript 完成")
                    }
                }, 1000)
                
                updateStatus("处理 JavaScript 回调请求...")
            }
        }
        
        @JavascriptInterface
        fun receiveJsonData(jsonStr: String) {
            runOnUiThread {
                try {
                    val jsonObject = JSONObject(jsonStr)
                    val formattedJson = jsonObject.toString(2)
                    tvJsOutput.text = "收到 JSON 数据:\n$formattedJson"
                    updateStatus("成功接收 JSON 数据")
                    
                    // 回传处理后的数据给 JavaScript
                    val responseJson = JSONObject().apply {
                        put("status", "success")
                        put("message", "Android 已成功接收数据")
                        put("timestamp", System.currentTimeMillis())
                        put("receivedData", jsonObject)
                    }
                    
                    webView.evaluateJavascript("receiveJsonFromAndroid('${responseJson.toString().replace("'", "\\'")}')"){}
                    
                } catch (e: Exception) {
                    tvJsOutput.text = "JSON 解析错误: ${e.message}"
                    updateStatus("JSON 数据解析失败")
                }
            }
        }
        
        @JavascriptInterface
        fun getAppData(): String {
            val appData = JSONObject().apply {
                put("appName", "Seachal Android Demos")
                put("version", "1.0.0")
                put("buildTime", System.currentTimeMillis())
                put("features", org.json.JSONArray().apply {
                    put("WebView 交互")
                    put("JavaScript 通信")
                    put("数据交换")
                    put("回调机制")
                })
            }
            
            runOnUiThread {
                updateStatus("JavaScript 请求应用数据")
            }
            
            return appData.toString()
        }
    }

    override fun onDestroy() {
        webView.removeJavascriptInterface("AndroidInterface")
        webView.destroy()
        super.onDestroy()
    }
} 