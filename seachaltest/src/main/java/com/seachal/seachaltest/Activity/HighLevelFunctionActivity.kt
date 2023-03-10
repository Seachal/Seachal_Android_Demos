package com.seachal.seachaltest.Activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.activity_high_level_function.*
import org.w3c.dom.Text

/**
 * The type High level function activity.
 *
 * [07 | 高阶函数：为什么说函数是Kotlin的“一等公民”？](https://time.geekbang.org/column/article/476637?utm_source=time_web&utm_medium=menu&utm_term=timewebmenu)
 */
class HighLevelFunctionActivity : AppCompatActivity() {
    companion object{
        var num = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_level_function)
//        1 匿名内部类
        tv_highlevel1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                gotoPreview(v)
            }
        })
//        2_1  sc自己增加的， 函数 onClick没有被省略所以，这种还不算 lambda。
        tv_highlevel2_1.setOnClickListener( View.OnClickListener {
             fun onClick(v: View?) {
                gotoPreview(v)
                 gotoTest(v)
            }
        })
//        2  object 关键子可以背省略     更像 Lambda 表达式了
        tv_highlevel2.setOnClickListener( View.OnClickListener {
           v:View -> gotoPreview(v)
        })

        //   3  Kotlin Lambda 不需要  SAM constructor
        tv_highlevel3.setOnClickListener(  {
            v:View -> gotoPreview(v)
        })
//        4   类型推导
        tv_highlevel4.setOnClickListener(  {
            v -> gotoPreview(v)
        })
//      5  lambda 是函数的最后一个参数，移动到括号外面
        tv_highlevel5.setOnClickListener( ) {
            v -> gotoPreview(v)
        }
//     6    函数只有一个 lambda 参数时， 函数的括号可以被省略。
        tv_highlevel6.setOnClickListener {
            v -> gotoPreview(v)
        }
//         7   lambda 表达式只有一个参数，  参数名用 it 代替
        tv_highlevel7.setOnClickListener {
            it -> gotoPreview(it)
        }
//      8  lambda  it 可以被省略
        tv_highlevel8.setOnClickListener {
            gotoPreview(it)
        }
    }
    fun  gotoPreview(v: View?){
          num  = num+1
        (v as TextView).text = num.toString()
    }
    fun  gotoTest(v: View?){
        (v as TextView).text = "2.1"
    }

}