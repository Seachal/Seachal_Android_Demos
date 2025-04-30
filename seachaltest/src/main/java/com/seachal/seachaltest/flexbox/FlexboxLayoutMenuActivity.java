package com.seachal.seachaltest.flexbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.R;
import com.seachal.seachaltest.bean.StartActivityBean;
import com.seachal.seachaltest.menu.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FlexboxLayout示例菜单
 * <p>
 * 本Activity作为FlexboxLayout示例的入口，展示了FlexboxLayout的各种用法和特性，
 * 包括基础布局、自适应换行、对齐方式、排列方向等多个示例。
 * </p>
 */
public class FlexboxLayoutMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_flexbox_layout_menu);

        // 设置标题
        setTitle("FlexboxLayout示例集");

        // 初始化视图
        recyclerView = findViewById(R.id.o_recycler_view);
        tvDescription = findViewById(R.id.o_tv_description);

        // 设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 设置适配器
        CategoryAdapter adapter = new CategoryAdapter(this, createDemoList());
        recyclerView.setAdapter(adapter);

        // 设置描述文本
        setDescriptionText();
    }

    /**
     * 创建FlexboxLayout示例列表
     */
    private List<com.seachal.seachaltest.menu.Category> createDemoList() {
        List<com.seachal.seachaltest.menu.Category> categories = new ArrayList<>();

        // 创建FlexboxLayout示例分类
        com.seachal.seachaltest.menu.Category flexboxCategory = 
                new com.seachal.seachaltest.menu.Category("FlexboxLayout示例");

        // 添加FlexboxLayout基础示例
        flexboxCategory.addActivity(new StartActivityBean(
                "FlexboxLayout基础",
                "展示FlexboxLayout的基本用法和属性",
                FlexboxLayoutDemoActivity.class));
        
        // 添加FlexboxLayout标签流示例
        flexboxCategory.addActivity(new StartActivityBean(
                "FlexboxLayout标签流",
                "使用FlexboxLayout实现标签云效果",
                FlexboxLayoutDemoActivity.class));
        
        // 添加FlexboxLayout网格示例
        flexboxCategory.addActivity(new StartActivityBean(
                "FlexboxLayout网格布局",
                "使用FlexboxLayout实现自适应网格布局",
                FlexboxLayoutDemoActivity.class));

        // 设置分类默认展开
        flexboxCategory.setExpanded(true);
        
        // 添加分类到列表
        categories.add(flexboxCategory);

        return categories;
    }

    /**
     * 设置描述文本
     */
    private void setDescriptionText() {
        String description = "【FlexboxLayout示例集】\n\n" +
                "FlexboxLayout是Google提供的一个布局管理器，实现了CSS Flexible Box Layout模块的功能。" +
                "它特别适合需要自适应布局的场景，如标签云、不规则网格等。\n\n" +
                "本示例集包含以下内容：\n" +
                "1. 基础布局：展示FlexboxLayout的基本属性和用法\n" +
                "2. 标签流：使用FlexboxLayout实现标签云效果\n" +
                "3. 网格布局：使用FlexboxLayout实现自适应网格布局\n\n" +
                "选择上方列表中的示例进行查看。";
        
        tvDescription.setText(description);
    }
}