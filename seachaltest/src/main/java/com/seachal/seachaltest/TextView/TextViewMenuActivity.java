package com.seachal.seachaltest.TextView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.R;
import com.seachal.seachaltest.adapter.MyAdapter;
import com.seachal.seachaltest.bean.StartActivityBean;
import com.seachal.seachaltest.customview.CustomTextViewTestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TextViewMenuActivity extends AppCompatActivity  {

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private final int RESULT_CODE_1 = 100;
    private long exitTime = 0;


    private List activityList = new ArrayList<StartActivityBean>();


    {

        activityList.add(new StartActivityBean("textView 带超链接", LinkTextViewActivity.class));
        activityList.add(new StartActivityBean("textView textsize 使用像素单位", TextSizeActivity.class));
        activityList.add(new StartActivityBean("TextView setText 的时候，onMessure,会被调用吗。测量文字是否2行", CustomTextViewTestActivity.class));
        activityList.add(new StartActivityBean("textView 文字描边", TextStrokeActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nenu);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(this.getClass().getSimpleName());
        recycler_view.setAdapter(new MyAdapter(TextViewMenuActivity.this, activityList));
        recycler_view.setLayoutManager(new LinearLayoutManager(TextViewMenuActivity.this));
    }










}
