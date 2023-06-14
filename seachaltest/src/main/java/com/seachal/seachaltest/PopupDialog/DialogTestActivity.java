package com.seachal.seachaltest.PopupDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.ColorsDialog;
import com.seachal.seachaltest.R;

public class DialogTestActivity extends AppCompatActivity {
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

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View localView = LayoutInflater.from(DialogTestActivity.this).inflate(R.layout.video_dialog_brightness, null);
                if (dialog == null){
                    dialog = createDialogWithView2(btn4,localView);
                }
                if (!dialog.isShowing()){
                    dialog.show();
                }else {
                    dialog.dismiss();
                }
            }
        });

        Button btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupUtil.showPopupWindow(DialogTestActivity.this,btn5);
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
     * @Author zhangxc
     * @Description //TODO
     * @Date 15:36 2023/6/9
     * @param localView
     * @return android.app.Dialog
     *
     *
     * Dialog 是依赖于 Window 的，不能直接设置 margin。但是可以通过设置 Dialog 中的内容布局和父容器来实现距离顶部的 margin。下面是一个示例代码：
     **/
    public Dialog createDialogWithView2(View anchorView ,View localView) {
        Dialog dialog = new Dialog(DialogTestActivity.this, R.style.video_style_dialog_progress);
        dialog.setContentView(localView);
        Window window = dialog.getWindow();
        window.addFlags(Window.FEATURE_ACTION_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        window.setLayout(-2, -2);
        WindowManager.LayoutParams localLayoutParams = window.getAttributes();
        localLayoutParams.gravity = Gravity.TOP| Gravity.CENTER_HORIZONTAL;
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
        int marginTop = (int) ((anchorView.getHeight() - localView.getHeight())/2f); // 设置距离顶部的 margin
        Log.e( "seachalheig","anchorView.getHeight() = " + anchorView.getHeight()); // 550 ,为什么是550呢？因为已经测量过了，所以是550
        Log.e( "seachalheig"," localView.getHeight() = " + localView.getHeight());  // 0 ,为什么是0呢？因为还没有测量，所以是0，怎样测量？下面有方法
//         帮我写 localView.getHeight()测量方法
        Log.e( "seachalheig"," marginTop = " + marginTop);
        layoutParams.topMargin = marginTop;
        localView.setLayoutParams(layoutParams);


        return dialog;

    }


}