package com.seachal.seachaltest.popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

/**
 * 实现弹出PopupWindow时背景变暗的效果
 */
public class DimBackgroundPopupWindowActivity extends AppCompatActivity {

    private PopupWindow mPopupWindow;
    private View mContentView;
    private float mDimAmount = 0.6f; // 背景变暗的程度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_dim_background_popup_window);

        Button btnShowPopup = findViewById(R.id.btn_show_popup);
        btnShowPopup.setOnClickListener(v -> showPopupWindow());

        // 初始化PopupWindow的内容视图
        mContentView = LayoutInflater.from(this).inflate(R.layout.o_popup_dim_background, null);
        Button btnDismiss = mContentView.findViewById(R.id.btn_dismiss);
        btnDismiss.setOnClickListener(v -> mPopupWindow.dismiss());
    }

    private void showPopupWindow() {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(mContentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    true);

            // 设置背景，这个是必须的，否则点击外部无法消失
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());

            // 设置PopupWindow显示和消失的动画
            mPopupWindow.setAnimationStyle(R.style.o_PopupAnimation);

            // 设置PopupWindow的显示和消失监听
            mPopupWindow.setOnDismissListener(() -> setBackgroundAlpha(1.0f));
        }

        // 显示PopupWindow并使背景变暗
        View rootView = getWindow().getDecorView().getRootView();
        mPopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
        setBackgroundAlpha(mDimAmount);
    }

    /**
     * 设置背景透明度
     * @param alpha 透明度，范围0.0f-1.0f，0完全透明，1完全不透明
     */
    private void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}