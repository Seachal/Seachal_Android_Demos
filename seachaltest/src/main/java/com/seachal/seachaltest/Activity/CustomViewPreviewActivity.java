package com.seachal.seachaltest.Activity;

import android.os.Bundle;
import android.view.View;

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
    }
}