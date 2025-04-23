package com.seachal.seachaltest.customtab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * 自定义Tab指示器接口
 */
public interface TabIndicator {
    /**
     * 初始化指示器
     * @param context 上下文
     * @return 指示器视图
     */
    View initIndicator(Context context);
    
    /**
     * 更新指示器位置
     * @param left 左边距
     * @param width 宽度
     * @param animate 是否使用动画
     */
    void updatePosition(float left, float width, boolean animate);
    
    /**
     * 设置指示器图像
     * @param drawable 图像资源
     */
    void setIndicatorDrawable(Drawable drawable);
    
    /**
     * 设置指示器尺寸
     * @param width 宽度
     * @param height 高度
     */
    void setIndicatorSize(int width, int height);
} 