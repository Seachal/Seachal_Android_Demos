# Android代码风格规范

## 通用规范

### 文件组织

1. **文件命名**：采用驼峰命名法，首字母大写
   - Activity文件以 `Activity` 结尾，如 `LoginActivity.kt`
   - Fragment文件以 `Fragment` 结尾，如 `ProfileFragment.kt`
   - Adapter文件以 `Adapter` 结尾，如 `UserAdapter.kt`
   - ViewModel文件以 `ViewModel` 结尾，如 `LoginViewModel.kt`
   - 自定义View以 `View` 结尾，如 `CircleProgressView.kt`

2. **包组织**：按功能模块组织，避免按类型组织
   ```
   com.example.app
   ├── data
   │   ├── model
   │   ├── repository
   │   └── source
   │       ├── local
   │       └── remote
   │   ├── di
   │   ├── ui
   │   │   ├── login
   │   │   │   ├── LoginActivity.kt
   │   │   │   ├── LoginViewModel.kt
   │   │   │   └── LoginState.kt
   │   │   └── profile
   │   │       ├── ProfileFragment.kt
   │   │       ├── ProfileViewModel.kt
   │   │       └── ProfileState.kt
   │   └── util
   │   ```

3. **资源文件组织**
   - 布局文件：`activity_名称.xml`，`fragment_名称.xml`，`item_名称.xml`
   - 图标资源：`ic_名称.xml`
   - 背景图：`bg_名称.xml`
   - 选择器：`selector_名称.xml`

### 代码格式

1. **缩进**：使用4个空格进行缩进，不使用Tab
2. **行长度**：每行代码不超过100个字符
3. **大括号**：左大括号不单独占一行
   ```kotlin
   if (condition) {
       // 代码
   }
   ```
4. **空行使用**
   - 类定义与方法之间
   - 逻辑相关的代码块之间
   - 定义变量与其他语句之间
5. **导包顺序**
   - Android导包
   - 第三方库导包
   - Java/Kotlin标准库导包
   - 本项目内导包

## Kotlin编码规范

### 命名规范

1. **类与接口**
   - 使用PascalCase，首字母大写
   - 类名应为名词或名词短语
   - 接口名可以是形容词或名词

2. **方法**
   - 使用camelCase，首字母小写
   - 方法名应为动词或动词短语
   ```kotlin
   fun calculateTotalPrice() { ... }
   fun showProgressBar() { ... }
   ```

3. **变量**
   - 使用camelCase，首字母小写
   - 变量名应为名词
   - Boolean变量应以is、has、can等前缀开头
   ```kotlin
   val userName: String
   val isLoggedIn: Boolean
   val hasPermission: Boolean
   ```

4. **常量**
   - 使用UPPER_SNAKE_CASE，全大写，下划线分隔
   ```kotlin
   const val MAX_COUNT = 100
   const val API_BASE_URL = "https://api.example.com"
   ```

5. **枚举**
   - 枚举类使用PascalCase
   - 枚举值使用UPPER_SNAKE_CASE
   ```kotlin
   enum class LogLevel {
       DEBUG,
       INFO,
       WARNING,
       ERROR
   }
   ```

### 语言特性使用

1. **属性**
   - 优先使用val，只在必要时使用var
   - 属性应声明类型，除非能明确推断
   ```kotlin
   val user: User = getUser()
   val name = user.name // 类型可推断时可省略
   ```

2. **空安全**
   - 尽量避免使用`!!`操作符
   - 合理使用`?.`安全调用操作符
   - 使用`?:`空合并操作符提供默认值
   ```kotlin
   // 好的实践
   val name = user?.name ?: "Unknown"
   
   // 避免以下写法
   val name = user!!.name
   ```

3. **扩展函数**
   - 扩展函数应为工具性质，不应改变对象的状态
   - 扩展函数应放在相关类的文件中
   ```kotlin
   // String扩展函数
   fun String.toTitleCase(): String {
       return this.split(" ").joinToString(" ") { it.capitalize() }
   }
   ```

