package com.seachal.seachaltest.customtab

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.seachal.seachaltest.R
import com.seachal.seachaltest.ScrollListFragment.Fragment1
import com.seachal.seachaltest.ScrollListFragment.Fragment2
import com.seachal.seachaltest.ScrollListFragment.Fragment3

class CustomTabDemoActivity : AppCompatActivity() {

    private lateinit var customTabLayout: CustomTabLayout
    private lateinit var viewPager: ViewPager2
    
    // 测试数据
    private val tabTitles = listOf("月考", "期中", "期末", "高考模考", "高考真题")
    private val fragments = listOf(
        Fragment1(),
        Fragment2(),
        Fragment3(),
        Fragment1(),
        Fragment2()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.o_activity_custom_tab_demo)
        
        // 初始化视图
        customTabLayout = findViewById(R.id.o_custom_tab_layout)
        viewPager = findViewById(R.id.o_view_pager)
        
        // 设置ViewPager2适配器
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragments.size
            
            override fun createFragment(position: Int): Fragment = fragments[position]
        }
        
        // 设置Tab数据
        customTabLayout.setTabItems(tabTitles)
        
        // 关联ViewPager2
        customTabLayout.setupWithViewPager2(viewPager)
        
        // 方法1：使用XML通过属性设置指示器图片
        // 这个是在o_activity_custom_tab_demo.xml中通过app:o_indicatorDrawable属性设置的
        
        // 方法2：通过代码设置指示器图片
        customTabLayout.setIndicatorDrawable(ContextCompat.getDrawable(this, R.drawable.o_shape_tab_underline_png2))
        
        // 方法3：通过代码设置指示器图片尺寸
        customTabLayout.setIndicatorSize(
            resources.getDimensionPixelSize(R.dimen.dp_px_40),
            resources.getDimensionPixelSize(R.dimen.dp_px_8)
        )
        
        // 方法4：使用自定义指示器工厂，完全自定义指示器
        // customTabLayout.setIndicatorFactory(CustomIndicatorFactory())
        
        // 设置Tab选中监听器
        customTabLayout.setOnTabSelectedListener(object : CustomTabLayout.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                // 可以在这里处理Tab选中事件
            }
        })
    }
    
    /**
     * 自定义指示器工厂示例
     * 可以替换成任意自定义的指示器实现
     */
    inner class CustomIndicatorFactory : TabIndicatorFactory {
        override fun createTabIndicator(): TabIndicator {
            return CustomImageIndicator()
        }
    }
    
    /**
     * 自定义图片指示器示例
     * 使用自定义布局作为指示器
     */
    inner class CustomImageIndicator : TabIndicator {
        private lateinit var rootView: LinearLayout
        private lateinit var imageView: ImageView
        
        override fun initIndicator(context: Context): View {
            // 创建一个新的布局作为指示器
            rootView = LinearLayout(context)
            rootView.orientation = LinearLayout.VERTICAL
            
            // 从XML加载自定义指示器布局，或者直接创建视图
            val indicatorView = LayoutInflater.from(context).inflate(R.layout.o_layout_tab_indicator, null, false)
            imageView = indicatorView.findViewById(R.id.o_iv_tab_indicator)
            
            // 设置默认图片
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.o_shape_tab_underline_png2))
            imageView.visibility = View.VISIBLE
            
            // 添加到根视图
            rootView.addView(indicatorView)
            
            return rootView
        }
        
        override fun updatePosition(left: Float, width: Float, animate: Boolean) {
            // 更新指示器位置
            val layoutParams = rootView.layoutParams as? FrameLayout.LayoutParams
            if (layoutParams != null) {
                layoutParams.width = width.toInt()
                layoutParams.leftMargin = left.toInt()
                rootView.layoutParams = layoutParams
            }
        }
        
        override fun setIndicatorDrawable(drawable: Drawable?) {
            if (this::imageView.isInitialized && drawable != null) {
                imageView.setImageDrawable(drawable)
                imageView.visibility = View.VISIBLE
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
}