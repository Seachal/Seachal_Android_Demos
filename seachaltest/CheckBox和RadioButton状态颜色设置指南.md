# CheckBox 和 RadioButton 状态颜色设置指南

## 概述
本指南详细说明如何为 CheckBox 和 RadioButton 设置选中和非选中状态的不同颜色，就像您截图中看到的选中是橙色、未选中是灰色的效果。

## 实现方法

### 方法一：使用 ColorStateList（推荐）

#### 1. 定义基础颜色 (colors.xml)
```xml
<!-- CheckBox 和 RadioButton 状态颜色 -->
<color name="checkbox_checked_color">#FF6B00</color>          <!-- 选中时的橙色 -->
<color name="checkbox_unchecked_color">#CCCCCC</color>        <!-- 未选中时的灰色 -->
<color name="checkbox_disabled_color">#E0E0E0</color>         <!-- 禁用时的浅灰色 -->

<color name="radiobutton_checked_color">#FF6B00</color>       <!-- 选中时的橙色 -->
<color name="radiobutton_unchecked_color">#CCCCCC</color>     <!-- 未选中时的灰色 -->
<color name="radiobutton_disabled_color">#E0E0E0</color>      <!-- 禁用时的浅灰色 -->
```

#### 2. 创建 ColorStateList (color/checkbox_custom_tint.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 选中状态 - 橙色 -->
    <item android:state_checked="true" android:color="@color/checkbox_checked_color" />
    <!-- 禁用状态 - 浅灰色 -->
    <item android:state_enabled="false" android:color="@color/checkbox_disabled_color" />
    <!-- 默认状态（未选中）- 灰色 -->
    <item android:color="@color/checkbox_unchecked_color" />
</selector>
```

#### 3. 在布局文件中使用
```xml
<CheckBox
    android:id="@+id/cb_custom"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="自定义颜色CheckBox"
    android:buttonTint="@color/checkbox_custom_tint"
    android:textColor="@color/text_black" />
```

#### 4. 在代码中设置
```kotlin
// 方法1：使用 buttonTintList (API 21+)
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    cbCustom.buttonTintList = ContextCompat.getColorStateList(this, R.color.checkbox_custom_tint)
}

// 方法2：使用 CompoundButtonCompat (兼容低版本)
CompoundButtonCompat.setButtonTintList(cbCustom, 
    ContextCompat.getColorStateList(this, R.color.checkbox_custom_tint))
```

### 方法二：使用自定义 Drawable

#### 1. 创建选中状态的 Drawable (drawable/checkbox_checked.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="#FF6B00" />
    <corners android:radius="4dp" />
    <stroke android:width="2dp" android:color="#FF6B00" />
</shape>
```

#### 2. 创建未选中状态的 Drawable (drawable/checkbox_unchecked.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@android:color/transparent" />
    <corners android:radius="4dp" />
    <stroke android:width="2dp" android:color="#CCCCCC" />
</shape>
```

#### 3. 创建状态选择器 (drawable/checkbox_selector.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_checked="true" android:drawable="@drawable/checkbox_checked" />
    <item android:drawable="@drawable/checkbox_unchecked" />
</selector>
```

#### 4. 在布局中使用
```xml
<CheckBox
    android:id="@+id/cb_custom_drawable"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="自定义Drawable CheckBox"
    android:button="@drawable/checkbox_selector" />
```

### 方法三：完全代码实现

```kotlin
class CheckBoxRadioButtonActivity : AppCompatActivity() {
    
    private fun setupCustomColors() {
        // 创建 ColorStateList
        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),  // 选中状态
            intArrayOf(-android.R.attr.state_checked), // 未选中状态
            intArrayOf(-android.R.attr.state_enabled)  // 禁用状态
        )
        
        val colors = intArrayOf(
            ContextCompat.getColor(this, R.color.checkbox_checked_color),   // 选中 - 橙色
            ContextCompat.getColor(this, R.color.checkbox_unchecked_color), // 未选中 - 灰色
            ContextCompat.getColor(this, R.color.checkbox_disabled_color)   // 禁用 - 浅灰色
        )
        
        val colorStateList = ColorStateList(states, colors)
        
        // 应用到 CheckBox
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cbCustom.buttonTintList = colorStateList
        } else {
            CompoundButtonCompat.setButtonTintList(cbCustom, colorStateList)
        }
    }
}
```

## RadioButton 设置（类似方法）

### ColorStateList 方式 (color/radiobutton_custom_tint.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- 选中状态 - 橙色 -->
    <item android:state_checked="true" android:color="@color/radiobutton_checked_color" />
    <!-- 禁用状态 - 浅灰色 -->
    <item android:state_enabled="false" android:color="@color/radiobutton_disabled_color" />
    <!-- 默认状态（未选中）- 灰色 -->
    <item android:color="@color/radiobutton_unchecked_color" />
</selector>
```

### 在代码中使用
```kotlin
// RadioButton 设置自定义颜色
rbCustom.apply {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        buttonTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.radiobutton_custom_tint)
    }
    
    // 设置文字颜色
    setTextColor(ContextCompat.getColor(this@MainActivity, R.color.text_black))
    
    // 设置文字大小
    textSize = 16f
}
```

## 完整示例代码

```kotlin
/**
 * 演示自定义样式设置（代码方式）
 */
private fun demonstrateCustomStyles() {
    // CheckBox 自定义样式
    cbCustom3.apply {
        // 设置按钮颜色（需要 API 21+）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buttonTintList = ContextCompat.getColorStateList(
                this@CheckBoxRadioButtonActivity, 
                R.color.checkbox_custom_tint
            )
        }
        
        // 设置文字颜色
        setTextColor(ContextCompat.getColor(this@CheckBoxRadioButtonActivity, R.color.text_black))
        
        // 设置文字大小
        textSize = 16f
    }

    // RadioButton 自定义样式
    rbBlue.apply {
        // 设置按钮颜色（需要 API 21+）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buttonTintList = ContextCompat.getColorStateList(
                this@CheckBoxRadioButtonActivity, 
                R.color.radiobutton_blue_tint
            )
        }
        
        // 设置文字颜色
        setTextColor(Color.BLUE)
    }
}
```

## 注意事项

1. **API 版本兼容性**：`buttonTint` 和 `buttonTintList` 需要 API 21+，对于低版本可以使用 `CompoundButtonCompat`

2. **颜色状态优先级**：在 ColorStateList 中，状态的顺序很重要，更具体的状态应该放在前面

3. **资源命名**：遵循 Android 资源命名规范，使用有意义的名称

4. **主题兼容性**：确保自定义颜色与应用主题协调一致

## 效果预览

- **选中状态**：橙色 (#FF6B00)
- **未选中状态**：灰色 (#CCCCCC)  
- **禁用状态**：浅灰色 (#E0E0E0)

这样就可以实现像您截图中看到的效果：选中时显示橙色，未选中时显示灰色。 