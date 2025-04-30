package com.seachal.seachaltest.flexbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.JustifyContent;
import com.seachal.seachaltest.R;

/**
 * FlexboxLayout示例
 * <p>
 * 本Activity展示了FlexboxLayout的各种用法和属性，包括：
 * 1. 基础布局和自动换行
 * 2. 不同的排列方向（水平、垂直）
 * 3. 不同的对齐方式（开始、结束、居中等）
 * 4. 不同的内容分布方式（等间距、两端对齐等）
 * 5. 子项的伸缩比例设置
 * </p>
 */
public class FlexboxLayoutDemoActivity extends AppCompatActivity {

    private TextView tvFeatureDescription;
    private FlexboxLayout flexboxBasic;
    private FlexboxLayout flexboxDirection;
    private FlexboxLayout flexboxWrap;
    private FlexboxLayout flexboxJustify;
    private FlexboxLayout flexboxAlign;
    private FlexboxLayout flexboxOrder;
    
    // 方向控制按钮
    private Button btnDirectionRow;
    private Button btnDirectionColumn;
    
    // 换行控制按钮
    private Button btnWrapNowrap;
    private Button btnWrapWrap;
    private Button btnWrapWrapReverse;
    
    // 主轴对齐按钮
    private Button btnJustifyStart;
    private Button btnJustifyCenter;
    private Button btnJustifyEnd;
    private Button btnJustifySpaceBetween;
    private Button btnJustifySpaceAround;
    private Button btnJustifySpaceEvenly;
    
