package com.seachal.seachaltest.popupwindow;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.R;
import com.seachal.seachaltest.adapter.MyAdapter;
import com.seachal.seachaltest.bean.StartActivityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * PopupWindow示例菜单
 * <p>
 * 本Activity展示了各种PopupWindow的使用场景和实现方式，包括：
 * 1. 基础PopupWindow - 展示最基本的弹窗实现
 * 2. 带动画效果的PopupWindow - 展示弹出和消失动画
 * 3. 自定义布局PopupWindow - 展示如何使用自定义布局
 * 4. 带背景变暗效果的PopupWindow - 实现背景变暗效果
 * 5. 带箭头指向的PopupWindow - 实现带箭头指向特定位置的弹窗
 * </p>
 */
public class PopupWindowMenuActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<StartActivityBean> activityList = new ArrayList<>();

    {
        // 添加各种PopupWindow示例
        activityList.add(new StartActivityBean("基础PopupWindow", "展示最基本的PopupWindow创建和显示方法", BasicPopupWindowActivity.class));
        activityList.add(new StartActivityBean("带动画效果的PopupWindow", "展示PopupWindow的进入和退出动画效果", AnimatedPopupWindowActivity.class));
        activityList.add(new StartActivityBean("自定义布局PopupWindow", "使用自定义XML布局创建复杂PopupWindow", CustomLayoutPopupWindowActivity.class));
        activityList.add(new StartActivityBean("背景变暗的PopupWindow", "实现弹出PopupWindow时背景变暗的效果", DimBackgroundPopupWindowActivity.class));
        activityList.add(new StartActivityBean("带箭头的PopupWindow", "实现带箭头指向特定位置的PopupWindow", ArrowPopupWindowActivity.class));
        activityList.add(new StartActivityBean("ListPopupWindow", "使用ListPopupWindow实现下拉列表", ListPopupWindowActivity.class));
        activityList.add(new StartActivityBean("PopupMenu", "使用PopupMenu实现上下文菜单", PopupMenuActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nenu);
        ButterKnife.bind(this);
        
        // 设置标题
        getSupportActionBar().setTitle("PopupWindow示例");
        
        Log.e("TAG", "onCreate: PopupWindowMenuActivity");
        
        // 设置RecyclerView
        recyclerView.setAdapter(new MyAdapter(PopupWindowMenuActivity.this, activityList));
        recyclerView.setLayoutManager(new LinearLayoutManager(PopupWindowMenuActivity.this));
    }
}