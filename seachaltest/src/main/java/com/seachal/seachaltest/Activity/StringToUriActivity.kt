package com.seachal.seachaltest.Activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import org.json.JSONObject


class StringToUriActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_to_uri)
        val textView:TextView  = findViewById<TextView>(R.id.textView)
        textView.text = "hello"
        textView.setOnClickListener {
            try {
                val  str = "xkw://train/native/?targetType=172&params={\"course\":\"978593720889311232\",\"chapter\": \"978596699155529728\"}"
//               String 转 uri。

                val uri:Uri? = Uri.parse(str)
                val   targetType =  uri?.getQueryParameter("targetType")
                //  是一个json  字符串
                val   params =   uri?.getQueryParameter("params")
                //    params 转成 json对象
                val jsonObject: JSONObject = JSONObject(params)
                val  course =  jsonObject.getString("course")
                val  chapter =  jsonObject.getString("chapter")
                val  text =  "targetType=${targetType},course=${course},chapter=${chapter}"
                textView.text = text


//                测试一下会不会报异常
                val uri1:Uri? = Uri.parse(" ")
                val uri2:Uri? = Uri.parse("")
                val  string = uri2?.getQueryParameter("targetType")
                val uri4:Uri? = Uri.parse("zhangsan") // 会报异常
                val  string2 = uri4?.getQueryParameter("targetType")
                val uri3:Uri? = Uri.parse(null) // 会报异常

            }catch (e:Exception){
                Log.e("StringToUriActivity",e.printStackTrace().toString())
                textView.text = e.message
            }

        }

    }
}