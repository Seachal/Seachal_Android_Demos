package com.seachal.seachaltest.ScrollListFragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.seachal.seachaltest.R
import com.seachal.seachaltest.utils.getViewVisiblePercent


class ScrollListFragmentActivity : AppCompatActivity() {

   var  textView: TextView ?= null
    var  scrollView: ScrollView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scrollView = findViewById<ScrollView>(R.id.scrollView1)
        textView = findViewById<TextView>(R.id.textView)
        textView?.setOnClickListener {
            Log.e("ScrollList", "onClick: textView")
        }
        // 动态添加 Fragment
        addFragments()

//          scrollView  scroll 监听
        scrollView?.viewTreeObserver?.addOnScrollChangedListener {
            val percent = getViewVisiblePercent(textView)
            Log.e("ScrollList", "onCreate: percent = $percent")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView?.setOnScrollChangeListener(object : View.OnScrollChangeListener{
                override fun onScrollChange(
                    v: View?,
                    scrollX: Int,
                    scrollY: Int,
                    oldScrollX: Int,
                    oldScrollY: Int
                ) {
                    val percent = getViewVisiblePercent(textView)
                 Log.e("ScrollList", "onCreate: percent = $percent")
                }

            })
        }
    }

    private fun addFragments() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // 添加第一个 Fragment
        val fragment1: Fragment = Fragment1()
        fragmentTransaction.add(R.id.fragment_container, fragment1, "fragment1")

        // 添加第二个 Fragment
        val fragment2: Fragment = Fragment1()
        fragmentTransaction.add(R.id.fragment_container, fragment2, "fragment2")

        val fragment3: Fragment = Fragment1()
        fragmentTransaction.add(R.id.fragment_container, fragment3, "fragment3")

        val fragment4: Fragment = Fragment1()
        fragmentTransaction.add(R.id.fragment_container, fragment4, "fragmen4")

        // 添加更多的 Fragment...
        fragmentTransaction.commit()
    }
}