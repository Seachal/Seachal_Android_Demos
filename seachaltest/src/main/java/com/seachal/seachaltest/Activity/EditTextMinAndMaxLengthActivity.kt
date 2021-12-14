package com.seachal.seachaltest.Activity

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import com.seachal.seachaltest.utils.DensityUtils
import com.seachal.seachaltest.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_edit_text_min_and_max_length.*

/**
 * EditText  最小字符数，最大字符数,addView
 */
class EditTextMinAndMaxLengthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text_min_and_max_length)

        val ll_root: LinearLayout = findViewById<LinearLayout>(R.id.ll_root);
        val et_min :EditText = findViewById(R.id.et_min)
        et_min.inputType =InputType.TYPE_CLASS_DATETIME
        et_min2.inputType = InputType.TYPE_DATETIME_VARIATION_DATE
        et_min3.inputType = InputType.TYPE_DATETIME_VARIATION_NORMAL



        val textView1 = LayoutInflater.from(this).inflate(R.layout.layout_life_pay_textview, ll_root, false) as TextView
        textView1.text = "toast 获取 view 内容"
        textView1.setOnClickListener {
            Toast.makeText(this@EditTextMinAndMaxLengthActivity,textView1.text, Toast.LENGTH_SHORT).show()
        }
        ll_root.addView(textView1);


//         测试 add 一个 textview
        val textView2 = LayoutInflater.from(this).inflate(R.layout.layout_life_pay_textview, ll_root, false) as TextView
        textView2.setOnClickListener { it ->
            Toast.makeText(this@EditTextMinAndMaxLengthActivity,"textView2", Toast.LENGTH_SHORT).show()
                    it.setTag("name")
        }
        Log.i("sss:","tag:"+textView2.getTag())
        ll_root.addView(textView2);

//        =====  add 一个 添加的 View ,  setPadding setTextViewStyle, 这些竟然都没生生效。
        val tv = TextView(this)
        ViewUtils.setPadding(tv, DensityUtils.dip2px(this, 10F), DensityUtils.dip2px(this, 6F),
                DensityUtils.dip2px(this, 10F), DensityUtils.dip2px(this, 6F))
        ViewUtils.setTextViewStyle(tv, 12F, DensityUtils.dip2px(this, 0F),
                this.resources.getColor(R.color.cardview_dark_background),
                R.drawable.shape_mall_rectangle_tag_platform)

        tv.text= "测试 new TextView"
        ll_root.addView(tv)

        val textView3 = LayoutInflater.from(this).inflate(R.layout.layout_life_pay_textview, ll_root, false) as TextView
        textView3.text ="text3"
        textView3.setTag("my name:text3")
        textView3.setOnClickListener { it ->
//          kotlin 中  textView3.getTag()
            Toast.makeText(this@EditTextMinAndMaxLengthActivity,textView3.getTag() as String, Toast.LENGTH_SHORT).show()

        }
        ll_root.addView(textView3);



    }
}