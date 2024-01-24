package com.seachal.seachaltest.onActivityResult.ui.main

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_on_activity_result.tv_content

/**
 **
 * *
 * Project_Name:Seachal_Android_Demos
 * @Description: TODO
 * @author zhangxc
 * @date 2023/12/20 15:59
 * @Version：1.0
 */
open class BaseFragment1 : Fragment() {


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(
            "OnActivityResult",
            "BaseFragment1 ContainerFragment onActivityResult requestCode: $requestCode, resultCode: $resultCode, data: $data"
        )
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            val result = data?.getStringExtra("result")
            if (result != null) {

                Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
                //  把所有信息展示出来，requestCode: Int, resultCode: Int, data: Intent
                tv_content.text =
                    "BaseFragment1 Dialog onActivityResult  requestCode: $requestCode, resultCode: $resultCode, data: $data"

                Log.d(
                    "OnActivityResult",
                    "BaseFragment1 ContainerFragment requestCode: $requestCode, resultCode: $resultCode, result: $result"
                )

            }
        }
    }
}