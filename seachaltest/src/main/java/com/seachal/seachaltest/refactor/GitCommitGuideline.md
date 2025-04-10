# Git提交规范

## 提交消息格式

所有的提交消息应当遵循以下格式：

```
<类型>(<作用域>): <主题>

<正文>

<页脚>
```

### 类型

- **feat**: 新功能（feature）
- **fix**: 修复bug
- **docs**: 文档更新
- **style**: 代码风格变更（不影响代码运行的变动）
- **refactor**: 代码重构（既不是新增功能，也不是修改bug的代码变动）
- **perf**: 性能优化
- **test**: 增加测试
- **chore**: 构建过程或辅助工具的变动

### 作用域

作用域应当指明本次提交影响的模块或组件，例如：

- **user**: 用户模块
- **login**: 登录模块
- **network**: 网络层
- **ui**: UI组件
- **all**: 影响整个项目

### 主题

主题是对变更的简短描述：

- 使用祈使句，现在时态："change"而不是"changed"或"changes"
- 不要大写第一个字母
- 结尾不加句号（.）
- 不超过50个字符

### 正文

正文是对变更的详细描述：

- 使用祈使句，现在时态
- 包括变更的动机，并与之前的行为进行对比
- 每行不超过72个字符

### 页脚

页脚包含关闭Issue的引用或Breaking Changes的信息：

- **Fixes #123, #456**: 修复的Issue
- **Closes #123, #456**: 关闭的Issue
- **BREAKING CHANGE**: 不兼容的变更

## 提交消息示例

```
feat(user): 添加用户头像上传功能

实现用户头像上传功能，支持裁剪和预览：
- 添加图片选择器
- 添加图片裁剪功能
- 实现头像上传API

Closes #123
```

```
fix(network): 修复网络请求超时问题

将网络请求超时时间从15秒调整为30秒，解决在弱网环境下频繁超时的问题。

Fixes #456
```

```
refactor(login): 重构登录模块

使用MVVM架构重构登录模块，提高代码可维护性：
- 分离UI和业务逻辑
- 引入ViewModel处理登录逻辑
- 使用LiveData更新UI状态
```

## 分支管理

### 分支命名规范

- **master/main**: 主分支，保持稳定，随时可发布
- **develop**: 开发分支，包含最新的开发代码
- **feature/xxx**: 功能分支，用于开发新功能
- **bugfix/xxx**: 修复分支，用于修复非紧急bug
- **hotfix/xxx**: 热修复分支，用于修复紧急生产环境bug
- **release/x.y.z**: 发布分支，用于准备发布

### 分支命名示例

- `feature/user-profile`
- `bugfix/login-crash`
- `hotfix/critical-security-issue`
- `release/1.2.0`

## Git工作流

本项目采用GitFlow工作流：

### 功能开发流程

1. 从`develop`分支创建`feature`分支
   ```bash
   git checkout develop
   git pull
   git checkout -b feature/new-feature
   ```

2. 开发完成后，提交代码
   ```bash
   git add .
   git commit -m "feat(scope): add new feature"
   ```

3. 将`develop`分支合并到`feature`分支，解决冲突
   ```bash
   git checkout develop
   git pull
   git checkout feature/new-feature
   git merge develop
   ```

4. 推送`feature`分支
   ```bash
   git push origin feature/new-feature
   ```

5. 创建Pull Request，请求合并到`develop`分支
6. 代码审查通过后，合并到`develop`分支
7. 删除`feature`分支

### Bug修复流程

1. 从`develop`分支创建`bugfix`分支
   ```bash
   git checkout develop
   git pull
   git checkout -b bugfix/bug-name
   ```

2. 修复bug后，提交代码
   ```bash
   git add .
   git commit -m "fix(scope): fix bug description"
   ```

3. 将`develop`分支合并到`bugfix`分支，解决冲突
   ```bash
   git checkout develop
   git pull
   git checkout bugfix/bug-name
   git merge develop
   ```

4. 推送`bugfix`分支
   ```bash
   git push origin bugfix/bug-name
   ```

5. 创建Pull Request，请求合并到`develop`分支
6. 代码审查通过后，合并到`develop`分支
7. 删除`bugfix`分支

### 热修复流程

1. 从`master/main`分支创建`hotfix`分支
   ```bash
   git checkout master
   git pull
   git checkout -b hotfix/critical-bug
   ```

2. 修复bug后，提交代码
   ```bash
   git add .
   git commit -m "fix(scope): fix critical bug"
   ```

3. 推送`hotfix`分支
   ```bash
   git push origin hotfix/critical-bug
   ```

4. 创建Pull Request，请求合并到`master/main`和`develop`分支
5. 代码审查通过后，合并到`master/main`和`develop`分支
6. 删除`hotfix`分支

### 发布流程

