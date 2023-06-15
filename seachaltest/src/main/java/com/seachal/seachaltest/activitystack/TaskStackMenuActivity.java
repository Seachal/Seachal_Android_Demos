package com.seachal.seachaltest.activitystack;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.Activity.StackActivity;
import com.seachal.seachaltest.R;
import com.seachal.seachaltest.adapter.MyAdapter;
import com.seachal.seachaltest.bean.StartActivityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author zhangxc
 * @Description //TODO   获取任务栈
 * @Date 15:16 2023/6/15
 *
 * @return * @return null
 **/
public class TaskStackMenuActivity extends AppCompatActivity  {

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private final int RESULT_CODE_1 = 100;
    private long exitTime = 0;


    private List activityList = new ArrayList<StartActivityBean>();


    {

        activityList.add(new StartActivityBean("1 反射", StackActivity.class));



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nenu);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(this.getClass().getSimpleName());
        recycler_view.setAdapter(new MyAdapter(TaskStackMenuActivity.this, activityList));
        recycler_view.setLayoutManager(new LinearLayoutManager(TaskStackMenuActivity.this));
    }










}
