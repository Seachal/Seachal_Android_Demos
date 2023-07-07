package com.seachal.seachaltest.getdimension;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ScreenUtils;
import com.seachal.seachaltest.R;


/**
 * @Author zhangxc
 * @Description //TODO
 * @Date 14:40 2023/6/8
 *
 * @return * @return null
 *
 * getDimension 返回的是 px 值，   不用 dp2px转换了。
 **/

public class GetDimensionActivity extends AppCompatActivity {


     static final String TAG = "GetDimensionActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_dimension);

        float density = ScreenUtils.getScreenDensity();
        float scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;
        int densityDpi = ScreenUtils.getScreenDensityDpi();
        Log.e(TAG, "density：" + density);
        Log.e(TAG, "scaledDensity：" + scaledDensity);
        Log.e(TAG, "densityDpi：" + densityDpi);

        float floatDp = this.getResources().getDimension(R.dimen.dp_41);
        int pixelSizeDp = this.getResources().getDimensionPixelSize(R.dimen.dp_41);
        int pixelOffsetDp = this.getResources().getDimensionPixelOffset(R.dimen.dp_41);

        Log.e(TAG, "floatDp：" + floatDp);
        Log.e(TAG, "pixelSizeDp：" + pixelSizeDp);
        Log.e(TAG, "pixelOffsetDp：" + pixelOffsetDp);

        float floatPx = this.getResources().getDimension(R.dimen.px_41);
        int pixelSizePx = this.getResources().getDimensionPixelSize(R.dimen.px_41);
        int pixelOffsetPx = this.getResources().getDimensionPixelOffset(R.dimen.px_41);

        Log.e(TAG, "floatPx：" + floatPx);
        Log.e(TAG, "pixelSizePx：" + pixelSizePx);
        Log.e(TAG, "pixelOffsetPx：" + pixelOffsetPx);

        float floatSp = this.getResources().getDimension(R.dimen.sp_41);
        int pixelSizeSp = this.getResources().getDimensionPixelSize(R.dimen.sp_41);
        int pixelOffsetSp = this.getResources().getDimensionPixelOffset(R.dimen.sp_41);

        Log.e(TAG, "floatSp：" + floatSp);
        Log.e(TAG, "pixelSizeSp：" + pixelSizeSp);
        Log.e(TAG, "pixelOffsetSp：" + pixelOffsetSp);

        Log.e(TAG, "sp2px：" + DensityUtil.sp2px(40f));
        Log.e(TAG, "dp2px：" + DensityUtil.dp2px(40f));


    }
}

/**
 * @Author zhangxc
 * @Description //TODO
 * @Date 14:39 2023/6/8
 *
 * @return * @return null
 *
 * GetDimensionActivity    com.seachal.seachaltest              E  density：2.75
 * GetDimensionActivity    com.seachal.seachaltest              E  scaledDensity：3.85
 * GetDimensionActivity    com.seachal.seachaltest              E  densityDpi：440
 * GetDimensionActivity    com.seachal.seachaltest              E  floatDp：112.75
 * GetDimensionActivity    com.seachal.seachaltest              E  pixelSizeDp：113
 * GetDimensionActivity    com.seachal.seachaltest              E  pixelOffsetDp：112
 * ---
 * GetDimensionActivity    com.seachal.seachaltest              E  floatPx：44.0
 * GetDimensionActivity    com.seachal.seachaltest              E  pixelSizePx：44
 * GetDimensionActivity    com.seachal.seachaltest              E  pixelOffsetPx：44
 * ----
 * GetDimensionActivity    com.seachal.seachaltest              E  floatSp：157.84999
 * GetDimensionActivity    com.seachal.seachaltest              E  pixelSizeSp：158
 * GetDimensionActivity    com.seachal.seachaltest              E  pixelOffsetSp：157
 * ----
 * GetDimensionActivity    com.seachal.seachaltest              E  sp2px：154
 * GetDimensionActivity    com.seachal.seachaltest              E  dp2px：110
 **/
