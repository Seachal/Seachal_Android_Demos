package com.seachal.seachaltest.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.seachal.seachaltest.R;
import com.zhangyue.we.x2c.X2C;
import com.zhangyue.we.x2c.ano.Xml;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2019-10-23 16:10
 * *
 */
@Xml(layouts = "activity_count_down_timer")
public class CountDownTimerActivity extends AppCompatActivity {

    public static final String TAG = "CountDownTimerActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_count_down_timer);
        X2C.setContentView(this, R.layout.activity_count_down_timer);

        TextView textView = findViewById(R.id.tv_time);
        TextView textView2 = findViewById(R.id.tv_time2);

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished + "");
                Log.i(TAG, "seconds remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "done!");
            }
        }.start();
    }


//    public void scrollToView(NestedScrollView scrollView, View view) {
//        // Compute the coordinates of the view inside the scroll view
//        int x = view.getLeft();
//        int y = view.getTop();
//        for(ViewParent parent = view.getParent();
//            parent instanceof ViewGroup;
//            parent != scrollView;
//            parent = ((View)parent).getParent()) {
//            x += ((View)parent).getLeft();
//            y += ((View)parent).getTop();
//        }
//
//        // Scroll to the computed coordinates
//        scrollView.smoothScrollTo(x, y);
//    }




}