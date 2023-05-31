package com.seachal.seachaltest.utils

import android.app.Activity

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
object ActivityUtils {
    //获取主线程对象
    @JvmStatic
    val allActivitys: List<Activity>
        get() {
            val list: MutableList<Activity> = ArrayList()
            try {
                val activityThread = Class.forName("android.app.ActivityThread")
                val currentActivityThread =
                    activityThread.getDeclaredMethod("currentActivityThread")
                currentActivityThread.isAccessible = true
                //获取主线程对象
                val activityThreadObject = currentActivityThread.invoke(null)
                val mActivitiesField = activityThread.getDeclaredField("mActivities")
                mActivitiesField.isAccessible = true
                val mActivities = mActivitiesField[activityThreadObject] as Map<Any, Any>
                for ((_, value) in mActivities) {
                    val activityClientRecordClass: Class<*> = value.javaClass
                    val activityField = activityClientRecordClass.getDeclaredField("activity")
                    activityField.isAccessible = true
                    val o = activityField[value]
                    list.add(o as Activity)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return list
        }
}