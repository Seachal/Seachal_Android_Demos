package com.seachal.seachaltest.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;


/**
 * @Author zhangxc
 * @Description //TODO  background 并不会对子 view裁剪， 甚至会被子 view 遮挡住圆角。
 * 那需要裁剪的时候怎么办？  用cardview?
 *
 * background
 *
 * @Date 11:10 2022/9/26
 *
 * @return * @return null
 **/
public class BackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
    }
}