# 代码质量改进指南

## 代码风格

### 命名规范

1. **类命名**
   - 使用大驼峰命名法（UpperCamelCase）
   - 类名应当是名词或名词短语
   - 示例类应当以示例功能+组件类型命名，如`ButtonStyleActivity`

2. **方法命名**
   - 使用小驼峰命名法（lowerCamelCase）
   - 方法名应当是动词或动词短语
   - 例如：`initView()`, `loadData()`, `handleClick()`

3. **变量命名**
   - 使用小驼峰命名法
   - 避免使用单个字符命名（除循环计数器外）
   - 布尔变量应当以"is"或"has"等开头，如`isEnabled`
   - View变量应当以组件类型为后缀，如`titleTextView`

4. **常量命名**
   - 使用全大写字母和下划线
   - 例如：`MAX_COUNT`, `DEFAULT_SIZE`

5. **资源命名**
   - 布局文件：`组件类型_功能.xml`，如`activity_main.xml`
   - drawable文件：`用途_状态.xml`，如`button_pressed.xml`
   - ID命名：`组件类型_功能`，如`textview_title`

### 代码格式

1. **缩进**
   - 使用4个空格缩进
   - 不使用制表符（Tab）

2. **行宽**
   - 每行代码不超过100个字符
   - 超过时应适当换行

3. **空行**
   - 方法之间添加一个空行
   - 逻辑相关的代码块之间添加一个空行
   - 类的成员变量声明和方法之间添加一个空行

4. **括号**
   - 左大括号不单独占一行
   - 即使只有一行代码，也应使用大括号

5. **导入语句**
   - 不使用通配符导入（`import xx.*`）
   - 按照Java标准库、Android库、第三方库的顺序排列
   - 删除未使用的导入

## 代码质量

### 注释规范

1. **类注释**
   ```java
   /**
    * 类的功能描述
    * 
    * @author 作者名
    * @date 创建日期
    */
   ```

2. **方法注释**
   ```java
   /**
    * 方法的功能描述
    *
    * @param 参数名 参数说明
    * @return 返回值说明
    */
   ```

3. **变量注释**
   - 对于复杂意义的变量，添加单行注释说明用途

4. **代码块注释**
   - 对于复杂逻辑，在代码块前添加注释说明

5. **TODO注释**
   - 使用`// TODO: `标记待完成的任务
   - 包含具体的任务描述和期望完成日期

### 性能优化

1. **避免内存泄漏**
   - 正确管理生命周期
   - 避免静态变量持有Activity/Context引用
   - 及时释放资源（Cursor, InputStream等）

2. **线程管理**
   - 耗时操作放在后台线程
   - UI操作放在主线程
   - 避免频繁创建线程，考虑使用线程池

3. **布局优化**
   - 避免布局嵌套过深
   - 适当使用`<merge>`和`<include>`标签
   - 使用`ConstraintLayout`减少嵌套层级

4. **避免过度绘制**
   - 移除不必要的背景
   - 使用`clipRect`减少绘制区域
   - 避免在onDraw中创建新的对象

### 健壮性

1. **异常处理**
   - 捕获并处理可能的异常
   - 提供有意义的错误信息
   - 避免空白的catch块

2. **边界条件**
   - 检查输入参数的有效性
   - 处理集合为空或索引越界的情况
   - 考虑网络请求失败的情况

3. **空指针检查**
   - 使用`null`检查防止空指针异常
   - 考虑使用`@Nullable`和`@NonNull`注解
   - 适当使用Optional模式

## 示例代码规范

### 示例Activity

```java
/**
 * 按钮样式示例Activity
 * 展示不同样式按钮的使用方法
 * 
 * @author 开发者
 * @date 2023-01-01
 */
public class ButtonStyleActivity extends AppCompatActivity {
    
    private static final String TAG = "ButtonStyleActivity";
    private static final int DEFAULT_ANIMATION_DURATION = 300;
    
    private Button normalButton;
    private Button customButton;
    private TextView resultTextView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
        
        initViews();
        setupListeners();
    }
    
    /**
     * 初始化视图组件
     */
    private void initViews() {
        normalButton = findViewById(R.id.button_normal);
        customButton = findViewById(R.id.button_custom);
        resultTextView = findViewById(R.id.textview_result);
    }
    
    /**
     * 设置点击监听器
     */
    private void setupListeners() {
        normalButton.setOnClickListener(view -> {
            // 处理普通按钮点击
            resultTextView.setText("普通按钮被点击");
            Log.d(TAG, "Normal button clicked");
        });
        
        customButton.setOnClickListener(view -> {
            // 处理自定义按钮点击
            resultTextView.setText("自定义按钮被点击");
            Log.d(TAG, "Custom button clicked");
        });
    }
    
    @Override
    protected void onDestroy() {
        // 清理资源
        super.onDestroy();
    }
}
```

### 示例布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ui.button.ButtonStyleActivity">

    <!-- 普通按钮 -->
    <Button
        android:id="@+id/button_normal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="普通按钮"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/>

    <!-- 自定义按钮 -->
    <Button
        android:id="@+id/button_custom"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="自定义按钮"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toBottomOf="@id/button_normal"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/>

    <!-- 结果文本显示 -->
    <TextView
        android:id="@+id/textview_result"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        app:layout_constraintTop_toBottomOf="@id/button_custom"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        tools:text="结果显示区域"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

## 代码审查清单

在提交代码前，请检查以下项目：

- [ ] 代码遵循命名规范
- [ ] 格式符合代码风格要求
- [ ] 添加了适当的注释
- [ ] 处理了可能的异常
- [ ] 检查了边界条件
- [ ] 避免了内存泄漏
- [ ] 进行了必要的性能优化
- [ ] 删除了调试用代码（如临时Log）
- [ ] 代码逻辑清晰可理解
- [ ] 删除了未使用的资源和导入

## 重构技巧

1. **提取方法**
   - 将复杂逻辑拆分为多个小方法
   - 每个方法只做一件事

2. **合理使用继承和组合**
   - 优先使用组合而非继承
   - 创建基类封装共同行为

3. **使用设计模式**
   - 适当使用工厂、单例、观察者等设计模式
   - 不要过度设计，保持代码简单

4. **移除重复代码**
   - 提取公共方法
   - 使用工具类封装公共功能

## 提交信息规范

格式：`[类型] 简短描述`

类型：
- `feat`: 新功能
- `fix`: 修复Bug
- `docs`: 文档更新
- `style`: 代码风格调整
- `refactor`: 代码重构
- `perf`: 性能优化
- `test`: 测试相关
- `chore`: 构建过程或辅助工具变动

示例：
- `[feat] 添加按钮样式示例`
- `[fix] 修复列表滚动卡顿问题`
- `[refactor] 重构菜单系统` 