

## 应用安装状态检查接口
#### 1. 检查微信安装状态
```javascript
isWeChatInstalled()
```

+ **功能**: 检查用户设备是否已安装微信应用
+ **参数**: 无
+ **返回值**: `Boolean` 类型
    - `true`: 已安装微信
    - `false`: 未安装微信

#### 2. 通用应用安装状态检查
```javascript
isAppInstalled(packageName)
```

+ **功能**: 检查指定包名的应用是否已安装
+ **参数**: 
    - `packageName` (String): 应用包名
+ **返回值**: `Boolean` 类型
    - `true`: 已安装该应用
    - `false`: 未安装该应用

#### 常用应用包名参考
| 应用名称 | 包名 | 示例 |
| --- | --- | --- |
| QQ | `com.tencent.mobileqq` | isAppInstalled("com.tencent.mobileqq") |
| 支付宝 | `com.eg.android.AlipayGphone` | isAppInstalled("com.eg.android.AlipayGphone") |


#### 注意事项
+ 所有方法都是同步调用，直接返回结果
+ 返回值为原生 Boolean 类型，无需 JSON 解析
+ 建议优先使用 `isWeChatInstalled()` 检查微信，调用更简洁
+ 使用 `isAppInstalled()` 可以检查任意应用的安装状态

