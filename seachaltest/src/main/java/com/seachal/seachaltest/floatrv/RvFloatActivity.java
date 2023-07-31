package com.seachal.seachaltest.floatrv;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.seachal.seachaltest.R;
import com.seachal.seachaltest.adapter.MyAdapter;
import com.seachal.seachaltest.bean.StartActivityBean;
import com.seachal.seachaltest.gesturedetector.OnDoubleTapListener2Activity;
import com.seachal.seachaltest.gesturedetector.OnDoubleTapListenerActivity;
import com.seachal.seachaltest.gesturedetector.OnGestureListener1Activity;
import com.seachal.seachaltest.gesturedetector.OnGestureListener2Activity;
import com.seachal.seachaltest.gesturedetector.OnGestureListener3Activity;
import com.seachal.seachaltest.gesturedetector.OnGestureListener4Activity;
import com.seachal.seachaltest.gesturedetector.SimpleOnGestureListener2Activity;
import com.seachal.seachaltest.gesturedetector.SimpleOnGestureListenerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RvFloatActivity extends AppCompatActivity  {

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;


    @BindView(R.id.text_view)
    TextView text_view;



    private final int RESULT_CODE_1 = 100;
    private long exitTime = 0;


    private List activityList = new ArrayList<StartActivityBean>();


    {

        activityList.add(new StartActivityBean("float ", OnGestureListener1Activity.class));

        activityList.add(new StartActivityBean("float", OnGestureListener2Activity.class));


        activityList.add(new StartActivityBean("float ", OnGestureListener3Activity.class));

        activityList.add(new StartActivityBean("float", OnGestureListener4Activity.class));


        activityList.add(new StartActivityBean("float", OnDoubleTapListenerActivity.class));
        activityList.add(new StartActivityBean("float", OnDoubleTapListener2Activity.class));


        activityList.add(new StartActivityBean("float", SimpleOnGestureListenerActivity.class));
        activityList.add(new StartActivityBean("float", SimpleOnGestureListener2Activity.class));

        activityList.add(new StartActivityBean("float", OnDoubleTapListenerActivity.class));
        activityList.add(new StartActivityBean("float", OnDoubleTapListener2Activity.class));


        activityList.add(new StartActivityBean("float", SimpleOnGestureListenerActivity.class));
        activityList.add(new StartActivityBean("float", SimpleOnGestureListener2Activity.class));

        activityList.add(new StartActivityBean("float", OnDoubleTapListenerActivity.class));
        activityList.add(new StartActivityBean("float", OnDoubleTapListener2Activity.class));


        activityList.add(new StartActivityBean("float", SimpleOnGestureListenerActivity.class));
        activityList.add(new StartActivityBean("float", SimpleOnGestureListener2Activity.class));



        activityList.add(new StartActivityBean("float", OnDoubleTapListenerActivity.class));
        activityList.add(new StartActivityBean("float", OnDoubleTapListener2Activity.class));


        activityList.add(new StartActivityBean("float", SimpleOnGestureListenerActivity.class));
        activityList.add(new StartActivityBean("float", SimpleOnGestureListener2Activity.class));


        activityList.add(new StartActivityBean("float", OnDoubleTapListenerActivity.class));
        activityList.add(new StartActivityBean("float", OnDoubleTapListener2Activity.class));


        activityList.add(new StartActivityBean("float", SimpleOnGestureListenerActivity.class));
        activityList.add(new StartActivityBean("float", SimpleOnGestureListener2Activity.class));






    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_menu);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(this.getClass().getSimpleName());
        recycler_view.setAdapter(new MyAdapter(RvFloatActivity.this, activityList));
        recycler_view.setLayoutManager(new LinearLayoutManager(RvFloatActivity.this));
        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private static final int HIDE_THRESHOLD = 200;
            private int scrolledDistance = 0;
            private boolean controlsVisible = true;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // 向上滑动时dy为正数，向下滑动时dy为负数
                if (scrolledDistance > HIDE_THRESHOLD ) { // 向上滑动时隐藏
//                    text_view.animate().translationY(text_view.getHeight());
                    if (text_view.getVisibility() == View.VISIBLE) {
                        text_view.setVisibility(View.GONE);
                        controlsVisible = false;
                        scrolledDistance = 0;
                    }
                } else if (scrolledDistance < -HIDE_THRESHOLD ) { // 线下滑动时显示
//                    text_view.animate().translationY(0);
                    if (text_view.getVisibility() == View.GONE) {
                        text_view.setVisibility(View.VISIBLE);
                        controlsVisible = true;
                        scrolledDistance = 0;
                    }
                }

                // 检测滑动距离
                    scrolledDistance += dy;

            }
        });

        text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ToastUtils.showShort("点击了 text_view");
            }
        });

    }










}
