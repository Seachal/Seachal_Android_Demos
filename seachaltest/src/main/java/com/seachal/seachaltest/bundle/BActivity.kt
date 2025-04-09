package com.seachal.seachaltest.bundle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import com.zhangyue.we.x2c.X2C.setContentView

/**
 **
 * *
 * Project_Name:Seachal_Android_Demos
 * @Description: TODO
 * @author zhangxc
 * @date 2024/7/18 11:05
 * @Version：1.0
 */
class BActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        // 从 Intent 中获取数据
        val data = intent.getStringExtra("data_key")

        // 创建 BFragment 实例
        val fragment = BFragment()
        val args = Bundle()
        args.putString("fragment_data_key", data)
        fragment.arguments = args

        // 将 BFragment 添加到 Activity 的布局中
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}