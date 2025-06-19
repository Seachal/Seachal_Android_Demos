package com.seachal.seachaltest.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.Activity.AndroiodScreenPropertyActivity;
import com.seachal.seachaltest.Activity.BackgroundActivity;
import com.seachal.seachaltest.Activity.BackgroundTransparentActivity;
import com.seachal.seachaltest.Activity.BackgroundTransparentActivity2;
import com.seachal.seachaltest.Activity.BackgroundTransparentActivity3;
import com.seachal.seachaltest.Activity.ButtonActivity;
import com.seachal.seachaltest.Activity.CanvasSaveRestoreActivity;
import com.seachal.seachaltest.Activity.CardVeiwScrollActivity;
import com.seachal.seachaltest.Activity.CardViewActivity;
import com.seachal.seachaltest.Activity.CardViewActivity3;
import com.seachal.seachaltest.Activity.CardViewActivity4;
import com.seachal.seachaltest.Activity.CardViewActivity5;
import com.seachal.seachaltest.Activity.CountDownTimerActivity;
import com.seachal.seachaltest.Activity.CustomTitleActivity;
import com.seachal.seachaltest.Activity.CustomViewPreviewActivity;
import com.seachal.seachaltest.Activity.DebugActivity;
import com.seachal.seachaltest.Activity.EditTextMinAndMaxLengthActivity;
import com.seachal.seachaltest.Activity.LayerListActivity;
import com.seachal.seachaltest.Activity.OnClickAbleFasleActivity;
import com.seachal.seachaltest.Activity.SecondActivity;
import com.seachal.seachaltest.Activity.SeekBarSynchronizeActivity;
import com.seachal.seachaltest.Activity.SkipToTaobaoActivity;
import com.seachal.seachaltest.Activity.SkipToTaobaoActivity2;
import com.seachal.seachaltest.Activity.StringToUriActivity;
import com.seachal.seachaltest.Activity.TimerActivity;
import com.seachal.seachaltest.Activity.TopShadowTestActivity;
import com.seachal.seachaltest.Activity.URITestActivity;
import com.seachal.seachaltest.Activity.ViewMeasureActivity;
import com.seachal.seachaltest.Activity.ViewStubActivity;
import com.seachal.seachaltest.BitmapDip.BitmapDipActivity;
import com.seachal.seachaltest.FloatingActionButton.FloatingActionButtonActivity;
import com.seachal.seachaltest.HorizontalAndVerticalScreenSwitchActivity;
import com.seachal.seachaltest.ImageViewActivity;
import com.seachal.seachaltest.MediaPlayerActivity;
import com.seachal.seachaltest.MediaPlayerActivity2;
import com.seachal.seachaltest.ObjectAnimator.ObjectAnimatorActivity;
import com.seachal.seachaltest.PopupDialog.DialogTestActivity;
import com.seachal.seachaltest.R;
import com.seachal.seachaltest.RecyclerViewTest.RecyclerViewTestActivity;
import com.seachal.seachaltest.RecyclerViewTest.grid.GridLayoutManagerDemoActivity;
import com.seachal.seachaltest.ScrollListFragment.ScrollListFragmentActivity;
import com.seachal.seachaltest.ShareGeneratePicture.ShareGeneratePictureActivity;
import com.seachal.seachaltest.TextView.SpannableStringActivity;
import com.seachal.seachaltest.TextView.TextStrokeActivity;
import com.seachal.seachaltest.TextView.TextViewMenuActivity;
import com.seachal.seachaltest.ViewGroupAndChildActivity;
import com.seachal.seachaltest.activitystack.TaskStackMenuActivity;
import com.seachal.seachaltest.anr.ANRTestActivity;
import com.seachal.seachaltest.baservhelper.MultipleItemUseActivity;
import com.seachal.seachaltest.bean.StartActivityBean;
import com.seachal.seachaltest.bundle.AActivity;
import com.seachal.seachaltest.bundle.Bundle2AActivity;
import com.seachal.seachaltest.bundle.BundleAActivity;
import com.seachal.seachaltest.dialogfragment.DialogFragmentTestActivity;
import com.seachal.seachaltest.floatrv.RvFloatActivity;
import com.seachal.seachaltest.floatrv.WithAppBarLayout2;
import com.seachal.seachaltest.floatrv.behavior.RvFloatBehaviorActivity;
import com.seachal.seachaltest.gesturedetector.GestureMenuActivity;
import com.seachal.seachaltest.getdimension.GetDimensionActivity;
import com.seachal.seachaltest.info.OsInfoActivity;
import com.seachal.seachaltest.intentflag.LaunchOtherActivity;
import com.seachal.seachaltest.jumptobaseact.JumpActivity;
import com.seachal.seachaltest.jumptobaseact.JumpReferenceActivity;
import com.seachal.seachaltest.layoutparams.LayoutParamsActivity;
import com.seachal.seachaltest.navigation.BottomNavigation2Activity;
import com.seachal.seachaltest.navigation.BottomNavigationActivity;
import com.seachal.seachaltest.onActivityResult.OnActivityResultActivity;
import com.seachal.seachaltest.onActivityResult.OnActivityResultUserFragmentActivity;
import com.seachal.seachaltest.onActivityResult.OnActivityResultUserFragmentContainerActivity;
import com.seachal.seachaltest.onActivityResult.OnActivityResultUserFragmentContainerActivity2;
import com.seachal.seachaltest.onSaveInstanceState.ui.login.LoginActivity;
import com.seachal.seachaltest.onSaveInstanceState.ui.no.LoginActivityNo;
import com.seachal.seachaltest.overlap.OverlapViewActivity;
import com.seachal.seachaltest.permission.PermissionActivity;
import com.seachal.seachaltest.scroll.ScrollToTargetViewActivity;
import com.seachal.seachaltest.startmultiActivity.StartMultiActivity;
import com.seachal.seachaltest.touchevent.DragThreeViewActivity;
import com.seachal.seachaltest.touchevent.DragTwoViewActivity;
import com.seachal.seachaltest.touchevent.DragViewActivity;
import com.seachal.seachaltest.viewpager2.Viewpager2Activity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主菜单活动，以分类方式组织和展示所有示例
 */
