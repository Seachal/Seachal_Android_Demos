# CheckBox 和 RadioButton 使用指南

## 📋 项目概述

本示例展示了Android中CheckBox（复选框）和RadioButton（单选按钮）的完整使用方法，包括：

- ✅ 基础功能实现
- 🎨 自定义样式和颜色
- 🔧 事件监听和状态管理
- 💡 实际应用场景示例

## 🚀 功能特性

### CheckBox 功能
- 默认样式CheckBox
- 多种自定义样式（XML定义、tint属性、代码设置）
- 全选/取消全选功能
- 部分选中状态支持
- 实时结果显示

### RadioButton 功能
- 默认样式RadioButton
- 自定义颜色和样式
- RadioGroup组管理
- 多组互不影响的选择
- 动态结果更新

## 📂 文件结构

```
seachaltest/src/main/
├── java/com/seachal/seachaltest/checkbox_radiobutton/
│   └── CheckBoxRadioButtonActivity.kt           # 主要功能实现
├── res/
│   ├── layout/
│   │   └── activity_checkbox_radiobutton.xml    # 界面布局
│   ├── values/
│   │   └── colors_checkbox_radiobutton.xml      # 颜色定义
│   └── drawable/
│       ├── checkbox_custom_selector.xml         # CheckBox自定义样式
│       ├── radiobutton_red_selector.xml         # RadioButton自定义样式
│       └── bg_result_text.xml                   # 结果显示背景
```

## 🎯 核心实现

### 1. 自定义 CheckBox 样式

#### 方法一：XML Selector（推荐）
```xml
<!-- checkbox_custom_selector.xml -->
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 选中状态 -->
    <item android:state_checked="true">
        <shape android:shape="rectangle">
            <corners android:radius="4dp" />
            <solid android:color="@color/checkbox_red_checked" />
            <stroke android:width="2dp" android:color="@color/checkbox_red_checked" />
        </shape>
    </item>
    
    <!-- 未选中状态 -->
    <item android:state_checked="false">
        <shape android:shape="rectangle">
            <corners android:radius="4dp" />
            <solid android:color="@android:color/transparent" />
            <stroke android:width="2dp" android:color="@color/checkbox_red_normal" />
        </shape>
    </item>
</selector>
```

使用方式：
```xml
<CheckBox
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:button="@drawable/checkbox_custom_selector"
    android:text="自定义样式 CheckBox"
    android:textColor="@color/text_red" />
```

#### 方法二：使用 buttonTint 属性
```xml
<CheckBox
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:buttonTint="@color/checkbox_green_tint"
    android:text="绿色主题 CheckBox"
    android:textColor="@color/text_green" />
```

#### 方法三：代码动态设置
```kotlin
checkBox.apply {
    // 设置选中时的颜色（需要 API 21+）
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        buttonTintList = ContextCompat.getColorStateList(context, R.color.checkbox_custom_tint)
    }
    
    // 设置文字颜色和大小
    setTextColor(ContextCompat.getColor(context, R.color.text_custom))
    textSize = 16f
}
```

### 2. 自定义 RadioButton 样式

#### XML Selector 方式
```xml
<!-- radiobutton_red_selector.xml -->
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 选中状态 -->
    <item android:state_checked="true">
        <shape android:shape="oval">
            <solid android:color="@color/radiobutton_red_checked" />
            <stroke android:width="2dp" android:color="@color/radiobutton_red_checked" />
            <size android:width="20dp" android:height="20dp" />
        </shape>
    </item>
    
    <!-- 未选中状态 -->
    <item android:state_checked="false">
        <shape android:shape="oval">
            <solid android:color="@android:color/transparent" />
            <stroke android:width="2dp" android:color="@color/radiobutton_red_normal" />
            <size android:width="20dp" android:height="20dp" />
        </shape>
    </item>
</selector>
```

### 3. 事件监听实现

#### CheckBox 监听
```kotlin
checkBox.setOnCheckedChangeListener { _, isChecked ->
    showToast("CheckBox: ${if (isChecked) "选中" else "未选中"}")
    updateUI()
}
```

