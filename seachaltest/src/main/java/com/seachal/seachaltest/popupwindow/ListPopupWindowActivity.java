package com.seachal.seachaltest.popupwindow;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ListPopupWindow示例
 * <p>
 * 本Activity展示了ListPopupWindow的使用方法，包括：
 * 1. 创建和配置ListPopupWindow
 * 2. 设置ListPopupWindow的数据适配器
 * 3. 处理ListPopupWindow的项目点击事件
 * 4. 控制ListPopupWindow的显示位置和尺寸
 * </p>
 * <p>
 * ListPopupWindow是Android提供的一个特殊的PopupWindow子类，专门用于显示下拉列表。
 * 它简化了创建下拉菜单的过程，无需手动创建和管理ListView。
 * </p>
 */
public class ListPopupWindowActivity extends AppCompatActivity {

    private Button btnShowListPopup;
    private Button btnShowCustomListPopup;
    private Button btnShowWidthListPopup;
    private Button btnShowHeightListPopup;
    private TextView tvFeatureDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_list_popup_window);

        // 初始化视图
        initViews();
        
        // 设置功能描述文本
        setFeatureDescription();
        
        // 设置点击事件
        setClickListeners();
    }

    private void initViews() {
        btnShowListPopup = findViewById(R.id.o_btn_show_list_popup);
        btnShowCustomListPopup = findViewById(R.id.o_btn_show_custom_list_popup);
        btnShowWidthListPopup = findViewById(R.id.o_btn_show_width_list_popup);
        btnShowHeightListPopup = findViewById(R.id.o_btn_show_height_list_popup);
        tvFeatureDescription = findViewById(R.id.o_tv_feature_description);
    }

    private void setFeatureDescription() {
        String description = "【ListPopupWindow示例】\n\n" +
                "1. 基础ListPopupWindow：展示基本的下拉列表功能\n" +
                "2. 自定义样式ListPopupWindow：使用自定义适配器和样式\n" +
                "3. 自定义宽度ListPopupWindow：控制下拉列表的宽度\n" +
                "4. 自定义高度ListPopupWindow：控制下拉列表的高度\n\n" +
                "ListPopupWindow是Android提供的一个特殊的PopupWindow子类，专门用于显示下拉列表，简化了创建下拉菜单的过程。";
        tvFeatureDescription.setText(description);
    }

    private void setClickListeners() {
        // 显示基础ListPopupWindow
        btnShowListPopup.setOnClickListener(v -> showBasicListPopupWindow(v));
        
        // 显示自定义样式ListPopupWindow
        btnShowCustomListPopup.setOnClickListener(v -> showCustomListPopupWindow(v));
        
        // 显示自定义宽度ListPopupWindow
        btnShowWidthListPopup.setOnClickListener(v -> showCustomWidthListPopupWindow(v));
        
        // 显示自定义高度ListPopupWindow
        btnShowHeightListPopup.setOnClickListener(v -> showCustomHeightListPopupWindow(v));
    }

    /**
     * 显示基础ListPopupWindow
     */
    private void showBasicListPopupWindow(View anchorView) {
        // 准备数据
        List<String> items = new ArrayList<>();
        items.add("选项1");
        items.add("选项2");
        items.add("选项3");
        items.add("选项4");
        items.add("选项5");
        
        // 创建ListPopupWindow
        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        
        // 设置适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_list_item_1, items);
        listPopupWindow.setAdapter(adapter);
        
        // 设置锚点视图（ListPopupWindow将显示在此视图下方）
        listPopupWindow.setAnchorView(anchorView);
        
        // 设置项目点击监听器
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListPopupWindowActivity.this, 
                        "选择了：" + items.get(position), Toast.LENGTH_SHORT).show();
                listPopupWindow.dismiss();
            }
        });
        
        // 显示ListPopupWindow
        listPopupWindow.show();
    }

    /**
     * 显示自定义样式ListPopupWindow
     */
    private void showCustomListPopupWindow(View anchorView) {
        // 准备数据
        List<String> items = new ArrayList<>();
        items.add("自定义选项1");
        items.add("自定义选项2");
        items.add("自定义选项3");
        items.add("自定义选项4");
        items.add("自定义选项5");
        
        // 创建ListPopupWindow
        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        
        // 设置适配器（使用自定义布局）
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_list_item_activated_1, items);
        listPopupWindow.setAdapter(adapter);
        
        // 设置锚点视图
        listPopupWindow.setAnchorView(anchorView);
        
        // 设置背景
        listPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg_ffffff_12dp_corner));
        
        // 设置项目点击监听器
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListPopupWindowActivity.this, 
                        "选择了：" + items.get(position), Toast.LENGTH_SHORT).show();
                listPopupWindow.dismiss();
            }
        });
        
        // 显示ListPopupWindow
        listPopupWindow.show();
    }

    /**
     * 显示自定义宽度ListPopupWindow
     */
    private void showCustomWidthListPopupWindow(View anchorView) {
        // 准备数据
        List<String> items = new ArrayList<>();
        items.add("宽度自定义选项1");
        items.add("宽度自定义选项2");
        items.add("宽度自定义选项3");
        items.add("宽度自定义选项4");
        items.add("宽度自定义选项5");
        
        // 创建ListPopupWindow
        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        
        // 设置适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_list_item_1, items);
        listPopupWindow.setAdapter(adapter);
        
        // 设置锚点视图
        listPopupWindow.setAnchorView(anchorView);
        
        // 设置宽度（比锚点视图宽50%）
        listPopupWindow.setWidth((int)(anchorView.getWidth() * 1.5));
        
        // 设置项目点击监听器
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListPopupWindowActivity.this, 
                        "选择了：" + items.get(position), Toast.LENGTH_SHORT).show();
                listPopupWindow.dismiss();
            }
        });
        
        // 显示ListPopupWindow
        listPopupWindow.show();
    }

    /**
     * 显示自定义高度ListPopupWindow
     */
    private void showCustomHeightListPopupWindow(View anchorView) {
        // 准备数据
        List<String> items = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            items.add("高度自定义选项" + i);
        }
        
        // 创建ListPopupWindow
        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        
        // 设置适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_list_item_1, items);
        listPopupWindow.setAdapter(adapter);
        
        // 设置锚点视图
        listPopupWindow.setAnchorView(anchorView);
        
        // 设置高度（限制为200dp）
        int heightInPixels = (int) (200 * getResources().getDisplayMetrics().density);
        listPopupWindow.setHeight(heightInPixels);
        
        // 设置项目点击监听器
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListPopupWindowActivity.this, 
                        "选择了：" + items.get(position), Toast.LENGTH_SHORT).show();
                listPopupWindow.dismiss();
            }
        });
        
        // 显示ListPopupWindow
        listPopupWindow.show();
    }
}