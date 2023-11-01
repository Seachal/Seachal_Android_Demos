package com.seachal.seachaltest.layoutparams;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

public class LayoutParamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_params);

        LinearLayout linearLayout = findViewById(R.id.ll_container);
        TextView textView = new TextView(LayoutParamsActivity.this  );

// 设置文本内容
        textView.setText("Hello World");

// 设置布局参数为 WRAP_CONTENT 或其他宽高值

// 设置视图的 Gravity 属性
        textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//        textView 设置 padding
        textView.setPadding(10, 10, 10, 10);
        linearLayout.addView(textView);



        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
//         整体居左
        layoutParams.gravity = Gravity.START;
//        layoutParams.addRule(LinearLayout.); // LinearLayout 没有addRule方法
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); // LinearLayout 没有addRule方法
        textView.setLayoutParams(layoutParams);



//        RelativeLayout
        RelativeLayout relativeLayout = new RelativeLayout(LayoutParamsActivity.this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        ImageView imageView = new ImageView(LayoutParamsActivity.this);
        imageView.setImageResource(R.drawable.android_logo_m);
        layoutParams2.addRule(RelativeLayout.CENTER_HORIZONTAL); // 水平居中
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_TOP); // 底部对齐
        imageView.setLayoutParams(layoutParams2);

// 将 ImageView 添加到 RelativeLayout 中
        relativeLayout.addView(imageView);
        linearLayout.addView(relativeLayout);

    }
}