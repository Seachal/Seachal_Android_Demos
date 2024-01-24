package com.seachal.seachaltest.anr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seachal.seachaltest.R

//、 [Android进阶知识：ANR的定位与解决 - 掘金](https://juejin.cn/post/6844904069731975176)
class ANRTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anrtest)


        try {
            Log.d("ANR","开始sleep");
            Thread.sleep(60*1000);
            Log.d("ANR","sleep完成");

        } catch ( e:InterruptedException) {
            e.printStackTrace();
        }

    }
}