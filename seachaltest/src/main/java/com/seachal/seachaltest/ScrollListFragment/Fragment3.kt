package com.seachal.seachaltest.ScrollListFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.seachal.seachaltest.R

import kotlinx.android.synthetic.main.fragment1.textView


/**
 **
 * *
 * Project_Name:Seachal_Android_Demos
 * @Description: TODO 在 ScroolView 中滑动 Fragment， Fragment 划出不追执行生命周期。
 * @author zhangxc
 * @date 2023/11/27 17:14
 * @Version：1.0
 */

class Fragment3 : Fragment() {

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "Fragment3"
        textView.setOnClickListener {
            Log.e("Fragment3", "onClick: Fragment3")
        }

    }

    override fun onResume() {
        super.onResume()
        Log.e("Fragment1", "onResume: Fragment1")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Fragment1", "onPause: Fragment1")
    } // 其他生命周期方法...
}