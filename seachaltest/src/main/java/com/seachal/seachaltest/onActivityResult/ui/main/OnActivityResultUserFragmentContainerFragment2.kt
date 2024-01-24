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
import kotlinx.android.synthetic.main.fragment_on_activity_result.tv_click2
import kotlinx.android.synthetic.main.fragment_on_activity_result.tv_content

//  onActivityResult:MyFragment 发起 StartActivityForResult, BaseFragment1会收到onActivityResult 回调吗？
class OnActivityResultUserFragmentContainerFragment2 : BaseFragment1() {

    companion object {
        fun newInstance() = OnActivityResultUserFragmentContainerFragment2()
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
        return inflater.inflate(R.layout.fragment_on_activity_result, container, false)
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


}