    // 交叉轴对齐按钮
    private Button btnAlignStart;
    private Button btnAlignCenter;
    private Button btnAlignEnd;
    private Button btnAlignStretch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_flexbox_layout_demo);
        
        // 设置标题
        setTitle("FlexboxLayout示例");
        
        // 初始化视图
        initViews();
        
        // 设置功能描述文本
        setFeatureDescription();
        
        // 设置点击事件
        setClickListeners();
    }

    private void initViews() {
        tvFeatureDescription = findViewById(R.id.o_tv_feature_description);
        
        // 获取各个FlexboxLayout实例
        flexboxBasic = findViewById(R.id.o_flexbox_basic);
        flexboxDirection = findViewById(R.id.o_flexbox_direction);
        flexboxWrap = findViewById(R.id.o_flexbox_wrap);
        flexboxJustify = findViewById(R.id.o_flexbox_justify);
        flexboxAlign = findViewById(R.id.o_flexbox_align);
        flexboxOrder = findViewById(R.id.o_flexbox_order);
        
        // 方向控制按钮
        btnDirectionRow = findViewById(R.id.o_btn_direction_row);
        btnDirectionColumn = findViewById(R.id.o_btn_direction_column);
        
        // 换行控制按钮
        btnWrapNowrap = findViewById(R.id.o_btn_wrap_nowrap);
        btnWrapWrap = findViewById(R.id.o_btn_wrap_wrap);
        btnWrapWrapReverse = findViewById(R.id.o_btn_wrap_wrap_reverse);
        
        // 主轴对齐按钮
        btnJustifyStart = findViewById(R.id.o_btn_justify_start);
        btnJustifyCenter = findViewById(R.id.o_btn_justify_center);
        btnJustifyEnd = findViewById(R.id.o_btn_justify_end);
        btnJustifySpaceBetween = findViewById(R.id.o_btn_justify_space_between);
        btnJustifySpaceAround = findViewById(R.id.o_btn_justify_space_around);
        btnJustifySpaceEvenly = findViewById(R.id.o_btn_justify_space_evenly);
        
        // 交叉轴对齐按钮
        btnAlignStart = findViewById(R.id.o_btn_align_start);
        btnAlignCenter = findViewById(R.id.o_btn_align_center);
        btnAlignEnd = findViewById(R.id.o_btn_align_end);
        btnAlignStretch = findViewById(R.id.o_btn_align_stretch);
    }

    private void setFeatureDescription() {
        String description = "【FlexboxLayout示例】\n\n" +
                "FlexboxLayout是Google提供的一个布局管理器，实现了CSS Flexible Box Layout模块的功能。\n\n" +
                "1. 基础布局：展示基本的FlexboxLayout布局\n" +
                "2. 排列方向：可设置水平方向(row)或垂直方向(column)\n" +
                "3. 换行方式：控制子项是否换行以及换行方向\n" +
                "4. 主轴对齐：控制子项在主轴上的对齐方式\n" +
                "5. 交叉轴对齐：控制子项在交叉轴上的对齐方式\n" +
                "6. 排列顺序：通过order属性控制子项的显示顺序\n\n" +
                "FlexboxLayout特别适合需要自适应布局的场景，如标签云、不规则网格等。";
        tvFeatureDescription.setText(description);
    }

    private void setClickListeners() {
        // 方向控制按钮点击事件
        btnDirectionRow.setOnClickListener(v -> {
            flexboxDirection.setFlexDirection(FlexDirection.ROW);
            updateButtonStates(btnDirectionRow, btnDirectionColumn);
        });
        
        btnDirectionColumn.setOnClickListener(v -> {
            flexboxDirection.setFlexDirection(FlexDirection.COLUMN);
            updateButtonStates(btnDirectionColumn, btnDirectionRow);
        });
        
        // 换行控制按钮点击事件
        btnWrapNowrap.setOnClickListener(v -> {
            flexboxWrap.setFlexWrap(FlexWrap.NOWRAP);
            updateButtonStates(btnWrapNowrap, btnWrapWrap, btnWrapWrapReverse);
        });
        
        btnWrapWrap.setOnClickListener(v -> {
            flexboxWrap.setFlexWrap(FlexWrap.WRAP);
            updateButtonStates(btnWrapWrap, btnWrapNowrap, btnWrapWrapReverse);
        });
        
        btnWrapWrapReverse.setOnClickListener(v -> {
            flexboxWrap.setFlexWrap(FlexWrap.WRAP_REVERSE);
            updateButtonStates(btnWrapWrapReverse, btnWrapNowrap, btnWrapWrap);
        });
        
        // 主轴对齐按钮点击事件
        btnJustifyStart.setOnClickListener(v -> {
            flexboxJustify.setJustifyContent(JustifyContent.FLEX_START);
            updateButtonStates(btnJustifyStart, btnJustifyCenter, btnJustifyEnd, 
                    btnJustifySpaceBetween, btnJustifySpaceAround, btnJustifySpaceEvenly);
        });
        
        btnJustifyCenter.setOnClickListener(v -> {
            flexboxJustify.setJustifyContent(JustifyContent.CENTER);
            updateButtonStates(btnJustifyCenter, btnJustifyStart, btnJustifyEnd, 
                    btnJustifySpaceBetween, btnJustifySpaceAround, btnJustifySpaceEvenly);
        });
        
        btnJustifyEnd.setOnClickListener(v -> {
            flexboxJustify.setJustifyContent(JustifyContent.FLEX_END);
            updateButtonStates(btnJustifyEnd, btnJustifyStart, btnJustifyCenter, 
                    btnJustifySpaceBetween, btnJustifySpaceAround, btnJustifySpaceEvenly);
        });
        
        btnJustifySpaceBetween.setOnClickListener(v -> {
            flexboxJustify.setJustifyContent(JustifyContent.SPACE_BETWEEN);
            updateButtonStates(btnJustifySpaceBetween, btnJustifyStart, btnJustifyCenter, 
                    btnJustifyEnd, btnJustifySpaceAround, btnJustifySpaceEvenly);
        });
        
        btnJustifySpaceAround.setOnClickListener(v -> {
            flexboxJustify.setJustifyContent(JustifyContent.SPACE_AROUND);
            updateButtonStates(btnJustifySpaceAround, btnJustifyStart, btnJustifyCenter, 
                    btnJustifyEnd, btnJustifySpaceBetween, btnJustifySpaceEvenly);
        });
        
        btnJustifySpaceEvenly.setOnClickListener(v -> {
            flexboxJustify.setJustifyContent(JustifyContent.SPACE_EVENLY);
            updateButtonStates(btnJustifySpaceEvenly, btnJustifyStart, btnJustifyCenter, 
                    btnJustifyEnd, btnJustifySpaceBetween, btnJustifySpaceAround);
        });
        
        // 交叉轴对齐按钮点击事件
        btnAlignStart.setOnClickListener(v -> {
            flexboxAlign.setAlignItems(AlignItems.FLEX_START);
            updateButtonStates(btnAlignStart, btnAlignCenter, btnAlignEnd, btnAlignStretch);
        });
        
        btnAlignCenter.setOnClickListener(v -> {
            flexboxAlign.setAlignItems(AlignItems.CENTER);
            updateButtonStates(btnAlignCenter, btnAlignStart, btnAlignEnd, btnAlignStretch);
        });
        
        btnAlignEnd.setOnClickListener(v -> {
            flexboxAlign.setAlignItems(AlignItems.FLEX_END);
            updateButtonStates(btnAlignEnd, btnAlignStart, btnAlignCenter, btnAlignStretch);
        });
        
        btnAlignStretch.setOnClickListener(v -> {
            flexboxAlign.setAlignItems(AlignItems.STRETCH);
            updateButtonStates(btnAlignStretch, btnAlignStart, btnAlignCenter, btnAlignEnd);
        });
    }
    
    /**
     * 更新按钮状态，将选中的按钮设置为不可点击，其他按钮设置为可点击
     */
    private void updateButtonStates(Button selectedButton, Button... otherButtons) {
        selectedButton.setEnabled(false);
        selectedButton.setAlpha(0.5f);
        
        for (Button button : otherButtons) {
            button.setEnabled(true);
            button.setAlpha(1.0f);
        }
    }
}