package com.seachal.seachaltest.Activity

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import com.zhangyue.we.x2c.X2C.setContentView

/**
 **
 * *
 * Project_Name:Seachal_Android_Demos
 * @Description: TODO
 * @author zhangxc
 * @date 2023/8/15 10:25
 * @Version：1.0
 */
class SeekBarSynchronizeActivity : AppCompatActivity() {
    private lateinit var seekBar1: SeekBar
    private lateinit var seekBar2: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seekbar_synchronize)

        seekBar1 = findViewById(R.id.seekBar1)
        seekBar2 = findViewById(R.id.seekBar2)

        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // 当用户手动滑动 seekBar1 时，将进度同步给 seekBar2
                seekBar2.progress = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // 当用户手动滑动 seekBar2 时，将进度同步给 seekBar1
                seekBar1.progress = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
}
