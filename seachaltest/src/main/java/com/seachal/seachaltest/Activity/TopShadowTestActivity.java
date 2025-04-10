package com.seachal.seachaltest.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.seachal.seachaltest.R;

/**
 * 顶部圆角和阴影效果测试活动
 * 展示多种实现顶部圆角和阴影效果的方案
 */
public class TopShadowTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置使用XML布局文件
        setContentView(R.layout.activity_top_shadow_test);
        
        // 设置标题
        setTitle("顶部圆角与阴影效果对比");
    }
} 