1. 从`develop`分支创建`release`分支
   ```bash
   git checkout develop
   git pull
   git checkout -b release/1.2.0
   ```

2. 修复发布相关问题，更新版本号
   ```bash
   git add .
   git commit -m "chore(release): bump version to 1.2.0"
   ```

3. 推送`release`分支
   ```bash
   git push origin release/1.2.0
   ```

4. 创建Pull Request，请求合并到`master/main`和`develop`分支
5. 代码审查通过后，合并到`master/main`和`develop`分支
6. 在`master/main`分支上打标签
   ```bash
   git checkout master
   git pull
   git tag -a v1.2.0 -m "Version 1.2.0"
   git push origin v1.2.0
   ```

7. 删除`release`分支

## 代码审查指南

### 审查者职责

- 检查代码是否符合项目规范
- 验证功能实现是否符合需求
- 检查是否有潜在的性能问题
- 确保代码可维护性和可读性
- 检查测试覆盖率

### 提交者职责

- 提供清晰的提交描述
- 确保代码符合项目规范
- 编写必要的测试
- 及时响应审查者的反馈
- 解决审查者提出的问题

### Pull Request内容要求

每个Pull Request应包含：

1. **标题**：简明扼要描述变更内容
2. **描述**：详细说明变更的目的和影响
3. **相关Issue**：链接到相关的Issue
4. **测试**：说明如何测试此变更
5. **截图**（如适用）：UI变更的前后对比

## 冲突解决策略

1. **先更新，后合并**：合并前先更新目标分支
2. **小步提交**：频繁提交小的变更，减少冲突可能性
3. **及时沟通**：与团队成员沟通，避免同时修改相同文件
4. **使用工具**：使用合并工具解决复杂冲突

## Tag管理

### 版本号规则

采用语义化版本（Semantic Versioning）：`Major.Minor.Patch`

- **Major**: 不兼容的API变更
- **Minor**: 向后兼容的功能性新增
- **Patch**: 向后兼容的问题修正

### 标签命名

```
v<Major>.<Minor>.<Patch>
```

### 示例

- `v1.0.0`: 首次正式发布
- `v1.1.0`: 新增功能
- `v1.1.1`: 修复bug

## Git使用技巧

### 常用命令

```bash
# 查看日志
git log --oneline --graph --decorate

# 撤销最后一次提交
git reset --soft HEAD~1

# 修改最后一次提交
git commit --amend

# 丢弃工作区变更
git checkout -- <file>

# 暂存工作区
git stash save "work in progress"
git stash pop

# 查看远程分支
git remote -v

# 清理已合并分支
git branch --merged | grep -v "\*" | xargs -n 1 git branch -d
```

### Git别名配置

```bash
git config --global alias.co checkout
git config --global alias.br branch
git config --global alias.ci commit
git config --global alias.st status
git config --global alias.unstage 'reset HEAD --'
git config --global alias.last 'log -1 HEAD'
git config --global alias.visual '!gitk'
```

## Git钩子(Hooks)

### 常用钩子

- **pre-commit**: 提交前运行，用于代码检查、格式化
- **commit-msg**: 验证提交消息格式
- **pre-push**: 推送前运行，用于运行测试

### 提交前检查示例

创建`.git/hooks/pre-commit`文件：

```bash
#!/bin/sh
./gradlew ktlintCheck
if [ $? -ne 0 ]; then
    echo "代码风格检查失败，请修复后再提交"
    exit 1
fi
```

### 提交消息检查示例

创建`.git/hooks/commit-msg`文件：

```bash
#!/bin/sh
commit_msg=$(cat $1)
pattern='^(feat|fix|docs|style|refactor|perf|test|chore)(\([a-z0-9]+\))?: .{1,50}$'

if ! echo "$commit_msg" | head -1 | grep -qE "$pattern"; then
    echo "提交消息格式不符合规范"
    echo "格式应为: <type>(<scope>): <subject>"
    echo "例如: feat(user): add login feature"
    exit 1
fi
```

## CI集成

### 提交前检查

配置CI系统在每次提交时运行：

- 代码风格检查
- 静态代码分析
- 单元测试

### 合并前检查

配置CI系统在合并Pull Request前运行：

- 集成测试
- UI测试
- 性能测试

## 常见问题处理

### 1. 错误提交了敏感信息

```bash
# 删除最后一次提交
git reset --hard HEAD~1
git push origin HEAD --force
```

### 2. 需要回滚到特定版本

```bash
# 创建回滚提交
git revert <commit-hash>

# 或者回滚到特定版本
git reset --hard <commit-hash>
git push origin HEAD --force
```

### 3. 合并错误的分支

```bash
# 撤销合并
git reset --hard ORIG_HEAD
```

### 4. 找回丢失的提交

```bash
# 查看所有操作历史
git reflog

# 恢复到特定状态
git reset --hard <reflog-hash>
``` 