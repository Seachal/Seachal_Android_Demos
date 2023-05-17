package com.seachal.seachaltest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.seachal.seachaltest.BaseApp;
import com.seachal.seachaltest.R;
import com.seachal.seachaltest.bean.StartActivityBean;

public class SecondActivity extends BaseActivity {

    int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);




        LinearLayout ll_root = findViewById(R.id.ll_root);
        TextView tv1 = findViewById(R.id.tv_1);
        TextView tv2 = findViewById(R.id.tv_2);
        TextView tv3 = findViewById(R.id.tv_3);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SecondActivity.this,tv1.getText(),Toast.LENGTH_SHORT).show();

                String s1  = getIntent().getComponent().getClassName();
                String s2  =  getIntent().getComponent().getPackageName();
                String s3  =  getIntent().getComponent().getShortClassName();
                tv1.setText("点击了 tv1");
                tv1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent  = new Intent(SecondActivity.this,ThirdActivity.class);
                        startActivity(intent);
                    }
                },2000);

            }
        });


        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(SecondActivity.this,ThirdActivity.class);
                // 加上 FLAG_ACTIVITY_NEW_TASK，代码更健壮一些
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApp.getContext().startActivity(intent);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent  = new Intent(SecondActivity.this,ThirdActivity.class);
////                ContextImpl.startActivity(intent);


                try {
                    StartActivityBean startActivityBean = null;
                    Log.i("SecondActivity log",startActivityBean.getTitle());
                } catch (Exception e) {

                    Log.e( "SecondAct error Message",e.getMessage());
                    Log.e( "SecondAct error toStr",e.toString());
                    e.printStackTrace();
                }
            }
        });


//        textview 点击操作，可以访问 外部变量，可以改变外部变量吗？
        for (int i =0;i<3;i++){
            TextView tv4  = (TextView) LayoutInflater.from(this).inflate(R.layout.layout_life_pay_textview, ll_root, false);
            tv4.setText("tv4_"+i);
            tv4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(SecondActivity.this,tv4.getText(),Toast.LENGTH_SHORT).show();
                    tv4.setText("点击了 tv4"+tv4.getId());
                    clickCount ++;
                    Log.i("clickCount","clickCount:"+clickCount);
//                    Toast.makeText(SecondActivity.this, clickCount,Toast.LENGTH_SHORT).show();
                }
            });
            ll_root.addView(tv4);
        }

        findViewById(R.id.tv_5).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                 Intent intent  = new Intent(SecondActivity.this,ThirdActivity.class);
                 startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            setResult(Activity.RESULT_OK);
            Toast.makeText(SecondActivity.this,"ddd",Toast.LENGTH_SHORT).show();
//            finish();
        }
    }
}