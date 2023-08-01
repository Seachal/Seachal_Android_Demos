package com.seachal.seachaltest.floatrv;

import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.R;

import java.util.ArrayList;


public class RvFloatActivity extends AppCompatActivity implements HideScrollListener {

    private TextView toolbar;
    private RecyclerView recyclerView;
    private ImageButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_rv_onscroll);
        toolbar = findViewById(R.id.view_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.view_recycler);
        fab = (ImageButton) findViewById(R.id.view_tab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 60; i++) {
            list.add("item" + i);
        }
        FabRecyclerAdapter adapter = new FabRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new FabScrollListener(this));
    }

    @Override
    public void onHide() {
        //隐藏动画
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
/*        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();

        fab.animate().translationY(fab.getHeight() + layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));*/
    }

    @Override
    public void onShow() {
        // 显示动画--属性动画
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
//        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
    }
}

