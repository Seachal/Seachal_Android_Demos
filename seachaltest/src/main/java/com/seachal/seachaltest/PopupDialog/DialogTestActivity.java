package com.seachal.seachaltest.PopupDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.seachal.seachaltest.ColorsDialog;
import com.seachal.seachaltest.R;
import com.seachal.seachaltest.log.LogActivity;

public class DialogTestActivity extends LogActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);
        Button button = findViewById(R.id.btn1);
        ColorsDialog colorsDialog = new ColorsDialog(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorsDialog.show();
            }
        });

        Button button2 = findViewById(R.id.btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorsDialog.hide();
            }
        });

        Button btn4 = findViewById(R.id.btn4);

//          创建一个 dialog, 让 dialog依赖 button 居中（位于 button 中心）。  不成功
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View localView = LayoutInflater.from(DialogTestActivity.this).inflate(R.layout.video_dialog_brightness, null);
                if (dialog == null) {
                    dialog = createDialogWithView2(btn4, localView);
                }
                if (!dialog.isShowing()) {
                    dialog.show();
                } else {
                    dialog.dismiss();
                }
            }
        });




        Button btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupUtil.showPopupWindow(DialogTestActivity.this, btn5);
            }
        });


        //        创建一个 dialog, 让 dialog依赖 button 居中（位于 button 中心）。  不成功
        Button btn6 = findViewById(R.id.btn6);
        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View localView = LayoutInflater.from(DialogTestActivity.this).inflate(R.layout.video_dialog_brightness6, null);
                Dialog dialog = createDialogWithView6(btn6, localView);
                if (!dialog.isShowing()) {
                    dialog.show();
                } else {
                    dialog.dismiss();
                }
            }
        });


        Button btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogWithView7(btn7);
            }
        });

    }


    public Dialog createDialogWithView(View localView) {
        Dialog dialog = new Dialog(DialogTestActivity.this, R.style.video_style_dialog_progress);
        dialog.setContentView(localView);
        Window window = dialog.getWindow();
        window.addFlags(Window.FEATURE_ACTION_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        window.setLayout(-2, -2);
        WindowManager.LayoutParams localLayoutParams = window.getAttributes();
        localLayoutParams.gravity = Gravity.CENTER;
        window.setAttributes(localLayoutParams);
        return dialog;
    }


    /**
     * @param localView
     * @return android.app.Dialog
     * <p>
     * <p>
     * Dialog 是依赖于 Window 的，不能直接设置 margin。但是可以通过设置 Dialog 中的内容布局和父容器来实现距离顶部的 margin。下面是一个示例代码：
     * @Author zhangxc
     * @Description //TODO
     * @Date 15:36 2023/6/9
     **/
    public Dialog createDialogWithView2(View anchorView, View localView) {
//
        Dialog dialog = new Dialog(DialogTestActivity.this, R.style.orange_style_dialog_windowBackground);
        dialog.setContentView(localView);
        Window window = dialog.getWindow();
        window.addFlags(Window.FEATURE_ACTION_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        window.setLayout(-2, -2);
        WindowManager.LayoutParams localLayoutParams = window.getAttributes();
        localLayoutParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        window.setAttributes(localLayoutParams);


//        localView的父view 的布局参数，这里改成 RelativeLayout.LayoutParams就报错了，用 FrameLayout.LayoutParams 是对的。
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        /**
         *   E  anchorView.getHeight() = 550
         *   E   localView.getHeight() = 0
         *   E   marginTop = 275
         **/
        int marginTop = (int) ((anchorView.getHeight() - localView.getHeight()) / 2f); // 设置距离顶部的 margin
        Log.e("seachalheig", "anchorView.getHeight() = " + anchorView.getHeight()); // 550 ,为什么是550呢？因为已经测量过了，所以是550
        Log.e("seachalheig", " localView.getHeight() = " + localView.getHeight());  // 0 ,为什么是0呢？因为还没有测量，所以是0，怎样测量？下面有方法
//         帮我写 localView.getHeight()测量方法
        Log.e("seachalheig", " marginTop = " + marginTop);
        layoutParams.topMargin = marginTop;
        localView.setLayoutParams(layoutParams);


        return dialog;

    }


    public Dialog createDialogWithView6(View anchorView, View localView) {
//
        Dialog dialog = new Dialog(DialogTestActivity.this, R.style.orange_style_dialog_background);
        dialog.setContentView(localView);
        Window window = dialog.getWindow();
        window.addFlags(Window.FEATURE_ACTION_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        window.setLayout(-2, -2);
        WindowManager.LayoutParams localLayoutParams = window.getAttributes();
        localLayoutParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        window.setAttributes(localLayoutParams);


//        localView的父view 的布局参数，这里改成 RelativeLayout.LayoutParams就报错了，用 FrameLayout.LayoutParams 是对的。
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        /**
         *   E  anchorView.getHeight() = 550
         *   E   localView.getHeight() = 0
         *   E   marginTop = 275
         **/
        int marginTop = (int) ((anchorView.getHeight() - localView.getHeight()) / 2f); // 设置距离顶部的 margin
        Log.e("seachalheig", "anchorView.getHeight() = " + anchorView.getHeight()); // 550 ,为什么是550呢？因为已经测量过了，所以是550
        Log.e("seachalheig", " localView.getHeight() = " + localView.getHeight());  // 0 ,为什么是0呢？因为还没有测量，所以是0，怎样测量？下面有方法
//         帮我写 localView.getHeight()测量方法
        Log.e("seachalheig", " marginTop = " + marginTop);
        layoutParams.topMargin = marginTop;
        localView.setLayoutParams(layoutParams);


        return dialog;

    }

    public void createDialogWithView7(View targetView) {
        // 获取目标 View 在屏幕上的坐标
        int[] targetLocation = new int[2];
        targetView.getLocationOnScreen(targetLocation);
        int targetX = targetLocation[0] + targetView.getWidth() / 2;
        int targetY = targetLocation[1] + targetView.getHeight() / 2;

// 创建 Dialog
        Dialog dialog = new Dialog(DialogTestActivity.this);
//        View localView = LayoutInflater.from(DialogTestActivity.this).inflate(R.layout.video_dialog_brightness6, null);
        // 设置 Dialog 布局
        dialog.setContentView(R.layout.video_dialog_brightness7);
// 设置 Dialog 布局等参数

// 获取 Window 对象
        Window window = dialog.getWindow();
        if (window != null) {
            // 设置 Dialog 的宽度和高度
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

            // 设置 Dialog 的位置为目标 View 中心
            layoutParams.gravity = Gravity.CENTER | Gravity.LEFT; // 或者使用 Gravity.TOP | Gravity.START
            layoutParams.x = targetX - layoutParams.width / 2;
            layoutParams.y = targetY - layoutParams.height / 2;

            // 检查 Dialog 的位置是否超出屏幕范围，如果是，则根据需要进行调整
            DisplayMetrics displayMetrics = DialogTestActivity.this.getResources().getDisplayMetrics();
            int screenWidth = displayMetrics.widthPixels;
            int screenHeight = displayMetrics.heightPixels;

            if (layoutParams.x < 0) {
                layoutParams.x = 0;
            } else if (layoutParams.x + layoutParams.width > screenWidth) {
                layoutParams.x = screenWidth - layoutParams.width;
            }

            if (layoutParams.y < 0) {
                layoutParams.y = 0;
            } else if (layoutParams.y + layoutParams.height > screenHeight) {
                layoutParams.y = screenHeight - layoutParams.height;
            }

            // 应用 Dialog 的位置参数
            window.setAttributes(layoutParams);
        }


        // 显示 Dialog
        dialog.show();

    }

}