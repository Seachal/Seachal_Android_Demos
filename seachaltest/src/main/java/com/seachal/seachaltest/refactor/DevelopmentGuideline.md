# Android开发规范

## 架构模式

本项目采用MVVM（Model-View-ViewModel）架构模式，结合Android官方推荐的JetPack组件库，实现关注点分离和单向数据流。

### 架构分层

```
┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐
│   View  │◄───│ViewModel│◄───│Repository│◄───│ DataSource│
└─────────┘    └─────────┘    └─────────┘    └─────────┘
     ▲              │              │               │
     └──────────────┘              │               │
          数据绑定                 │               │
                            数据处理逻辑       网络/本地数据获取
```

1. **View层**：负责UI渲染和用户交互
   - Activity/Fragment
   - 自定义View
   - Adapter

2. **ViewModel层**：负责业务逻辑和数据转换
   - 持有并处理UI状态
   - 触发数据操作
   - 数据转换

3. **Repository层**：负责数据操作的抽象
   - 协调不同数据来源
   - 实现数据缓存策略
   - 对上层提供统一的数据接口

4. **DataSource层**：负责具体数据获取
   - 远程数据源（网络API）
   - 本地数据源（数据库、SharedPreferences）
   - 文件数据源

## 技术选择

### 基础框架

- **AndroidX**：使用AndroidX替代旧版Support库
- **Kotlin**：优先使用Kotlin语言开发新功能
- **协程**：使用Kotlin协程处理异步任务
- **JetPack**：优先使用官方JetPack组件

### 依赖注入

- **Hilt/Dagger2**：用于依赖注入，简化组件间的依赖关系

### UI组件

- **Material Components**：遵循Material Design设计规范
- **ConstraintLayout**：作为主要布局方式，减少视图层级
- **ViewBinding**：替代findViewById，提供类型安全的视图绑定
- **DataBinding**：需要双向绑定时使用，减少样板代码

### 数据处理

- **Room**：本地数据库操作
- **Retrofit + OkHttp**：网络请求
- **Moshi/Gson**：JSON序列化/反序列化
- **DataStore**：替代SharedPreferences存储键值对

### 异步处理

- **Kotlin协程**：处理异步任务
- **Flow**：处理数据流
- **LiveData**：用于UI层观察数据变化

### 图片加载

- **Glide/Coil**：图片加载和缓存

### 测试

- **JUnit**：单元测试
- **Mockito**：模拟测试依赖
- **Espresso**：UI测试

## 各层职责

### View层

1. **职责**
   - 展示数据到UI
   - 响应用户交互
   - 将用户事件传递给ViewModel

2. **规范**
   - Activity/Fragment职责单一，避免业务逻辑
   - 使用DataBinding减少样板代码
   - UI状态变化通过观察LiveData或StateFlow实现

3. **示例**

```kotlin
class UserProfileActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityUserProfileBinding
    private val viewModel: UserProfileViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 观察数据变化
        viewModel.userProfile.observe(this) { profile ->
            binding.user = profile
        }
        
        // 处理用户交互
        binding.buttonEdit.setOnClickListener {
            viewModel.onEditButtonClicked()
        }
    }
}
```

### ViewModel层

1. **职责**
   - 准备并维护UI所需数据
   - 处理业务逻辑
   - 协调Repository层数据操作

2. **规范**
   - 不持有View引用，避免内存泄漏
   - 通过LiveData/StateFlow暴露数据
   - 异步操作使用协程处理
   - 避免直接访问Android SDK组件

3. **示例**

```kotlin
class UserProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    
    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile
    
    init {
        loadUserProfile()
    }
    
    private fun loadUserProfile() {
        viewModelScope.launch {
            try {
                val profile = userRepository.getUserProfile()
                _userProfile.value = profile
            } catch (e: Exception) {
                // 处理错误
            }
        }
    }
    
    fun onEditButtonClicked() {
        // 处理编辑按钮点击事件
    }
}
```

### Repository层

1. **职责**
   - 提供统一的数据操作接口
   - 协调远程和本地数据源
   - 实现数据缓存策略

2. **规范**
   - 抽象接口定义数据操作
   - 处理数据获取的逻辑
   - 实现数据同步和缓存

3. **示例**

```kotlin
interface UserRepository {
    suspend fun getUserProfile(): UserProfile
    suspend fun updateUserProfile(profile: UserProfile): Result<Boolean>
}

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {
    
    override suspend fun getUserProfile(): UserProfile {
        // 先尝试从本地获取
        val localData = localDataSource.getUserProfile()
        
        // 如果本地数据不存在或已过期，则从远程获取
        if (localData == null || isDataExpired(localData)) {
            val remoteData = remoteDataSource.getUserProfile()
            // 保存到本地
            localDataSource.saveUserProfile(remoteData)
            return remoteData
        }
        
        return localData
    }
    
    override suspend fun updateUserProfile(profile: UserProfile): Result<Boolean> {
        return try {
            val result = remoteDataSource.updateUserProfile(profile)
            if (result) {
                localDataSource.saveUserProfile(profile)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun isDataExpired(data: UserProfile): Boolean {
        // 检查数据是否过期
        return System.currentTimeMillis() - data.lastUpdateTime > CACHE_TIMEOUT
    }
    
    companion object {
        private const val CACHE_TIMEOUT = 30 * 60 * 1000L // 30分钟
    }
}
```

