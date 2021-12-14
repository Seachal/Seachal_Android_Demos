package com.example.seachal.launchedapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;


/**
 * author : Peppa
 * date   : 2019/2/14 16:14
 * desc   :
 */

public class GlobalLication extends Application {


    private static Context context;

//    public static String APP_ID = Constant.WECHAT_APPID;
//    public static String APP_SECRET = Constant.WECHAT_APPSECRET;

    public static Context getContext() {
        return context;
    }




    @Override
    public void onCreate() {
        super.onCreate();


        context = getApplicationContext();
        Log.i("GlobalLication","launchedApp");

    }





}
