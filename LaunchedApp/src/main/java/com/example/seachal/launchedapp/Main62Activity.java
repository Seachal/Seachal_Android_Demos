package com.example.seachal.launchedapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Set;

public class Main62Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        GlobalLication.getContext();
        setContentView(R.layout.activity_main62);
        TextView textView = findViewById(R.id.text5);
        textView.setText(Main62Activity.class.getName());

        Intent intent = getIntent();
        Uri data = intent.getData();  //
        Bundle extras = intent.getExtras();  //
        String action = intent.getAction();
        String scheme = intent.getScheme();
        Set<String> categories = intent.getCategories();
        Log.e("TAG", "data==========="+data);
        Log.e("TAG", "extras==========="+extras);
        Log.e("TAG", "action==========="+action);
        Log.e("TAG", "categories==========="+categories);
        Log.e("TAG", "DataString==========="+intent.getDataString());
        Log.e("TAG", "==============================");
        Log.e("TAG", "scheme==========="+scheme);
        Log.e("TAG", "id ==========="+data.getQueryParameterNames());
        Log.e("TAG", "host==========="+data.getHost());
        Log.e("TAG", "path==========="+data.getPath());
        Log.e("TAG", "port==========="+data.getPort());
        Log.e("TAG", "==============================");
        Log.e("TAG", "getQuery==========="+data.getQuery());
        Log.e("TAG", "getPathSegments==========="+data.getPathSegments());
        Log.e("TAG", "getLastPathSegment==========="+data.getLastPathSegment());
        Log.e("TAG", "getSchemeSpecificPart==========="+data.getSchemeSpecificPart());
        Log.e("TAG", "getUserInfo==========="+data.getUserInfo());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }
}
