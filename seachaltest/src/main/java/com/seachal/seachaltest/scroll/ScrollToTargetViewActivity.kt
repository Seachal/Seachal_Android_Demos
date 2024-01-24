package com.seachal.seachaltest.scroll

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.seachal.seachaltest.Activity.BackgroundTransparentActivity
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.activity_scroll_to_target_view.button_first
import kotlinx.android.synthetic.main.activity_scroll_to_target_view.button_second
import kotlinx.android.synthetic.main.activity_scroll_to_target_view.recycler_view_scroll_1
import kotlinx.android.synthetic.main.activity_scroll_to_target_view.scrollView1
import kotlinx.android.synthetic.main.activity_scroll_to_target_view.textview_second
import kotlinx.android.synthetic.main.activity_with_appbarlayout2.scrollView

class ScrollToTargetViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_to_target_view)

        button_first.setOnClickListener {
            scrollToView1(scrollView1, textview_second)
        }

        val list: MutableList<BackgroundTransparentActivity.StyleData> = ArrayList()
        for (i in 0..500 step 10) {
            val data = BackgroundTransparentActivity.StyleData()
            data.item = "不透明度$i%"
            data.value = i
            list.add(data)
        }
        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view_scroll_1.layoutManager = manager
        val adapter = BackgroundTransparentActivity.StyleAdapter(this, manager, list)
        recycler_view_scroll_1.adapter = adapter


        button_second.setOnClickListener {
            var lectureIndex = 8
            (recycler_view_scroll_1.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                lectureIndex,
                0
            )
        }

        recycler_view_scroll_1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                  LogUtils.e("onScrolled")
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

//                 从开始滑动到停止newState的值分别是 1，,2，,0
                LogUtils.e("onScrollStateChanged:newState:$newState")
            }
        })

    }





    fun scrollToView1(nestedScrollView: NestedScrollView, targetView: View) {
// 首先确保目标View已经测量和布局完成
        nestedScrollView.post { // 计算目标View在屏幕内的顶部坐标
            val offset = targetView.top

            // 滚动到该坐标
//                nestedScrollView.scrollTo(0, offset);

            // 或者使用 smoothScrollTo 进行平滑滚动
            nestedScrollView.smoothScrollTo(0, offset)
        }
    }



}