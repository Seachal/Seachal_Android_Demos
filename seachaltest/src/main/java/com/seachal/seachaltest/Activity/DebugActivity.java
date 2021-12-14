package com.seachal.seachaltest.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;


/**
 * 用
 * <p>
 * [Android Studio 掌握这些调试技巧，Debug能力不能再高啦 - 简书](https://www.jianshu.com/p/985f788fae2c)
 */
public class DebugActivity extends AppCompatActivity {
// 变量断点
    private int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);


        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv5 = findViewById(R.id.tv5);

        tv1.setOnClickListener( view->{
            //        条件断点/ 日志断点
            for (int i = 0; i < 10; i++) {
                Log.i("TestTag", "This count is" + i);
            }
        });


        // 变量赋值
        int type = 1;
        switch (type){
            case 1:
                Log.i("TestTag", "This type is " + 1);
                break;
            case 2:
                Log.i("TestTag", "This type is" + 2);
                break;
            default:
                break;
        }

//        对象求值
        tv2.setOnClickListener(v -> {
            for (int i = 0; i < 10; i++) {
                Log.i("TestTag", "This count is" + i);

                if (TextUtils.isEmpty(getMyString(String.valueOf(i)))){
                    Log.i("TestTag", "This count string is" + i);
                }
            }
        });

        tv3.setOnClickListener(v -> {

            showLog(11);
        });


        Log.i("TestTag", "Result is" + result);
        String strNull = null;

        Log.i("TestTag", "strNull is" + strNull.toString());

    }

//方法断点
    private void showLog(int i){
        Log.i("TestTag", "This count is" + i);
    }

    private  String getMyString(String s){
        return s;
    }

}