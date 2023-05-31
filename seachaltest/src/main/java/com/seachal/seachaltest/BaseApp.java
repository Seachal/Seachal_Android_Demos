package com.seachal.seachaltest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.multidex.MultiDexApplication;

import java.util.Stack;

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

//  帮我写完   registerActivityLifecycleCallbacks


//    Activity 栈
    public  static  Stack<Activity> stack = new Stack<>();

    public  static  Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context =  getApplicationContext();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                //可以在这里add
                stack.push(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //可以在这里remove
                stack.remove(activity);
            }
        });
    }
}
