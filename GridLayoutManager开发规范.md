# GridLayoutManager 开发规范

## 📋 目录
- [代码规范](#代码规范)
- [架构设计规范](#架构设计规范)
- [ItemDecoration开发规范](#itemdecoration开发规范)
- [布局文件规范](#布局文件规范)
- [性能优化规范](#性能优化规范)
- [测试规范](#测试规范)
- [文档规范](#文档规范)

---

## 🔧 代码规范

### 命名规范

#### Activity命名
```java
// ✅ 正确：功能明确的Activity命名
public class GridLayoutManagerDemoActivity extends AppCompatActivity

// ❌ 错误：命名过于简单
public class GridActivity extends AppCompatActivity
```

#### 变量命名
```java
// ✅ 正确：使用有意义的变量名
private int horizontalSpacing;
private int verticalSpacing;
private int edgeMargin;

// ❌ 错误：使用缩写或无意义命名
private int hSpacing;
private int vSpacing;
private int margin;
```

#### 常量命名
```java
// ✅ 正确：使用大写字母和下划线
public static final int TYPE_BASIC = 1;
public static final int TYPE_WITH_DECORATION = 2;
public static final int TYPE_PRECISE_SPACING = 3;

// ❌ 错误：使用小写或驼峰命名
public static final int typeBasic = 1;
```

### 注释规范

#### 类注释
```java
/**
 * GridLayoutManager 演示Activity
 * 演示三种不同的使用场景：
 * 1. 基础场景（无特殊设置）
 * 2. 带ItemDecoration的场景
 * 3. 精确间距控制的场景
 * 
 * @author zhangxc
 * @date 2024-12-27
 */
public class GridLayoutManagerDemoActivity extends AppCompatActivity {
```

#### 方法注释
```java
/**
 * 场景3：精确间距控制
 * 屏幕宽度假设为750dp，item宽度335dp，水平间距16dp，边距32dp，垂直间距16dp
 */
private void setupPreciseSpacingGrid() {
    // 实现内容
}
```

#### 算法注释
```java
// 左右间距：边缘item和容器之间用edgeMargin，item之间用horizontalSpacing
if (column == 0) {
    // 第一列：左边距为edgeMargin，右边距为horizontalSpacing的一半
    outRect.left = edgeMargin;
    outRect.right = horizontalSpacing / 2;
}
```

---

## 🏗️ 架构设计规范

### 单一职责原则
```java
// ✅ 正确：每个类只负责一个职责
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    // 只负责基础的网格间距计算
}

public class PreciseSpacingDecoration extends RecyclerView.ItemDecoration {
    // 只负责精确的间距控制
}
```

### 开闭原则
```java
// ✅ 正确：通过接口扩展功能
public interface SpacingStrategy {
    void calculateSpacing(Rect outRect, int position, int spanCount);
}

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private SpacingStrategy spacingStrategy;
    
    public void setSpacingStrategy(SpacingStrategy strategy) {
        this.spacingStrategy = strategy;
    }
}
```

### 依赖倒置原则
```java
// ✅ 正确：依赖抽象而非具体实现
public class GridDemoAdapter extends RecyclerView.Adapter<GridDemoAdapter.ViewHolder> {
    private List<DemoItem> dataList; // 依赖List接口
    
    public GridDemoAdapter(List<DemoItem> dataList) {
        this.dataList = dataList;
    }
}
```

---

## 🎨 ItemDecoration开发规范

### 基础结构规范
```java
public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    
    // 1. 必须定义的成员变量
    private int horizontalSpacing;
    private int verticalSpacing;
    
    // 2. 构造函数必须验证参数
    public CustomItemDecoration(int horizontalSpacing, int verticalSpacing) {
        if (horizontalSpacing < 0 || verticalSpacing < 0) {
            throw new IllegalArgumentException("Spacing cannot be negative");
        }
        this.horizontalSpacing = horizontalSpacing;
        this.verticalSpacing = verticalSpacing;
    }
    
    // 3. 重写getItemOffsets方法
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        // 实现间距计算逻辑
    }
}
```

### 间距计算规范

#### 边界检查
```java
@Override
public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                           @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
    // ✅ 必须进行边界检查
    GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
    if (layoutManager == null) return;
    
    int position = parent.getChildAdapterPosition(view);
    if (position == RecyclerView.NO_POSITION) return;
    
    // 继续处理...
}
```

#### 位置计算
```java
// ✅ 正确：清晰的位置计算逻辑
int spanCount = layoutManager.getSpanCount();
int column = position % spanCount; // 列索引
int row = position / spanCount;    // 行索引

// ❌ 错误：硬编码或不清晰的计算
int column = position % 2; // 硬编码span数量
```

#### 间距分配算法
```java
// ✅ 推荐：均分间距算法（适用于大多数场景）
outRect.left = column * horizontalSpacing / spanCount;
outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount;

// ✅ 推荐：精确控制算法（适用于设计稿严格要求）
if (column == 0) {
    outRect.left = edgeMargin;
    outRect.right = horizontalSpacing / 2;
} else if (column == spanCount - 1) {
    outRect.left = horizontalSpacing / 2;
    outRect.right = edgeMargin;
}
```

---

## 📱 布局文件规范

### 命名规范
```xml
<!-- ✅ 正确：清晰的布局文件命名 -->
activity_grid_layout_manager_demo.xml
item_grid_basic.xml
item_grid_with_decoration.xml
item_grid_precise_spacing.xml

<!-- ❌ 错误：命名不够清晰 -->
activity_grid.xml
item1.xml
item2.xml
```

### ID命名规范
```xml
<!-- ✅ 正确：使用完整的描述性ID -->
<Button
    android:id="@+id/btn_basic"
    android:id="@+id/btn_with_decoration"
    android:id="@+id/btn_precise_spacing" />

<!-- ❌ 错误：使用简短或无意义的ID -->
<Button
    android:id="@+id/btn1"
    android:id="@+id/btn2" />
```

### 资源引用规范
```xml
<!-- ✅ 正确：使用资源引用而非硬编码 -->
<Button
    android:textColor="@color/btn_text_color_selector"
    android:background="@drawable/btn_selector"
    android:textSize="@dimen/button_text_size" />

<!-- ❌ 错误：硬编码数值和颜色 -->
<Button
    android:textColor="#FFFFFF"
    android:background="#2196F3"
    android:textSize="12sp" />
```

### 布局层级规范
```xml
<!-- ✅ 正确：扁平化的布局结构 -->
<LinearLayout>
    <TextView />
    <RecyclerView />
</LinearLayout>

<!-- ❌ 错误：过度嵌套的布局 -->
<LinearLayout>
    <RelativeLayout>
        <LinearLayout>
            <TextView />
        </LinearLayout>
    </RelativeLayout>
</LinearLayout>
```

---

## ⚡ 性能优化规范

### ItemDecoration性能优化
```java
public class OptimizedGridDecoration extends RecyclerView.ItemDecoration {
    
    // ✅ 缓存计算结果
    private final SparseArray<Rect> offsetCache = new SparseArray<>();
    
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        
        // 检查缓存
        Rect cachedOffset = offsetCache.get(position);
        if (cachedOffset != null) {
            outRect.set(cachedOffset);
            return;
        }
        
        // 计算并缓存结果
        calculateOffsets(outRect, position);
        offsetCache.put(position, new Rect(outRect));
    }
}
```

### Adapter性能优化
```java
public class GridDemoAdapter extends RecyclerView.Adapter<GridDemoAdapter.ViewHolder> {
    
    // ✅ 使用final关键字优化
    private final List<DemoItem> dataList;
    private final LayoutInflater inflater;
    
    // ✅ 避免在onBindViewHolder中创建对象
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DemoItem item = dataList.get(position);
        holder.bind(item); // 将绑定逻辑移到ViewHolder中
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        
        // ✅ 在ViewHolder中实现绑定逻辑
        public void bind(DemoItem item) {
            textView.setText(item.title);
        }
    }
}
```

### 内存优化
```java
// ✅ 及时清理资源
private void clearItemDecorations() {
    while (recyclerView.getItemDecorationCount() > 0) {
        recyclerView.removeItemDecorationAt(0);
    }
}

@Override
protected void onDestroy() {
    super.onDestroy();
    // 清理缓存和引用
    if (adapter != null) {
        adapter.clearData();
    }
}
```

---

## 🧪 测试规范

### 单元测试规范
```java
@RunWith(AndroidJUnit4.class)
public class GridItemDecorationTest {
    
    @Test
    public void testHorizontalSpacingCalculation() {
        GridItemDecoration decoration = new GridItemDecoration(16, 16);
        Rect outRect = new Rect();
        
        // 测试第一列的间距计算
        decoration.calculateHorizontalSpacing(outRect, 0, 2);
        assertEquals(0, outRect.left);
        assertEquals(8, outRect.right);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSpacing() {
        new GridItemDecoration(-16, 16);
    }
}
```

### UI测试规范
```java
@RunWith(AndroidJUnit4.class)
public class GridLayoutManagerDemoActivityTest {
    
    @Rule
    public ActivityTestRule<GridLayoutManagerDemoActivity> activityRule = 
        new ActivityTestRule<>(GridLayoutManagerDemoActivity.class);
    
    @Test
    public void testScenarioSwitching() {
        // 测试场景切换功能
        onView(withId(R.id.btn_with_decoration)).perform(click());
        onView(withId(R.id.tv_description))
            .check(matches(withText(containsString("ItemDecoration"))));
    }
}
```

---

## 📚 文档规范

### README文档结构
```markdown
# 项目名称

## 概述
简要描述项目的目的和功能

## 功能特性
- 功能点1
- 功能点2

## 技术实现
关键技术点的说明

## 使用方法
详细的使用步骤

## API文档
主要类和方法的说明

## 性能考虑
性能优化的要点

## 已知问题
当前存在的问题和限制

## 更新日志
版本更新记录
```

### 代码文档规范
```java
/**
 * 精确间距控制的ItemDecoration
 * 
 * <p>实现特点：
 * <ul>
 *   <li>边缘item和容器之间无间距</li>
 *   <li>item之间有精确的间距控制</li>
 *   <li>支持自定义边距和间距参数</li>
 * </ul>
 * 
 * <p>使用示例：
 * <pre>{@code
 * PreciseSpacingDecoration decoration = new PreciseSpacingDecoration(
 *     dpToPx(32), // 边距
 *     dpToPx(16), // 水平间距
 *     dpToPx(16)  // 垂直间距
 * );
 * recyclerView.addItemDecoration(decoration);
 * }</pre>
 * 
 * @author zhangxc
 * @version 1.0
 * @since 2024-12-27
 */
public class PreciseSpacingDecoration extends RecyclerView.ItemDecoration {
```

---

## ✅ 代码检查清单

### 提交前检查
- [ ] 所有变量和方法都有清晰的命名
- [ ] 关键方法都有注释说明
- [ ] 没有硬编码的数值和字符串
- [ ] 进行了必要的边界检查
- [ ] 实现了适当的异常处理
- [ ] 遵循了单一职责原则
- [ ] 考虑了性能优化
- [ ] 编写了相应的测试用例
- [ ] 更新了相关文档

### 代码审查要点
- [ ] 架构设计是否合理
- [ ] 是否遵循了开发规范
- [ ] 性能是否有优化空间
- [ ] 是否存在潜在的内存泄漏
- [ ] 异常处理是否完善
- [ ] 测试覆盖率是否足够

---

## 📈 持续改进

### 性能监控
- 使用Android Studio Profiler监控内存使用
- 测试不同数据量下的滚动性能
- 监控ItemDecoration计算的耗时

### 用户反馈
- 收集用户对不同间距效果的反馈
- 统计最常用的配置参数
- 根据反馈优化默认设置

### 版本迭代
- 定期重构代码提高可维护性
- 添加新的间距计算策略
- 优化现有算法的性能

---

## 🔗 相关资源

- [Android RecyclerView官方文档](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [ItemDecoration最佳实践](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ItemDecoration)
- [阿里巴巴Android开发手册](https://github.com/alibaba/p3c)
- [Material Design Grid Lists](https://material.io/components/lists#anatomy)

---

*本规范文档会根据项目发展持续更新，请定期查看最新版本。* 