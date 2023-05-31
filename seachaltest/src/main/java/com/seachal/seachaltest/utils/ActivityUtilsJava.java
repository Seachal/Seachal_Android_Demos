package com.seachal.seachaltest.utils;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 *
 * @author zhangxc
 * @Description: TODO
 * @date 2023/5/30 14:56
 * @Version：1.0
 */
public class ActivityUtilsJava {


    public static List<Activity> getAllActivitys(){
        List<Activity> list=new ArrayList<>();
        try {
            Class<?> activityThread=Class.forName("android.app.ActivityThread");
            Method currentActivityThread=activityThread.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            //获取主线程对象
            Object activityThreadObject=currentActivityThread.invoke(null);
            Field mActivitiesField = activityThread.getDeclaredField("mActivities");
            mActivitiesField.setAccessible(true);
            Map<Object,Object> mActivities = (Map<Object,Object>) mActivitiesField.get(activityThreadObject);
            for (Map.Entry<Object,Object> entry:mActivities.entrySet()){
                Object value = entry.getValue();
                Class<?> activityClientRecordClass = value.getClass();
                Field activityField = activityClientRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Object o = activityField.get(value);
                list.add((Activity) o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
