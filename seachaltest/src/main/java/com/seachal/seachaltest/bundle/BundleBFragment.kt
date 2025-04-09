package com.seachal.seachaltest.bundle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.seachal.seachaltest.R

/**
 **
 * *
 * Project_Name:Seachal_Android_Demos
 * @Description: TODO
 * @author zhangxc
 * @date 2024/7/18 11:08
 * @Version：1.0
 */
class BundleBFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 获取传递给 Fragment 的数据
        val data = arguments?.getString("data_key")
        if (data != null) {
            view.findViewById<TextView>(R.id.fragment_text_view).text = data
        }
    }
}