package com.seachal.seachaltest.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

/**
 * description: 写个案例测试 viewstub
 * author: ZhangXC
 * date: 2022/4/20 16:36
 * update: 2022/4/20 16:36
 * version:  v1.0
*/
public class ViewStubActivity extends AppCompatActivity {

    private ViewStub mViewStub1;
    private ViewStub mViewStub2;

    private View mViewStubContentView;
    private View mViewStubContentView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
        //首先根据 id 获取 ViewStub
        mViewStub1 = findViewById(R.id.style_1);
        mViewStub2 = findViewById(R.id.style_2);
        //同时在我们需要的时候，初始化 ViewStub 包裹的布局，其实ViewStub的延迟加载就是这么个原理的
        mViewStubContentView = mViewStub1.inflate();
        //同时获取 ViewStub的 LayoutParams 参数
        ViewGroup.LayoutParams params = mViewStub1.getLayoutParams();
        Log.i("LOH", params.width + "...height..." + params.height);
        //我们使用display按钮来控制 ViewStub加载出来以后的view的显示与隐藏
        findViewById(R.id.display).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewStubContentView.getVisibility() == View.VISIBLE) {
                    mViewStubContentView.setVisibility(View.GONE);
                } else {
                    mViewStubContentView.setVisibility(View.VISIBLE);
                }
            }
        });
//        viewstub 加载显示出来
        mViewStubContentView2 = mViewStub2.inflate();
//        java.lang.IllegalStateException: ViewStub must have a non-null ViewGroup viewParent
        // mViewStubContentView2 = mViewStub2.inflate();
        findViewById(R.id.display2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*        "也就是说，一旦调用inflate上面的方法后ViewStub就会变成null了，因此使用该对象特别需要注意空指针问题。"
       上面这个说法好矛盾呀，viewStub2 还好好的可以用呀。
                */
                mViewStub2.setVisibility(View.GONE);
            }
        });


        findViewById(R.id.display3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewStub2.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.display4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ViewStub viewStub3 = findViewById(R.id.style_2);
//                    Attempt to invoke virtual method 'android.view.View android.view.ViewStub.inflate()' on a null object reference
                    View mViewStubContentView3 = viewStub3.inflate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


}