# Android项目架构设计指南

## 目录
1. [架构概述](#架构概述)
2. [分层架构](#分层架构)
3. [模块化策略](#模块化策略)
4. [数据流管理](#数据流管理)
5. [依赖注入](#依赖注入)
6. [测试策略](#测试策略)
7. [最佳实践示例](#最佳实践示例)

## 架构概述

我们的Android应用采用基于MVVM（Model-View-ViewModel）的清晰分层架构，结合Clean Architecture的核心原则，以确保代码的可测试性、可维护性和可扩展性。

### 架构目标

- **关注点分离**: 确保各层职责明确，降低耦合
- **可测试性**: 便于进行单元测试和UI测试
- **可维护性**: 便于理解和修改现有代码
- **可扩展性**: 便于添加新功能
- **单向数据流**: 确保数据流向清晰可预测

### 架构图

```
┌───────────────────────────────────────────────────────────────┐
│                                                               │
│  ┌─────────────┐     ┌─────────────┐     ┌─────────────┐     │
│  │             │     │             │     │             │     │
│  │    UI层     │◄────┤  ViewModel  │◄────┤   领域层    │     │
│  │  Activities │     │             │     │  UseCase    │     │
│  │  Fragments  │     │             │     │             │     │
│  │  Composables│     │             │     │             │     │
│  │             │     │             │     │             │     │
│  └─────────────┘     └─────────────┘     └──────┬──────┘     │
│                                                  │            │
│                                                  │            │
│                                                  ▼            │
│                                          ┌───────────────┐    │
│                                          │               │    │
│                                          │    数据层     │    │
│                                          │ Repositories  │    │
│                                          │               │    │
│                                          └───────┬───────┘    │
│                                                  │            │
│                     ┌─────────────────────┬──────┴─────────┐  │
│                     │                     │                │  │
│               ┌─────▼─────┐         ┌─────▼─────┐    ┌─────▼─────┐
│               │           │         │           │    │           │
│               │  Remote   │         │  Local    │    │  内存缓存  │
│               │  API服务   │         │  数据库    │    │           │
│               │           │         │           │    │           │
│               └───────────┘         └───────────┘    └───────────┘
│                                                               │
└───────────────────────────────────────────────────────────────┘
```

## 分层架构

### 1. 表现层 (UI层)

**职责**: 展示数据并处理用户交互。

**组件**:
- Activities
- Fragments
- Composables
- Adapters
- ViewHolders

**原则**:
- UI组件不应包含业务逻辑
- 通过观察ViewModel的LiveData/StateFlow接收数据
- 通过调用ViewModel方法发送用户操作
- 使用单向数据流模式

```kotlin
class UserProfileFragment : Fragment() {
    private val viewModel: UserProfileViewModel by viewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // 观察ViewModel状态
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UserProfileState.Loading -> showLoading()
                is UserProfileState.Success -> showUserData(state.user)
                is UserProfileState.Error -> showError(state.message)
            }
        }
        
        // 发送用户操作到ViewModel
        binding.refreshButton.setOnClickListener {
            viewModel.refreshUserProfile()
        }
    }
}
```

### 2. ViewModel层

**职责**: 准备和管理UI所需的数据，处理UI逻辑。

**组件**:
- ViewModels
- UI状态类
- UI事件处理

**原则**:
- 不应包含Android框架类的引用（View, Context等）
- 使用LiveData/StateFlow暴露数据
- 处理UI相关的逻辑和状态转换
- 调用UseCase/领域层执行业务逻辑

```kotlin
class UserProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UserProfileState>()
    val uiState: LiveData<UserProfileState> = _uiState
    
    fun refreshUserProfile() {
        _uiState.value = UserProfileState.Loading
        viewModelScope.launch {
            try {
                val user = getUserProfileUseCase()
                _uiState.value = UserProfileState.Success(user)
            } catch (e: Exception) {
                _uiState.value = UserProfileState.Error(e.message ?: "Unknown error")
            }
        }
    }
    
    fun updateUserProfile(userProfile: UserProfile) {
        viewModelScope.launch {
            updateUserProfileUseCase(userProfile)
            refreshUserProfile()
        }
    }
}

sealed class UserProfileState {
    object Loading : UserProfileState()
    data class Success(val user: UserProfile) : UserProfileState()
    data class Error(val message: String) : UserProfileState()
}
```

### 3. 领域层 (Domain层)

**职责**: 包含业务逻辑和规则。

**组件**:
- UseCases
- 领域模型
- 领域服务

**原则**:
- 纯Kotlin代码，无Android依赖
- 每个UseCase应该专注于单一业务用例
- 协调多个仓库，实现业务逻辑
- 提供领域模型的转换

```kotlin
class GetUserProfileUseCase(
    private val userRepository: UserRepository,
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): UserProfile {
        val userId = settingsRepository.getCurrentUserId()
        return userRepository.getUserProfile(userId)
    }
}

class UpdateUserProfileUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userProfile: UserProfile) {
        userRepository.updateUserProfile(userProfile)
    }
}
```

### 4. 数据层 (Data层)

**职责**: 获取和存储数据。

**组件**:
- Repositories实现
- 数据源（远程、本地）
- 数据模型
- Mappers

**原则**:
- 实现Repository接口
- 协调不同数据源
- 处理缓存策略
- 执行数据转换

```kotlin
class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun getUserProfile(userId: String): UserProfile {
        return try {
            // 尝试从远程获取最新数据
            val remoteUser = remoteDataSource.getUserProfile(userId)
            // 更新本地缓存
            localDataSource.saveUserProfile(remoteUser)
            remoteUser
        } catch (e: Exception) {
            // 远程获取失败，使用本地缓存
            localDataSource.getUserProfile(userId)
        }
    }
    
    override suspend fun updateUserProfile(userProfile: UserProfile) {
        remoteDataSource.updateUserProfile(userProfile)
        localDataSource.saveUserProfile(userProfile)
    }
}
```

## 模块化策略

### 模块类型

1. **App模块**: 应用入口，集成所有功能模块
2. **Feature模块**: 基于业务功能划分的模块
3. **Core模块**: 提供共享功能的基础模块
4. **Library模块**: 可重用的工具和组件库

### 模块化原则

- **内聚性高**: 模块内部组件紧密相关
- **耦合性低**: 模块间依赖清晰可控
- **API隐藏实现**: 通过接口暴露功能，隐藏实现
- **单向依赖**: 避免循环依赖

### 模块依赖图

```
┌──────────────────────────────────────────┐
│                                          │
│               App Module                 │
│                                          │
└───────┬───────────┬────────────┬─────────┘
        │           │            │
        ▼           ▼            ▼
┌──────────┐  ┌──────────┐  ┌──────────┐
│          │  │          │  │          │
│ Feature1 │  │ Feature2 │  │ Feature3 │
│  Module  │  │  Module  │  │  Module  │
│          │  │          │  │          │
└────┬─────┘  └────┬─────┘  └────┬─────┘
     │             │             │
     └─────────────┼─────────────┘
                   │
                   ▼
          ┌─────────────────┐
          │                 │
          │   Core Module   │
          │                 │
          └────────┬────────┘
                   │
                   ▼
          ┌─────────────────┐
          │                 │
          │ Library Module  │
          │                 │
          └─────────────────┘
```

### 模块划分示例

```
app/                  # 应用入口模块
├── feature-auth/     # 认证功能模块
├── feature-profile/  # 用户资料模块
├── feature-chat/     # 聊天功能模块
├── feature-payment/  # 支付功能模块
├── core-network/     # 网络核心模块
├── core-database/    # 数据库核心模块
├── core-ui/          # UI组件核心模块
└── library-analytics/ # 分析工具库
```

## 数据流管理

### 单向数据流 (Unidirectional Data Flow)

我们采用单向数据流模式来管理应用状态和UI交互：

1. **状态(State)**: 代表UI在某一时刻的完整快照
2. **事件(Event)**: 用户操作或系统事件
3. **副作用(Side Effect)**: 一次性事件，如导航或Toast

```
┌─────────────┐      ┌─────────────┐      ┌─────────────┐
│             │      │             │      │             │
│    事件     │─────►│   状态更新   │─────►│  UI渲染更新  │
│             │      │             │      │             │
└─────────────┘      └─────────────┘      └─────────────┘
       ▲                                        │
       │                                        │
       └────────────────────────────────────────┘
```

### 状态管理

推荐使用以下方式之一来管理UI状态：

1. **LiveData**: 生命周期感知的可观察数据持有者
2. **StateFlow**: Kotlin Flow API的状态容器
3. **MVI架构**: 更严格的单向数据流实现

```kotlin
// 使用StateFlow示例
class TaskListViewModel(private val getTasksUseCase: GetTasksUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskListState>(TaskListState.Loading)
    val uiState: StateFlow<TaskListState> = _uiState
    
    init {
        loadTasks()
    }
    
    fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = TaskListState.Loading
            try {
                val tasks = getTasksUseCase()
                _uiState.value = TaskListState.Success(tasks)
            } catch (e: Exception) {
                _uiState.value = TaskListState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class TaskListState {
    object Loading : TaskListState()
    data class Success(val tasks: List<Task>) : TaskListState()
    data class Error(val message: String) : TaskListState()
}
```

## 依赖注入

我们使用Hilt/Dagger2进行依赖注入，以实现组件的松耦合和可测试性。

### 依赖注入原则

- 使用构造函数注入而非字段注入
- 通过接口注入依赖，而非具体实现
- 在适当的作用域内提供依赖
- 使用模块化的依赖提供方式

### Hilt实现示例

```kotlin
// Application类
@HiltAndroidApp
class MyApplication : Application()

// Activity注入
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var analyticsTracker: AnalyticsTracker
}

// ViewModel注入
@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel()

// 模块定义
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-database"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideUserRepository(
        api: UserApi, 
        db: AppDatabase
    ): UserRepository {
        return UserRepositoryImpl(api, db.userDao())
    }
}
```

## 测试策略

### 测试金字塔

我们遵循测试金字塔策略:

```
    ┌───────┐
    │  UI   │  少量
    │ 测试  │
  ┌─┴───────┴─┐
  │ 集成测试  │  适量
┌─┴───────────┴─┐
│    单元测试    │  大量
└───────────────┘
```

### 测试类型

1. **单元测试**
   - 测试单个类/函数的逻辑
   - 使用模拟依赖
   - 不依赖Android框架

2. **集成测试**
   - 测试多个组件的交互
   - 可能包含部分真实依赖
   - 可能依赖部分Android框架

3. **UI测试**
   - 测试UI交互和流程
   - 使用Espresso或UI Automator
   - 依赖完整的Android环境

### 测试实现示例

```kotlin
// ViewModel单元测试
@RunWith(JUnit4::class)
class UserViewModelTest {
    
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    @get:Rule
    val coroutineRule = MainCoroutineRule()
    
    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: UserViewModel
    
    @Before
    fun setup() {
        userRepository = mock(UserRepository::class.java)
        viewModel = UserViewModel(userRepository)
    }
    
    @Test
    fun `when loadUser succeeds then state is Success`() = runTest {
        // Given
        val user = User("1", "John Doe")
        whenever(userRepository.getUser("1")).thenReturn(user)
        
        // When
        viewModel.loadUser("1")
        
        // Then
        val state = viewModel.state.value
        assertThat(state).isInstanceOf(UserState.Success::class.java)
        assertThat((state as UserState.Success).user).isEqualTo(user)
    }
}
```

## 最佳实践示例

### 完整的Feature模块架构

以下是一个典型Feature模块的架构示例:

```
feature-profile/
├── api/
│   ├── ProfileApi.kt          # 远程API接口
│   └── model/                 # API模型
│       └── ProfileResponse.kt
├── data/
│   ├── repository/
│   │   └── ProfileRepositoryImpl.kt
│   ├── source/
│   │   ├── ProfileRemoteDataSource.kt
│   │   └── ProfileLocalDataSource.kt
│   └── mapper/
│       └── ProfileMapper.kt
├── domain/
│   ├── model/
│   │   └── UserProfile.kt
│   ├── repository/
│   │   └── ProfileRepository.kt
│   └── usecase/
│       ├── GetUserProfileUseCase.kt
│       └── UpdateUserProfileUseCase.kt
└── presentation/
    ├── profile/
    │   ├── ProfileFragment.kt
    │   ├── ProfileViewModel.kt
    │   └── ProfileState.kt
    └── edit/
        ├── EditProfileFragment.kt
        ├── EditProfileViewModel.kt
        └── EditProfileState.kt
```

### MVVM示例实现

```kotlin
// Domain层 - 实体
data class User(
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String?
)

// Domain层 - Repository接口
interface UserRepository {
    suspend fun getUser(id: String): User
    suspend fun updateUser(user: User)
}

// Domain层 - UseCase
class GetUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: String): User = repository.getUser(id)
}

// Presentation层 - 状态
sealed class UserState {
    object Loading : UserState()
    data class Success(val user: User) : UserState()
    data class Error(val message: String) : UserState()
}

// Presentation层 - ViewModel
@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UserState>(UserState.Loading)
    val state: StateFlow<UserState> = _state
    
    fun loadUser(id: String) {
        viewModelScope.launch {
            _state.value = UserState.Loading
            try {
                val user = getUserUseCase(id)
                _state.value = UserState.Success(user)
            } catch (e: Exception) {
                _state.value = UserState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

// Presentation层 - Fragment
@AndroidEntryPoint
class UserFragment : Fragment() {
    
    private val viewModel: UserViewModel by viewModels()
    private lateinit var binding: FragmentUserBinding
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // 收集状态流
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is UserState.Loading -> showLoading()
                        is UserState.Success -> showUserData(state.user)
                        is UserState.Error -> showError(state.message)
                    }
                }
            }
        }
        
        // 加载用户数据
        viewModel.loadUser("current_user_id")
    }
    
    private fun showLoading() {
        binding.progressBar.isVisible = true
        binding.userContent.isVisible = false
        binding.errorMessage.isVisible = false
    }
    
    private fun showUserData(user: User) {
        binding.progressBar.isVisible = false
        binding.userContent.isVisible = true
        binding.errorMessage.isVisible = false
        
        binding.userName.text = user.name
        binding.userEmail.text = user.email
        Glide.with(requireContext())
            .load(user.photoUrl)
            .placeholder(R.drawable.profile_placeholder)
            .into(binding.userPhoto)
    }
    
    private fun showError(message: String) {
        binding.progressBar.isVisible = false
        binding.userContent.isVisible = false
        binding.errorMessage.isVisible = true
        binding.errorMessage.text = message
    }
}
```

---

通过遵循这份架构指南，我们可以构建出结构清晰、可维护、可测试、可扩展的Android应用。该指南适用于中小型到大型项目，可根据项目规模和需求进行适当调整。 