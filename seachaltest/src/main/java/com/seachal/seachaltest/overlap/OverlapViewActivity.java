package com.seachal.seachaltest.overlap;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

public class OverlapViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlap_view);

        TextView textView = findViewById(R.id.tip_view);
        View topView = findViewById(R.id.top_view);
        View bottomView = findViewById(R.id.bottom_view);

        topView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OverlapViewActivity", "点击了上层 view");
                textView.setText("点击了上层 view");
            }
        });


        topView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//               这里返回 true，表示 topView
                return true;
            }
        });

        bottomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理下层 view 的点击事件
                // ...
                Log.i("OverlapViewActivity", "点击了底层 view");
                textView.setText("点击了底层 view");
            }
        });

    }
}