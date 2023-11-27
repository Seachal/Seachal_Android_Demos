package com.seachal.seachaltest.onActivityResult.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import com.seachal.seachaltest.onActivityResult.SecondBActivity
import kotlinx.android.synthetic.main.fragment_main.*

/*
 * 在托管的 Activity 不重写 onActivityResult时可以收到 onActivityResult回调。
 *       OnActivityResultUserFragmentContainerFragment onActivityResult requestCode: 200, resultCode: -1, data: Intent { cmp=com.seachal.seachaltest/.onActivityResult.SecondBActivity (has extras) }
 *      OnActivityResultUserFragmentContainerActivity: requestCode = 65736, resultCode = -1, data = Intent { cmp=com.seachal.seachaltest/.onActivityResult.SecondBActivity (has extras) }
 *在托管的 Activity 重写 onActivityResult时
 *       OnActivityResultUserFragmentContainerFragment onActivityResult requestCode: 200, resultCode: -1, data: Intent { cmp=com.seachal.seachaltest/.onActivityResult.SecondBActivity (has extras) }
 *       OnActivityResultUserFragmentContainerActivity: requestCode = 65736, resultCode = -1, data = Intent { cmp=com.seachal.seachaltest/.onActivityResult.SecondBActivity (has extras) }
 */
class OnActivityResultUserFragmentContainerFragment : Fragment() {

    companion object {
        fun newInstance() = OnActivityResultUserFragmentContainerFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.run {
            tv_click2.setOnClickListener {
                val intent = Intent(activity, SecondBActivity::class.java)
                startActivityForResult(intent, 200)
            }
        }

    }





//    在 Fragment 中处理 onActivityResult

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("OnActivityResult", "OnActivityResultUserFragmentContainerFragment onActivityResult requestCode: $requestCode, resultCode: $resultCode, data: $data")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val result = data?.getStringExtra("result")
                if (result!= null) {

                    Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
                    //  把所有信息展示出来，requestCode: Int, resultCode: Int, data: Intent
                    tv_content.text = "Dialog onActivityResult  requestCode: $requestCode, resultCode: $resultCode, data: $data"

                    Log.d("OnActivityResult", "Second onActivityResult requestCode: $requestCode, resultCode: $resultCode, result: $result")

                }
            }
        }
    }

}