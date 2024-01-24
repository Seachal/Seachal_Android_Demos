package com.seachal.seachaltest.onActivityResult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seachal.seachaltest.R
import com.seachal.seachaltest.onActivityResult.ui.main.OnActivityResultUserFragmentContainerFragment
import com.seachal.seachaltest.onActivityResult.ui.main.OnActivityResultUserFragmentContainerFragment2
//  onActivityResult:MyFragment 发起 StartActivityForResult, BaseFragment1会收到onActivityResult 回调吗？
class OnActivityResultUserFragmentContainerActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_activity_result)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    OnActivityResultUserFragmentContainerFragment2.newInstance()
                )
                .commitNow()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("onActivityResult", "ContainerActivity: requestCode = $requestCode, resultCode = $resultCode, data = $data")
        super.onActivityResult(requestCode, resultCode, data)
    }
}