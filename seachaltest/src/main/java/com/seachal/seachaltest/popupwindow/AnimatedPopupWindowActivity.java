package com.seachal.seachaltest.popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

/**
 * 带动画效果的PopupWindow示例
 * <p>
 * 本Activity展示了如何为PopupWindow添加动画效果，包括：
 * 1. 使用系统预定义动画
 * 2. 使用自定义XML动画资源
 * 3. 从不同方向弹出的动画效果
 * 4. 设置动画时长
 * </p>
 */
public class AnimatedPopupWindowActivity extends AppCompatActivity {

    private Button btnSystemAnimation;
    private Button btnCustomAnimation;
    private Button btnBottomAnimation;
    private Button btnTopAnimation;
    private Button btnLeftAnimation;
    private Button btnRightAnimation;
    private TextView tvFeatureDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_animated_popup_window);

        // 初始化视图
        initViews();
        
        // 设置功能描述文本
        setFeatureDescription();
        
        // 设置点击事件
        setClickListeners();
    }

    private void initViews() {
        btnSystemAnimation = findViewById(R.id.o_btn_system_animation);
        btnCustomAnimation = findViewById(R.id.o_btn_custom_animation);
        btnBottomAnimation = findViewById(R.id.o_btn_bottom_animation);
        btnTopAnimation = findViewById(R.id.o_btn_top_animation);
        btnLeftAnimation = findViewById(R.id.o_btn_left_animation);
        btnRightAnimation = findViewById(R.id.o_btn_right_animation);
        tvFeatureDescription = findViewById(R.id.o_tv_feature_description);
    }

    private void setFeatureDescription() {
        String description = "【带动画效果的PopupWindow示例】\n\n" +
                "1. 系统动画：使用Android系统预定义的动画资源\n" +
                "2. 自定义动画：使用项目中自定义的XML动画资源\n" +
                "3. 方向动画：从不同方向弹出的动画效果\n\n" +
                "PopupWindow动画通过setAnimationStyle()方法设置，可以使用系统预定义的动画样式或自定义的动画资源。";
        tvFeatureDescription.setText(description);
    }

    private void setClickListeners() {
        // 使用系统预定义动画
        btnSystemAnimation.setOnClickListener(v -> showPopupWithSystemAnimation());
        
        // 使用自定义XML动画资源
        btnCustomAnimation.setOnClickListener(v -> showPopupWithCustomAnimation());
        
        // 从底部弹出
        btnBottomAnimation.setOnClickListener(v -> showPopupFromBottom());
        
        // 从顶部弹出
        btnTopAnimation.setOnClickListener(v -> showPopupFromTop());
        
        // 从左侧弹出
        btnLeftAnimation.setOnClickListener(v -> showPopupFromLeft());
        
        // 从右侧弹出
        btnRightAnimation.setOnClickListener(v -> showPopupFromRight());
    }

    /**
     * 使用系统预定义动画的PopupWindow
     */
    private void showPopupWithSystemAnimation() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("使用系统预定义动画\nandroid.R.style.Animation_Dialog");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置系统预定义动画
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        
        // 在屏幕中心显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 使用自定义XML动画资源的PopupWindow
     */
    private void showPopupWithCustomAnimation() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("使用自定义XML动画资源\npop_from_bottom_anim_in/out");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置自定义动画样式（使用项目中已有的动画资源）
        popupWindow.setAnimationStyle(R.style.o_PopupAnimation);
        
        // 在屏幕中心显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 从底部弹出的PopupWindow
     */
    private void showPopupFromBottom() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("从底部弹出的PopupWindow");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.MATCH_PARENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画样式（从底部弹出）
        popupWindow.setAnimationStyle(R.style.o_PopupAnimationFromBottom);
        
        // 在屏幕底部显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 从顶部弹出的PopupWindow
     */
    private void showPopupFromTop() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("从顶部弹出的PopupWindow");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.MATCH_PARENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画样式（从顶部弹出）
        popupWindow.setAnimationStyle(R.style.o_PopupAnimationFromTop);
        
        // 在屏幕顶部显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.TOP, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 从左侧弹出的PopupWindow
     */
    private void showPopupFromLeft() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("从左侧弹出的PopupWindow");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画样式（从左侧弹出）
        popupWindow.setAnimationStyle(R.style.o_PopupAnimationFromLeft);
        
        // 在屏幕左侧显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.START | Gravity.CENTER_VERTICAL, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 从右侧弹出的PopupWindow
     */
    private void showPopupFromRight() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_basic, null);
        TextView tvPopupContent = contentView.findViewById(R.id.o_tv_popup_content);
        tvPopupContent.setText("从右侧弹出的PopupWindow");
        
        // 设置关闭按钮点击事件
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画样式（从右侧弹出）
        popupWindow.setAnimationStyle(R.style.o_PopupAnimationFromRight);
        
        // 在屏幕右侧显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.END | Gravity.CENTER_VERTICAL, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }
}