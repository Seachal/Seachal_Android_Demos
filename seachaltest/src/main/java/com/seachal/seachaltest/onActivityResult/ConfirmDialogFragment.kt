package com.seachal.seachaltest.onActivityResult

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import com.seachal.seachaltest.dialogfragment.BaseDialogFragmentKotlin
import kotlinx.android.synthetic.main.activity_on_result.tv_result
import kotlinx.android.synthetic.main.t_layout_dialog_confirm.tv_content
import kotlinx.android.synthetic.main.t_layout_dialog_confirm.view.tv_cancel
import kotlinx.android.synthetic.main.t_layout_dialog_confirm.view.tv_confirm
import kotlinx.android.synthetic.main.t_layout_dialog_confirm.view.tv_content


/**
 * TODO  删除时的确认 dialog.
 *
 */
class ConfirmDialogFragment(var content: String) : BaseDialogFragmentKotlin() {

    internal lateinit var listener: ConfirmListener

    private var mActivity: Activity? = null


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
//                dismiss()
                listener.onCancel()
            }
            tv_confirm.setOnClickListener {
//                dismiss()
                listener.onConfirm()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            // 初始化接口实例来发送事件
            listener = context as ConfirmListener
        } catch (e: ClassCastException) {
            // Activity没有实现接口，抛出异常
            throw ClassCastException(
                (context.toString() +
                        " must implement ConfirmListener")
            )
        }

        if (context is Activity) {
            mActivity = context
        }

    }

    interface ConfirmListener {
        fun onConfirm()
        fun onCancel()
    }




//    在 Fragment 中处理 onActivityResult

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val result = data?.getStringExtra("result")
                if (result!= null) {

                    Toast.makeText(mActivity, result, Toast.LENGTH_SHORT).show()
                    //  把所有信息展示出来，requestCode: Int, resultCode: Int, data: Intent
                    tv_content.text = "Dialog onActivityResult  requestCode: $requestCode, resultCode: $resultCode, data: $data"

                    Log.d("OnActivityResult", "Second onActivityResult requestCode: $requestCode, resultCode: $resultCode, result: $result")

                }
            }
        }
    }

}


