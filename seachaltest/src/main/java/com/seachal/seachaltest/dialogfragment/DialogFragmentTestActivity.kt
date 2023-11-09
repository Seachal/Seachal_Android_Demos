package com.seachal.seachaltest.dialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.activity_dialog_fragment_test.btn_3
import kotlinx.android.synthetic.main.activity_dialog_fragment_test.btn_hide_dialog
import kotlinx.android.synthetic.main.activity_dialog_fragment_test.btn_show_dialog

class DialogFragmentTestActivity : AppCompatActivity() {

    //     AConfirmDialogFragment 懒加载
    private val trainingConfirmDialogFragment by lazy {
        AConfirmDialogFragment("确定删除该条目？")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_fragment_test)

        btn_show_dialog.setOnClickListener {
            trainingConfirmDialogFragment.content = "测试第 1 show修改文案是否生效？"
            trainingConfirmDialogFragment.show(supportFragmentManager, "AConfirmDialogFragment")
        }
        btn_hide_dialog.setOnClickListener {
            trainingConfirmDialogFragment.dismiss()
        }
        btn_3.setOnClickListener {
            trainingConfirmDialogFragment.content = "测试第 2 次修改 dialog文案是否生效？"
            trainingConfirmDialogFragment.show(supportFragmentManager, "AConfirmDialogFragment")
        }

    }
}