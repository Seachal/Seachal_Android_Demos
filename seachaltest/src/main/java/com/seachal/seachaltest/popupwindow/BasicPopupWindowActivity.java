package com.seachal.seachaltest.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

/**
 * 基础PopupWindow示例
 * <p>
 * 本Activity展示了PopupWindow的基本用法，包括：
 * 1. 创建基本的PopupWindow
 * 2. 设置PopupWindow的尺寸和背景
 * 3. 控制PopupWindow的显示位置
 * 4. 处理PopupWindow的点击事件和消失回调
 * 5. 设置PopupWindow的焦点和外部点击关闭行为
 * </p>
 */
public class BasicPopupWindowActivity extends AppCompatActivity {

    private Button btnShowAtLocation;
    private Button btnShowAsDropDown;
    private Button btnShowWithFocus;
    private Button btnShowWithoutFocus;
    private TextView tvFeatureDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_basic_popup_window);

        // 初始化视图
        initViews();
        
        // 设置功能描述文本
        setFeatureDescription();
        
        // 设置点击事件
        setClickListeners();
    }

    private void initViews() {
        btnShowAtLocation = findViewById(R.id.o_btn_show_at_location);
        btnShowAsDropDown = findViewById(R.id.o_btn_show_as_dropdown);
        btnShowWithFocus = findViewById(R.id.o_btn_show_with_focus);
        btnShowWithoutFocus = findViewById(R.id.o_btn_show_without_focus);
        tvFeatureDescription = findViewById(R.id.o_tv_feature_description);
    }

    private void setFeatureDescription() {
        String description = "【基础PopupWindow示例】\n\n" +
                "1. showAtLocation：在指定位置显示PopupWindow\n" +
                "2. showAsDropDown：作为下拉菜单显示在指定View下方\n" +
                "3. 带焦点：PopupWindow可以接收触摸事件，点击外部关闭\n" +
                "4. 无焦点：PopupWindow不接收触摸事件，点击外部不关闭\n\n" +
                "PopupWindow是一个轻量级的浮动视图容器，可以显示在Activity的顶层，不会改变当前Activity的生命周期。";
        tvFeatureDescription.setText(description);
    }

    private void setClickListeners() {
        // 在指定位置显示PopupWindow
        btnShowAtLocation.setOnClickListener(v -> showPopupWindowAtLocation());
        
        // 作为下拉菜单显示
        btnShowAsDropDown.setOnClickListener(v -> showPopupWindowAsDropDown(v));
        
        // 带焦点的PopupWindow（可点击外部关闭）
        btnShowWithFocus.setOnClickListener(v -> showPopupWindowWithFocus());
        
        // 无焦点的PopupWindow（点击外部不关闭）
        btnShowWithoutFocus.setOnClickListener(v -> showPopupWindowWithoutFocus());
    }

    /**
     * 在屏幕中心位置显示PopupWindow
     */
    private void showPopupWindowAtLocation() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("使用showAtLocation方法显示在屏幕中心");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景（必须设置背景才能使点击外部关闭生效）
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        
        // 设置消失监听
        popupWindow.setOnDismissListener(() -> 
                Toast.makeText(BasicPopupWindowActivity.this, 
                        "PopupWindow已关闭", Toast.LENGTH_SHORT).show());
        
        // 在指定位置显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 作为下拉菜单显示在指定View下方
     */
    private void showPopupWindowAsDropDown(View anchorView) {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("使用showAsDropDown方法显示在按钮下方");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        
        // 在指定View下方显示PopupWindow
        popupWindow.showAsDropDown(anchorView);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 显示带焦点的PopupWindow（点击外部可关闭）
     */
    private void showPopupWindowWithFocus() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("带焦点的PopupWindow\n点击外部区域可关闭");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow，第四个参数设为true表示可获取焦点
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true); // 设置为true，表示PopupWindow可获取焦点
        
        // 设置背景（必须设置背景才能使点击外部关闭生效）
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        
        // 设置触摸外部关闭
        popupWindow.setOutsideTouchable(true);
        
        // 在屏幕中心显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 显示无焦点的PopupWindow（点击外部不关闭）
     */
    private void showPopupWindowWithoutFocus() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("无焦点的PopupWindow\n点击外部区域不会关闭\n只能通过关闭按钮关闭");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow，第四个参数设为false表示不获取焦点
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                false); // 设置为false，表示PopupWindow不获取焦点
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        
        // 设置触摸外部不关闭
        popupWindow.setOutsideTouchable(false);
        
        // 在屏幕中心显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }
}