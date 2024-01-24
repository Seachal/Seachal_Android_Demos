package com.seachal.seachaltest.getdimension

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ScreenUtils
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.activity_get_dimension.tv_1
import kotlinx.android.synthetic.main.activity_get_dimension.tv_2
import kotlinx.android.synthetic.main.activity_get_dimension.tv_3
import kotlinx.android.synthetic.main.activity_get_dimension.tv_4
import kotlinx.android.synthetic.main.activity_get_dimension.tv_5
import kotlinx.android.synthetic.main.activity_get_dimension.tv_6

/**
 * @Author zhangxc
 * @Description //TODO
 * @Date 14:40 2023/6/8
 *
 * @return * @return null
 *
 * getDimension 返回的是 px 值，   不用 dp2px转换了。
 */
class GetDimensionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_dimension)
        val density = ScreenUtils.getScreenDensity()
        val scaledDensity = Resources.getSystem().displayMetrics.scaledDensity
        val densityDpi = ScreenUtils.getScreenDensityDpi()
        Log.e(TAG, "density：$density")
        Log.e(TAG, "scaledDensity：$scaledDensity")
        Log.e(TAG, "densityDpi：$densityDpi")
        val floatDp = this.resources.getDimension(R.dimen.dp_41)
        val pixelSizeDp = this.resources.getDimensionPixelSize(R.dimen.dp_41)
        val pixelOffsetDp = this.resources.getDimensionPixelOffset(R.dimen.dp_41)
        Log.e(TAG, "floatDp：$floatDp")
        Log.e(TAG, "pixelSizeDp：$pixelSizeDp")
        Log.e(TAG, "pixelOffsetDp：$pixelOffsetDp")
        val floatPx = this.resources.getDimension(R.dimen.px_41)
        val pixelSizePx = this.resources.getDimensionPixelSize(R.dimen.px_41)
        val pixelOffsetPx = this.resources.getDimensionPixelOffset(R.dimen.px_41)
        Log.e(TAG, "floatPx：$floatPx")
        Log.e(TAG, "pixelSizePx：$pixelSizePx")
        Log.e(TAG, "pixelOffsetPx：$pixelOffsetPx")
        val floatSp = this.resources.getDimension(R.dimen.sp_41)
        val pixelSizeSp = this.resources.getDimensionPixelSize(R.dimen.sp_41)
        val pixelOffsetSp = this.resources.getDimensionPixelOffset(R.dimen.sp_41)
        Log.e(TAG, "floatSp：$floatSp")
        Log.e(TAG, "pixelSizeSp：$pixelSizeSp")
        Log.e(TAG, "pixelOffsetSp：$pixelOffsetSp")
        Log.e(TAG, "sp2px：" + DensityUtil.sp2px(40f))
        Log.e(TAG, "dp2px：" + DensityUtil.dp2px(40f))


//         这种 dp_px_28 转换出来的 size是正确的。
        tv_1.setOnClickListener({
            Log.d(TAG, "tv_1: " + tv_1.textSize)
            tv_1.setTextSize(TypedValue.COMPLEX_UNIT_PX,  resources.getDimension(R.dimen.dp_px_28))
        })
        tv_2.setOnClickListener({
            Log.d(TAG, "tv_2: " + tv_2.textSize)
            tv_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,  resources.getDimension(R.dimen.dp_px_28))
        })

        tv_3.setOnClickListener({
            Log.d(TAG, "tv_3: " + tv_3.textSize)
            tv_3.setTextSize(TypedValue.COMPLEX_UNIT_SP,  resources.getDimension(R.dimen.dp_px_28))
        })

        tv_4.setOnClickListener({
            Log.d(TAG, "tv_4: " + tv_4.textSize)
            tv_4.setTextSize(TypedValue.COMPLEX_UNIT_PT,  resources.getDimension(R.dimen.dp_px_28))
        })

        tv_5.setOnClickListener({
            Log.d(TAG, "tv_5: " + tv_5.textSize)
            tv_5.setTextSize(TypedValue.COMPLEX_UNIT_IN,  resources.getDimension(R.dimen.dp_px_28))
        })

        tv_6.setOnClickListener({
            Log.d(TAG, "tv_6: " + tv_6.textSize)
            tv_6.setTextSize(TypedValue.COMPLEX_UNIT_MM,  resources.getDimension(R.dimen.dp_px_28))
        })

    }

    companion object {
        const val TAG = "GetDimensionActivity"
    }
}
