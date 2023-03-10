package com.seachal.seachaltest.Activity;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 *
 * @author zhangxc
 * @Description: TODO
 * @date 2023/3/7 11:04
 * @Version：1.0
 */
public  class BaseActivity extends AppCompatActivity {

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (requestCode>0){
            Log.d("BaseActivity","大");
        }else {
            Log.d("BaseActivity","小");
        }
        super.startActivityForResult(intent, requestCode);
    }
}
