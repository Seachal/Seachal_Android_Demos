package com.seachal.seachaltest.Activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.seachal.seachaltest.R
import com.seachal.seachaltest.bean.CourseJumpBean
import com.seachal.seachaltest.bean.CourseJumpBeanString
import com.seachal.seachaltest.utils.GsonUtil
import org.json.JSONObject

/**
 * TODO
 * "{"course":"978593720889311232","chapter": "978596699155529728"}"
 *
 */
class StringToUriActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_to_uri)
        val textView:TextView  = findViewById<TextView>(R.id.textView)
//        textView.text = "hello"
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
                val  course =  jsonObject.getLong("course")// getString
                val  chapter =  jsonObject.getLong("chapter") // getString  //  当值值对时， "978596699155529728" 可以解析正常， "" 用 getLong 接收会报异常
                val  text =  "targetType=${targetType},course=${course},chapter=${chapter}"
                textView.text = text


//                测试一下会不会报异常
                val uri1:Uri? = Uri.parse(" ")// uri1:" "
                val uri2:Uri? = Uri.parse("") // uri2:""
                val  string = uri2?.getQueryParameter("targetType") // string:null uri2:""

                val uri4:Uri? = Uri.parse("zhangsan") // uri4:"zhangsan"
                val  string2 = uri4?.getQueryParameter("targetType")// string2:null uri4:"zhangsan"
                val uri3:Uri? = Uri.parse(null) // 会报异常 ，    private StringUri(String uriString) {if (uriString == null) {throw new NullPointerException("uriString"); }

            }catch (e:Exception){
                Log.e("StringToUriActivity",e.printStackTrace().toString())
                textView.text = e.message
            }

        }

        val textView2:TextView  = findViewById<TextView>(R.id.textView2)
//        textView2.text = "hello"
        textView2.setOnClickListener {
            try {
                val  str = "xkw://train/native/?targetType=172&params={\"course\":\"978593720889311232\",\"chapter\": \"\"}"
//               String 转 uri。

                val uri:Uri? = Uri.parse(str)
                val   targetType =  uri?.getQueryParameter("targetType")
                //  是一个json  字符串
                val   params =   uri?.getQueryParameter("params")
                //    params 转成 json对象
                val jsonObject: JSONObject = JSONObject(params)
                val  course =  jsonObject.getLong("course")
                val  chapter =  jsonObject.getLong("chapter")
                val  text =  "targetType=${targetType},course=${course},chapter=${chapter}"
                textView2.text = text




            }catch (e:Exception){
                Log.e("StringToUriActivity",e.printStackTrace().toString())
                textView2.text = e.message
            }

        }



        val textviewGson:TextView  = findViewById<TextView>(R.id.textView_gson)
        textviewGson.setOnClickListener {
            try {
// 字段是可空类型：  data class CourseJumpBean(val courseId: Long?, val chapterId: Long?) . 注意 Long 后面的？号。  json key "course" 和  bean 的courseId 没有完全对应上 得到的是 null, 容错很好，   chapter 和 chapterId 也没有完全对应上，并且没有值，得到的是 null,
// 字段是非空类型：   data class CourseJumpBean(val courseId: Long, val chapterId: Long)    json key "course" 和  bean 的courseId 没有完全对应上 得到的是 null, 容错很好，   chapter 和 chapterId 也没有完全对应上，并且没有值，得到的是 null,
//              val  str = "xkw://train/native/?targetType=172&params={\"course\":\"978593720889311232\",\"chapter\": \"\"}"

//  修改params={"
                val  str = "xkw://train/native/?targetType=172&params={\"courseId\":\"978593720889311232\",\"chapterId\": \"\"}"

                val uri:Uri? = Uri.parse(str)
                val   targetType =  uri?.getQueryParameter("targetType")
                //  是一个json  字符串
                val   jsonString =   uri?.getQueryParameter("params")
                //    params 转成 json对象
              /*  val gson = Gson()
                val data = gson.fromJson(jsonString, CourseJumpBean::class.java)
                */
                val data =   GsonUtil.toBean<CourseJumpBean>(jsonString?:"",CourseJumpBean::class.java)

                val  text =  "targetType=${targetType},course=${data?.courseId},chapter=${data?.chapterId}"

                textviewGson.text = text
            }catch (e:Exception){
                Log.e("StringToUriActivity",e.printStackTrace().toString())
                textviewGson.text = e.message
            }

        }


        val textviewGsonString:TextView  = findViewById<TextView>(R.id.textView_gson_String)
        textviewGsonString.setOnClickListener {
            try {
// 字段是可空类型：  data class CourseJumpBean(val courseId: Long?, val chapterId: Long?) . 注意 Long 后面的？号。  json key "course" 和  bean 的courseId 没有完全对应上 得到的是 null, 容错很好，   chapter 和 chapterId 也没有完全对应上，并且没有值，得到的是 null,
// 字段是非空类型：   data class CourseJumpBean(val courseId: Long, val chapterId: Long)    json key "course" 和  bean 的courseId 没有完全对应上 得到的是 null, 容错很好，   chapter 和 chapterId 也没有完全对应上，并且没有值，得到的是 null,
//              val  str = "xkw://train/native/?targetType=172&params={\"course\":\"978593720889311232\",\"chapter\": \"\"}"

//  修改params={"
                val  str = "xkw://train/native/?targetType=172&params={\"courseId\":\"978593720889311232\",\"chapterId\": \"\"}"

                val uri:Uri? = Uri.parse(str)
                val   targetType =  uri?.getQueryParameter("targetType")
                //  是一个json  字符串
                val   jsonString =   uri?.getQueryParameter("params")
                //    params 转成 json对象
                /*  val gson = Gson()
                  val data = gson.fromJson(jsonString, CourseJumpBean::class.java)
                  */
                val data =   GsonUtil.toBean<CourseJumpBeanString>(jsonString?:"",CourseJumpBeanString::class.java)


                var chapterId:Long? = null
                try {
                    chapterId = data?.chapterId?.toLong()
                }catch (e:Exception){
                    chapterId = null
                }

                var courseId:Long? = null
                try {
                    courseId =   data?.courseId?.toLong()
                }catch (e:Exception){
                    courseId = null
                }

                val  text =  "targetType=${targetType},course=${courseId},chapter=${chapterId}"

                textviewGsonString.text = text
            }catch (e:Exception){
                Log.e("StringToUriActivity",e.printStackTrace().toString())
                textviewGsonString.text = e.message
            }

        }



    }


}