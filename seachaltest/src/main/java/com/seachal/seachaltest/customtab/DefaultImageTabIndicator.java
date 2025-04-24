package com.seachal.seachaltest.customtab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 默认图片指示器实现
 * 使用ImageView作为指示器，支持设置图片和大小
 * 完美还原设计稿上的图片指示器效果
 */
public class DefaultImageTabIndicator implements TabIndicator {
    
    private ImageView mIndicatorView;
    private int mIndicatorWidth = FrameLayout.LayoutParams.WRAP_CONTENT;
    private int mIndicatorHeight = FrameLayout.LayoutParams.WRAP_CONTENT;
    private float mCurrentLeft = 0;
    
    @Override
    public View initIndicator(Context context) {
        // 创建ImageView作为指示器
        mIndicatorView = new ImageView(context);
        // 保持图片原有比例，不拉伸变形
        mIndicatorView.setScaleType(ImageView.ScaleType.CENTER);
        
        // 设置布局参数
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                mIndicatorWidth, mIndicatorHeight);
        mIndicatorView.setLayoutParams(params);
        
        return mIndicatorView;
    }
    
    @Override
    public void updatePosition(float left, float width, boolean animate) {
        if (mIndicatorView == null) {
            return;
        }
        
        // 获取指示器当前布局参数
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mIndicatorView.getLayoutParams();
        
        // 如果指示器宽度是自适应模式，则使用Tab宽度
        if (mIndicatorWidth == FrameLayout.LayoutParams.WRAP_CONTENT || mIndicatorWidth <= 0) {
            params.width = (int) width;
        }
        
        // 居中显示指示器
        float centerOffset = 0;
        if (mIndicatorWidth > 0 && mIndicatorWidth < width) {
            centerOffset = (width - mIndicatorWidth) / 2f;
        }
        
        // 计算新的左边距
        params.leftMargin = (int) (left + centerOffset);
        
        if (animate) {
            // 使用动画平滑过渡到新位置
            mIndicatorView.animate()
                    .translationX(left - mCurrentLeft)
                    .setDuration(300)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();
            mCurrentLeft = left;
        } else {
            // 直接设置到新位置
            mIndicatorView.setTranslationX(0);
            mIndicatorView.setLayoutParams(params);
            mCurrentLeft = left;
        }
    }
    
    @Override
    public void setIndicatorDrawable(Drawable drawable) {
        if (mIndicatorView != null && drawable != null) {
            mIndicatorView.setImageDrawable(drawable);
            
            // 确保图片可见
            mIndicatorView.setVisibility(View.VISIBLE);
            
            // 确保图片正确显示
            mIndicatorView.invalidate();
        }
    }
    
    @Override
    public void setIndicatorSize(int width, int height) {
        if (width <= 0 || height <= 0) {
            return;
        }
        
        mIndicatorWidth = width;
        mIndicatorHeight = height;
        
        if (mIndicatorView != null) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mIndicatorView.getLayoutParams();
            params.width = width;
            params.height = height;
            mIndicatorView.setLayoutParams(params);
            
            // 确保布局更新
            mIndicatorView.requestLayout();
        }
    }
} 