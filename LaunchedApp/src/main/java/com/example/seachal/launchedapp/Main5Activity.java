package com.example.seachal.launchedapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GlobalLication.getContext();
        setContentView(R.layout.activity_main5);
    }
}
