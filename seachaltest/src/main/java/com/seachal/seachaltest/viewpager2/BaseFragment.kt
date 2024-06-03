package com.seachal.seachaltest.viewpager2

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() , IBasePage {

    private lateinit var attachedActivity: Activity
    private var waitingDialog: Dialog? = null
    private val loadingDialogList = mutableListOf<Dialog?>()
    protected var TAG = javaClass.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getContentLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListeners()
        loadData()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        activity?.let { this.attachedActivity = it }
    }



    fun hideLoading() {
        if (loadingDialogList.isNotEmpty()) {
            loadingDialogList[loadingDialogList.size - 1]?.dismiss()
            loadingDialogList.removeLast()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        waitingDialog?.let { if (it.isShowing) it.dismiss() }
    }

}