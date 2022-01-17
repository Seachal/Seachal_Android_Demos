package com.example.hiphonezhu.nestedscrolling;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final StickyLayout stickyNavLayout = (StickyLayout)findViewById(R.id.stickyNavLayout);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        final View vAlways = findViewById(R.id.v_always);

        rv.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<>();
        for(int i = 0; i < 50; i++)
        {
            data.add("item" + i);
        }
        rv.setAdapter(new MyAdapter(this, data));

        vAlways.post(new Runnable() {
            @Override
            public void run() {
                stickyNavLayout.setAlways(vAlways, vAlways.getPaddingTop());
            }
        });
    }
}
