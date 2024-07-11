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
import com.seachal.seachaltest.utils.getViewVisiblePercent
import kotlinx.android.synthetic.main.activity_atuto_text_size.view.text
import kotlinx.android.synthetic.main.activity_os_info.textView1
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

class Fragment2 : Fragment() {

    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "Fragment2"
        textView.setOnClickListener {
            Log.e("Fragment2", "onClick: Fragment2")
        }

    }

    override fun onResume() {
        super.onResume()
        Log.e("Fragment2", "onResume: Fragment2")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Fragment2", "onPause: Fragment2")
    } // 其他生命周期方法...
}