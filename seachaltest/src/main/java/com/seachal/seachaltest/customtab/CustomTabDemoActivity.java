package com.seachal.seachaltest.customtab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.seachal.seachaltest.R;
import com.seachal.seachaltest.ScrollListFragment.Fragment1;
import com.seachal.seachaltest.ScrollListFragment.Fragment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义TabLayout演示Activity
 * 展示如何使用CustomTabLayout并设置图片指示器
 */
public class CustomTabDemoActivity extends AppCompatActivity {

    private CustomTabLayout customTabLayout;
    private ViewPager2 viewPager;
    
    // 测试数据
    private List<String> tabTitles = Arrays.asList("月考", "期中", "期末", "高考模考", "高考真题");
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_custom_tab_demo);
        
        // 初始化fragment列表
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment1());
        
        // 初始化视图
        customTabLayout = findViewById(R.id.o_custom_tab_layout);
        viewPager = findViewById(R.id.o_view_pager);
        
        // 设置ViewPager2适配器
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        
        // 设置Tab数据
        customTabLayout.setTabItems(tabTitles);
        
        // 关联ViewPager2
        customTabLayout.setupWithViewPager2(viewPager);
        
        // ----- 设置图片指示器的几种方式 -----
        
        // 方法1：在XML中已经设置app:o_indicatorDrawable属性
        
        // 方法2：通过代码设置指示器图片（推荐方式）
        // 直接使用设计稿中的图片资源
        Drawable indicatorDrawable = ContextCompat.getDrawable(this, R.drawable.o_shape_tab_underline_png2);
        customTabLayout.setIndicatorDrawable(indicatorDrawable);
        
        // 方法3：设置指示器图片尺寸，确保不会被拉伸变形
        customTabLayout.setIndicatorSize(
                getResources().getDimensionPixelSize(R.dimen.dp_px_40),  // 设计稿上的宽度
                getResources().getDimensionPixelSize(R.dimen.dp_px_8)    // 设计稿上的高度
        );
        
        // 方法4：如果有特殊需求，可以使用自定义指示器工厂
        // customTabLayout.setIndicatorFactory(new CustomIndicatorFactory());
        
        // 设置Tab选中监听器
        customTabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                // 可以在这里处理Tab选中事件
            }
        });
    }
    
    /**
     * 自定义指示器工厂示例
     * 只在需要更复杂的自定义指示器时才需要使用
     */
    class CustomIndicatorFactory implements TabIndicatorFactory {
        @Override
        public TabIndicator createTabIndicator() {
            return new CustomImageIndicator();
        }
    }
    
    /**
     * 自定义图片指示器示例
     * 可以实现更复杂的指示器效果
     */
    class CustomImageIndicator implements TabIndicator {
        private FrameLayout rootView;
        private ImageView imageView;
        
        @Override
        public View initIndicator(Context context) {
            // 创建一个新的布局作为指示器
            rootView = new FrameLayout(context);
            
            // 从XML加载自定义指示器布局
            View indicatorView = LayoutInflater.from(context).inflate(R.layout.o_layout_tab_indicator, null);
            imageView = indicatorView.findViewById(R.id.o_iv_tab_indicator);
            
            // 设置默认图片
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.o_shape_tab_underline_png2));
            imageView.setVisibility(View.VISIBLE);
            
            // 添加到根视图
            rootView.addView(indicatorView);
            
            return rootView;
        }
        
        @Override
        public void updatePosition(float left, float width, boolean animate) {
            // 更新指示器位置
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) rootView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = (int) width;
                layoutParams.leftMargin = (int) left;
                rootView.setLayoutParams(layoutParams);
            }
        }
        
        @Override
        public void setIndicatorDrawable(Drawable drawable) {
            if (imageView != null && drawable != null) {
                imageView.setImageDrawable(drawable);
                imageView.setVisibility(View.VISIBLE);
            }
        }
        
        @Override
        public void setIndicatorSize(int width, int height) {
            if (imageView != null) {
                android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = height;
                imageView.setLayoutParams(layoutParams);
            }
        }
    }
} 