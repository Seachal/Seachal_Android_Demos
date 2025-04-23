# CustomTabLayout

一个灵活的自定义TabLayout，可以轻松自定义指示器。支持可替换的指示器实现，包括图像指示器和绘制指示器。

## 功能特点

- 支持自定义指示器图片
- 支持精确控制指示器尺寸
- 允许完全自定义指示器外观和行为
- 指示器与TabLayout解耦，方便扩展
- 支持与ViewPager2集成
- 支持固定宽度和自适应宽度两种指示器模式

## 使用方法

### 1. 在XML中声明CustomTabLayout

```xml
<com.seachal.seachaltest.customtab.CustomTabLayout
    android:id="@+id/o_custom_tab_layout"
    android:layout_width="match_parent"
    android:layout_height="100dp"
    android:background="@android:color/white"
    app:o_indicatorColor="#FF5722"
    app:o_indicatorHeight="@dimen/dp_px_4"
    app:o_indicatorRadius="@dimen/dp_px_2"
    app:o_indicatorWidthMode="wrap_content"
    app:o_normalTextColor="#666666"
    app:o_selectedTextColor="#FF5722"
    app:o_indicatorDrawable="@drawable/o_shape_tab_underline_png2" />
```

### 2. 在Activity或Fragment中设置Tab数据

```kotlin
// 初始化视图
customTabLayout = findViewById(R.id.o_custom_tab_layout)

// 设置Tab数据
val tabTitles = listOf("Tab 1", "Tab 2", "Tab 3")
customTabLayout.setTabItems(tabTitles)

// 设置Tab选中监听器
customTabLayout.setOnTabSelectedListener(object : CustomTabLayout.OnTabSelectedListener {
    override fun onTabSelected(position: Int) {
        // 处理Tab选中事件
    }
})
```

### 3. 与ViewPager2集成

```kotlin
// 初始化ViewPager2
viewPager = findViewById(R.id.view_pager)
viewPager.adapter = yourAdapter

// 关联ViewPager2和CustomTabLayout
customTabLayout.setupWithViewPager2(viewPager)
```

## 自定义指示器

### 方法1：使用XML属性设置指示器图片

在XML布局中添加 `app:o_indicatorDrawable` 属性：

```xml
app:o_indicatorDrawable="@drawable/your_indicator_image"
```

### 方法2：通过代码设置指示器图片

```kotlin
customTabLayout.setIndicatorDrawable(
    ContextCompat.getDrawable(context, R.drawable.your_indicator_image)
)
```

### 方法3：设置指示器尺寸

```kotlin
customTabLayout.setIndicatorSize(
    resources.getDimensionPixelSize(R.dimen.indicator_width),
    resources.getDimensionPixelSize(R.dimen.indicator_height)
)
```

### 方法4：完全自定义指示器

1. 创建自定义指示器实现 `TabIndicator` 接口：

```kotlin
class MyCustomIndicator : TabIndicator {
    private lateinit var rootView: View
    
    override fun initIndicator(context: Context): View {
        // 初始化指示器视图
        rootView = YourCustomView(context)
        return rootView
    }
    
    override fun updatePosition(left: Float, width: Float, animate: Boolean) {
        // 更新指示器位置
    }
    
    override fun setIndicatorDrawable(drawable: Drawable?) {
        // 设置指示器图片
    }
    
    override fun setIndicatorSize(width: Int, height: Int) {
        // 设置指示器尺寸
    }
}
```

2. 创建指示器工厂：

```kotlin
class MyIndicatorFactory : TabIndicatorFactory {
    override fun createTabIndicator(): TabIndicator {
        return MyCustomIndicator()
    }
}
```

3. 设置自定义指示器工厂：

```kotlin
customTabLayout.setIndicatorFactory(MyIndicatorFactory())
```

## 完整示例

```kotlin
class TabDemoActivity : AppCompatActivity() {
    private lateinit var customTabLayout: CustomTabLayout
    private lateinit var viewPager: ViewPager2
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_demo)
        
        // 初始化视图
        customTabLayout = findViewById(R.id.custom_tab_layout)
        viewPager = findViewById(R.id.view_pager)
        
        // 设置ViewPager2适配器
        viewPager.adapter = TabPagerAdapter(this, getFragments())
        
        // 设置Tab数据
        customTabLayout.setTabItems(listOf("Tab 1", "Tab 2", "Tab 3"))
        
        // 关联ViewPager2
        customTabLayout.setupWithViewPager2(viewPager)
        
        // 自定义指示器图片和尺寸
        customTabLayout.setIndicatorDrawable(
            ContextCompat.getDrawable(this, R.drawable.tab_indicator)
        )
        customTabLayout.setIndicatorSize(
            resources.getDimensionPixelSize(R.dimen.indicator_width),
            resources.getDimensionPixelSize(R.dimen.indicator_height)
        )
    }
}
```

## 自定义属性列表

| 属性名 | 类型 | 描述 |
|-------|------|------|
| o_indicatorColor | color | 指示器颜色 |
| o_indicatorHeight | dimension | 指示器高度 |
| o_indicatorWidthMode | enum | 指示器宽度模式 (fixed/wrap_content) |
| o_indicatorFixedWidth | dimension | 固定宽度模式下的指示器宽度 |
| o_indicatorRadius | dimension | 指示器圆角半径 |
| o_selectedTextColor | color | 选中状态的文本颜色 |
| o_normalTextColor | color | 未选中状态的文本颜色 |
| o_indicatorDrawable | reference | 指示器图片资源 |

## 性能优化

1. CustomTabLayout使用RecyclerView实现，有良好的性能和复用机制
2. 指示器与TabLayout解耦，减少不必要的视图层级
3. 支持ViewPager2的预加载机制，提高页面切换的流畅度

## 注意事项

1. 使用自定义指示器时，确保正确实现所有TabIndicator接口方法
2. 设置指示器图片后，indicatorColor属性将不再生效
3. 推荐在主线程中调用UI更新相关方法
4. 与ViewPager2集成时，确保ViewPager2和CustomTabLayout的子项数量一致 