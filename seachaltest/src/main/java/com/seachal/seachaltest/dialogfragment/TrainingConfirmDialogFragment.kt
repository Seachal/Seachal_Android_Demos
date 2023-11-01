package com.seachal.seachaltest.dialogfragment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import com.blankj.utilcode.util.SnackbarUtils.dismiss
import com.seachal.seachaltest.R

import kotlinx.android.synthetic.main.t_layout_dialog_confirm.*
import kotlinx.android.synthetic.main.t_layout_dialog_confirm.view.tv_cancel
import kotlinx.android.synthetic.main.t_layout_dialog_confirm.view.tv_confirm
import kotlinx.android.synthetic.main.t_layout_dialog_confirm.view.tv_content
import kotlinx.android.synthetic.main.t_layout_dialog_confirm.view.tv_title


/**
 * TODO  删除时的确认 dialog.
 *
 */
class TrainingConfirmDialogFragment(var content: String) : BaseDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        isCancelable = false
        dialog?.window?.run {
            decorView.setPadding(0, 0, 0, 0)
            attributes.run {
                // DialogFragment区域外可点击，不触发Cancel
//                addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
                //去除DialogFragment阴影
//                clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//                width = WindowManager.LayoutParams.MATCH_PARENT
//                height = WindowManager.LayoutParams.MATCH_PARENT
                gravity = Gravity.CENTER
            }
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        return inflater.inflate(R.layout.t_layout_dialog_confirm, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.run {
            tv_content.text = content
            tv_cancel.setOnClickListener {
                dismiss()
//                listener.onCancel()
            }
            tv_confirm.setOnClickListener {
                dismiss()
//                listener.onConfirm()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            // 初始化接口实例来发送事件
//            listener = context as ConfirmListener
        } catch (e: ClassCastException) {
            // Activity没有实现接口，抛出异常
            throw ClassCastException(
                (context.toString() +
                        " must implement ConfirmListener")
            )
        }

    }

    interface ConfirmListener {
        fun onConfirm()
        fun onCancel()
    }



}


