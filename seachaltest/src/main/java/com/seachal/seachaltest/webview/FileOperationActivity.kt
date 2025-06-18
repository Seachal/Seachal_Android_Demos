package com.seachal.seachaltest.webview

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.MenuItem
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R
import java.io.File

/**
 * WebView 文件操作示例
 * 
 * 本示例演示了 WebView 中的文件操作功能：
 * 1. 文件上传功能（支持多种文件类型）
 * 2. 文件下载功能
 * 3. 权限处理（存储权限）
 * 4. 自定义下载目录
 * 5. 下载进度监听
 * 6. 文件类型过滤
 * 
 * 遵循阿里巴巴 Android 开发规范：
 * - 运行时权限请求和处理
 * - 安全的文件访问
 * - 错误处理和用户反馈
 * 
 * @author Seachal
 * @since 2025-01-27
 */
class FileOperationActivity : AppCompatActivity() {
    
    companion object {
        private const val TAG = "FileOperationActivity"
        private const val REQUEST_STORAGE_PERMISSION = 1001
        private const val REQUEST_FILE_CHOOSER = 1002
    }
    
    private lateinit var webView: WebView
    private var fileUploadCallback: ValueCallback<Array<Uri>>? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_operation)
        
        setupActionBar()
        initViews()
        setupWebView()
        checkStoragePermission()
        loadDemoPage()
    }
    
    /**
     * 设置标题栏
     */
    private fun setupActionBar() {
        supportActionBar?.apply {
            title = "文件上传下载示例"
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
        val webSettings = webView.settings
        
        // 基础设置
        webSettings.apply {
            // 启用 JavaScript
            javaScriptEnabled = true
            
            // 启用 DOM Storage
            domStorageEnabled = true
            
            // 允许文件访问（文件上传需要）
            allowFileAccess = true
            allowContentAccess = true
            
            // 其他设置
            useWideViewPort = true
            loadWithOverviewMode = true
            cacheMode = WebSettings.LOAD_NO_CACHE
        }
        
        // 设置 WebViewClient
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d(TAG, "Page loaded successfully")
            }
        }
        
        // 设置 WebChromeClient（处理文件上传）
        webView.webChromeClient = object : WebChromeClient() {
            
            // 处理文件上传（Android 5.0+）
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                Log.d(TAG, "onShowFileChooser called")
                
                // 保存回调
                fileUploadCallback = filePathCallback
                
                // 创建文件选择器
                val intent = createFileChooserIntent(fileChooserParams)
                
                try {
                    startActivityForResult(intent, REQUEST_FILE_CHOOSER)
                    return true
                } catch (e: Exception) {
                    Log.e(TAG, "Error starting file chooser", e)
                    Toast.makeText(this@FileOperationActivity, "无法打开文件选择器", Toast.LENGTH_SHORT).show()
                    fileUploadCallback?.onReceiveValue(null)
                    fileUploadCallback = null
                    return false
                }
            }
        }
        
        // 设置下载监听器
        webView.setDownloadListener { url, userAgent, contentDisposition, mimeType, contentLength ->
            Log.d(TAG, "Download requested: $url")
            downloadFile(url, contentDisposition, mimeType)
        }
    }
    
    /**
     * 创建文件选择器 Intent
     */
    private fun createFileChooserIntent(fileChooserParams: WebChromeClient.FileChooserParams?): Intent {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        
        // 根据参数设置文件类型
        val acceptTypes = fileChooserParams?.acceptTypes
        if (acceptTypes != null && acceptTypes.isNotEmpty()) {
            val mimeType = acceptTypes[0]
            intent.type = if (mimeType.isNotEmpty()) mimeType else "*/*"
            Log.d(TAG, "File chooser MIME type: $mimeType")
        } else {
            intent.type = "*/*"
        }
        
        // 支持多选
        if (fileChooserParams?.mode == WebChromeClient.FileChooserParams.MODE_OPEN_MULTIPLE) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            Log.d(TAG, "Multiple file selection enabled")
        }
        
        return Intent.createChooser(intent, "选择文件")
    }
    
    /**
     * 下载文件
     */
    private fun downloadFile(url: String, contentDisposition: String, mimeType: String) {
        Log.d(TAG, "Starting download: $url")
        
        if (!hasStoragePermission()) {
            Toast.makeText(this, "需要存储权限才能下载文件", Toast.LENGTH_SHORT).show()
            requestStoragePermission()
            return
        }
        
        try {
            // 提取文件名
            val fileName = extractFileName(url, contentDisposition)
            Log.d(TAG, "Download filename: $fileName")
            
            // 创建下载请求
            val request = DownloadManager.Request(Uri.parse(url)).apply {
                setTitle("下载文件")
                setDescription("正在下载 $fileName")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                setMimeType(mimeType)
                
                // 设置请求头
                addRequestHeader("User-Agent", webView.settings.userAgentString)
            }
            
            // 开始下载
            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadId = downloadManager.enqueue(request)
            
            Toast.makeText(this, "开始下载: $fileName", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Download started with ID: $downloadId")
            
        } catch (e: Exception) {
            Log.e(TAG, "Error starting download", e)
            Toast.makeText(this, "下载失败: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
    /**
     * 提取文件名
     */
    private fun extractFileName(url: String, contentDisposition: String): String {
        return try {
            // 首先尝试从 Content-Disposition 头提取
            if (contentDisposition.isNotEmpty()) {
                val filenameIndex = contentDisposition.indexOf("filename=")
                if (filenameIndex >= 0) {
                    var filename = contentDisposition.substring(filenameIndex + 9)
                    if (filename.startsWith("\"") && filename.endsWith("\"")) {
                        filename = filename.substring(1, filename.length - 1)
                    }
                    if (filename.isNotEmpty()) {
                        return filename
                    }
                }
            }
            
            // 从 URL 提取文件名
            val uri = Uri.parse(url)
            val pathSegments = uri.pathSegments
            if (pathSegments.isNotEmpty()) {
                val lastSegment = pathSegments.last()
                if (lastSegment.contains(".")) {
                    return lastSegment
                }
            }
            
            // 默认文件名
            "download_${System.currentTimeMillis()}.bin"
        } catch (e: Exception) {
            Log.e(TAG, "Error extracting filename", e)
            "download_${System.currentTimeMillis()}.bin"
        }
    }
    
    /**
     * 检查存储权限
     */
    private fun checkStoragePermission() {
        if (!hasStoragePermission()) {
            requestStoragePermission()
        }
    }
    
    /**
     * 是否有存储权限
     */
    private fun hasStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * 请求存储权限
     */
    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_STORAGE_PERMISSION
        )
    }
    
    /**
     * 加载演示页面
     */
    private fun loadDemoPage() {
        val htmlContent = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>文件操作演示</title>
                <style>
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
                        margin: 0;
                        padding: 20px;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        color: white;
                        line-height: 1.6;
                    }
                    .container {
                        background: rgba(255,255,255,0.1);
                        padding: 20px;
                        border-radius: 15px;
                        backdrop-filter: blur(10px);
                        margin-bottom: 20px;
                    }
                    .title {
                        text-align: center;
                        font-size: 24px;
                        margin-bottom: 20px;
                        color: #fff;
                    }
                    .section {
                        background: rgba(255,255,255,0.1);
                        padding: 15px;
                        border-radius: 10px;
                        margin: 15px 0;
                    }
                    .file-input {
                        width: 100%;
                        padding: 12px;
                        border: 2px dashed rgba(255,255,255,0.3);
                        border-radius: 8px;
                        background: rgba(255,255,255,0.1);
                        color: white;
                        margin: 10px 0;
                        cursor: pointer;
                        transition: all 0.3s ease;
                    }
                    .file-input:hover {
                        border-color: rgba(255,255,255,0.6);
                        background: rgba(255,255,255,0.2);
                    }
                    .btn {
                        background: #4CAF50;
                        color: white;
                        padding: 12px 24px;
                        border: none;
                        border-radius: 8px;
                        cursor: pointer;
                        margin: 8px 4px;
                        font-size: 14px;
                        transition: background 0.3s;
                        text-decoration: none;
                        display: inline-block;
                    }
                    .btn:hover {
                        background: #45a049;
                    }
                    .btn-download {
                        background: #2196F3;
                    }
                    .btn-download:hover {
                        background: #0b7dda;
                    }
                    .btn-danger {
                        background: #f44336;
                    }
                    .btn-danger:hover {
                        background: #da190b;
                    }
                    .file-info {
                        background: rgba(0,0,0,0.3);
                        padding: 10px;
                        border-radius: 5px;
                        margin: 10px 0;
                        font-family: monospace;
                        font-size: 12px;
                        min-height: 20px;
                    }
                    .upload-area {
                        border: 2px dashed rgba(255,255,255,0.3);
                        border-radius: 10px;
                        padding: 20px;
                        text-align: center;
                        margin: 15px 0;
                        transition: all 0.3s ease;
                    }
                    .upload-area.dragover {
                        border-color: #4CAF50;
                        background: rgba(76, 175, 80, 0.1);
                    }
                    .preview-container {
                        display: flex;
                        flex-wrap: wrap;
                        gap: 10px;
                        margin: 15px 0;
                    }
                    .preview-item {
                        background: rgba(255,255,255,0.1);
                        padding: 10px;
                        border-radius: 8px;
                        max-width: 200px;
                        text-align: center;
                    }
                    .preview-item img {
                        max-width: 100%;
                        max-height: 100px;
                        border-radius: 5px;
                    }
                    .download-link {
                        color: #FFE082;
                        text-decoration: none;
                        padding: 8px 16px;
                        background: rgba(255,255,255,0.1);
                        border-radius: 5px;
                        margin: 5px;
                        display: inline-block;
                        transition: background 0.3s;
                    }
                    .download-link:hover {
                        background: rgba(255,255,255,0.2);
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1 class="title">📁 文件操作演示</h1>
                    
                    <div class="section">
                        <h3>📤 文件上传</h3>
                        <p>选择文件进行上传测试：</p>
                        
                        <!-- 单文件上传 -->
                        <label for="single-file">单文件上传：</label>
                        <input type="file" id="single-file" class="file-input" onchange="handleSingleFileUpload(this)">
                        
                        <!-- 多文件上传 -->
                        <label for="multiple-files">多文件上传：</label>
                        <input type="file" id="multiple-files" class="file-input" multiple onchange="handleMultipleFileUpload(this)">
                        
                        <!-- 图片上传 -->
                        <label for="image-files">图片上传：</label>
                        <input type="file" id="image-files" class="file-input" accept="image/*" multiple onchange="handleImageUpload(this)">
                        
                        <!-- 拖拽上传区域 -->
                        <div class="upload-area" id="drag-area" ondrop="handleDrop(event)" ondragover="handleDragOver(event)" ondragleave="handleDragLeave(event)">
                            <p>📋 拖拽文件到此处上传</p>
                            <p style="font-size: 12px; opacity: 0.7;">或点击选择文件</p>
                            <input type="file" id="drag-file" style="display: none;" multiple onchange="handleDragFileUpload(this)">
                        </div>
                        
                        <div class="file-info" id="upload-info">等待选择文件...</div>
                        
                        <!-- 文件预览 -->
                        <div class="preview-container" id="preview-container"></div>
                    </div>
                    
                    <div class="section">
                        <h3>📥 文件下载</h3>
                        <p>点击下面的链接下载测试文件：</p>
                        
                        <!-- 文本文件下载 -->
                        <a href="javascript:void(0)" onclick="downloadTextFile()" class="download-link">📄 下载文本文件 (test.txt)</a>
                        
                        <!-- JSON 文件下载 -->
                        <a href="javascript:void(0)" onclick="downloadJsonFile()" class="download-link">📊 下载 JSON 文件 (data.json)</a>
                        
                        <!-- CSV 文件下载 -->
                        <a href="data:text/csv;charset=utf-8,姓名,年龄,城市%0A张三,25,北京%0A李四,30,上海%0A王五,28,广州" 
                           download="data.csv" class="download-link">📈 下载 CSV 文件 (data.csv)</a>
                        
                        <!-- 动态生成下载 -->
                        <button class="btn btn-download" onclick="generateAndDownload()">🔧 生成并下载文件</button>
                        
                        <!-- 外部文件下载（需要网络） -->
                        <div style="margin-top: 15px;">
                            <p>外部文件下载测试：</p>
                            <a href="https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf" 
                               class="download-link">📕 下载 PDF 文件</a>
                            <a href="https://sample-videos.com/zip/10/mp4/SampleVideo_1280x720_1mb.mp4" 
                               class="download-link">🎬 下载视频文件</a>
                        </div>
                        
                        <div class="file-info" id="download-info">点击链接开始下载...</div>
                    </div>
                    
                    <div class="section">
                        <h3>🔧 文件操作工具</h3>
                        <button class="btn" onclick="showFileInfo()">📋 显示文件信息</button>
                        <button class="btn btn-danger" onclick="clearPreviews()">🗑️ 清除预览</button>
                        <button class="btn btn-download" onclick="testBlobDownload()">💾 测试 Blob 下载</button>
                    </div>
                </div>
                
                <script>
                    let uploadedFiles = [];
                    
                    // 单文件上传处理
                    function handleSingleFileUpload(input) {
                        const file = input.files[0];
                        if (file) {
                            displayFileInfo([file], 'single');
                            console.log('Single file selected:', file.name);
                        }
                    }
                    
                    // 多文件上传处理
                    function handleMultipleFileUpload(input) {
                        const files = Array.from(input.files);
                        if (files.length > 0) {
                            displayFileInfo(files, 'multiple');
                            console.log('Multiple files selected:', files.map(f => f.name));
                        }
                    }
                    
                    // 图片上传处理
                    function handleImageUpload(input) {
                        const files = Array.from(input.files);
                        if (files.length > 0) {
                            displayFileInfo(files, 'image');
                            createImagePreviews(files);
                            console.log('Image files selected:', files.map(f => f.name));
                        }
                    }
                    
                    // 拖拽上传处理
                    function handleDrop(event) {
                        event.preventDefault();
                        const dragArea = document.getElementById('drag-area');
                        dragArea.classList.remove('dragover');
                        
                        const files = Array.from(event.dataTransfer.files);
                        if (files.length > 0) {
                            displayFileInfo(files, 'drag');
                            createImagePreviews(files.filter(f => f.type.startsWith('image/')));
                            console.log('Files dropped:', files.map(f => f.name));
                        }
                    }
                    
                    function handleDragOver(event) {
                        event.preventDefault();
                        document.getElementById('drag-area').classList.add('dragover');
                    }
                    
                    function handleDragLeave(event) {
                        event.preventDefault();
                        document.getElementById('drag-area').classList.remove('dragover');
                    }
                    
                    function handleDragFileUpload(input) {
                        const files = Array.from(input.files);
                        if (files.length > 0) {
                            displayFileInfo(files, 'drag-input');
                            createImagePreviews(files.filter(f => f.type.startsWith('image/')));
                        }
                    }
                    
                    // 显示文件信息
                    function displayFileInfo(files, type) {
                        uploadedFiles = files;
                        const info = document.getElementById('upload-info');
                        
                        let infoText = `[${type.toUpperCase()}] 已选择 ${files.length} 个文件:\\n`;
                        files.forEach((file, index) => {
                            const size = (file.size / 1024).toFixed(2);
                            infoText += `${index + 1}. ${file.name} (${size} KB, ${file.type || '未知类型'})\\n`;
                        });
                        
                        info.textContent = infoText;
                    }
                    
                    // 创建图片预览
                    function createImagePreviews(imageFiles) {
                        const container = document.getElementById('preview-container');
                        
                        imageFiles.forEach(file => {
                            if (file.type.startsWith('image/')) {
                                const reader = new FileReader();
                                reader.onload = function(e) {
                                    const preview = document.createElement('div');
                                    preview.className = 'preview-item';
                                    preview.innerHTML = `
                                        <img src="${e.target.result}" alt="${file.name}">
                                        <p style="font-size: 12px; margin: 5px 0;">${file.name}</p>
                                    `;
                                    container.appendChild(preview);
                                };
                                reader.readAsDataURL(file);
                            }
                        });
                    }
                    
                    // 下载文本文件
                    function downloadTextFile() {
                        const content = '这是一个测试文本文件的内容。\\n可以包含多行内容。\\n下载时间：' + new Date().toLocaleString();
                        const blob = new Blob([content], { type: 'text/plain;charset=utf-8' });
                        const url = URL.createObjectURL(blob);
                        const a = document.createElement('a');
                        a.href = url;
                        a.download = 'test.txt';
                        document.body.appendChild(a);
                        a.click();
                        document.body.removeChild(a);
                        URL.revokeObjectURL(url);
                    }
                    
                    // 下载JSON文件
                    function downloadJsonFile() {
                        const data = {
                            name: "测试数据",
                            timestamp: new Date().toISOString(),
                            data: [1, 2, 3, 4, 5],
                            nested: { key: "value" }
                        };
                        const content = JSON.stringify(data, null, 2);
                        const blob = new Blob([content], { type: 'application/json;charset=utf-8' });
                        const url = URL.createObjectURL(blob);
                        const a = document.createElement('a');
                        a.href = url;
                        a.download = 'data.json';
                        document.body.appendChild(a);
                        a.click();
                        document.body.removeChild(a);
                        URL.revokeObjectURL(url);
                    }
                    
                    // 生成并下载文件
                    function generateAndDownload() {
                        const content = '文件生成时间: ' + new Date().toLocaleString() + '\\n' +
                            '用户代理: ' + navigator.userAgent + '\\n' +
                            '屏幕尺寸: ' + screen.width + 'x' + screen.height + '\\n' +
                            '语言: ' + navigator.language + '\\n' +
                            '在线状态: ' + (navigator.onLine ? '在线' : '离线') + '\\n\\n' +
                            '这是一个动态生成的文件内容。\\n包含当前的系统信息和时间戳。';
                        
                        const blob = new Blob([content], { type: 'text/plain;charset=utf-8' });
                        const url = URL.createObjectURL(blob);
                        const a = document.createElement('a');
                        a.href = url;
                        a.download = `generated_${Date.now()}.txt`;
                        document.body.appendChild(a);
                        a.click();
                        document.body.removeChild(a);
                        URL.revokeObjectURL(url);
                        
                        document.getElementById('download-info').textContent = `已生成并下载文件: ${a.download}`;
                    }
                    
                    // 测试 Blob 下载
                    function testBlobDownload() {
                        const data = {
                            message: "这是一个测试 Blob 下载",
                            timestamp: new Date().toISOString(),
                            fileInfo: uploadedFiles.map(f => ({
                                name: f.name,
                                size: f.size,
                                type: f.type,
                                lastModified: new Date(f.lastModified).toISOString()
                            }))
                        };
                        
                        const blob = new Blob([JSON.stringify(data, null, 2)], { 
                            type: 'application/json;charset=utf-8' 
                        });
                        const url = URL.createObjectURL(blob);
                        const a = document.createElement('a');
                        a.href = url;
                        a.download = `blob_test_${Date.now()}.json`;
                        document.body.appendChild(a);
                        a.click();
                        document.body.removeChild(a);
                        URL.revokeObjectURL(url);
                        
                        document.getElementById('download-info').textContent = `Blob 下载完成: ${a.download}`;
                    }
                    
                    // 显示文件信息
                    function showFileInfo() {
                        if (uploadedFiles.length === 0) {
                            alert('请先选择文件');
                            return;
                        }
                        
                        let info = `文件详细信息:\\n\\n`;
                        uploadedFiles.forEach((file, index) => {
                            info += `文件 ${index + 1}:\\n`;
                            info += `  名称: ${file.name}\\n`;
                            info += `  大小: ${(file.size / 1024).toFixed(2)} KB\\n`;
                            info += `  类型: ${file.type || '未知'}\\n`;
                            info += `  最后修改: ${new Date(file.lastModified).toLocaleString()}\\n\\n`;
                        });
                        
                        alert(info);
                    }
                    
                    // 清除预览
                    function clearPreviews() {
                        document.getElementById('preview-container').innerHTML = '';
                        document.getElementById('upload-info').textContent = '预览已清除，等待选择文件...';
                        uploadedFiles = [];
                        
                        // 清除所有文件输入
                        document.querySelectorAll('input[type="file"]').forEach(input => {
                            input.value = '';
                        });
                    }
                    
                    // 点击拖拽区域选择文件
                    document.getElementById('drag-area').addEventListener('click', function() {
                        document.getElementById('drag-file').click();
                    });
                    
                    // 监听下载链接点击
                    document.querySelectorAll('.download-link').forEach(link => {
                        link.addEventListener('click', function(e) {
                            const filename = this.getAttribute('download') || '未知文件';
                            document.getElementById('download-info').textContent = `开始下载: ${filename}`;
                            console.log('Download started:', filename);
                        });
                    });
                    
                    console.log('File operation demo page loaded');
                </script>
            </body>
            </html>
        """.trimIndent()
        
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        when (requestCode) {
            REQUEST_FILE_CHOOSER -> {
                Log.d(TAG, "File chooser result: $resultCode")
                
                val results = if (resultCode == RESULT_OK && data != null) {
                    // 处理选择的文件
                    when {
                        data.clipData != null -> {
                            // 多个文件
                            val clipData = data.clipData!!
                            Array(clipData.itemCount) { i ->
                                clipData.getItemAt(i).uri
                            }
                        }
                        data.data != null -> {
                            // 单个文件
                            arrayOf(data.data!!)
                        }
                        else -> null
                    }
                } else {
                    null
                }
                
                // 返回结果给 WebView
                fileUploadCallback?.onReceiveValue(results)
                fileUploadCallback = null
                
                // 记录选择的文件
                results?.let { uris ->
                    Log.d(TAG, "Selected ${uris.size} file(s)")
                    uris.forEach { uri ->
                        Log.d(TAG, "Selected file: $uri")
                    }
                    Toast.makeText(this, "已选择 ${uris.size} 个文件", Toast.LENGTH_SHORT).show()
                } ?: run {
                    Log.d(TAG, "No files selected")
                    Toast.makeText(this, "未选择文件", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        when (requestCode) {
            REQUEST_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Storage permission granted")
                    Toast.makeText(this, "存储权限已授予", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d(TAG, "Storage permission denied")
                    Toast.makeText(this, "存储权限被拒绝，无法下载文件", Toast.LENGTH_LONG).show()
                }
            }
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
    
    override fun onDestroy() {
        super.onDestroy()
        
        // 清理文件上传回调
        fileUploadCallback?.onReceiveValue(null)
        fileUploadCallback = null
        
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