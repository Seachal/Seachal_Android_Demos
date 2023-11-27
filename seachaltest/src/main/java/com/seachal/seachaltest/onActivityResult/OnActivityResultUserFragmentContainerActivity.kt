package com.seachal.seachaltest.onActivityResult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seachal.seachaltest.R
import com.seachal.seachaltest.onActivityResult.ui.main.OnActivityResultUserFragmentContainerFragment

class OnActivityResultUserFragmentContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_activity_result)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    OnActivityResultUserFragmentContainerFragment.newInstance()
                )
                .commitNow()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("onActivityResult", "OnActivityResultUserFragmentContainerActivity: requestCode = $requestCode, resultCode = $resultCode, data = $data")
        super.onActivityResult(requestCode, resultCode, data)
    }
}