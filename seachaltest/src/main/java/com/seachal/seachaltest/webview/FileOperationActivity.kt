package com.seachal.seachaltest.webview

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.webkit.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R
import java.io.File
import java.io.FileOutputStream

/**
 * WebView 文件操作示例
 * 
 * 功能包括：
 * - 文件上传
 * - 文件下载
 * - 权限处理
 * 
 * 遵循阿里巴巴 Android 开发手册规范
 */
class FileOperationActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var statusText: TextView
    private lateinit var btnUpload: Button
    private lateinit var btnDownload: Button
    
    private var fileChooserCallback: ValueCallback<Array<Uri>>? = null

    companion object {
        private const val TAG = "FileOperationActivity"
        private const val REQUEST_FILE_CHOOSER = 1001
        private const val REQUEST_STORAGE_PERMISSION = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_operation)
        
        initViews()
        setupWebView()
        loadDemoPage()
        
        // 设置标题
        title = "文件操作示例"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViews() {
        webView = findViewById(R.id.webview)
        statusText = findViewById(R.id.tv_status)
        btnUpload = findViewById(R.id.btn_upload)
        btnDownload = findViewById(R.id.btn_download)
        
        btnUpload.setOnClickListener { openFileChooser() }
        btnDownload.setOnClickListener { downloadTestFile() }
    }

    private fun setupWebView() {
        webView.settings.apply {
            javaScriptEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            domStorageEnabled = true
            
            // 文件上传支持
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
        }
        
        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                fileChooserCallback = filePathCallback
                openFileChooser()
                return true
            }
        }
        
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                statusText.text = "页面加载完成"
            }
        }
    }

    private fun loadDemoPage() {
        val htmlContent = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>文件操作示例</title>
                <style>
                    body { 
                        font-family: Arial, sans-serif; 
                        padding: 20px; 
                        background-color: #f5f5f5; 
                    }
                    .container { 
                        max-width: 600px; 
                        margin: 0 auto; 
                        background: white; 
                        padding: 20px; 
                        border-radius: 8px; 
                        box-shadow: 0 2px 10px rgba(0,0,0,0.1); 
                    }
                    .upload-area { 
                        border: 2px dashed #ccc; 
                        padding: 40px; 
                        text-align: center; 
                        margin: 20px 0; 
                        border-radius: 8px; 
                        background-color: #fafafa; 
                    }
                    .upload-area:hover { 
                        border-color: #007BFF; 
                        background-color: #f0f8ff; 
                    }
                    .btn { 
                        background-color: #007BFF; 
                        color: white; 
                        border: none; 
                        padding: 10px 20px; 
                        border-radius: 4px; 
                        cursor: pointer; 
                        margin: 5px; 
                    }
                    .btn:hover { 
                        background-color: #0056b3; 
                    }
                    .info { 
                        background-color: #e7f3ff; 
                        padding: 10px; 
                        border-radius: 4px; 
                        margin: 10px 0; 
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>📁 WebView 文件操作示例</h1>
                    
                    <div class="info">
                        <p><strong>功能说明：</strong></p>
                        <ul>
                            <li>🔼 文件上传：选择本地文件上传到应用</li>
                            <li>🔽 文件下载：下载文件到设备存储</li>
                            <li>🔒 权限处理：自动请求必要的存储权限</li>
                        </ul>
                    </div>
                    
                    <h2>📤 文件上传</h2>
                    <div class="upload-area" onclick="selectFile()">
                        <p>📁 点击选择文件或拖拽文件到此区域</p>
                        <p style="color: #666; font-size: 14px;">支持图片、文档、音频、视频等多种格式</p>
                    </div>
                    
                    <input type="file" id="fileInput" multiple style="display: none;" onchange="handleFileSelect(this)">
                    
                    <div id="fileInfo" class="info" style="display: none;">
                        <p id="fileDetails">等待选择文件...</p>
                    </div>
                    
                    <h2>📥 文件下载</h2>
                    <p>点击下面的链接下载示例文件：</p>
                    <button class="btn" onclick="downloadText()">📄 下载文本文件</button>
                    <button class="btn" onclick="downloadJson()">📊 下载JSON文件</button>
                    
                    <div id="downloadInfo" class="info" style="display: none;">
                        <p id="downloadDetails">等待下载...</p>
                    </div>
                </div>
                
                <script>
                    function selectFile() {
                        document.getElementById('fileInput').click();
                    }
                    
                    function handleFileSelect(input) {
                        const files = input.files;
                        if (files.length > 0) {
                            let info = '已选择 ' + files.length + ' 个文件:\\n';
                            for (let i = 0; i < files.length; i++) {
                                const file = files[i];
                                const size = (file.size / 1024).toFixed(2);
                                info += (i + 1) + '. ' + file.name + ' (' + size + ' KB)\\n';
                            }
                            
                            document.getElementById('fileDetails').innerText = info;
                            document.getElementById('fileInfo').style.display = 'block';
                            
                            // 通知 Android
                            if (window.AndroidInterface) {
                                window.AndroidInterface.onFileSelected(files.length);
                            }
                        }
                    }
                    
                    function downloadText() {
                        const content = '这是一个测试文本文件\\n下载时间：' + new Date().toLocaleString();
                        downloadFile(content, 'test.txt', 'text/plain');
                    }
                    
                    function downloadJson() {
                        const data = {
                            message: '测试JSON文件',
                            timestamp: new Date().toISOString(),
                            data: [1, 2, 3, 4, 5]
                        };
                        const content = JSON.stringify(data, null, 2);
                        downloadFile(content, 'data.json', 'application/json');
                    }
                    
                    function downloadFile(content, filename, type) {
                        const blob = new Blob([content], { type: type });
                        const url = URL.createObjectURL(blob);
                        const a = document.createElement('a');
                        a.href = url;
                        a.download = filename;
                        document.body.appendChild(a);
                        a.click();
                        document.body.removeChild(a);
                        URL.revokeObjectURL(url);
                        
                        document.getElementById('downloadDetails').innerText = '已下载：' + filename;
                        document.getElementById('downloadInfo').style.display = 'block';
                        
                        // 通知 Android
                        if (window.AndroidInterface) {
                            window.AndroidInterface.onFileDownloaded(filename);
                        }
                    }
                </script>
            </body>
            </html>
        """.trimIndent()
        
        // 添加 JavaScript 接口
        webView.addJavascriptInterface(object {
            @JavascriptInterface
            fun onFileSelected(count: Int) {
                runOnUiThread {
                    statusText.text = "已选择 $count 个文件"
                    Toast.makeText(this@FileOperationActivity, "文件选择成功", Toast.LENGTH_SHORT).show()
                }
            }
            
            @JavascriptInterface
            fun onFileDownloaded(filename: String) {
                runOnUiThread {
                    statusText.text = "已下载：$filename"
                    Toast.makeText(this@FileOperationActivity, "文件下载成功", Toast.LENGTH_SHORT).show()
                }
            }
        }, "AndroidInterface")
        
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
    }

    private fun openFileChooser() {
        if (checkStoragePermission()) {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "*/*"
                addCategory(Intent.CATEGORY_OPENABLE)
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            }
            startActivityForResult(Intent.createChooser(intent, "选择文件"), REQUEST_FILE_CHOOSER)
        }
    }

    private fun downloadTestFile() {
        if (checkStoragePermission()) {
            try {
                val content = "这是一个测试下载文件\n创建时间：${System.currentTimeMillis()}"
                val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val file = File(downloadsDir, "webview_test_${System.currentTimeMillis()}.txt")
                
                FileOutputStream(file).use { fos ->
                    fos.write(content.toByteArray())
                }
                
                statusText.text = "文件已下载到：${file.absolutePath}"
                Toast.makeText(this, "下载成功", Toast.LENGTH_SHORT).show()
                
            } catch (e: Exception) {
                Log.e(TAG, "Download failed", e)
                statusText.text = "下载失败：${e.message}"
                Toast.makeText(this, "下载失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkStoragePermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) 
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, 
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 
                REQUEST_STORAGE_PERMISSION)
            false
        } else {
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        when (requestCode) {
            REQUEST_FILE_CHOOSER -> {
                val results = if (resultCode == Activity.RESULT_OK && data != null) {
                    when {
                        data.clipData != null -> {
                            val clipData = data.clipData!!
                            Array(clipData.itemCount) { i ->
                                clipData.getItemAt(i).uri
                            }
                        }
                        data.data != null -> arrayOf(data.data!!)
                        else -> null
                    }
                } else null
                
                fileChooserCallback?.onReceiveValue(results)
                fileChooserCallback = null
                
                if (results != null) {
                    statusText.text = "已选择 ${results.size} 个文件"
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "存储权限已授予", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "需要存储权限才能使用文件功能", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
} 