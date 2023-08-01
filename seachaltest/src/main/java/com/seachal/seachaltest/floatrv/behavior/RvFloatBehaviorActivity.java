package com.seachal.seachaltest.floatrv.behavior;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.seachal.seachaltest.R;
import com.seachal.seachaltest.floatrv.FabRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 *
 * @author zhangxc
 * @Description: TODO
 * @date 2023/7/31 14:59
 * @Version：1.0
 */
public class RvFloatBehaviorActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_rv_behavior);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        setTitle("这是搜索框");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("Item" + i);
        }
        RecyclerView.Adapter adapter = new FabRecyclerAdapter(list);
        recyclerview.setAdapter(adapter);
    }

    public void rotate(View v) {
        Snackbar.make(v, "你好吗？", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "我很好！", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}

