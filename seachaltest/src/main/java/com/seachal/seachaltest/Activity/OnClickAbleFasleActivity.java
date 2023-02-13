package com.seachal.seachaltest.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

public class OnClickAbleFasleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_able_fasle);

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv1.setText(tv1.getText().toString()+"1");
            }
        });

        TextView tv2 = findViewById(R.id.tv2);
        tv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv2.setText(tv2.getText().toString()+"2");
            }
        });
    }
}