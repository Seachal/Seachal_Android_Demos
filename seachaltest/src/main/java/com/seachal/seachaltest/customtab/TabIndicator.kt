package com.seachal.seachaltest.customtab

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.seachal.seachaltest.R

/**
 * Tab指示器接口
 * 用于自定义Tab指示器的外观和行为
 */
interface TabIndicator {
    /**
     * 初始化指示器
     */
    fun initIndicator(context: Context): View
    
    /**
     * 更新指示器位置
     */
    fun updatePosition(left: Float, width: Float, animate: Boolean)
    
    /**
     * 设置指示器图片
     */
    fun setIndicatorDrawable(drawable: Drawable?)
    
    /**
     * 设置指示器尺寸
     */
    fun setIndicatorSize(width: Int, height: Int)
}

/**
 * 默认图片指示器实现
 */
class DefaultImageTabIndicator : TabIndicator {
    private lateinit var rootView: FrameLayout
    private lateinit var imageView: ImageView
    private var indicatorHeight: Int = 0
    
    override fun initIndicator(context: Context): View {
        rootView = FrameLayout(context)
        
        // 从XML布局加载指示器
        val indicatorView = LayoutInflater.from(context).inflate(R.layout.o_layout_tab_indicator, null, false)
        imageView = indicatorView.findViewById(R.id.o_iv_tab_indicator)
        
        // 获取默认指示器高度
        indicatorHeight = context.resources.getDimensionPixelSize(R.dimen.dp_px_4)
        
        // 确保指示器默认可见
        imageView.visibility = View.VISIBLE
        
        // 设置FrameLayout参数
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        rootView.addView(indicatorView, layoutParams)
        
        // 设置rootView的初始宽高，确保有足够空间显示指示器
        rootView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        
        return rootView
    }
    
    override fun updatePosition(left: Float, width: Float, animate: Boolean) {
        if (!this::rootView.isInitialized) return
        
        try {
            val layoutParams = rootView.layoutParams as? FrameLayout.LayoutParams
            if (layoutParams != null) {
                layoutParams.width = width.toInt()
                layoutParams.leftMargin = left.toInt()
                layoutParams.bottomMargin = 0
                rootView.layoutParams = layoutParams
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    override fun setIndicatorDrawable(drawable: Drawable?) {
        if (this::imageView.isInitialized) {
            if (drawable != null) {
                imageView.setImageDrawable(drawable)
                imageView.visibility = View.VISIBLE
            } else {
                // 即使没有自定义Drawable，也使用XML中设置的默认图片
                imageView.visibility = View.VISIBLE
            }
        }
    }
    
    override fun setIndicatorSize(width: Int, height: Int) {
        if (this::imageView.isInitialized) {
            val layoutParams = imageView.layoutParams
            layoutParams.width = width
            layoutParams.height = height
            imageView.layoutParams = layoutParams
        }
    }
}

/**
 * 自定义Tab指示器工厂接口
 * 用于创建自定义的Tab指示器
 */
interface TabIndicatorFactory {
    fun createTabIndicator(): TabIndicator
} 