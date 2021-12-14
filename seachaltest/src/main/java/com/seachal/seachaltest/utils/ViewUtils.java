package com.seachal.seachaltest.utils;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Description:
 *
 * @author liuyuge
 * @date: 2017-03-14 10:23
 */

public class ViewUtils {
    /**
     * 添加一个线性布局
     *
     * @param viewParent
     * @return
     */
    public static ViewGroup addView(ViewGroup viewParent) {
        LinearLayout linearLayout = new LinearLayout(viewParent.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        viewParent.addView(linearLayout);
        return linearLayout;
    }

    /**
     * 回收ImageView占用的图像内存;
     *
     * @param view
     */
    public static void recycleImageView(View view) {
        //详见 https://developer.android.com/topic/performance/graphics/manage-memory.html#recycle
        /*
         * 我们支持的版本最低版本已经是14 无需强制回收，导致的
         *  Canvas: trying to use a recycled bitmap android.graphics.Bitmap@172a7ed
            android.graphics.Canvas.throwIfCannotDraw(Canvas.java:1270)
            如果需要强制回收，可以参考ImageLoader的ImageViewAware，使用弱引用
         */
        /*if (view == null) return;
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable instanceof BitmapDrawable) {
                Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
                if (bmp != null && !bmp.isRecycled()) {
                    ((ImageView) view).setImageBitmap(null);
                    bmp.recycle();
                    bmp = null;
                }
            }
        }*/
    }

    public static void clear(int size, int count, List<View> list) {
        for (int i = 0; i < count; i++) {
            list.remove(list.size() + i);
        }
    }


    /**
     * 设置textView的padding
     * @param textView
     * @param l paddingLeft
     * @param t paddingTop
     * @param r paddingRight
     * @param b paddingBottom
     */
    public static void setPadding(TextView textView, int l, int t, int r, int b) {
        if (textView == null) return;
        textView.setPadding(l, t, r, b);
    }

    /**
     * 设置TextView的样式（字体大小，高度，颜色，背景）
     * @param textView
     * @param size 字体大小
     * @param height 文本高度
     * @param color 字体颜色
     * @param drawable 文本背景
     */
    public static void setTextViewStyle(TextView textView, float size, int height, int color, int drawable) {
        if (textView == null) return;
        textView.setTextSize(size);
        textView.setHeight(height);
        textView.setBackgroundResource(drawable);
        textView.setTextColor(color);
        textView.setGravity(Gravity.CENTER);//文本居中
        textView.setSingleLine();//文本单行
    }
}
