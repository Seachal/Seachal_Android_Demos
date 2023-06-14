package com.seachal.seachaltest.PopupDialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.seachal.seachaltest.R;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 *
 * @author zhangxc
 * @Description: TODO
 * @date 2023/6/9 14:28
 * @Version：1.0
 */
public class PopupUtil {
    public static void showPopupWindow(Context context, View anchorView) {

        // 创建一个布局作为弹窗的容器
        LinearLayout popupLayout = new LinearLayout(context);
        popupLayout.setOrientation(LinearLayout.VERTICAL);

        // 向布局中添加一些组件
        TextView textview = new TextView(context);
        textview.setText("Hello World");
        popupLayout.addView(textview);

        EditText editText = new EditText(context);
        popupLayout.addView(editText);

        Button button = new Button(context);
        button.setText("确定");
        popupLayout.addView(button);

        // 创建popup窗口并将其与布局关联起来
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(popupLayout);
        popup.setWidth(200); // 宽度自适应
        popup.setHeight(300); // 高度自适应
        popup.setBackgroundDrawable(new ColorDrawable()); // 设置背景，使PopupWindow能够响应返回键或者点击空白区域消失
        popup.setFocusable(true); // 设置焦点，防止点击事件穿透PopupWindow被传到底层界面上
        popup.setOutsideTouchable(true); // 设置是否允许PopupWindow以外触摸范围来关闭该窗口
        popup.setAnimationStyle(R.style.pop_animation); // 设置动画效果，样式指定在styles.xml中定义的pop_animation_style


        int[] location = new int[2];
        anchorView.getLocationOnScreen(location); // 获取依赖某个View的坐标位置
        int viewX = location[0];
        int viewY = location[1];

// 计算PopupWindow显示的位置，这里以右上角为例

        Log.d("zxc", "showPopupWindow:viewX = "+ viewX);
        Log.d("zxc", "showPopupWindow:anchorView.getWidth() = "+ anchorView.getWidth());
        Log.d("zxc", "showPopupWindow:popup.getWidth = "+  popup.getWidth());
        Log.d("zxc", "showPopupWindow:viewY = "+ viewY);
        Log.d("zxc", "showPopupWindow:anchorView.getHeight() = "+ anchorView.getHeight());
        Log.d("zxc", "showPopupWindow: popup.getHeight() = "+  popup.getHeight());


        int x = viewX + anchorView.getWidth() - popup.getWidth();
        int y = viewY + anchorView.getHeight()- popup.getHeight();
        Log.d("zxc", "showPopupWindow: x = " + x + " y = " + y);


/*//        这里并没有依赖于anchorView，而是直接指定了位置
        popup.showAtLocation(anchorView, Gravity.END, 0, 0);*/

        popup.showAtLocation(anchorView, Gravity.LEFT, viewX, viewY);


    }
}

/**
 * @Author zhangxc
 * @Description //TODO
 * @Date 14:56 2023/6/9
 *
 * @return * @return null
 *
 *
 *  这是 popup 大小不固定的时候，  算出的  pop 宽是 -2  高是 -2，说明计算不对。
 *   D  showPopupWindow:viewX = 0
 *   D  showPopupWindow:anchorView.getWidth() = 825
 *   D  showPopupWindow:popup.getWidth = -2
 *   D  showPopupWindow:viewY = 932
 *   D  showPopupWindow:anchorView.getHeight() = 550
 *   D  showPopupWindow: popup.getHeight() = -2
 *   D  showPopupWindow: x = 827 y = 1484
 *
 *
 *
 *
 *
 **/


