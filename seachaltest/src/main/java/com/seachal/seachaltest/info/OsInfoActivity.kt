package com.seachal.seachaltest.info

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.activity_os_info.textView
import kotlinx.android.synthetic.main.activity_os_info.textView1


class OsInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_os_info)
//        textView1.setOnClickListener {
//           var version = getHarmonyVersion()
//            tex
//        }

        if (isHarmonyOs()) {
            var version = getHarmonyVersion()
            if (version.contains("2.")) {
                // 是鸿蒙系统2.x版本
                textView1.text = "是鸿蒙系统2.x版本${version}"
            } else {
                textView1.text = "不是鸿蒙系统2.x版本${version}"
            }
        } else {
            textView1.text = "不是鸿蒙系统"
        }

    }


////     这个其实是获取的手机型号
//    fun  gpt( textView1:TextView){
//        val systemVersion = Build.DISPLAY
//// 或者
//// String systemVersion = android.os.Build.VERSION.INCREMENTAL;
//        if (systemVersion.contains("HarmonyOS") || systemVersion.contains("harmonyos") || systemVersion.contains(
//                "HARMONYOS"
//            )
//        ) {
//            // 是鸿蒙系统
//            if (systemVersion.contains("2.")) {
//                // 是鸿蒙系统2.x版本
//                textView1.text = "是鸿蒙系统2.x版本${systemVersion}"
//            }else{
//                textView1.text = "不是鸿蒙系统2.x版本${systemVersion}"
//            }
//        }else{
//            textView1.text = "不是鸿蒙系统${systemVersion}"
//        }
//    }


    /**
     * 是否为鸿蒙系统
     *
     * @return true为鸿蒙系统
     */
    fun isHarmonyOs(): Boolean {
        return try {
            val buildExClass = Class.forName("com.huawei.system.BuildEx")
            val osBrand = buildExClass.getMethod("getOsBrand").invoke(buildExClass)
            "Harmony".equals(osBrand.toString(), ignoreCase = true)
        } catch (x: Throwable) {
            false
        }
    }

    /**
     * 获取鸿蒙系统版本号
     *
     * @return 版本号
     */
    fun getHarmonyVersion(): String {
        return getProp("hw_sc.build.platform.version", "")
    }

    private fun getProp(property: String, defaultValue: String): String {
        try {
            val spClz = Class.forName("android.os.SystemProperties")
            val method = spClz.getDeclaredMethod("get", String::class.java)
            val value = method.invoke(spClz, property) as String
            return if (TextUtils.isEmpty(value)) {
                defaultValue
            } else value
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return defaultValue
    }
}