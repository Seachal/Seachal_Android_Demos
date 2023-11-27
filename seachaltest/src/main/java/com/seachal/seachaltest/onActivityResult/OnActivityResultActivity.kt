package com.seachal.seachaltest.onActivityResult

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils.startActivityForResult
import com.seachal.seachaltest.R
import com.zhangyue.we.x2c.X2C.setContentView
import kotlinx.android.synthetic.main.activity_on_result.btn_activity_b
import kotlinx.android.synthetic.main.activity_on_result.tv_result


/**
 * TODO onActivityResult.
 *  1. 从ActivityA跳转到ActivityB，ActivityB中有一个按钮，点击按钮后，返回ActivityA，并且携带数据
 *
 */
class OnActivityResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_result)

        btn_activity_b.setOnClickListener {
            btn_activity_b_click()
        }

    }

    fun btn_activity_b_click(){
        val intent = Intent(this, SecondBActivity::class.java)
        startActivityForResult(intent, 100)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                val result = data?.getStringExtra("result")
                if (result!= null) {
                    // 处理返回数据
                    // 显示返回数据
                    // 关闭Activity

//                     把所有信息展示出来，requestCode: Int, resultCode: Int, data: Intent
                    tv_result.text  = "requestCode: $requestCode, resultCode: $resultCode, result: $result"
                }
            }
        }
    }


}