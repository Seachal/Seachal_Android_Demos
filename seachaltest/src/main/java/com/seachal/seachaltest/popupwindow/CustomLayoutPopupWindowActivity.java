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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

/**
 * 自定义布局PopupWindow示例
 * <p>
 * 本Activity展示了如何使用自定义XML布局创建复杂的PopupWindow，包括：
 * 1. 创建包含多种控件的自定义布局
 * 2. 处理PopupWindow中控件的点击事件
 * 3. 实现不同样式和功能的PopupWindow
 * 4. 在PopupWindow中使用RecyclerView等复杂控件
 * </p>
 */
public class CustomLayoutPopupWindowActivity extends AppCompatActivity {

    private Button btnSimpleCustomLayout;
    private Button btnComplexCustomLayout;
    private Button btnFormPopupWindow;
    private Button btnConfirmPopupWindow;
    private TextView tvFeatureDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_custom_layout_popup_window);

        // 初始化视图
        initViews();
        
        // 设置功能描述文本
        setFeatureDescription();
        
        // 设置点击事件
        setClickListeners();
    }

    private void initViews() {
        btnSimpleCustomLayout = findViewById(R.id.o_btn_simple_custom_layout);
        btnComplexCustomLayout = findViewById(R.id.o_btn_complex_custom_layout);
        btnFormPopupWindow = findViewById(R.id.o_btn_form_popup_window);
        btnConfirmPopupWindow = findViewById(R.id.o_btn_confirm_popup_window);
        tvFeatureDescription = findViewById(R.id.o_tv_feature_description);
    }

    private void setFeatureDescription() {
        String description = "【自定义布局PopupWindow示例】\n\n" +
                "1. 简单自定义布局：使用自定义XML布局创建基本PopupWindow\n" +
                "2. 复杂自定义布局：包含多种控件的复杂PopupWindow\n" +
                "3. 表单PopupWindow：包含输入框等表单控件的PopupWindow\n" +
                "4. 确认对话框PopupWindow：实现类似确认对话框功能的PopupWindow\n\n" +
                "通过自定义布局，可以实现各种复杂的PopupWindow界面，满足不同的业务需求。";
        tvFeatureDescription.setText(description);
    }

    private void setClickListeners() {
        // 简单自定义布局
        btnSimpleCustomLayout.setOnClickListener(v -> showSimpleCustomLayoutPopup());
        
        // 复杂自定义布局
        btnComplexCustomLayout.setOnClickListener(v -> showComplexCustomLayoutPopup());
        
        // 表单PopupWindow
        btnFormPopupWindow.setOnClickListener(v -> showFormPopupWindow());
        
        // 确认对话框PopupWindow
        btnConfirmPopupWindow.setOnClickListener(v -> showConfirmPopupWindow());
    }

    /**
     * 显示简单自定义布局的PopupWindow
     */
    private void showSimpleCustomLayoutPopup() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_simple_custom, null);
        
        // 获取视图中的控件
        TextView tvTitle = contentView.findViewById(R.id.o_tv_popup_title);
        TextView tvContent = contentView.findViewById(R.id.o_tv_popup_content);
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 设置控件内容
        tvTitle.setText("简单自定义布局");
        tvContent.setText("这是一个使用自定义XML布局创建的简单PopupWindow，包含标题、内容和关闭按钮。");
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画
        popupWindow.setAnimationStyle(R.style.o_PopupAnimation);
        
        // 在屏幕中心显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 显示复杂自定义布局的PopupWindow
     */
    private void showComplexCustomLayoutPopup() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_complex_custom, null);
        
        // 获取视图中的控件
        Button btnOption1 = contentView.findViewById(R.id.o_btn_option1);
        Button btnOption2 = contentView.findViewById(R.id.o_btn_option2);
        Button btnOption3 = contentView.findViewById(R.id.o_btn_option3);
        Button btnClose = contentView.findViewById(R.id.o_btn_close_popup);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画
        popupWindow.setAnimationStyle(R.style.o_PopupAnimation);
        
        // 在屏幕中心显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        
        // 设置选项按钮点击事件
        btnOption1.setOnClickListener(v -> {
            Toast.makeText(CustomLayoutPopupWindowActivity.this, "选择了选项1", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        
        btnOption2.setOnClickListener(v -> {
            Toast.makeText(CustomLayoutPopupWindowActivity.this, "选择了选项2", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        
        btnOption3.setOnClickListener(v -> {
            Toast.makeText(CustomLayoutPopupWindowActivity.this, "选择了选项3", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        
        // 设置关闭按钮点击事件
        btnClose.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 显示包含表单的PopupWindow
     */
    private void showFormPopupWindow() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_form, null);
        
        // 获取视图中的控件
        Button btnSubmit = contentView.findViewById(R.id.o_btn_submit);
        Button btnCancel = contentView.findViewById(R.id.o_btn_cancel);
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画
        popupWindow.setAnimationStyle(R.style.o_PopupAnimation);
        
        // 在屏幕中心显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        
        // 设置提交按钮点击事件
        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(CustomLayoutPopupWindowActivity.this, "表单已提交", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        
        // 设置取消按钮点击事件
        btnCancel.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * 显示确认对话框样式的PopupWindow
     */
    private void showConfirmPopupWindow() {
        // 创建PopupWindow的内容视图
        View contentView = LayoutInflater.from(this).inflate(R.layout.o_popup_window_confirm, null);
        
        // 获取视图中的控件
        TextView tvTitle = contentView.findViewById(R.id.o_tv_popup_title);
        TextView tvContent = contentView.findViewById(R.id.o_tv_popup_content);
        Button btnConfirm = contentView.findViewById(R.id.o_btn_confirm);
        Button btnCancel = contentView.findViewById(R.id.o_btn_cancel);
        
        // 设置控件内容
        tvTitle.setText("确认操作");
        tvContent.setText("您确定要执行此操作吗？此操作不可撤销。");
        
        // 创建PopupWindow
        PopupWindow popupWindow = new PopupWindow(contentView, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                true);
        
        // 设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        
        // 设置动画
        popupWindow.setAnimationStyle(R.style.o_PopupAnimation);
        
        // 在屏幕中心显示PopupWindow
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        
        // 设置确认按钮点击事件
        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(CustomLayoutPopupWindowActivity.this, "操作已确认", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        
        // 设置取消按钮点击事件
        btnCancel.setOnClickListener(v -> {
            Toast.makeText(CustomLayoutPopupWindowActivity.this, "操作已取消", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
    }
}