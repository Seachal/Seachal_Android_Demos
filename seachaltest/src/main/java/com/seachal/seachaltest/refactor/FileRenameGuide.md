# 文件重命名指南

本文档列出项目中需要重命名的文件，以符合统一的命名规范。

## Activity类重命名

| 当前名称 | 建议名称 | 重命名原因 |
|---------|---------|----------|
| CardVeiwScrollActivity | CardViewScrollActivity | 拼写错误：Veiw -> View |
| AndroiodScreenPropertyActivity | AndroidScreenPropertyActivity | 拼写错误：Androiod -> Android |
| URITestActivity | UriTestActivity | 命名规范：URI应为Uri (遵循Java类名规范) |
| CardViewActivity3 | MaterialCardViewActivity | 数字命名改为具体功能描述 |
| CardViewActivity4 | NestedCardViewActivity | 数字命名改为具体功能描述 |
| CardViewActivity5 | ShadowLayoutCardViewActivity | 数字命名改为具体功能描述 |
| BackgroundTransparentActivity | TransparencyOpacityActivity | 更明确描述功能 |
| BackgroundTransparentActivity2 | TransparencyAlphaActivity | 更明确描述功能 |
| BackgroundTransparentActivity3 | RecyclerViewScrollModeActivity | 名称与功能不符，更正为实际功能 |
| OnClickAbleFasleActivity | DisableClickEventsActivity | 拼写错误+表达不清晰 |
| DragTwoViewActivity | DragViewSuccessExampleActivity | 更清晰描述功能 |
| DragThreeViewActivity | DragViewFailureExampleActivity | 更清晰描述功能 |

## Fragment类重命名

| 当前名称 | 建议名称 | 重命名原因 |
|---------|---------|----------|
| *待检查Fragment类名* | | |

## 包名重命名

| 当前名称 | 建议名称 | 重命名原因 |
|---------|---------|----------|
| TextView | text | 包名应使用小写，与包内类区分 |
| BitmapDip | bitmap | 包名应明确表达内容 |
| FloatingActionButton | floatingbutton | 包名应简洁 |
| baservhelper | baseadapter | 包名应表达实际功能 |

## 其他建议

1. 将Activity分包到更具体的功能包中，例如：
   - com.seachal.seachaltest.ui.layout
   - com.seachal.seachaltest.ui.button
   - com.seachal.seachaltest.ui.text

2. 对于测试类，建议添加明确的Test前缀或后缀，例如：
   - ANRTestActivity
   - UriTestActivity

3. 对于示例类，建议遵循一致的命名模式：
   - 示例类：SomethingExampleActivity
   - 测试类：SomethingTestActivity
   - 演示类：SomethingDemoActivity

4. 对于有版本号或多种实现的类，使用描述性后缀，如：
   - 而非：CardViewActivity3
   - 应为：MaterialCardViewActivity 