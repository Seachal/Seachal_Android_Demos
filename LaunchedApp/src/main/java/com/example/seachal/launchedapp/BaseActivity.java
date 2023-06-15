package com.example.seachal.launchedapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 *
 * @author zhangxc
 * @Description: TODO
 * @date 2023/6/15 14:36
 * @Version：1.0
 */
public class BaseActivity  extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate:"+getIntent().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:"+getIntent().toString());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart:"+getIntent().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume:"+getIntent().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause:");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop:");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent:==========="+intent.toString());
        setIntent(intent);
    }

}
