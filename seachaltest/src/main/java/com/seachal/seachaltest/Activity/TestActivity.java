package com.seachal.seachaltest.Activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2020/3/10 17:28
 * *
 */
public class TestActivity  extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIntent().getExtras();

        getIntent().getBundleExtra("sss");

    }
}
