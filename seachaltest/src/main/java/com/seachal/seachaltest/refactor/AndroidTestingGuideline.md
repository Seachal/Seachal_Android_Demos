# Android测试指南

## 目录
1. [测试概述](#测试概述)
2. [测试金字塔](#测试金字塔)
3. [单元测试](#单元测试)
4. [集成测试](#集成测试)
5. [UI测试](#UI测试)
6. [测试驱动开发](#测试驱动开发)
7. [持续集成](#持续集成)
8. [最佳实践](#最佳实践)
9. [常见问题与解决方案](#常见问题与解决方案)

## 测试概述

Android应用的测试是确保应用质量、功能正确性和用户体验的关键环节。完善的测试策略可以帮助我们：

- 尽早发现并修复bug
- 防止代码重构过程中引入新问题
- 加快开发速度和迭代周期
- 提高代码质量和可维护性
- 增强开发团队信心

在Android开发中，我们采用分层测试策略，从底层单元测试到顶层UI测试，全面覆盖应用的各个方面。

## 测试金字塔

测试金字塔是一种可视化测试策略的方法，它强调了不同类型测试的比例关系：

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

这种策略的核心理念是：

- **大量的单元测试**：针对应用的最小功能单元，运行速度快，维护成本低
- **适量的集成测试**：验证不同组件间的交互，确保它们协同工作
- **少量的UI测试**：模拟用户操作，验证端到端功能，但运行速度慢，维护成本高

遵循这种比例分配，可以在保证测试覆盖率的同时，最大化测试的ROI（投资回报率）。

## 单元测试

单元测试专注于验证代码的最小独立单元（通常是一个方法或一个类）的行为。

### 单元测试框架

在Android开发中，我们使用以下框架进行单元测试：

- **JUnit4**：基础测试框架
- **MockK/Mockito**：用于模拟依赖
- **Truth/AssertJ**：提供流畅的断言API
- **Robolectric**：在JVM上模拟Android环境（无需设备/模拟器）

### 适用范围

单元测试适用于测试：

- ViewModel
- UseCase/Interactor
- Repository
- 工具类
- 数据模型
- 业务逻辑

### 目录结构

单元测试放置在`src/test/java`目录下，包结构应该与被测试代码保持一致：

```
src/
├── main/java/com/example/app/
│   └── feature/
│       ├── FeatureViewModel.kt
│       └── FeatureRepository.kt
└── test/java/com/example/app/
    └── feature/
        ├── FeatureViewModelTest.kt
        └── FeatureRepositoryTest.kt
```

### 单元测试命名

采用以下格式命名单元测试：

```
[被测试类名]Test
```

例如：`UserViewModelTest`，`LoginUseCaseTest`。

### 测试方法命名

使用以下格式命名测试方法：

```
`when_[条件]_then_[预期结果]`
```

例如：
- `when_userAuthenticated_then_returnsUserData`
- `when_networkError_then_returnsOfflineData`

或直接使用反引号包裹描述性文本（Kotlin特性）：

```kotlin
@Test
fun `when login with valid credentials then return success`() {
    // 测试代码
}
```

### 单元测试示例

```kotlin
class UserViewModelTest {
    
    // 使用 JUnit 规则设置测试环境
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    @get:Rule
    val coroutineRule = TestCoroutineRule()
    
    // 模拟依赖
    private val userRepository = mockk<UserRepository>()
    
    // 被测试对象
    private lateinit var viewModel: UserViewModel
    
    @Before
    fun setup() {
        viewModel = UserViewModel(userRepository)
    }
    
    @Test
    fun `when loadUser succeeds then state is Success`() = coroutineRule.runTest {
        // Arrange
        val user = User("1", "Test User")
        coEvery { userRepository.getUser("1") } returns user
        
        // Act
        viewModel.loadUser("1")
        
        // Assert
        val state = viewModel.state.value
        assertThat(state).isInstanceOf(UserState.Success::class.java)
        assertThat((state as UserState.Success).user).isEqualTo(user)
    }
    
    @Test
    fun `when loadUser fails then state is Error`() = coroutineRule.runTest {
        // Arrange
        val exception = Exception("Network error")
        coEvery { userRepository.getUser(any()) } throws exception
        
        // Act
        viewModel.loadUser("1")
        
        // Assert
        val state = viewModel.state.value
        assertThat(state).isInstanceOf(UserState.Error::class.java)
        assertThat((state as UserState.Error).message).contains("Network error")
    }
}
```

### 单元测试最佳实践

1. **保持测试独立**：每个测试应该独立运行，不依赖于其他测试的状态
2. **使用模拟对象**：模拟外部依赖，确保只测试目标单元
3. **测试公共API**：专注于测试类的公共接口，而非内部实现
4. **避免逻辑在测试中**：测试应该简单明了，不包含复杂逻辑
5. **遵循AAA模式**：Arrange（准备）、Act（执行）、Assert（断言）
6. **捕捉边界情况**：测试各种边界条件和异常情况
7. **使用假数据工厂**：创建测试数据生成器，便于复用测试数据

## 集成测试

集成测试验证多个组件一起工作的情况，确保它们能正确交互。

### 集成测试框架

- **JUnit4**：基础测试框架
- **AndroidX Test**：提供Android特定的测试支持
- **Espresso**：用于UI交互的部分（当集成测试涉及UI时）
- **MockWebServer**：用于模拟网络请求

### 适用范围

集成测试适用于：

- 数据库操作
- 网络请求
- 多个组件共同工作的功能
- 服务集成

### 目录结构

集成测试放置在`src/androidTest/java`目录下：

```
src/
├── main/java/com/example/app/
└── androidTest/java/com/example/app/
    └── data/
        ├── DatabaseIntegrationTest.kt
        └── ApiIntegrationTest.kt
```

### 集成测试命名

```
[测试范围]IntegrationTest
```

例如：`DatabaseIntegrationTest`，`NetworkRepositoryIntegrationTest`。

### 集成测试示例

```kotlin
@RunWith(AndroidJUnit4::class)
class UserRepositoryIntegrationTest {
    
    private lateinit var database: AppDatabase
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService
    private lateinit var userRepository: UserRepositoryImpl
    
    @Before
    fun setup() {
        // 设置模拟数据库
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        
        // 设置模拟网络服务
        mockWebServer = MockWebServer()
        mockWebServer.start()
        
        val baseUrl = mockWebServer.url("/")
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        apiService = retrofit.create(ApiService::class.java)
        
        // 创建仓库实例
        userRepository = UserRepositoryImpl(
            remoteDataSource = UserRemoteDataSourceImpl(apiService),
            localDataSource = UserLocalDataSourceImpl(database.userDao())
        )
    }
    
    @After
    fun tearDown() {
        database.close()
        mockWebServer.shutdown()
    }
    
    @Test
    fun getUserProfile_networkAvailable_savesToDatabaseAndReturnsData() = runBlocking {
        // Arrange
        val userId = "user_1"
        val responseJson = """{"id": "user_1", "name": "John Doe", "email": "john@example.com"}"""
        
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(responseJson)
        
        mockWebServer.enqueue(mockResponse)
        
        // Act
        val result = userRepository.getUserProfile(userId)
        
        // Assert
        // 验证从网络获取的数据
        assertThat(result.id).isEqualTo("user_1")
        assertThat(result.name).isEqualTo("John Doe")
        
        // 验证数据已保存到数据库
        val savedUser = database.userDao().getUserById(userId)
        assertThat(savedUser).isNotNull()
        assertThat(savedUser?.name).isEqualTo("John Doe")
    }
}
```

### 集成测试最佳实践

1. **使用测试替身**：使用模拟服务器代替真实网络请求
2. **内存数据库**：测试数据库操作时使用内存数据库
3. **隔离测试环境**：确保每个测试都有独立的环境
4. **清理资源**：测试完成后释放所有资源（数据库连接、网络等）
5. **检查组件间交互**：验证数据在不同组件间正确传递

## UI测试

UI测试通过模拟用户交互来验证应用的界面和功能是否按预期工作。

### UI测试框架

- **Espresso**：用于测试应用内UI交互
- **UI Automator**：用于跨应用UI测试
- **Compose Testing**：用于Jetpack Compose UI测试
- **Screenshot Testing**：用于UI外观一致性测试

### 适用范围

UI测试适用于：

- 用户流程验证
- 屏幕渲染
- 导航
- 输入处理
- 屏幕间交互

### 目录结构

UI测试放置在`src/androidTest/java`目录下，通常按照功能或屏幕组织：

```
src/
└── androidTest/java/com/example/app/
    └── ui/
        ├── login/
        │   └── LoginScreenTest.kt
        └── profile/
            └── ProfileScreenTest.kt
```

### UI测试命名

```
[屏幕名称]Test 或 [功能]FlowTest
```

例如：`LoginScreenTest`，`CheckoutFlowTest`。

### UI测试示例 (Espresso)

```kotlin
@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    
    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)
    
    @Test
    fun loginWithValidCredentials_navigatesToMainScreen() {
        // 输入有效的用户名和密码
        onView(withId(R.id.et_username))
            .perform(typeText("valid_user"), closeSoftKeyboard())
        
        onView(withId(R.id.et_password))
            .perform(typeText("valid_password"), closeSoftKeyboard())
        
        // 点击登录按钮
        onView(withId(R.id.btn_login))
            .perform(click())
        
        // 验证导航到主屏幕
        onView(withId(R.id.main_container))
            .check(matches(isDisplayed()))
    }
    
    @Test
    fun loginWithInvalidCredentials_showsErrorMessage() {
        // 输入无效的用户名和密码
        onView(withId(R.id.et_username))
            .perform(typeText("invalid_user"), closeSoftKeyboard())
        
        onView(withId(R.id.et_password))
            .perform(typeText("invalid_password"), closeSoftKeyboard())
        
        // 点击登录按钮
        onView(withId(R.id.btn_login))
            .perform(click())
        
        // 验证显示错误消息
        onView(withId(R.id.tv_error))
            .check(matches(isDisplayed()))
            .check(matches(withText(containsString("Invalid credentials"))))
    }
}
```

### UI测试示例 (Compose)

```kotlin
@RunWith(AndroidJUnit4::class)
class LoginComposeScreenTest {
    
    @get:Rule
    val composeRule = createComposeRule()
    
    @Before
    fun setup() {
        composeRule.setContent {
            MyAppTheme {
                LoginScreen(
                    onLoginSuccess = {},
                    onNavigateToRegister = {}
                )
            }
        }
    }
    
    @Test
    fun loginWithValidCredentials_callsSuccessCallback() {
        // 输入有效的用户名和密码
        composeRule.onNodeWithTag("username_input")
            .performTextInput("valid_user")
        
        composeRule.onNodeWithTag("password_input")
            .performTextInput("valid_password")
        
        composeRule.onNodeWithTag("login_button")
            .performClick()
        
        // 验证登录状态
        composeRule.onNodeWithTag("loading_indicator")
            .assertIsDisplayed()
        
        // 等待加载完成
        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag("loading_indicator")
                .fetchSemanticsNodes().isEmpty()
        }
        
        // 验证成功状态
        composeRule.onNodeWithText("Login Successful")
            .assertIsDisplayed()
    }
}
```

### UI测试最佳实践

1. **测试关键用户流程**：专注于测试最重要的用户流程
2. **保持测试稳定**：避免依赖时序、位置等不稳定因素
3. **使用ID进行元素选择**：为UI元素添加测试ID，便于测试选择
4. **模拟依赖**：使用测试替身替代真实网络或数据库
5. **等待策略**：使用适当的等待策略处理异步操作
6. **测试错误状态**：验证应用在出错时的行为
7. **测试不同屏幕尺寸**：确保应用在不同设备上的适配性

## 测试驱动开发

测试驱动开发（TDD）是一种开发方法，强调先编写测试，然后再实现功能。

### TDD工作流程

1. **编写失败的测试**：首先编写一个能够体现新功能需求的测试
2. **运行测试**：验证测试失败（因为功能尚未实现）
3. **实现功能**：编写刚好足够通过测试的功能代码
4. **运行测试**：验证测试通过
5. **重构**：改进代码实现，确保测试仍然通过
6. **重复**：继续下一个功能点

### TDD示例

假设我们要实现一个用户注册功能，TDD流程如下：

1. **编写测试**:

```kotlin
@Test
fun `register with valid data returns success`() = runTest {
    // Arrange
    val email = "test@example.com"
    val password = "Password123"
    val user = User(email = email)
    
    coEvery { userRepository.checkEmailExists(email) } returns false
    coEvery { userRepository.registerUser(email, password) } returns user
    
    // Act
    val result = registerUseCase(email, password)
    
    // Assert
    assertThat(result.isSuccess).isTrue()
    assertThat(result.getOrNull()).isEqualTo(user)
}

@Test
fun `register with existing email returns failure`() = runTest {
    // Arrange
    val email = "existing@example.com"
    val password = "Password123"
    
    coEvery { userRepository.checkEmailExists(email) } returns true
    
    // Act
    val result = registerUseCase(email, password)
    
    // Assert
    assertThat(result.isFailure).isTrue()
    val exception = result.exceptionOrNull()
    assertThat(exception).isInstanceOf(RegistrationException::class.java)
    assertThat(exception?.message).contains("Email already exists")
}
```

2. **实现功能**:

```kotlin
class RegisterUseCase(private val userRepository: UserRepository) {
    
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return try {
            // 检查邮箱是否已存在
            val emailExists = userRepository.checkEmailExists(email)
            if (emailExists) {
                return Result.failure(RegistrationException("Email already exists"))
            }
            
            // 注册用户
            val user = userRepository.registerUser(email, password)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

3. **重构**:

```kotlin
class RegisterUseCase(private val userRepository: UserRepository) {
    
    suspend operator fun invoke(email: String, password: String): Result<User> {
        // 验证输入
        if (!isValidEmail(email)) {
            return Result.failure(RegistrationException("Invalid email format"))
        }
        
        if (!isValidPassword(password)) {
            return Result.failure(RegistrationException("Password doesn't meet requirements"))
        }
        
        return try {
            // 检查邮箱是否已存在
            if (userRepository.checkEmailExists(email)) {
                return Result.failure(RegistrationException("Email already exists"))
            }
            
            // 注册用户
            val user = userRepository.registerUser(email, password)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(RegistrationException("Registration failed", e))
        }
    }
    
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8 && 
               password.any { it.isDigit() } && 
               password.any { it.isUpperCase() }
    }
}
```

4. **添加新测试**:

```kotlin
@Test
fun `register with invalid email format returns failure`() = runTest {
    // Arrange
    val email = "invalid-email"
    val password = "Password123"
    
    // Act
    val result = registerUseCase(email, password)
    
    // Assert
    assertThat(result.isFailure).isTrue()
    val exception = result.exceptionOrNull()
    assertThat(exception?.message).contains("Invalid email format")
}
```

### TDD的优势

1. **更好的代码质量**：代码从一开始就是可测试的
2. **清晰的需求理解**：编写测试迫使开发者理解需求
3. **更少的bug**：及早发现问题
4. **更好的API设计**：从使用者角度设计API
5. **文档作用**：测试作为使用示例和文档

## 持续集成

将测试集成到CI/CD流程中是确保代码质量的关键。

### CI配置

在CI系统（如GitHub Actions, Jenkins, CircleCI）中设置以下工作流：

1. **编译检查**：确保代码可以编译
2. **静态分析**：运行ktlint, detekt等静态分析工具
3. **单元测试**：运行所有单元测试
4. **集成测试**：在模拟器或设备上运行集成测试
5. **UI测试**：在模拟器或设备上运行UI测试

### 示例GitHub Actions配置

```yaml
name: Android CI

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main, develop ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      
      - name: Set up JDK
        uses: actions/setup-java@v2
        with:
          java-version: '11'
          distribution: 'adopt'
          
      - name: Grant execute permission for gradlew
        run: chmod +x gradlew
        
      - name: Run static analysis
        run: ./gradlew ktlintCheck detekt
        
      - name: Run unit tests
        run: ./gradlew testDebugUnitTest
        
      - name: Build with Gradle
        run: ./gradlew assembleDebug
        
      - name: Upload APK
        uses: actions/upload-artifact@v2
        with:
          name: app-debug
          path: app/build/outputs/apk/debug/app-debug.apk
          
  instrumented-tests:
    runs-on: macos-latest
    needs: build
    steps:
      - uses: actions/checkout@v2
      
      - name: Set up JDK
        uses: actions/setup-java@v2
        with:
          java-version: '11'
          distribution: 'adopt'
          
      - name: Grant execute permission for gradlew
        run: chmod +x gradlew
        
      - name: Run instrumentation tests
        uses: reactivecircus/android-emulator-runner@v2
        with:
          api-level: 29
          arch: x86
          profile: Nexus 6
          script: ./gradlew connectedDebugAndroidTest
```

### 测试报告

配置CI以生成并发布测试报告：

```groovy
// build.gradle
android {
    testOptions {
        unitTests.all {
            testLogging {
                events "passed", "skipped", "failed"
                exceptionFormat "full"
            }
        }
        
        // JaCoCo配置，用于代码覆盖率
        unitTests {
            includeAndroidResources = true
            returnDefaultValues = true
        }
    }
}

// 代码覆盖率配置
apply plugin: 'jacoco'

jacoco {
    toolVersion = "0.8.7"
}

tasks.withType(Test) {
    jacoco.includeNoLocationClasses = true
    jacoco.excludes = ['jdk.internal.*']
}

task jacocoTestReport(type: JacocoReport, dependsOn: ['testDebugUnitTest']) {
    reports {
        xml.enabled = true
        html.enabled = true
    }
    
    def fileFilter = ['**/R.class', '**/R$*.class', '**/BuildConfig.*', '**/Manifest*.*', '**/*Test*.*', 'android/**/*.*']
    def debugTree = fileTree(dir: "${buildDir}/intermediates/classes/debug", excludes: fileFilter)
    def mainSrc = "${project.projectDir}/src/main/java"
    
    sourceDirectories.from(files([mainSrc]))
    classDirectories.from(files([debugTree]))
    executionData.from(fileTree(dir: "$buildDir", includes: [
            "jacoco/testDebugUnitTest.exec",
            "outputs/code-coverage/connected/*coverage.ec"
    ]))
}
```

## 最佳实践

### 通用测试原则

1. **测试可读性**：测试应该易于理解，清晰地表达意图
2. **测试独立性**：每个测试应该独立运行，不依赖其他测试
3. **测试速度**：测试应该快速运行，特别是单元测试
4. **测试维护性**：测试代码应像产品代码一样受到重视和维护
5. **测试覆盖率**：关注关键业务逻辑的覆盖，而非简单追求覆盖率数字

### 测试代码组织

1. **遵循相同的包结构**：测试代码的包结构应与源代码一致
2. **使用测试辅助类**：创建测试工厂、假数据生成器等辅助类
3. **共享测试资源**：在适当位置共享测试数据和工具

### 测试夹具（Test Fixtures）

创建测试夹具来简化测试数据准备：

```kotlin
// 测试数据工厂
object TestDataFactory {
    fun createUser(
        id: String = "user_1",
        name: String = "Test User",
        email: String = "test@example.com"
    ) = User(id, name, email)
    
    fun createUserList(count: Int = 10): List<User> {
        return (1..count).map { 
            createUser(id = "user_$it", name = "User $it")
        }
    }
}

// 测试规则
class TestCoroutineRule : TestWatcher() {
    private val testDispatcher = UnconfinedTestDispatcher()
    
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }
    
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
    
    fun runTest(block: suspend () -> Unit) = runBlocking { block() }
}
```

## 常见问题与解决方案

### 1. Flaky Tests（不稳定测试）

**问题**：测试有时通过，有时失败。

**解决方案**：
- 避免依赖时序和动画
- 使用适当的等待策略（IdlingResource, awaitility）
- 确保测试环境一致性
- 避免测试间依赖

### 2. 测试Android依赖

**问题**：很多Android类难以测试（Context, Activity等）。

**解决方案**：
- 使用依赖注入框架（Hilt, Dagger）
- 创建接口并使用模拟实现
- 使用Robolectric模拟Android环境
- 采用架构模式分离关注点

### 3. 异步操作测试

**问题**：异步操作（协程、RxJava）使测试复杂化。

**解决方案**：
- 使用TestCoroutineDispatcher
- 使用TestObserver（RxJava）
- 等待策略：awaitility, IdlingResource
- 将异步操作封装在可测试的抽象后

### 4. 数据库和网络测试

**问题**：真实数据库和网络请求会使测试变慢且不稳定。

**解决方案**：
- 使用内存数据库
- 使用MockWebServer模拟网络
- 创建测试替身（Test Doubles）
- 使用仓库模式隔离数据源

### 5. UI测试性能

**问题**：UI测试运行缓慢。

**解决方案**：
- 减少UI测试数量，专注于关键路径
- 并行运行测试
- 使用更快的设备/模拟器
- 预先准备应用状态，避免冗长的设置步骤

---

遵循本指南中的最佳实践和策略，可以建立一个全面、有效的测试体系，提高应用质量，加速开发流程，并确保最终用户满意的产品体验。 