#### RadioGroup 监听
```kotlin
radioGroup.setOnCheckedChangeListener { _, checkedId ->
    val selectedText = when (checkedId) {
        R.id.rb_option1 -> "选项一"
        R.id.rb_option2 -> "选项二"
        R.id.rb_option3 -> "选项三"
        else -> "未选择"
    }
    showToast("选择了: $selectedText")
}
```

### 4. 全选功能实现

```kotlin
private fun updateSelectAllStatus() {
    val allChecked = hobbyCheckBoxes.all { it.isChecked }
    val noneChecked = hobbyCheckBoxes.none { it.isChecked }
    
    cbSelectAll.setOnCheckedChangeListener(null) // 临时移除监听
    
    when {
        allChecked -> {
            cbSelectAll.isChecked = true
            cbSelectAll.isIndeterminate = false
        }
        noneChecked -> {
            cbSelectAll.isChecked = false
            cbSelectAll.isIndeterminate = false
        }
        else -> {
            // 部分选中状态
            cbSelectAll.isChecked = false
            cbSelectAll.isIndeterminate = true
        }
    }
    
    // 重新设置监听器
    cbSelectAll.setOnCheckedChangeListener { _, isChecked ->
        hobbyCheckBoxes.forEach { it.isChecked = isChecked }
        updateCheckBoxResult()
    }
}
```

## 🎨 样式配置

### 颜色定义
```xml
<!-- colors_checkbox_radiobutton.xml -->
<resources>
    <!-- 文本颜色 -->
    <color name="text_red">#E53E3E</color>
    <color name="text_green">#38A169</color>
    <color name="text_blue">#3182CE</color>
    
    <!-- CheckBox 颜色 -->
    <color name="checkbox_green_tint">#38A169</color>
    <color name="checkbox_red_normal">#FEB2B2</color>
    <color name="checkbox_red_checked">#E53E3E</color>
    
    <!-- RadioButton 颜色 -->
    <color name="radiobutton_green_tint">#38A169</color>
    <color name="radiobutton_blue_tint">#3182CE</color>
    
    <!-- 通用颜色 -->
    <color name="gender_tint">#805AD5</color>
    <color name="hobby_tint">#ED8936</color>
    <color name="divider_color">#E2E8F0</color>
</resources>
```

## 💡 最佳实践

### 1. 性能优化
- 使用 `setOnCheckedChangeListener(null)` 临时移除监听器，避免递归调用
- 批量操作时先移除监听器，操作完成后再重新设置
- 使用 `isIndeterminate` 属性显示部分选中状态

### 2. 用户体验
- 提供即时反馈（Toast 提示）
- 实时更新结果显示
- 使用图标和 Emoji 增强视觉效果
- 合理的间距和分组

### 3. 样式设计
- 保持一致的设计风格
- 使用语义化的颜色
- 适当的圆角和边框
- 考虑不同主题模式

## 🔧 使用方法

1. **运行示例**：在主菜单中选择"按钮组件" > "CheckBox和RadioButton示例"

2. **功能测试**：
   - 点击不同样式的 CheckBox 观察效果
   - 使用全选功能测试批量操作
   - 在 RadioGroup 中选择不同选项
   - 点击"显示选择结果"查看汇总

3. **自定义样式**：参考示例代码修改颜色和样式

## 📱 效果展示

- **默认样式**：系统原生样式
- **自定义样式1**：红色方形 CheckBox
- **自定义样式2**：绿色圆形样式
- **全选功能**：支持全选/取消全选/部分选中
- **分组选择**：性别选择（水平布局）
- **多选列表**：颜色选择（垂直布局）

## 🛠️ 扩展功能

可以基于此示例扩展的功能：
- 动态添加/删除选项
- 数据持久化存储
- 网络请求获取选项
- 表单验证
- 多级联动选择

## 📚 相关知识点

- Android 视图事件处理
- 自定义 Drawable 和 Selector
- Material Design 设计规范
- 状态管理和数据绑定
- 用户界面最佳实践

---

*本示例遵循 Android 开发最佳实践，代码结构清晰，注释完整，适合学习和参考使用。* 