package com.seachal.seachaltest.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

/**
 * @Author zhangxc
 * @Description //TODO
 * @Date 13:47 2023/9/13
 *       <!--
 * 链接：https://blog.csdn.net/mg2flyingff/article/details/105877114
 * -->
 *
 * 可以看出，view的在屏幕上的位置越靠下，阴影越重，但阴影都是黑色的
 * @return * @return null
 **/
public class CardVeiwScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_veiw_scroll);
    }
}