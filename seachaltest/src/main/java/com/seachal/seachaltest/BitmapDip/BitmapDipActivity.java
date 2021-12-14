package com.seachal.seachaltest.BitmapDip;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;


/**
 * 一个用于验证，不同尺寸的图片，放在不同的资源文件夹（drawable）下， 加载出来会有什么效果差别。
 * <p>
 * 缺少，相同尺寸的图片，放在不同的资源文件夹（drawable）下,会有什么差别
 */
public class BitmapDipActivity extends AppCompatActivity {

    public static final String TAG = "BitmapDipActivity";

    ImageView iv_l;


    ImageView iv_m;

    ImageView iv_h;

    ImageView iv_xh;


    ImageView iv_xxh;

    ImageView iv_xxxh;
    //--
    ImageView iv_240_h;
    ImageView iv_240_xh;


    ImageView iv_240_xxh;
    ImageView iv_240_xxh1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_dpi);
        Log.i(TAG, "screen dpi:" + getDensity());

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        TextView textView = findViewById(R.id.tv_dpi);
        textView.setText("x:" + xdpi + ",y:" + ydpi);

        iv_l = findViewById(R.id.iv_l);
        iv_m = findViewById(R.id.iv_m);
        iv_h = findViewById(R.id.iv_h);
        iv_xh = findViewById(R.id.iv_xh);
        iv_xxh = findViewById(R.id.iv_xxh);
        iv_xxxh = findViewById(R.id.iv_xxxh);

        iv_240_h = findViewById(R.id.iv_240_h);
        iv_240_xh = findViewById(R.id.iv_240_xh);
        iv_240_xxh = findViewById(R.id.iv_240_xxh);
        printBitmapSize(iv_240_xxh);
        /**
         *  width = 118 height = 210
         *  getByteCount = 99120
         *  getAllocationByteCount = 99120
         */
        iv_240_xxh1 = findViewById(R.id.iv_240_xxh1);
        printBitmapSize(iv_240_xxh1);
        /**
         *  width = 473 height = 840
         *  getByteCount = 1589280
         *  getAllocationByteCount = 1589280
         *  计算
         *  473 * 840 = 397320
         *  1589280 / 3970320 = 3.90 约等于 4 。
         *  根据公式 Bitamp 所占内存大小 = 宽度像素 x (inTargetDensity / inDensity) x 高度像素 x (inTargetDensity / inDensity) x 一个像素所占的内存字节大小
         *
         *  宽度像素 * 高度像素 * (inTargetDensity / inDensity)方 = 占用的内存大小。
         * inTargetDensity = 2.625；
         * inDensity =
         *
         */


    }

    public void buttonClick(View view) {
        Log.i("iv_l:", iv_l.getWidth() + ":" + iv_l.getHeight());
        Log.i("iv_m:", iv_m.getWidth() + ":" + iv_m.getHeight());
        Log.i("iv_h:", iv_h.getWidth() + ":" + iv_h.getHeight());
        Log.i("iv_xh:", iv_xh.getWidth() + ":" + iv_xh.getHeight());
        Log.i("iv_xxh:", iv_xxh.getWidth() + ":" + iv_xxh.getHeight());
        Log.i("iv_xxxh:", iv_xxxh.getWidth() + ":" + iv_xxxh.getHeight());


        Log.i("iv_240_h:", iv_240_h.getWidth() + ":" + iv_240_h.getHeight());
        Log.i("iv_240_xh:", iv_240_xh.getWidth() + ":" + iv_240_xh.getHeight());
        Log.i("iv_240_xxh:", iv_240_xxh.getWidth() + ":" + iv_240_xxh.getHeight());
    }


    private void printBitmapSize(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            bitmap.getByteCount();
            Log.d(TAG, " width = " + bitmap.getWidth() + " height = " + bitmap.getHeight());
            Log.d(TAG, " getByteCount = " + bitmap.getByteCount());
            Log.d(TAG, " getAllocationByteCount = " + bitmap.getAllocationByteCount());
        } else {
            Log.d(TAG, "Drawable is null !");
        }
    }

    /**
     * 获取屏幕 dpi
     */
    private float getDensity() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }


}
