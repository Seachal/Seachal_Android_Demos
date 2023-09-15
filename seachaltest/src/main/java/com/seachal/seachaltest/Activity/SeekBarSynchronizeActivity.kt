package com.seachal.seachaltest.Activity


import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R


/**
 **
 * *
// * Project_Name:Seachal_Android_Demos
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

      /*  seekBar1.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> seekBar1.progressDrawable =
//                        seekBar1.
//                        resources.getDrawable(R.drawable.t_jz_bottom_seek_progress)
//                         设置 maxheight
                        seekBar1.setMinHeight(minHeight);
                    seekBar1.setMaxHeight(maxHeight);
                    MotionEvent.ACTION_UP -> seekBar1.progressDrawable =
                        resources.getDrawable(R.drawable.t_jz_bottom_seek_progress_default)
                }
   // 这里 return false，否则会影响到 seekBar1 的滑动事件
                return false
            }
        })*/

        seekBar1.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // 进度改变时的逻辑处理，此处不需要操作
                try {
                    val superClass = seekBar.javaClass.superclass?.superclass
                    val mMaxHeightField = superClass?.getDeclaredField("mMaxHeight")
                    mMaxHeightField?.isAccessible = true
                    mMaxHeightField?.set(seekBar, resources.getDimension(R.dimen.dp_px_26))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
             /*   seekBar.thumb = getDrawable(R.drawable.t_jz_bottom_seek_poster_thumb)*/
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // 当手指触摸到 SeekBar 时执行的逻辑
//                seekBar.progressDrawable =
//                    resources.getDrawable(R.drawable.t_jz_bottom_seek_progress)
                try {
                    val superClass = seekBar.javaClass.superclass?.superclass
                    val mMaxHeightField = superClass?.getDeclaredField("mMaxHeight")
                    mMaxHeightField?.isAccessible = true
                    mMaxHeightField?.set(seekBar, resources.getDimension(R.dimen.dp_px_26))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
              /*  seekBar.scaleY = 2f
                seekBar.thumb = getDrawable(R.drawable.t_jz_bottom_seek_poster_thumb)*/
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // 当手指离开 SeekBar 时执行的逻辑
//                seekBar.progressDrawable =
//                    resources.getDrawable(R.drawable.t_jz_bottom_seek_progress_default)
                try {
                    val superClass = seekBar.javaClass.superclass?.superclass
                    val mMaxHeightField = superClass?.getDeclaredField("mMaxHeight")
                    mMaxHeightField?.isAccessible = true
                    mMaxHeightField?.set(seekBar, resources.getDimension(R.dimen.dp_px_6))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
/*                seekBar.scaleY = 1f
                seekBar.thumb = getDrawable(R.drawable.t_jz_bottom_seek_poster_thumb)*/
//                seekBar.thumb  无法调整 scale

//                seekBar.setStyle(R.style.SeekBarStyle)
            }
        })




   /*     seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // 当用户手动滑动 seekBar1 时，将进度同步给 seekBar2
                seekBar2.progress = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })*/

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
