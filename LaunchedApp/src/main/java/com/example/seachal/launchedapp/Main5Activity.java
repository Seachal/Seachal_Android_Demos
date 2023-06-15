package com.example.seachal.launchedapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Main5Activity   extends BaseActivity {
    private String TAG = "Main5Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate:"+getIntent().toString());
        if (getIntent().getExtras() != null){
            Log.d(TAG, "onCreate  getExtras :"+getIntent().getStringExtra("name"));
        }
        GlobalLication.getContext();
        setContentView(R.layout.activity_main5);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:"+getIntent().toString());
        if (getIntent().getExtras() != null){
            Log.d(TAG, "onStart  getExtras :"+getIntent().getStringExtra("name"));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart:"+getIntent().toString());
        if (getIntent().getExtras() != null){
            Log.d(TAG, "onRestart  getExtras :"+getIntent().getStringExtra("name"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume:"+getIntent().toString());
        if (getIntent().getExtras() != null){
            Log.d(TAG, "onResume  getExtras :"+getIntent().getStringExtra("name"));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause:");
        if (getIntent().getExtras() != null){
            Log.d(TAG, "onPause  getExtras :"+getIntent().getStringExtra("name"));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop:");
        if (getIntent().getExtras() != null){
            Log.d(TAG, "onStop  getExtras :"+getIntent().getStringExtra("name"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:");
        if (getIntent().getExtras() != null){
            Log.d(TAG, "onDestroy  getExtras :"+getIntent().getStringExtra("name"));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent:==========="+intent.toString());
        if (getIntent().getExtras() != null){
            Log.d(TAG, "onNewIntent  getExtras setIntent 前 :"+getIntent().getStringExtra("name"));
        }
        setIntent(intent);
        if (getIntent().getExtras() != null){
            Log.d(TAG, "onNewIntent  getExtras  setIntent 后:"+getIntent().getStringExtra("name"));
        }
    }

}
