package com.example.seachal.launchedapp;

import android.os.Bundle;

public class Main5Activity   extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GlobalLication.getContext();
        setContentView(R.layout.activity_main5);
    }
}
