package com.seachal.seachaltest.customtab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        
        // 设置Tab选中监听器
        customTabLayout.setOnTabSelectedListener(object : CustomTabLayout.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                // 可以在这里处理Tab选中事件
            }
        })
    }
}