4. **Lambda表达式**
   - 当Lambda是函数的最后一个参数时，使用括号外Lambda语法
   - 当Lambda只有一个参数时，使用it代替
   - 较长的Lambda应使用多行格式
   ```kotlin
   // 短Lambda
   items.filter { it.isValid }
   
   // 多行Lambda
   items.map { item ->
       val result = process(item)
       result.toString()
   }
   ```

5. **作用域函数**
   - `let`：处理非空值并转换结果
   - `run`：不需要将对象作为lambda参数时
   - `with`：调用同一个对象的多个方法时
   - `apply`：配置对象属性
   - `also`：执行操作但返回对象本身
   ```kotlin
   // 使用apply配置对象
   val textView = TextView(context).apply {
       text = "Hello"
       textSize = 14f
       setTextColor(Color.BLACK)
   }
   
   // 使用let处理可空值
   user?.let {
       displayUserInfo(it)
   }
   ```

6. **协程**
   - 使用适当的协程作用域
   - 通过SupervisorJob处理错误
   - 避免在suspend函数中调用阻塞方法
   ```kotlin
   // ViewModel中使用协程
   viewModelScope.launch {
       try {
           val result = repository.fetchData()
           _state.value = SuccessState(result)
       } catch (e: Exception) {
           _state.value = ErrorState(e.message)
       }
   }
   ```

### 代码组织

1. **类组织顺序**
   - 伴生对象 (companion object)
   - 属性
   - 初始化块
   - 构造函数
   - 方法（先公有后私有）
   - 嵌套类/接口

2. **接口实现**
   - 使用适当的可见性修饰符
   - 使用委托模式实现接口
   ```kotlin
   class UserRepository(
       private val remoteDataSource: RemoteDataSource,
       private val localDataSource: LocalDataSource
   ) : UserDataSource {
       override fun getUser(id: String) = localDataSource.getUser(id)
   }
   ```

## Java编码规范

### 命名规范

1. **类与接口**
   - 使用PascalCase，首字母大写
   - 类名应为名词或名词短语
   - 接口名可以是形容词或名词

2. **方法**
   - 使用camelCase，首字母小写
   - 方法名应为动词或动词短语
   ```java
   public void calculateTotalPrice() { ... }
   public void showProgressBar() { ... }
   ```

3. **变量**
   - 使用camelCase，首字母小写
   - 变量名应为名词
   - Boolean变量以is、has、can等前缀开头
   ```java
   String userName;
   boolean isLoggedIn;
   boolean hasPermission;
   ```

4. **常量**
   - 使用UPPER_SNAKE_CASE，全大写，下划线分隔
   ```java
   public static final int MAX_COUNT = 100;
   public static final String API_BASE_URL = "https://api.example.com";
   ```

### 语言特性使用

1. **依赖注入**
   - 使用构造函数注入依赖
   - 避免使用单例模式，优先使用依赖注入框架
   ```java
   public class UserRepository {
       private final UserApi userApi;
       private final UserDao userDao;
       
       @Inject
       public UserRepository(UserApi userApi, UserDao userDao) {
           this.userApi = userApi;
           this.userDao = userDao;
       }
   }
   ```

2. **异常处理**
   - 只捕获具体的异常，避免捕获通用Exception
   - 避免空catch块，至少记录日志
   - 不使用异常控制流程
   ```java
   try {
       // 代码
   } catch (IOException e) {
       Log.e(TAG, "Failed to read file", e);
   }
   ```

3. **并发编程**
   - 使用Java并发工具类而非自己实现
   - 避免直接使用Thread，优先使用线程池
   - 慎用synchronized，考虑使用Lock接口
   ```java
   private final ExecutorService executor = Executors.newSingleThreadExecutor();
   
   public void processData() {
       executor.execute(() -> {
           // 后台处理
       });
   }
   ```

4. **集合使用**
   - 优先使用接口类型声明集合（List而非ArrayList）
   - 使用Java 8的集合流操作
   - 使用泛型
   ```java
   List<User> users = new ArrayList<>();
   
   // 使用流操作
   List<String> names = users.stream()
           .filter(user -> user.isActive())
           .map(User::getName)
           .collect(Collectors.toList());
   ```

