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
open class BaseJumpBaseReferenceActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_jump_base)
        tv_one.text = "我是基类"
    }

    override fun onResume() {
        super.onResume()
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