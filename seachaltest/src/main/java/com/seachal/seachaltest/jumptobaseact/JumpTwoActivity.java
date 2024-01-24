package com.seachal.seachaltest.jumptobaseact;


import android.os.Bundle;
import android.widget.TextView;

import com.seachal.seachaltest.R;

public class JumpTwoActivity extends BaseJumpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_second);
        TextView tv_reslut = findViewById(R.id.tv_reslut);


    }

}