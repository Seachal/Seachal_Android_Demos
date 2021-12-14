package com.seachal.seachaltest.permission

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R


/**
 * 其他 App
 */
class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Permission onCreate","savedInstanceState:"+savedInstanceState);
        setContentView(R.layout.activity_permission)
        val btn_open = findViewById<Button>(R.id.btn_open)


           btn_open.setOnClickListener({
             PhoneUtils.getPermissionSetting(getPackageName())?.let {
//                 if (ForceUtils.activityMatch(it)) {
                     try {
                         startActivity(it)
                     } catch (e: Exception) {
//                         reportCrash("跳转权限或者默认设置页失败", e)
                     }
//                 }
             }
         });
    }
}