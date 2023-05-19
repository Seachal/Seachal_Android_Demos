package com.example.seachal.launchedapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main61Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GlobalLication.getContext();
        setContentView(R.layout.activity_main5);
        TextView textView = findViewById(R.id.text5);
        textView.setText(Main61Activity.class.getName());
    }
}
