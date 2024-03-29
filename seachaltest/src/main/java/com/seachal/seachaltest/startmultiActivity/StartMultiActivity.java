package com.seachal.seachaltest.startmultiActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.Activity.SecondActivity;
import com.seachal.seachaltest.Activity.ThirdActivity;
import com.seachal.seachaltest.R;


/**
 * @Author zhangxc
 * @Description //TODO    一次 start 多 TimerActivity 是可以的。
 * @Date 10:33 2023/9/2
 * @return * @return null
 **/
public class StartMultiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_multi);

        TextView tv_start_multi = findViewById(R.id.tv_start_multi);

        tv_start_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartMultiActivity.this, SecondActivity.class));
                startActivity(new Intent(StartMultiActivity.this, ThirdActivity.class));
            }
        });

    }
}