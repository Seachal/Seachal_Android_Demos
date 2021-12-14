package com.seachal.seachaltest;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2020/11/17 10:38
 * *
 */
public class BaseApp  extends MultiDexApplication {

    private  static Context context;

    public  static  Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context =  getApplicationContext();
    }
}
