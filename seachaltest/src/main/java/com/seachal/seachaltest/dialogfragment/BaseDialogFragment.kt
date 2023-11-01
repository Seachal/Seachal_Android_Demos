package com.seachal.seachaltest.dialogfragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

open class BaseDialogFragment : DialogFragment() {
    override fun show(manager: FragmentManager, tag: String?) {
        Log.e("BaseDialogFragment", "show")
        if (!manager.isDestroyed && !manager.isStateSaved) {
            super.show(manager, tag)
            Log.e("BaseDialogFragment", "可以show")
        }
    }

    override fun dismiss() {
        dismissAllowingStateLoss()
        Log.e("BaseDialogFragment", "dismiss")
    }

    //    重新并打印BaseDialogFragment的生命周期方法
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("BaseDialogFragment", "onAttach")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.e("BaseDialogFragment", "onCreateDialog")
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("BaseDialogFragment", "onActivityCreated")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("BaseDialogFragment", "onViewCreated")
    }


    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        Log.e("BaseDialogFragment", "onGetLayoutInflater")
        return super.onGetLayoutInflater(savedInstanceState)
    }


    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        Log.e("BaseDialogFragment", "onInflate")
    }

    override fun onAttachFragment(childFragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(childFragment)
        Log.e("BaseDialogFragment", "onAttachFragment")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e("BaseDialogFragment", "onHiddenChanged")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("BaseDialogFragment", "onSaveInstanceState")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        Log.e("BaseDialogFragment", "onCreateView")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("BaseDialogFragment", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("BaseDialogFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("BaseDialogFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("BaseDialogFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("BaseDialogFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("BaseDialogFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("BaseDialogFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("BaseDialogFragment", "onDetach")
    }

}