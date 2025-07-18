# OrangeProgressBar 颜色自定义使用指南

## 概述

`OrangeProgressBar` 是一个功能强大的自定义进度条组件，支持完全自定义的颜色配置。开发者可以通过 XML 属性或代码方法灵活设置三个主要颜色属性，使进度条适应各种设计主题。

## 支持的颜色属性

### 1. progressColor - 进度颜色
- **作用**: 设置进度条的填充颜色（包含渐变效果）
- **默认值**: `#FF8A50`（橙色）
- **特性**: 自动生成基于此颜色的渐变效果

### 2. backgroundColor - 背景颜色  
- **作用**: 设置进度条的背景轨道颜色
- **默认值**: `#EEEEEE`（浅灰色）

### 3. thumbColor - 指示器颜色
- **作用**: 设置圆形指示器的颜色
- **默认值**: `#FF5722`（深橙色）

## XML 中使用自定义颜色

### 基本语法

```xml
<com.seachal.seachaltest.customview.OrangeProgressBar
    android:id="@+id/customProgressBar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:progressBarHeight="12dp"
    app:thumbSize="18dp"
    app:orangeProgress="75"
    app:orangeMaxProgress="100"
    app:progressColor="#2196F3"
    app:backgroundColor="#E3F2FD"
    app:thumbColor="#1976D2" />
```

### 完整示例 - 不同颜色主题

#### 蓝色主题
```xml
<com.seachal.seachaltest.customview.OrangeProgressBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:progressColor="#2196F3"
    app:backgroundColor="#E3F2FD"
    app:thumbColor="#1976D2"
    app:orangeProgress="65" />
```

#### 绿色主题
```xml
<com.seachal.seachaltest.customview.OrangeProgressBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:progressColor="#4CAF50"
    app:backgroundColor="#E8F5E8"
    app:thumbColor="#2E7D32"
    app:orangeProgress="80" />
```

#### 紫色主题
```xml
<com.seachal.seachaltest.customview.OrangeProgressBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:progressColor="#9C27B0"
    app:backgroundColor="#F3E5F5"
    app:thumbColor="#7B1FA2"
    app:orangeProgress="45" />
```

## 代码中动态设置颜色

### 方法一：单独设置每个颜色

```kotlin
val progressBar = findViewById<OrangeProgressBar>(R.id.progressBar)

// 单独设置进度颜色
progressBar.setProgressColor(Color.parseColor("#E91E63"))

// 单独设置背景颜色
progressBar.setProgressBackgroundColor(Color.parseColor("#FCE4EC"))

// 单独设置指示器颜色
progressBar.setThumbColor(Color.parseColor("#C2185B"))
```

### 方法二：同时设置三个颜色

```kotlin
val progressBar = findViewById<OrangeProgressBar>(R.id.progressBar)

// 同时设置三个颜色（推荐方式，性能更好）
progressBar.setColors(
    progressColor = Color.parseColor("#E91E63"),    // 进度颜色
    backgroundColor = Color.parseColor("#FCE4EC"),  // 背景颜色
    thumbColor = Color.parseColor("#C2185B")        // 指示器颜色
)
```

### 获取当前颜色值

```kotlin
val progressBar = findViewById<OrangeProgressBar>(R.id.progressBar)

// 获取当前设置的颜色
val currentProgressColor = progressBar.getProgressColor()
val currentBackgroundColor = progressBar.getProgressBackgroundColor()  
val currentThumbColor = progressBar.getThumbColor()

Log.d("Colors", "进度: $currentProgressColor, 背景: $currentBackgroundColor, 指示器: $currentThumbColor")
```

## 颜色主题设计建议

### 1. 对比度原则
- 确保进度颜色与背景颜色有足够的对比度
- 指示器颜色应该比进度颜色稍深，以便清晰可见

### 2. 常用配色方案

#### Material Design 风格
```kotlin
// 蓝色系
Triple(Color.parseColor("#2196F3"), Color.parseColor("#E3F2FD"), Color.parseColor("#1976D2"))

// 绿色系  
Triple(Color.parseColor("#4CAF50"), Color.parseColor("#E8F5E8"), Color.parseColor("#2E7D32"))

// 红色系
Triple(Color.parseColor("#F44336"), Color.parseColor("#FFEBEE"), Color.parseColor("#D32F2F"))

// 紫色系
Triple(Color.parseColor("#9C27B0"), Color.parseColor("#F3E5F5"), Color.parseColor("#7B1FA2"))
```

