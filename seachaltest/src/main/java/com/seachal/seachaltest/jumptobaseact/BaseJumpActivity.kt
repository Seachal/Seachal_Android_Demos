package com.seachal.seachaltest.jumptobaseact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.layout_base_reference.tv_one

/**
 **
 *   如果是这种情况的话， 我不用一个Activity 基类，写一个工具方法也可以实现类似的效果。
 *   所以，课程、会员继承BaseJumpActivity， 拆成单独的Activity，没有必要。
 * *
 * Project_Name:Seachal_Android_Demos
 * @Description: TODO
 * @author zhangxc
 * @date 2023/12/25 13:47
 * @Version：1.0
 */
open class BaseJumpActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//         在这里是否可以？ 好像是不可以的， 那么在onCreate 之后的生命周期，例如 onResume 是否可以？
//      基类中不  setContentView(R.layout.xx);的情况， 可以调用子类控件吗？
//    TODO   答案是不可以： Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.TextView.setText
//        tv_one.text = "我是基类"
    }

    override fun onResume() {
        super.onResume()
//  但是延迟到onResume 之中，就能获取到子类的控件了。
    tv_one.text = "我是基类"
    }

    companion object {


        fun startMeForResult(
            activity: Activity,
            isOne:Boolean,
        ) {

            // 判断是会员还是课程
            if (isOne) {
                activity.startActivity(
                    Intent(
                        activity,
                        JumpOneActivity::class.java
                    )
                )
            } else {
                activity.startActivity(
                    Intent(
                        activity,
                        JumpTwoActivity::class.java
                    )
                )
            }
        }


    }
}