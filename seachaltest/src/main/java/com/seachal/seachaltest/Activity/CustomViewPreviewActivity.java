package com.seachal.seachaltest.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;
import com.seachal.seachaltest.View.RadishCircleProgressBar;

public class CustomViewPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_preview);
        RadishCircleProgressBar mRadishCircleProgressBar = findViewById(R.id.radish_circle_progress);
        mRadishCircleProgressBar.setVisibility(View.VISIBLE);
        mRadishCircleProgressBar.setProgress(10);
        mRadishCircleProgressBar.setMaxProgress(100);

        mRadishCircleProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadishCircleProgressBar.setProgress(50);
            }
        });

        ProgressBar mProgressBar = findViewById(R.id.loading);
        mProgressBar.setIndeterminate(false); // 设置为确定型进度条

//         progressbar 不可以设置文字字体和颜色
//// 设置文字内容
//        mProgressBar.setTooltipText("Loading...");
//// 设置文字的字体大小
//        mProgressBar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
//// 设置文字的颜色
//        mProgressBar.setTextColor(Color.RED);

    }
}