#### 渐变效果
组件会自动基于 `progressColor` 生成美观的渐变效果：
- 起始色：比基色稍亮 (亮度 × 1.1)
- 中间色：基色本身
- 结束色：比基色稍暗 (亮度 × 0.8)

### 3. 品牌色适配
```kotlin
// 根据品牌主色自动生成配套颜色
fun generateThemeColors(brandColor: Int): Triple<Int, Int, Int> {
    val hsv = FloatArray(3)
    Color.colorToHSV(brandColor, hsv)
    
    // 生成背景色（降低饱和度和亮度）
    val backgroundHsv = hsv.clone()
    backgroundHsv[1] = backgroundHsv[1] * 0.2f // 降低饱和度
    backgroundHsv[2] = 0.95f // 提高亮度
    val backgroundColor = Color.HSVToColor(backgroundHsv)
    
    // 生成指示器色（稍微加深）
    val thumbHsv = hsv.clone()
    thumbHsv[2] = thumbHsv[2] * 0.8f // 降低亮度
    val thumbColor = Color.HSVToColor(thumbHsv)
    
    return Triple(brandColor, backgroundColor, thumbColor)
}
```

## 完整使用示例

### Activity 代码示例

```kotlin
class MainActivity : Activity() {
    
    private lateinit var progressBar: OrangeProgressBar
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        progressBar = findViewById(R.id.progressBar)
        
        // 设置自定义颜色主题
        setupCustomTheme()
        
        // 设置进度
        progressBar.setProgress(75)
        
        // 带动画设置进度
        progressBar.setProgressWithAnimation(90, 1000)
    }
    
    private fun setupCustomTheme() {
        // 设置蓝色主题
        progressBar.setColors(
            progressColor = Color.parseColor("#2196F3"),
            backgroundColor = Color.parseColor("#E3F2FD"),
            thumbColor = Color.parseColor("#1976D2")
        )
    }
}
```

### XML 布局示例

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- 默认橙色主题 -->
    <com.seachal.seachaltest.customview.OrangeProgressBar
        android:id="@+id/progressBar1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:orangeProgress="65" />

    <!-- 自定义蓝色主题 -->
    <com.seachal.seachaltest.customview.OrangeProgressBar
        android:id="@+id/progressBar2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        app:orangeProgress="80"
        app:progressColor="#2196F3"
        app:backgroundColor="#E3F2FD"
        app:thumbColor="#1976D2" />

</LinearLayout>
```

## 高级技巧

### 1. 动态主题切换

```kotlin
fun switchToNightMode(progressBar: OrangeProgressBar) {
    progressBar.setColors(
        progressColor = Color.parseColor("#BB86FC"),  // 紫色主色
        backgroundColor = Color.parseColor("#121212"), // 深色背景
        thumbColor = Color.parseColor("#985EFF")      // 亮紫色指示器
    )
}

fun switchToLightMode(progressBar: OrangeProgressBar) {
    progressBar.setColors(
        progressColor = Color.parseColor("#6200EE"),  // 标准紫色
        backgroundColor = Color.parseColor("#F5F5F5"), // 浅色背景
        thumbColor = Color.parseColor("#3700B3")      // 深紫色指示器
    )
}
```

### 2. 随机颜色生成

```kotlin
fun setRandomTheme(progressBar: OrangeProgressBar) {
    val random = Random()
    val hue = random.nextFloat() * 360f
    
    val progressColor = Color.HSVToColor(floatArrayOf(hue, 0.7f, 0.8f))
    val backgroundColor = Color.HSVToColor(floatArrayOf(hue, 0.1f, 0.95f))
    val thumbColor = Color.HSVToColor(floatArrayOf(hue, 0.8f, 0.6f))
    
    progressBar.setColors(progressColor, backgroundColor, thumbColor)
}
```

## 注意事项

1. **性能优化**: 使用 `setColors()` 方法同时设置多个颜色比分别调用单独的设置方法性能更好
2. **颜色兼容性**: 确保颜色在不同 Android 版本和设备上的兼容性
3. **无障碍性**: 考虑色盲用户，确保足够的颜色对比度
4. **内存管理**: 颜色更改会重新生成渐变着色器，频繁更改可能影响性能

## 总结

通过以上功能，`OrangeProgressBar` 提供了完全的颜色自定义能力，开发者可以：

- ✅ 在 XML 中声明式设置颜色
- ✅ 在代码中动态修改颜色  
- ✅ 获取当前颜色值
- ✅ 批量设置多个颜色
- ✅ 自动生成渐变效果
- ✅ 适配各种设计主题

这使得组件具有极高的灵活性和复用性，能够满足各种项目的设计需求。 