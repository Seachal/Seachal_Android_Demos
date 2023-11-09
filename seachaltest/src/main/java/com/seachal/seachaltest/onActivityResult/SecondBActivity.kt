package com.seachal.seachaltest.onActivityResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.activity_second_bactivity.btn_back
import kotlinx.android.synthetic.main.activity_second_bactivity.btn_back_result

class SecondBActivity : AppCompatActivity() {
//    requestCode
     var requestCode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_bactivity)

//    OnActivityResultActivity 的 requestCode 是 100 ，我怎样知道 OnResult 的 request 是 100 呢？


//        获取 OnActivityResultActivity 的 requestCode
         requestCode = intent.getIntExtra("requestCode", 0)

        btn_back.setOnClickListener {
            btn_back_click()
        }

        btn_back_result.setOnClickListener {
            btn_back_result_click()
        }

    }

    fun btn_back_click(){
  /*     if (requestCode == 100){
           val intent = intent
           intent.putExtra("result", "Hello FirstActivity")
           setResult(RESULT_OK, intent)
           finish()
       }*/
        val intent = intent
        intent.putExtra("result", "Hello FirstActivity")
        setResult(RESULT_OK, intent)
        finish()
    }

    fun btn_back_result_click(){
//        if (requestCode == 200){
//            val intent = intent
//            intent.putExtra("result", "Hello FirstActivity")
//            setResult(RESULT_OK, intent)
//            finish()
//        }

        val intent = intent
        intent.putExtra("result", "Hello FirstActivity")
        setResult(RESULT_OK, intent)
        finish()
    }
}