public class CategorizedMainMenuActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    
    @BindView(R.id.no_results)
    TextView noResultsText;
    
    @BindView(R.id.header_text)
    TextView headerText;
    
    private List<Category> allCategories;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nenu);
        ButterKnife.bind(this);

        // Set title for the activity
        setTitle("Android Demo示例");

        Log.e("TAG", "onCreate: CategorizedMainMenuActivity");
        
        allCategories = createCategories();
        adapter = new CategoryAdapter(this, allCategories);
        
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Update the header text with demo count
        updateHeaderText(allCategories);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }
        });
        
        // Add clear action when search is closed
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Reset to show all demos when search is closed
                adapter.updateData(allCategories);
                showNoResultsMessage(false);
                return true;
            }
        });
        
        return true;
    }
    
    /**
     * 根据搜索关键词过滤演示项目
     */
    private void filterData(String query) {
        if (query.isEmpty()) {
            adapter.updateData(allCategories);
            showNoResultsMessage(false);
            return;
        }
        
        // 将搜索关键字转为小写，便于不区分大小写搜索
        String lowercaseQuery = query.toLowerCase();
        
        List<Category> filteredCategories = new ArrayList<>();
        
        // 遍历所有分类和活动，筛选匹配的项目
        for (Category category : allCategories) {
            // 创建一个新的分类用于存放匹配的活动
            Category filteredCategory = new Category(category.getTitle());
            
            // 遍历当前分类中的所有活动
            for (StartActivityBean activity : category.getActivities()) {
                // 如果活动标题包含搜索关键字，则添加到过滤结果中
                if (activity.getTitle().toLowerCase().contains(lowercaseQuery)) {
                    filteredCategory.addActivity(activity);
                }
            }
            
            // 如果过滤后的分类包含匹配的活动，则添加到结果列表中
            if (!filteredCategory.getActivities().isEmpty()) {
                filteredCategory.setExpanded(true); // 默认展开包含搜索结果的分类
                filteredCategories.add(filteredCategory);
            }
        }
        
        // 更新适配器数据
        adapter.updateData(filteredCategories);
        
        // 显示"无结果"消息（如果没有匹配项）
        showNoResultsMessage(filteredCategories.isEmpty());
    }
    
    /**
     * 更新顶部标题文字显示示例总数
     */
    private void updateHeaderText(List<Category> categories) {
        int totalDemos = 0;
        for (Category category : categories) {
            totalDemos += category.getActivities().size();
        }
        headerText.setText("Android 示例库（共 " + totalDemos + " 个示例，" + categories.size() + " 个分类）");
    }

    private void showNoResultsMessage(boolean show) {
        noResultsText.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * 创建所有分类及其包含的示例
     */
    private List<Category> createCategories() {
        List<Category> categories = new ArrayList<>();

        // 导航组件示例
        Category navigationCategory = new Category("导航组件");
        navigationCategory.addActivity(new StartActivityBean("底部导航栏示例", "实现Material Design风格的底部导航栏", BottomNavigationActivity.class));
        navigationCategory.addActivity(new StartActivityBean("底部导航栏示例", "实现Material Design风格的底部导航栏2", BottomNavigation2Activity.class));

        navigationCategory.addActivity(new StartActivityBean("跳转到淘宝", "使用隐式Intent打开淘宝App", SkipToTaobaoActivity.class));
        navigationCategory.addActivity(new StartActivityBean("跳转到淘宝2", "通过URI方式跳转至淘宝App", SkipToTaobaoActivity.class));
        navigationCategory.addActivity(new StartActivityBean("Android 跳转栈测试", "测试Activity跳转栈行为", SkipToTaobaoActivity2.class));
        navigationCategory.addActivity(new StartActivityBean("通过协议启动另一个 App", "使用自定义协议启动其他应用", LaunchOtherActivity.class));
        navigationCategory.addActivity(new StartActivityBean("获取Activity Task栈多种方式", "展示不同方法获取任务栈信息", TaskStackMenuActivity.class));
        categories.add(navigationCategory);
        
        // UI组件 - 图片相关
        Category imageCategory = new Category("图片组件");
        imageCategory.addActivity(new StartActivityBean("ImageView测试", "测试ImageView的基本功能和属性", ImageViewActivity.class));
        imageCategory.addActivity(new StartActivityBean("分享生成图片", "动态生成图片并分享到其他应用", ShareGeneratePictureActivity.class));
        imageCategory.addActivity(new StartActivityBean("不同资源目录下的Bitmap内存占用", "分析不同资源目录下图片的内存占用情况", BitmapDipActivity.class));
        categories.add(imageCategory);

        // UI组件 - 文本相关
        Category textCategory = new Category("文本组件");
        textCategory.addActivity(new StartActivityBean("TextView菜单", "展示TextView的上下文菜单功能", TextViewMenuActivity.class));
        textCategory.addActivity(new StartActivityBean("文本描边", "实现文字描边效果展示", TextStrokeActivity.class));
        textCategory.addActivity(new StartActivityBean("富文本SpannableString", "使用SpannableString实现富文本效果", SpannableStringActivity.class));
        textCategory.addActivity(new StartActivityBean("EditText最小/最大字符数", "设置输入框字符数限制", EditTextMinAndMaxLengthActivity.class));
        textCategory.addActivity(new StartActivityBean("String转URI", "演示字符串与URI的转换", StringToUriActivity.class));
        textCategory.addActivity(new StartActivityBean("URI测试", "测试URI的各种方法与属性", URITestActivity.class));
        categories.add(textCategory);

        // UI组件 - 按钮相关
        Category buttonCategory = new Category("按钮组件");
        buttonCategory.addActivity(new StartActivityBean("Button测试", "测试各种按钮类型和样式", ButtonActivity.class));
        buttonCategory.addActivity(new StartActivityBean("FloatingActionButton", "Material Design浮动操作按钮实现", FloatingActionButtonActivity.class));
        buttonCategory.addActivity(new StartActivityBean("点击事件禁用", "禁用视图点击事件的方法演示", OnClickAbleFasleActivity.class));
        buttonCategory.addActivity(new StartActivityBean("CheckBox和RadioButton示例", "复选框和单选按钮的使用，包含自定义样式和颜色", com.seachal.seachaltest.checkbox_radiobutton.CheckBoxRadioButtonActivity.class));
        categories.add(buttonCategory);

        // UI组件 - 布局相关
        Category layoutCategory = new Category("布局组件");
        layoutCategory.addActivity(new StartActivityBean("RecyclerView多种Grid布局", "展示不同类型的网格布局", RecyclerViewTestActivity.class));
        layoutCategory.addActivity(new StartActivityBean("GridLayoutManager演示", "完整的GridLayoutManager使用示例", GridLayoutManagerDemoActivity.class));
        layoutCategory.addActivity(new StartActivityBean("🌟 GridSpaceDecoration演示", "专业的网格间距装饰器，支持复杂布局和精确间距控制", com.seachal.seachaltest.RecyclerViewTest.GridSpaceDecorationDemoActivity.class));
        layoutCategory.addActivity(new StartActivityBean("CardView基础", "基础卡片视图使用方法", CardViewActivity.class));
        layoutCategory.addActivity(new StartActivityBean("CardView滚动效果", "滚动时卡片的动画效果", CardVeiwScrollActivity.class));
        layoutCategory.addActivity(new StartActivityBean("MaterialCardView", "Material Design风格卡片", CardViewActivity3.class));
        layoutCategory.addActivity(new StartActivityBean("CardView嵌套", "卡片视图的嵌套使用", CardViewActivity4.class));
        layoutCategory.addActivity(new StartActivityBean("ShadowLayout阴影", "自定义阴影效果实现", CardViewActivity5.class));
        layoutCategory.addActivity(new StartActivityBean("Background背景测试", "测试背景对子视图的影响", BackgroundActivity.class));
        layoutCategory.addActivity(new StartActivityBean("View与父View测量", "父子视图的测量关系", ViewMeasureActivity.class));
        layoutCategory.addActivity(new StartActivityBean("Layer-list测试", "使用Layer-list创建复合drawable", LayerListActivity.class));
        layoutCategory.addActivity(new StartActivityBean("ViewStub延迟加载", "按需加载界面元素", ViewStubActivity.class));
        layoutCategory.addActivity(new StartActivityBean("LayoutParams属性测试", "测试布局参数的作用", LayoutParamsActivity.class));
        layoutCategory.addActivity(new StartActivityBean("父子布局显示隐藏关系", "父视图控制子视图显示状态", ViewGroupAndChildActivity.class));
        layoutCategory.addActivity(new StartActivityBean("自定义标题栏", "创建自定义标题栏", CustomTitleActivity.class));
        layoutCategory.addActivity(new StartActivityBean("屏幕参数测试", "获取屏幕尺寸与密度", AndroiodScreenPropertyActivity.class));
        layoutCategory.addActivity(new StartActivityBean("透明度测试1", "控制视图透明度", BackgroundTransparentActivity.class));
        layoutCategory.addActivity(new StartActivityBean("透明度测试2", "不同透明度效果对比", BackgroundTransparentActivity2.class));
        layoutCategory.addActivity(new StartActivityBean("RecyclerView滑动模式", "不同的滑动行为模式", BackgroundTransparentActivity3.class));
        layoutCategory.addActivity(new StartActivityBean("MultipleItem适配器", "BaseRecyclerViewAdapterHelper的多类型适配器示例", MultipleItemUseActivity.class));
        layoutCategory.addActivity(new StartActivityBean("顶部圆角阴影测试", "比较不同的顶部圆角和阴影实现方案", TopShadowTestActivity.class));
        categories.add(layoutCategory);

        // UI组件 - 弹窗和对话框
        Category dialogCategory = new Category("弹窗和对话框");
        dialogCategory.addActivity(new StartActivityBean("对话框测试", "各种对话框的使用方法", DialogTestActivity.class));
        dialogCategory.addActivity(new StartActivityBean("DialogFragment测试", "使用DialogFragment创建对话框", DialogFragmentTestActivity.class));
        dialogCategory.addActivity(new StartActivityBean("PopupWindow示例", "各种PopupWindow的使用方法和效果展示", com.seachal.seachaltest.popupwindow.PopupWindowMenuActivity.class));
        categories.add(dialogCategory);

        // 动画和过渡效果
        Category animationCategory = new Category("动画和过渡");
        animationCategory.addActivity(new StartActivityBean("ObjectAnimator动画", "使用ObjectAnimator创建动画", ObjectAnimatorActivity.class));
        animationCategory.addActivity(new StartActivityBean("横竖屏切换", "处理屏幕方向变化", HorizontalAndVerticalScreenSwitchActivity.class));
        categories.add(animationCategory);

        // 滚动和列表
        Category scrollCategory = new Category("滚动和列表");
        scrollCategory.addActivity(new StartActivityBean("浮动效果(Scroll实现)", "使用滚动实现浮动效果", RvFloatActivity.class));
        scrollCategory.addActivity(new StartActivityBean("浮动效果(Behavior实现)", "使用Behavior实现浮动效果", RvFloatBehaviorActivity.class));
        scrollCategory.addActivity(new StartActivityBean("移除AppBarLayout阴影", "自定义AppBarLayout去除阴影", WithAppBarLayout2.class));
        scrollCategory.addActivity(new StartActivityBean("滚动到指定位置", "控制滚动到特定视图位置", ScrollToTargetViewActivity.class));
        scrollCategory.addActivity(new StartActivityBean("Fragment列表滚动生命周期", "分析滚动对Fragment生命周期的影响", ScrollListFragmentActivity.class));
        categories.add(scrollCategory);

        // 事件处理
        Category eventCategory = new Category("事件处理");
        eventCategory.addActivity(new StartActivityBean("长按拖拽示例1", "基础长按拖拽功能", DragViewActivity.class));
        eventCategory.addActivity(new StartActivityBean("长按拖拽示例2", "改进的拖拽实现", DragTwoViewActivity.class));
        eventCategory.addActivity(new StartActivityBean("长按拖拽示例3", "复杂拖拽交互", DragThreeViewActivity.class));
        eventCategory.addActivity(new StartActivityBean("重叠View事件响应", "分析重叠视图的事件分发", OverlapViewActivity.class));
        eventCategory.addActivity(new StartActivityBean("手势检测", "使用GestureDetector检测各种手势", GestureMenuActivity.class));
        categories.add(eventCategory);

        // 页面和导航管理
        Category pageManagementCategory = new Category("页面管理");
        pageManagementCategory.addActivity(new StartActivityBean("ViewPager2", "使用ViewPager2实现页面滑动", Viewpager2Activity.class));
        pageManagementCategory.addActivity(new StartActivityBean("跳转方式1", "Activity跳转方法示例", JumpActivity.class));
        pageManagementCategory.addActivity(new StartActivityBean("跳转方式2", "另一种Activity跳转方式", JumpReferenceActivity.class));
        pageManagementCategory.addActivity(new StartActivityBean("启动多个Activity", "连续启动多个页面", StartMultiActivity.class));
        // 新增 CustomTabDemoActivity 跳转入口
        pageManagementCategory.addActivity(
            new StartActivityBean(
                "CustomTab演示", // 标题
                "自定义Tab页面演示", // 简要描述
                com.seachal.seachaltest.customtab.CustomTabDemoActivity.class // 跳转的Activity类
            )
        );
        categories.add(pageManagementCategory);

        // 系统功能
        Category systemCategory = new Category("系统功能");
        systemCategory.addActivity(new StartActivityBean("计时器", "使用定时器功能", TimerActivity.class));
        systemCategory.addActivity(new StartActivityBean("倒计时", "倒计时功能实现", CountDownTimerActivity.class));
        systemCategory.addActivity(new StartActivityBean("设备信息", "获取设备系统信息", OsInfoActivity.class));
        systemCategory.addActivity(new StartActivityBean("权限测试", "运行时权限请求和处理", PermissionActivity.class));
        systemCategory.addActivity(new StartActivityBean("ANR测试", "模拟应用无响应场景", ANRTestActivity.class));
        systemCategory.addActivity(new StartActivityBean("getDimension测试", "测试不同获取尺寸方法的区别", GetDimensionActivity.class));
        systemCategory.addActivity(new StartActivityBean("SeekBar同步", "多个进度条同步控制", SeekBarSynchronizeActivity.class));
        categories.add(systemCategory);

        // 多媒体功能
        Category multimediaCategory = new Category("多媒体功能");
        multimediaCategory.addActivity(new StartActivityBean("媒体播放器", "音频播放基础功能", MediaPlayerActivity.class));
        multimediaCategory.addActivity(new StartActivityBean("媒体播放器监听", "处理媒体播放器事件", MediaPlayerActivity2.class));
        categories.add(multimediaCategory);

        // 活动结果和状态
        Category activityResultCategory = new Category("活动结果和状态");
        activityResultCategory.addActivity(new StartActivityBean("Activity生命周期", "Activity生命周期回调演示", SecondActivity.class));
        activityResultCategory.addActivity(new StartActivityBean("onActivityResult基础", "使用onActivityResult获取返回数据", OnActivityResultActivity.class));
        activityResultCategory.addActivity(new StartActivityBean("DialogFragment结果", "从DialogFragment返回结果", OnActivityResultUserFragmentActivity.class));
        activityResultCategory.addActivity(new StartActivityBean("Fragment发起结果", "Fragment启动Activity获取结果", OnActivityResultUserFragmentContainerActivity.class));
        activityResultCategory.addActivity(new StartActivityBean("Fragment结果传递", "Fragment间结果传递", OnActivityResultUserFragmentContainerActivity2.class));
        activityResultCategory.addActivity(new StartActivityBean("状态保存和恢复", "保存和恢复Activity状态", LoginActivity.class));
        activityResultCategory.addActivity(new StartActivityBean("无状态保存和恢复", "不保存状态的对比示例", LoginActivityNo.class));
        categories.add(activityResultCategory);

        // 数据传递
        Category dataTransferCategory = new Category("数据传递");
        dataTransferCategory.addActivity(new StartActivityBean("Intent传递数据", "使用Intent在组件间传递数据", AActivity.class));
        dataTransferCategory.addActivity(new StartActivityBean("Bundle传递数据1", "使用putExtra传递Bundle", BundleAActivity.class));
        dataTransferCategory.addActivity(new StartActivityBean("Bundle传递数据2", "使用putExtras传递Bundle", Bundle2AActivity.class));
        categories.add(dataTransferCategory);

        // 绘制和自定义视图
        Category customViewCategory = new Category("绘制和自定义视图");
        customViewCategory.addActivity(new StartActivityBean("Canvas保存和恢复", "Canvas状态管理", CanvasSaveRestoreActivity.class));
        customViewCategory.addActivity(new StartActivityBean("自定义进度条", "创建自定义进度指示器", CustomViewPreviewActivity.class));
        categories.add(customViewCategory);

        // 开发工具和测试
        Category developerToolsCategory = new Category("开发工具和调试");
        developerToolsCategory.addActivity(new StartActivityBean("调试工具", "Android调试技巧演示", DebugActivity.class));
        categories.add(developerToolsCategory);
        
        // FlexboxLayout布局示例
        Category flexboxCategory = new Category("FlexboxLayout布局");
        flexboxCategory.addActivity(new StartActivityBean("FlexboxLayout示例集", "FlexboxLayout的各种用法和特性展示", com.seachal.seachaltest.flexbox.FlexboxLayoutMenuActivity.class));
        categories.add(flexboxCategory);

        // WebView 示例
        Category webViewCategory = new Category("WebView 示例");
        webViewCategory.addActivity(new StartActivityBean("WebView 示例菜单", "WebView 功能的完整演示集合", com.seachal.seachaltest.webview.WebViewMenuActivity.class));
        webViewCategory.addActivity(new StartActivityBean("基础 WebView", "基本的网页加载和导航功能", com.seachal.seachaltest.webview.BasicWebViewActivity.class));
        webViewCategory.addActivity(new StartActivityBean("JavaScript 交互", "Android 与 JavaScript 双向通信", com.seachal.seachaltest.webview.JSInteractionActivity.class));
        webViewCategory.addActivity(new StartActivityBean("进度条 WebView", "带加载进度显示的 WebView", com.seachal.seachaltest.webview.ProgressWebViewActivity.class));
        webViewCategory.addActivity(new StartActivityBean("文件操作", "WebView 文件上传下载功能", com.seachal.seachaltest.webview.FileOperationActivity.class));
        webViewCategory.addActivity(new StartActivityBean("缓存管理", "WebView 缓存策略和离线访问", com.seachal.seachaltest.webview.CacheWebViewActivity.class));
        categories.add(webViewCategory);

        return categories;
    }
}