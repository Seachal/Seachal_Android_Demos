package com.seachal.seachaltest.popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

/**
 * 实现带箭头指向特定位置的PopupWindow
 */
public class ArrowPopupWindowActivity extends AppCompatActivity {

    private PopupWindow mPopupWindow;
    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_arrow_popup_window);

        Button btnTop = findViewById(R.id.btn_show_top);
        Button btnBottom = findViewById(R.id.btn_show_bottom);
        Button btnLeft = findViewById(R.id.btn_show_left);
        Button btnRight = findViewById(R.id.btn_show_right);

        btnTop.setOnClickListener(v -> showPopupWindow(v, PopupPosition.TOP));
        btnBottom.setOnClickListener(v -> showPopupWindow(v, PopupPosition.BOTTOM));
        btnLeft.setOnClickListener(v -> showPopupWindow(v, PopupPosition.LEFT));
        btnRight.setOnClickListener(v -> showPopupWindow(v, PopupPosition.RIGHT));

        // 初始化PopupWindow的内容视图
        mContentView = LayoutInflater.from(this).inflate(R.layout.o_popup_arrow, null);
        Button btnDismiss = mContentView.findViewById(R.id.btn_dismiss);
        btnDismiss.setOnClickListener(v -> mPopupWindow.dismiss());
    }

    private enum PopupPosition {
        TOP, BOTTOM, LEFT, RIGHT
    }

    private void showPopupWindow(View anchor, PopupPosition position) {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(mContentView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    true);

            // 设置背景，这个是必须的，否则点击外部无法消失
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        }

        // 根据位置设置不同的动画样式和显示位置
        int animStyle;
        int xOffset = 0;
        int yOffset = 0;

        switch (position) {
            case TOP:
                animStyle = R.style.o_PopupAnimationTop;
                yOffset = -anchor.getHeight();
                break;
            case BOTTOM:
                animStyle = R.style.o_PopupAnimationBottom;
                yOffset = anchor.getHeight();
                break;
            case LEFT:
                animStyle = R.style.o_PopupAnimationLeft;
                xOffset = -anchor.getWidth();
                break;
            case RIGHT:
            default:
                animStyle = R.style.o_PopupAnimationRight;
                xOffset = anchor.getWidth();
                break;
        }

        mPopupWindow.setAnimationStyle(animStyle);
        mPopupWindow.showAsDropDown(anchor, xOffset, yOffset);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}