5. **Builder模式**
   - 推荐使用Builder模式创建复杂对象
   ```java
   User user = new User.Builder()
           .id("123")
           .name("John")
           .email("john@example.com")
           .build();
   ```

### 代码组织

1. **类组织顺序**
   - 静态变量
   - 成员变量
   - 构造函数
   - 方法（先公有后私有）
   - 内部类/接口

2. **接口实现**
   - 优先使用组合而非继承
   - 实现接口时使用适当的可见性修饰符
   ```java
   public class UserRepositoryImpl implements UserRepository {
       private final RemoteDataSource remoteDataSource;
       private final LocalDataSource localDataSource;
       
       @Inject
       public UserRepositoryImpl(RemoteDataSource remote, LocalDataSource local) {
           this.remoteDataSource = remote;
           this.localDataSource = local;
       }
       
       @Override
       public User getUser(String id) {
           return localDataSource.getUser(id);
       }
   }
   ```

## XML布局规范

### 通用规则

1. **ID命名**
   - 使用小写字母和下划线
   - 前缀表示控件类型：`tv_`（TextView）, `btn_`（Button）, `et_`（EditText）等
   ```xml
   <TextView
       android:id="@+id/tv_user_name"
       ... />
   
   <Button
       android:id="@+id/btn_login"
       ... />
   ```

2. **布局结构**
   - 使用ConstraintLayout作为顶层布局
   - 避免嵌套布局层次过深（>3层）
   - 使用merge减少布局层次
   - 使用include复用布局

3. **资源引用**
   - 使用styles定义共用的样式
   - 使用dimens定义尺寸值
   - 使用colors定义颜色值
   - 使用strings定义文本值
   ```xml
   <TextView
       android:layout_width="match_parent"
       android:layout_height="@dimen/height_standard"
       android:textColor="@color/text_primary"
       android:text="@string/welcome_message"
       style="@style/TextAppearance.App.Headline" />
   ```

4. **属性顺序**
   - id
   - layout_width/height
   - layout_margin/padding
   - layout_constraints
   - background
   - 文本相关属性
   - 其他属性

## 性能优化规范

1. **视图优化**
   - 避免过度绘制
   - 使用自定义View时优化onDraw方法
   - 使用ViewHolder模式
   
2. **内存优化**
   - 避免内存泄漏（注意生命周期和回调）
   - 大图片使用Glide或Picasso加载
   - 使用弱引用处理长生命周期对象
   
3. **响应时间优化**
   - UI线程不做耗时操作
   - 使用协程或RxJava处理后台任务
   - 延迟加载不是立即需要的内容

## 最佳实践示例

### 1. ViewModel实现（Kotlin）

```kotlin
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Initial)
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    fun loadUser(userId: String) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                val user = userRepository.getUser(userId)
                _userState.value = UserState.Success(user)
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "Unknown error")
            }
        }
    }

    sealed class UserState {
        object Initial : UserState()
        object Loading : UserState()
        data class Success(val user: User) : UserState()
        data class Error(val message: String) : UserState()
    }
}
```

### 2. Repository实现（Kotlin）

```kotlin
class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun getUser(userId: String): User {
        return try {
            // 先尝试从本地获取
            val localUser = localDataSource.getUser(userId)
            if (localUser != null) {
                localUser
            } else {
                // 本地没有则从远程获取并保存
                val remoteUser = remoteDataSource.getUser(userId)
                localDataSource.saveUser(remoteUser)
                remoteUser
            }
        } catch (e: Exception) {
            throw UserException("Failed to get user: ${e.message}")
        }
    }

    override suspend fun updateUser(user: User): Result<Unit> {
        return try {
            remoteDataSource.updateUser(user)
            localDataSource.saveUser(user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(UserException("Failed to update user: ${e.message}"))
        }
    }
}
```

### 3. Activity实现（Kotlin）

