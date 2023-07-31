package com.seachal.seachaltest.gesturedetector;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.R;
import com.seachal.seachaltest.adapter.MyAdapter;
import com.seachal.seachaltest.bean.StartActivityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GestureMenuActivity extends AppCompatActivity  {

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private final int RESULT_CODE_1 = 100;
    private long exitTime = 0;


    private List activityList = new ArrayList<StartActivityBean>();


    {

        activityList.add(new StartActivityBean("手势  OnGestureListener return true", OnGestureListener1Activity.class));

        activityList.add(new StartActivityBean("手势  OnGestureListener return false", OnGestureListener2Activity.class));


        activityList.add(new StartActivityBean("手势  OnGestureListener return true 分发给 Activity ", OnGestureListener3Activity.class));

        activityList.add(new StartActivityBean("手势  OnGestureListener return false  分发给 Activity", OnGestureListener4Activity.class));


        activityList.add(new StartActivityBean("手势  OnDoubleTapListener", OnDoubleTapListenerActivity.class));
        activityList.add(new StartActivityBean("手势  OnDoubleTapListener 分发给 Activity", OnDoubleTapListener2Activity.class));


        activityList.add(new StartActivityBean("手势  SimpleOnGestureListener", SimpleOnGestureListenerActivity.class));
        activityList.add(new StartActivityBean("手势  SimpleOnGestureListener 分发给 Activity", SimpleOnGestureListener2Activity.class));



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_menu);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(this.getClass().getSimpleName());
        recycler_view.setAdapter(new MyAdapter(GestureMenuActivity.this, activityList));
        recycler_view.setLayoutManager(new LinearLayoutManager(GestureMenuActivity.this));
    }










}
