package com.example.seachal.launchedapp;

import android.content.Intent;
import android.os.Bundle;

public class TrainTransferActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_transfer);


        Intent intent = new Intent(TrainTransferActivity.this, Main5Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}