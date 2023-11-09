package com.seachal.seachaltest.onActivityResult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seachal.seachaltest.R
import com.zhangyue.we.x2c.X2C.setContentView
import kotlinx.android.synthetic.main.activity_on_result.btn_activity_b
import kotlinx.android.synthetic.main.activity_on_result.tv_result


/**
 * TODO onActivityResult.
 *  1. 从ActivityA跳转到ActivityB，ActivityB中有一个按钮，点击按钮后，返回ActivityA，并且携带数据
 *
 */
class OnActivityResultUserFragmentActivity : AppCompatActivity(),
    ConfirmDialogFragment.ConfirmListener {
    lateinit var dialogFragment: ConfirmDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_result)
        btn_activity_b.setOnClickListener {
            btn_activity_b_click()
        }

    }

    fun btn_activity_b_click() {
//        弹出 ConfirmDialogFragment弹窗
        dialogFragment = ConfirmDialogFragment("确定要删除吗？")
        dialogFragment.listener = this
        dialogFragment.show(supportFragmentManager, "confirm")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                val result = data?.getStringExtra("result")
                if (result!= null) {
                    //  把所有信息展示出来，requestCode: Int, resultCode: Int, data: Intent
                    tv_result.text  = "requestCode: $requestCode, resultCode: $resultCode, result: $result"
//                    用 Log 把数据打印出来
                    Log.d("OnActivityResult", "First onActivityResult requestCode: $requestCode, resultCode: $resultCode, result: $result")
                }
            }
        }

//   因为 startActivityForResult 是OnActivityResultUserFragmentActivity 调用的， 所以 dialogFragment是不会触发
//        onActivityResult的， 这里手动调用一些。 转发到dialogFragment ， 其实换个函数名也是可以的。
        dialogFragment
            .onActivityResult(requestCode, resultCode, data);
    }





    override fun onConfirm() {
        val intent = Intent(this, SecondBActivity::class.java)
        startActivityForResult(intent, 200)
    }

    override fun onCancel() {

    }
}