```kotlin
@AndroidEntryPoint
class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    private val viewModel: UserViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupViews()
        observeViewModel()
        
        val userId = intent.getStringExtra(EXTRA_USER_ID) ?: return
        viewModel.loadUser(userId)
    }
    
    private fun setupViews() {
        binding.btnUpdateProfile.setOnClickListener {
            // 处理点击事件
        }
    }
    
    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userState.collect { state ->
                    when (state) {
                        is UserState.Initial -> {
                            // 初始状态
                        }
                        is UserState.Loading -> {
                            showLoading(true)
                        }
                        is UserState.Success -> {
                            showLoading(false)
                            displayUserData(state.user)
                        }
                        is UserState.Error -> {
                            showLoading(false)
                            showError(state.message)
                        }
                    }
                }
            }
        }
    }
    
    private fun displayUserData(user: User) {
        with(binding) {
            tvUserName.text = user.name
            tvUserEmail.text = user.email
            ivUserAvatar.load(user.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_user_placeholder)
                error(R.drawable.ic_error)
            }
        }
    }
    
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.contentGroup.isVisible = !isLoading
    }
    
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    companion object {
        private const val EXTRA_USER_ID = "extra_user_id"
        
        fun createIntent(context: Context, userId: String): Intent {
            return Intent(context, UserProfileActivity::class.java).apply {
                putExtra(EXTRA_USER_ID, userId)
            }
        }
    }
}
```

### 4. 布局文件示例

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="@dimen/spacing_16">

    <androidx.constraintlayout.widget.Group
        android:id="@+id/content_group"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:constraint_referenced_ids="iv_user_avatar,tv_user_name,tv_user_email,btn_update_profile" />

    <com.google.android.material.imageview.ShapeableImageView
        android:id="@+id/iv_user_avatar"
        android:layout_width="96dp"
        android:layout_height="96dp"
        android:layout_marginTop="@dimen/spacing_32"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:shapeAppearanceOverlay="@style/ShapeAppearance.App.CircleImageView"
        tools:src="@tools:sample/avatars" />

    <TextView
        android:id="@+id/tv_user_name"
        style="@style/TextAppearance.App.Headline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="@dimen/spacing_16"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/iv_user_avatar"
        tools:text="John Doe" />

    <TextView
        android:id="@+id/tv_user_email"
        style="@style/TextAppearance.App.Body"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="@dimen/spacing_8"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_user_name"
        tools:text="john.doe@example.com" />

    <com.google.android.material.button.MaterialButton
        android:id="@+id/btn_update_profile"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="@dimen/spacing_32"
        android:text="@string/update_profile"
        app:layout_constraintTop_toBottomOf="@id/tv_user_email" />

    <ProgressBar
        android:id="@+id/progress_bar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:visibility="gone"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:visibility="visible" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## 代码检查工具配置

### ktlint

在项目级build.gradle中配置：

```groovy
plugins {
    id "org.jlleitschuh.gradle.ktlint" version "10.3.0"
}

subprojects {
    apply plugin: "org.jlleitschuh.gradle.ktlint"
    
    ktlint {
        debug = true
        version = "0.45.2"
        android = true
        outputToConsole = true
        ignoreFailures = false
        enableExperimentalRules = true
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}
```

### Detekt

在项目级build.gradle中配置：

```groovy
plugins {
    id "io.gitlab.arturbosch.detekt" version "1.20.0"
}

detekt {
    toolVersion = "1.20.0"
    config = files("$projectDir/config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    allRules = false
    baseline = file("$projectDir/config/detekt/baseline.xml")
    
    reports {
        xml {
            enabled = true
            destination = file("$buildDir/reports/detekt/detekt.xml")
        }
        html {
            enabled = true
            destination = file("$buildDir/reports/detekt/detekt.html")
        }
    }
}
```

## IDE配置

### Android Studio配置

1. **代码风格**
   - 导入项目代码风格文件（`.editorconfig`）
   - 使用标准缩进（4个空格）
   - 启用自动导入优化

2. **插件安装**
   - Kotlin Fillter
   - Rainbow Brackets
   - SonarLint

3. **自动保存配置**
   - 启用"保存时格式化代码"
   - 启用"保存时优化导入"

## 总结

1. 代码风格是团队协作的基础，所有团队成员都应遵循
2. 风格规范应定期更新以适应项目发展
3. 使用自动检查工具确保代码质量
4. 通过代码审查确保规范执行
5. 新团队成员应熟悉并遵循本规范 