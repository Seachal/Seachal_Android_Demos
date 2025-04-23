//package com.seachal.seachaltest;
//
//import android.os.Bundle;
//import android.util.Log;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.seachal.seachaltest.Activity.AndroiodScreenPropertyActivity;
//import com.seachal.seachaltest.Activity.BackgroundActivity;
//import com.seachal.seachaltest.Activity.BackgroundTransparentActivity;
//import com.seachal.seachaltest.Activity.BackgroundTransparentActivity2;
//import com.seachal.seachaltest.Activity.BackgroundTransparentActivity3;
//import com.seachal.seachaltest.Activity.ButtonActivity;
//import com.seachal.seachaltest.Activity.CanvasSaveRestoreActivity;
//import com.seachal.seachaltest.Activity.CardVeiwScrollActivity;
//import com.seachal.seachaltest.Activity.CardViewActivity;
//import com.seachal.seachaltest.Activity.CardViewActivity3;
//import com.seachal.seachaltest.Activity.CardViewActivity4;
//import com.seachal.seachaltest.Activity.CardViewActivity5;
//import com.seachal.seachaltest.Activity.CountDownTimerActivity;
//import com.seachal.seachaltest.Activity.CustomTitleActivity;
//import com.seachal.seachaltest.Activity.CustomViewPreviewActivity;
//import com.seachal.seachaltest.Activity.DebugActivity;
//import com.seachal.seachaltest.Activity.EditTextMinAndMaxLengthActivity;
//import com.seachal.seachaltest.Activity.LayerListActivity;
//import com.seachal.seachaltest.Activity.OnClickAbleFasleActivity;
//import com.seachal.seachaltest.Activity.SecondActivity;
//import com.seachal.seachaltest.Activity.SeekBarSynchronizeActivity;
//import com.seachal.seachaltest.Activity.SkipToTaobaoActivity;
//import com.seachal.seachaltest.Activity.SkipToTaobaoActivity2;
//import com.seachal.seachaltest.Activity.StringToUriActivity;
//import com.seachal.seachaltest.Activity.TimerActivity;
//import com.seachal.seachaltest.Activity.URITestActivity;
//import com.seachal.seachaltest.Activity.ViewMeasureActivity;
//import com.seachal.seachaltest.Activity.ViewStubActivity;
//import com.seachal.seachaltest.BitmapDip.BitmapDipActivity;
//import com.seachal.seachaltest.FloatingActionButton.FloatingActionButtonActivity;
//import com.seachal.seachaltest.ObjectAnimator.ObjectAnimatorActivity;
//import com.seachal.seachaltest.PopupDialog.DialogTestActivity;
//import com.seachal.seachaltest.RecyclerViewTest.RecyclerViewTestActivity;
//import com.seachal.seachaltest.ScrollListFragment.ScrollListFragmentActivity;
//import com.seachal.seachaltest.ShareGeneratePicture.ShareGeneratePictureActivity;
//import com.seachal.seachaltest.TextView.TextViewMenuActivity;
//import com.seachal.seachaltest.activitystack.TaskStackMenuActivity;
//import com.seachal.seachaltest.adapter.MyAdapter;
//import com.seachal.seachaltest.anr.ANRTestActivity;
//import com.seachal.seachaltest.baservhelper.MultipleItemUseActivity;
//import com.seachal.seachaltest.bean.StartActivityBean;
//import com.seachal.seachaltest.bundle.AActivity;
//import com.seachal.seachaltest.bundle.Bundle2AActivity;
//import com.seachal.seachaltest.bundle.BundleAActivity;
//import com.seachal.seachaltest.dialogfragment.DialogFragmentTestActivity;
//import com.seachal.seachaltest.floatrv.RvFloatActivity;
//import com.seachal.seachaltest.floatrv.WithAppBarLayout2;
//import com.seachal.seachaltest.floatrv.behavior.RvFloatBehaviorActivity;
//import com.seachal.seachaltest.gesturedetector.GestureMenuActivity;
//import com.seachal.seachaltest.getdimension.GetDimensionActivity;
//import com.seachal.seachaltest.info.OsInfoActivity;
//import com.seachal.seachaltest.intentflag.LaunchOtherActivity;
//import com.seachal.seachaltest.jumptobaseact.JumpActivity;
//import com.seachal.seachaltest.jumptobaseact.JumpReferenceActivity;
//import com.seachal.seachaltest.layoutparams.LayoutParamsActivity;
//import com.seachal.seachaltest.onActivityResult.OnActivityResultActivity;
//import com.seachal.seachaltest.onActivityResult.OnActivityResultUserFragmentActivity;
//import com.seachal.seachaltest.onActivityResult.OnActivityResultUserFragmentContainerActivity;
//import com.seachal.seachaltest.onActivityResult.OnActivityResultUserFragmentContainerActivity2;
//import com.seachal.seachaltest.onSaveInstanceState.ui.login.LoginActivity;
//import com.seachal.seachaltest.onSaveInstanceState.ui.no.LoginActivityNo;
//import com.seachal.seachaltest.overlap.OverlapViewActivity;
//import com.seachal.seachaltest.permission.PermissionActivity;
//import com.seachal.seachaltest.scroll.ScrollToTargetViewActivity;
//import com.seachal.seachaltest.startmultiActivity.StartMultiActivity;
//import com.seachal.seachaltest.touchevent.DragThreeViewActivity;
//import com.seachal.seachaltest.touchevent.DragTwoViewActivity;
//import com.seachal.seachaltest.touchevent.DragViewActivity;
//import com.seachal.seachaltest.viewpager2.Viewpager2Activity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
//public class MainMenuActivity extends AppCompatActivity {
//
//    @BindView(R.id.recycler_view)
//    RecyclerView recycler_view;
//
//    private final int RESULT_CODE_1 = 100;
//    private long exitTime = 0;
//
//
//    private List activityList = new ArrayList<StartActivityBean>();
//
//
//    {
//
////         0-9
//        activityList.add(new StartActivityBean("android ANR测试", ANRTestActivity.class));
//        activityList.add(new StartActivityBean("Bitmmap 放在不同的资源文件夹下，加载时所占用的内存", BitmapDipActivity.class));
//        activityList.add(new StartActivityBean("android 分享生成图片", ShareGeneratePictureActivity.class));
//        activityList.add(new StartActivityBean("RecyclerView 多种 grid 布局", RecyclerViewTestActivity.class));
//        activityList.add(new StartActivityBean("CountDownTimer 倒计时", CountDownTimerActivity.class));
//        activityList.add(new StartActivityBean("Uri.parse", URITestActivity.class));
//        activityList.add(new StartActivityBean("跳转到淘宝", SkipToTaobaoActivity.class));
//        activityList.add(new StartActivityBean("跳转到淘宝2", SkipToTaobaoActivity.class));
//        activityList.add(new StartActivityBean("android 跳转栈测试", SkipToTaobaoActivity2.class));
//        activityList.add(new StartActivityBean("View与父 View 的测量", ViewMeasureActivity.class));
//        activityList.add(new StartActivityBean("EditText  最小字符数，最大字符数,addView", EditTextMinAndMaxLengthActivity.class));
//
////       10-19
//        activityList.add(new StartActivityBean("TextView ", TextViewMenuActivity.class));
//        activityList.add(new StartActivityBean("TimerActivity 生命周期 SecondActivity", SecondActivity.class));
//        activityList.add(new StartActivityBean("DebugActivity", DebugActivity.class));
//        activityList.add(new StartActivityBean("自定义 进度条", CustomViewPreviewActivity.class));
//        activityList.add(new StartActivityBean("权限 & 设置, 在设置拒绝位置权限，app是否会被杀死？是。", PermissionActivity.class));
//        activityList.add(new StartActivityBean("测试 RadioButton 是否可以使用 gif,tint png", ImageViewActivity.class));
//        activityList.add(new StartActivityBean("background 并不会对子 view裁剪， 甚至会被子 view 遮挡住圆角。 ", BackgroundActivity.class));
//        activityList.add(new StartActivityBean("cardView 阴影颜色", CardViewActivity.class));
//        activityList.add(new StartActivityBean("cardView 原生  越靠近屏幕底部颜色越深", CardVeiwScrollActivity.class));
//        activityList.add(new StartActivityBean("MaterialCardView", CardViewActivity3.class));
//        activityList.add(new StartActivityBean("cardview 嵌套 cardview", CardViewActivity4.class));
//
////        20-29
//        activityList.add(new StartActivityBean("lihangleo2:ShadowLayout:3.3.2", CardViewActivity5.class));
////        activityList.add(new StartActivityBean(" shadeoimage", CardViewActivity6.class));
//        activityList.add(new StartActivityBean("屏幕 各种参数", AndroiodScreenPropertyActivity.class));
//        activityList.add(new StartActivityBean("自定义 titleView", CustomTitleActivity.class));
//        activityList.add(new StartActivityBean("ViewStub", ViewStubActivity.class));
//        activityList.add(new StartActivityBean("如果禁用 TimerActivity 中所有点击事件", OnClickAbleFasleActivity.class));
//        activityList.add(new StartActivityBean("Button RadioGroupButton  RadioButton", ButtonActivity.class));
//        activityList.add(new StartActivityBean("Dialog", DialogTestActivity.class));
//        activityList.add(new StartActivityBean("DialogFragment, 从 TimerActivity 弹出 Dialog不会影响，TimerActivity 生命周期 ", DialogFragmentTestActivity.class));
//        activityList.add(new StartActivityBean("Canvas Save Restore 画布保存与恢复", CanvasSaveRestoreActivity.class));
//        activityList.add(new StartActivityBean("手势 GestureDetector menu ", GestureMenuActivity.class));
//        activityList.add(new StartActivityBean("一次启动多个 TimerActivity,完全可以", StartMultiActivity.class));
//
////      30-39
//        activityList.add(new StartActivityBean(" 获取Activity Task栈 menu 多种方式", TaskStackMenuActivity.class));
//        activityList.add(new StartActivityBean("String 转 uri,JsonString 转 Bean", StringToUriActivity.class));
//        activityList.add(new StartActivityBean(" 自定义长按事件 1 ", DragViewActivity.class));
//        activityList.add(new StartActivityBean(" 自定义长按事件 2 成功案例 ", DragTwoViewActivity.class));
//        activityList.add(new StartActivityBean(" 自定义长按事件 3失败案例", DragThreeViewActivity.class));
//        activityList.add(new StartActivityBean(" 有两个重叠的 view , 点击的时候，上层的 view 和下层的 view 都能响应事件吗?", OverlapViewActivity.class));
//        activityList.add(new StartActivityBean(" layer-list 测试案例", LayerListActivity.class));
//        activityList.add(new StartActivityBean(" getDimension  与 getDimensionPixelSize 区别  ", GetDimensionActivity.class));
//        activityList.add(new StartActivityBean(" 通过协议启动另一个 App  ", LaunchOtherActivity.class));
//        activityList.add(new StartActivityBean(" xml锁定方向，手机横竖屏旋转，屏幕内容不会发生变化， 不会触发onConfigurationChanged?  ", HorizontalAndVerticalScreenSwitchActivity.class));
//        activityList.add(new StartActivityBean(" 悬浮效果  scroll实现 ", RvFloatActivity.class));
//
////      40-49
//        activityList.add(new StartActivityBean(" 悬浮效果  behavior 实现, behavior更丝滑", RvFloatBehaviorActivity.class));
//        activityList.add(new StartActivityBean("  去掉AppBarLayout阴影", WithAppBarLayout2.class));
//        activityList.add(new StartActivityBean(" 2 个 seekbar进度同步", SeekBarSynchronizeActivity.class));
//        activityList.add(new StartActivityBean(" Transparent  Transparency 不透明度", BackgroundTransparentActivity.class));
//        activityList.add(new StartActivityBean(" Transparent  Transparency 透明度", BackgroundTransparentActivity2.class));
//        activityList.add(new StartActivityBean(" recycleview 的几种滑动模式", BackgroundTransparentActivity3.class));
//        activityList.add(new StartActivityBean(" baseRecyclerViewAdapterHelper MultipleItemUseActivity", MultipleItemUseActivity.class));
//        activityList.add(new StartActivityBean(" FloatingActionButton ", FloatingActionButtonActivity.class));
//        activityList.add(new StartActivityBean(" LayoutParams gravity  layout_gravity addRule ", LayoutParamsActivity.class));
//        activityList.add(new StartActivityBean(" onActivityResult:TimerActivity ", OnActivityResultActivity.class));
//        activityList.add(new StartActivityBean(" onActivityResult:DialogFragment ", OnActivityResultUserFragmentActivity.class));
//
////        50-59
//        activityList.add(new StartActivityBean(" onActivityResult:Fragment 发起 StartActivityForResult", OnActivityResultUserFragmentContainerActivity.class));
//        activityList.add(new StartActivityBean(" onActivityResult:MyFragment 发起 StartActivityForResult, BaseFragment1会收到onActivityResult 回调吗？", OnActivityResultUserFragmentContainerActivity2.class));
//        activityList.add(new StartActivityBean(" 列表中有 Fragment ,上滑完全离开可视区域，会执行Fragment 生命周期方法吗？ 答：不会 ", ScrollListFragmentActivity.class));
//        activityList.add(new StartActivityBean(" 点击启动属性动画左右滑动3s 后隐藏 ", ObjectAnimatorActivity.class));
//        activityList.add(new StartActivityBean("用基类的伴生对象方法跳转，和基类 TimerActivity 调用子类 TimerActivity 的方式 1  ", JumpActivity.class));
//        activityList.add(new StartActivityBean("基类 TimerActivity 调用子类 TimerActivity 的方式2   ", JumpReferenceActivity.class));
//        activityList.add(new StartActivityBean("Timer 定时器启动了  ", TimerActivity.class));
//        activityList.add(new StartActivityBean("滑动到指定位置  ", ScrollToTargetViewActivity.class));
//        activityList.add(new StartActivityBean("判断手机系统  ", OsInfoActivity.class));
//        activityList.add(new StartActivityBean("onSaveInstanceState 和 onRestoreInstanceState    ", LoginActivity.class));
//        activityList.add(new StartActivityBean("No  onSaveInstanceState 和 onRestoreInstanceState    ", LoginActivityNo.class));
//
//
////        60-69
//        activityList.add(new StartActivityBean("viewpgager2  更新数据时的刷新   ", Viewpager2Activity.class));
//        activityList.add(new StartActivityBean("父子布局的显示隐藏,父布局的隐藏对子布局的影响  ", ViewGroupAndChildActivity.class));
//        activityList.add(new StartActivityBean("音乐媒体播放器 music media player  ", MediaPlayerActivity.class));
//        activityList.add(new StartActivityBean("音乐媒体播放器 music media player prepare 监听  ", MediaPlayerActivity2.class));
//        activityList.add(new StartActivityBean("测试 ImageView 被 LinearLayout遮挡后，能否响应点击  ", HomeActivity.class));
//        activityList.add(new StartActivityBean("A Activity 给 B Activity 中传递数据，B Activity 要把数据传递给 B Fragment , 用intent", AActivity.class));
//        activityList.add(new StartActivityBean("A Activity 给 B Activity 中传递数据，B Activity 要把数据传递给 B Fragment , 用Bundle  intent.putExtra( \"bundle_key\",bundle)", BundleAActivity.class));
//        activityList.add(new StartActivityBean("A Activity 给 B Activity 中传递数据，B Activity 要把数据传递给 B Fragment , 用Bundle  intent.putExtras( bundle)", Bundle2AActivity.class));
//
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_nenu);
//        ButterKnife.bind(this);
//        Log.e("TAG", "onCreate: " + "MainMenuActivity");
//        recycler_view.setAdapter(new MyAdapter(MainMenuActivity.this, activityList));
//        recycler_view.setLayoutManager(new LinearLayoutManager(MainMenuActivity.this));
//
////        // 判断权限
////        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
////        if (EasyPermissions.hasPermissions(this, perms)) {
////            recycler_view.setAdapter(new MyAdapter(MainActivity.this, activityList));
////            recycler_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
////        } else {
////            // 如果用户拒绝权限，第二次打开才会显示提示文字
////            EasyPermissions.requestPermissions(this, "维持App正常运行需要存储权限", RESULT_CODE_1, perms);
////        }
//    }
//
//
////    // 双击退出
////    @Override
////    public void onBackPressed() {
////        if (System.currentTimeMillis() - exitTime > 2000) {
////            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
////            exitTime = System.currentTimeMillis();
////        } else {
////            super.onBackPressed();
////        }
////    }
//
//
////    @Override
////    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////
////        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
////    }
////
////    /**
////     * 同意授权
////     *
////     * @param requestCode
////     * @param perms
////     */
////    @Override
////    public void onPermissionsGranted(int requestCode, List<String> perms) {
////
////
////    }
////
////    /**
////     * 拒绝授权
////     *
////     * @param requestCode
////     * @param perms
////     */
////    @Override
////    public void onPermissionsDenied(int requestCode, List<String> perms) {
////        Toast.makeText(this, "权限", Toast.LENGTH_SHORT).show();
////        finish();
////    }
//
//
//}
