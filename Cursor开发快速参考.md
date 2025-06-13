# Cursor Android 开发快速参考

## 🚀 常用代码片段触发词

| 片段 | 触发词 | 描述 |
|------|--------|------|
| Activity模板 | `activity` | 生成标准Activity结构 |
| ItemDecoration | `itemdecoration` | 生成ItemDecoration模板 |
| Adapter | `adapter` | 生成RecyclerView Adapter |
| 标准间距算法 | `gridspacing` | 均分间距计算 |
| 精确间距算法 | `precisespacing` | 边缘对齐间距计算 |
| DP转PX | `dptopx` | 单位转换工具方法 |
| 安全类型转换 | `safelayoutmanager` | GridLayoutManager安全转换 |
| 空值检查 | `nullcheck` | 标准null检查模式 |
| 资源访问 | `resourceaccess` | ContextCompat资源访问 |
| 类文档 | `classdoc` | 完整类注释模板 |
| 方法文档 | `methoddoc` | 方法注释模板 |
| 布局模板 | `androidlayout` | 基础XML布局 |
| 按钮选择器 | `buttonwithselector` | 带选择器的按钮 |

## 📋 命名规范速查

### Java类命名
```
Activity: [Feature][Purpose]Activity
Fragment: [Feature][Purpose]Fragment  
Adapter: [Feature][DataType]Adapter
ItemDecoration: [Purpose]ItemDecoration
```

### 资源文件命名
```
Layout: activity_[name].xml / item_[name].xml
ID: [type]_[feature]_[purpose]
Color: module_[purpose]_color
Dimen: module_[description]
```

### 变量命名
```
成员变量: camelCase (recyclerView, adapter)
常量: UPPER_SNAKE_CASE (DEFAULT_SPAN_COUNT)
布尔值: is/has/can/should + 描述 (isLoading, hasData)
```

## ⚡ 快速检查清单

### 创建Activity时
- [ ] 继承AppCompatActivity
- [ ] onCreate()中调用super.onCreate()
- [ ] 使用initViews()、initData()、setupXXX()方法分离逻辑
- [ ] 添加@NonNull/@Nullable注解
- [ ] 处理Bundle savedInstanceState

### 创建ItemDecoration时
- [ ] 构造函数参数验证（非负数检查）
- [ ] getItemOffsets()中检查LayoutManager不为null
- [ ] 检查position不等于RecyclerView.NO_POSITION
- [ ] 计算spanCount、column、row
- [ ] 考虑边缘情况处理

### 创建Adapter时
- [ ] 使用ViewHolder模式
- [ ] final关键字修饰不可变变量
- [ ] onBindViewHolder()中避免对象创建
- [ ] 实现数据绑定bind()方法
- [ ] 考虑数据更新notifyDataSetChanged()

### 布局文件时
- [ ] 使用dp单位（间距、尺寸）
- [ ] 使用sp单位（文字大小）
- [ ] 引用资源而非硬编码值
- [ ] 添加content description（图片）
- [ ] 避免布局嵌套过深（最多4层）

## 🛠 常用工具方法

### DP转PX
```java
private int dpToPx(int dp) {
    DisplayMetrics metrics = getResources().getDisplayMetrics();
    return (int) (dp * metrics.density);
}
```

### 安全类型转换
```java
private GridLayoutManager getGridLayoutManager(RecyclerView rv) {
    RecyclerView.LayoutManager lm = rv.getLayoutManager();
    return (lm instanceof GridLayoutManager) ? (GridLayoutManager) lm : null;
}
```

### 资源安全访问
```java
int color = ContextCompat.getColor(context, R.color.primary);
Drawable drawable = ContextCompat.getDrawable(context, R.drawable.icon);
```

## 🎯 间距算法速查

### 标准均分算法
```java
// 水平间距均分
outRect.left = column * horizontalSpacing / spanCount;
outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount;

// 垂直间距
if (row > 0) outRect.top = verticalSpacing;
```

### 精确边缘对齐算法
```java
if (column == 0) {
    outRect.left = edgeMargin;
    outRect.right = horizontalSpacing / 2;
} else if (column == spanCount - 1) {
    outRect.left = horizontalSpacing / 2;
    outRect.right = edgeMargin;
} else {
    outRect.left = horizontalSpacing / 2;
    outRect.right = horizontalSpacing / 2;
}
```

## 🚨 常见错误避免

### ItemDecoration常见坑
- ❌ 忘记检查LayoutManager为null
- ❌ 忘记检查position有效性
- ❌ 在getItemOffsets()中创建对象
- ❌ 负数间距参数未验证
- ✅ 在构造函数中验证参数
- ✅ 缓存计算结果
- ✅ 添加边界检查

### Adapter常见坑
- ❌ onBindViewHolder()中findViewById
- ❌ ViewHolder中缓存Context引用
- ❌ 直接修改列表数据不通知
- ✅ ViewHolder中缓存View引用
- ✅ 使用notifyXXX()方法更新
- ✅ 避免内存泄漏

### 布局常见坑
- ❌ 硬编码尺寸和颜色
- ❌ 使用px单位
- ❌ 忘记content description
- ✅ 使用资源引用
- ✅ 使用dp/sp单位
- ✅ 支持无障碍访问

## 🔧 Cursor设置优化

### 推荐配置
```json
{
  "editor.formatOnSave": true,
  "editor.codeActionsOnSave": {
    "source.organizeImports": true,
    "source.fixAll": true
  },
  "java.compile.nullAnalysis.mode": "automatic"
}
```

### 快捷键
- `Ctrl+Space`: 代码补全
- `Ctrl+Shift+P`: 命令面板
- `Ctrl+K Ctrl+C`: 注释代码
- `Ctrl+K Ctrl+U`: 取消注释
- `Alt+Shift+F`: 格式化代码

## 📚 阿里巴巴规范要点

### 强制规则
- Activity间数据传递避免Intent+Parcelable大数据
- 禁止在非UI线程进行View操作
- 数据库Cursor必须确保关闭
- 使用ThreadPoolExecutor而非Executors
- 布局避免ViewGroup多重嵌套

### 推荐规则
- 使用DialogFragment而非Dialog
- SharedPreference使用apply()而非commit()
- 图片压缩处理减少内存占用
- 文本大小使用dp单位保证一致性

## 🎨 UI规范要点

### 间距规范
- 遵循8dp网格系统
- 常用间距：8dp, 16dp, 24dp, 32dp
- 文字间距：4dp, 8dp, 12dp

### 颜色规范
- 定义在colors.xml中
- 使用语义化命名
- 支持深色模式

### 图标规范
- 使用vector drawable
- 标准尺寸：24dp, 32dp, 48dp
- 保持一致的视觉风格

---

💡 **提示**: 使用这些代码片段和规范可以大大提高开发效率，确保代码质量和一致性。记住先思考架构，再编写代码！ 