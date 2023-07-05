package com.seachal.seachaltest;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HorizontalAndVerticalScreenSwitchActivity extends AppCompatActivity {

//    tag
     final String TAG = "HorizontalAndVertical";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_and_vertical_screen_switch);
        Log.i(TAG,"onCreate:1");

    }

//      当在  AndroidManifest 清单文件中固定住方向后， 切换横竖屏不会触发onConfigurationChanged。
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏
            Log.i(TAG,"newConfig.orientation:"+newConfig.orientation);
        } else {//竖屏
            Log.i(TAG,"newConfig.orientation:"+newConfig.orientation);
        }
    }
}