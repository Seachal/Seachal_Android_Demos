package com.seachal.seachaltest.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

import java.util.List;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2019-11-05 19:45
 * *
 */
public class URITestActivity  extends AppCompatActivity {

    public static final String tag = "URITestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        String mUriStr = "http://www.java2s.com:8080/yourpath/fileName.htm?stove=10&path=32&id=4#harvic";
        String mUriStr1 =    "https://h5.atoshi.cn//productdetail?productId=135&userId=100124026379";

        Log.d("URITestActivity","mUriStr1:"+mUriStr1);
        Uri mUri = Uri.parse(mUriStr1);
        Uri mUri2 = Uri.parse(mUriStr);

        String productId = mUri.getQueryParameter("productId");
        String inviterId = mUri.getQueryParameter("userId");

        Log.d("URITestActivity","mUriStr2:"+productId);
        Log.d("URITestActivity","mUriStr3:"+inviterId);

        List<String> pathSegList = mUri2.getPathSegments();
        for (String pathItem:pathSegList){
            Log.d("qijian","pathSegItem:"+pathItem);
        }



        String mUriStr3 = "http://www.java2s.com:8080/yourpath/fileName.htm?stove=10&path=32&id#harvic";
        mUri = Uri.parse(mUriStr3);
        Log.d(tag,"getQueryParameter(\"stove\"):"+mUri.getQueryParameter("stove"));
        Log.d(tag,"getQueryParameter(\"id\"):"+mUri.getQueryParameter("id"));
        Log.d(tag,"getQueryParameter(\"path\"):"+mUri.getQueryParameter("path"));

    }
}
