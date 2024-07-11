package com.seachal.seachaltest.layoutparams

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import org.w3c.dom.Text

class LayoutParamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_params)

        val textMarginTop = findViewById<TextView>(R.id.tv_margin_top);
// 获取当前的布局参数，假设是LinearLayout.LayoutParams
        val layoutParams1 = textMarginTop.layoutParams as LinearLayout.LayoutParams

// 设置marginTop，这里的数值是以像素为单位
        layoutParams1.topMargin = resources.getDimension(R.dimen.dp_px_240).toInt()// 例如设置上边距为20像素

// 将修改后的参数应用回TextView
        textMarginTop.layoutParams = layoutParams1


//       
        val linearLayout = findViewById<LinearLayout>(R.id.ll_container)
        val textView = TextView(this@LayoutParamsActivity)

// 设置文本内容
        textView.text = "Hello World"

// 设置布局参数为 WRAP_CONTENT 或其他宽高值

// 设置视图的 Gravity 属性
        textView.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
        textView.setBackgroundColor(getResources().getColor(R.color.colorAccent))
        //        textView 设置 padding
        textView.setPadding(10, 10, 10, 10)
        linearLayout.addView(textView)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        //         整体居左
        layoutParams.gravity = Gravity.START
        //        layoutParams.addRule(LinearLayout.); // LinearLayout 没有addRule方法
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); // LinearLayout 没有addRule方法
        textView.setLayoutParams(layoutParams)


//        RelativeLayout
        val relativeLayout = RelativeLayout(this@LayoutParamsActivity)
        val layoutParams2 = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        val imageView = ImageView(this@LayoutParamsActivity)
        imageView.setImageResource(R.drawable.android_logo_m)
        layoutParams2.addRule(RelativeLayout.CENTER_HORIZONTAL) // 水平居中
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_TOP) // 底部对齐
        imageView.setLayoutParams(layoutParams2)

// 将 ImageView 添加到 RelativeLayout 中
        relativeLayout.addView(imageView)
        linearLayout.addView(relativeLayout)
    }
}