### DataSource层

1. **职责**
   - 实现具体的数据访问
   - 封装数据来源的细节

2. **规范**
   - 远程数据源处理网络请求
   - 本地数据源处理数据库操作
   - 隐藏数据访问细节

3. **示例**

```kotlin
// 远程数据源
class UserRemoteDataSource(private val apiService: ApiService) {
    
    suspend fun getUserProfile(): UserProfile {
        return apiService.getUserProfile().toUserProfile()
    }
    
    suspend fun updateUserProfile(profile: UserProfile): Boolean {
        val request = profile.toUpdateRequest()
        val response = apiService.updateUserProfile(request)
        return response.isSuccessful
    }
}

// 本地数据源
class UserLocalDataSource(private val userDao: UserDao) {
    
    suspend fun getUserProfile(): UserProfile? {
        return userDao.getUserProfile()?.toUserProfile()
    }
    
    suspend fun saveUserProfile(profile: UserProfile) {
        userDao.insert(profile.toUserEntity())
    }
}
```

## 数据流通

### 单向数据流

```
UI事件 -> ViewModel(业务逻辑) -> Repository(数据操作) -> ViewModel(状态更新) -> UI更新
```

1. **UI事件触发**：用户交互触发事件
2. **ViewModel处理**：接收事件，执行业务逻辑
3. **Repository操作**：执行数据操作
4. **状态更新**：ViewModel更新状态
5. **UI响应**：UI通过观察者模式更新界面

### 错误处理流程

```
错误发生 -> Repository捕获 -> ViewModel处理 -> UI展示
```

1. **错误捕获**：Repository层捕获数据操作错误
2. **错误传递**：通过Result或异常传递给ViewModel
3. **错误处理**：ViewModel更新错误状态
4. **UI展示**：UI观察错误状态并展示给用户

## 模块化

### 模块划分

1. **基础模块（Base Module）**
   - 基础工具类
   - 通用UI组件
   - 核心服务接口

2. **功能模块（Feature Module）**
   - 按业务功能划分
   - 独立编译和测试
   - 可单独发布

3. **公共模块（Common Module）**
   - 共享资源
   - 网络组件
   - 数据库组件

### 模块依赖关系

```
Feature Modules
       ↓
Common Modules
       ↓
 Base Module
       ↓
 External Libs
```

## 测试策略

### 单元测试

- 测试Repository和ViewModel逻辑
- 使用Mock隔离外部依赖
- 专注于业务逻辑验证

### UI测试

- 使用Espresso测试UI交互
- 验证UI元素展示和响应

### 集成测试

- 测试多个组件协同工作
- 验证数据流通流程

## 版本兼容性

- 最低支持API级别：21（Android 5.0）
- 目标API级别：当前最新正式版
- 使用AndroidX兼容库
- 针对关键API的版本适配处理

## 性能优化

1. **启动优化**
   - 减少Application初始化工作
   - 使用懒加载延迟初始化
   - 控制启动页面复杂度

2. **内存优化**
   - 避免内存泄漏
   - 大图片使用缩放处理
   - 及时释放不需要的资源

3. **网络优化**
   - 实现合理的缓存机制
   - 避免频繁请求
   - 数据压缩和分页加载

4. **布局优化**
   - 减少布局层级
   - 使用ConstraintLayout
   - 避免过度绘制

## CI/CD流程

1. **持续集成**
   - 提交代码触发自动构建
   - 运行单元测试和代码静态分析
   - 生成测试报告

2. **持续部署**
   - 自动生成测试包
   - 部署到测试环境
   - 通知测试团队

## 文档管理

- API文档：使用KDoc格式编写
- 架构文档：描述模块结构和交互
- 开发指南：记录开发流程和规范

## 工具和环境

- 开发工具：Android Studio最新稳定版
- 构建工具：Gradle
- 版本控制：Git
- 代码规范检查：ktlint/detekt
- 代码审查：Pull Request和Code Review

## 安全规范

1. **数据存储安全**
   - 敏感信息加密存储
   - 避免明文存储密码
   - 使用Android KeyStore

2. **网络安全**
   - 使用HTTPS
   - 实现证书固定
   - 防止中间人攻击

3. **代码安全**
   - 避免硬编码敏感信息
   - 使用ProGuard混淆代码
   - 防止逆向工程

## 资源管理

1. **字符串资源**
   - 所有文本放入strings.xml
   - 支持多语言

2. **图片资源**
   - 使用矢量图形（Vector Drawable）
   - 提供不同密度的位图
   - 压缩图片减小APK大小

3. **主题和样式**
   - 统一使用主题定义视觉风格
   - 抽取公共样式
   - 支持深色模式 