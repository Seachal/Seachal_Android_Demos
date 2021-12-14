//package com.seachal.seachaltest.android_time;
//
//import android.icu.text.SimpleDateFormat;
//import android.os.SystemClock;
//import android.text.TextUtils;
//
//import java.util.Date;
//import java.util.Locale;
//import java.util.TimeZone;
//
///**
// * *
// * *
// * Project_Name:SeachalDemos
// * [(8条消息)Android 客户端与服务器端时间校准_wuyou1336的博客-CSDN博客_android 从请求头中取的服器时间](https://blog.csdn.net/wuyou1336/article/details/52764533)
// * @author zhangxc
// * @date 2020/8/17 10:39
// * *
// */
//public  class ServerTimeUtils {
//
//    public static void getServerTimeDelta(ResponseInfo<String> responseInfo){
//        if (responseInfo != null) {
//            Header headers = responseInfo.getFirstHeader("Date");
//            String strServerDate = headers.getValue();
//            if (!TextUtils.isEmpty(strServerDate)){
//                //Thu, 29 Sep 2016 07:57:42 GMT
//                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z",
//                        Locale.ENGLISH);
//                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
//                try {
//                    Date serverDate  = simpleDateFormat.parse(strServerDate);
//
//                    GeexekApplication.ResponseTime = serverDate.getTime();
////    elapsedRealtime 表示系统开机到当前的时间总数。它包括了系统深度睡眠的时间。这个时钟是单调的，它保证一直计时，即使CPU处于省电模式，所以它是推荐使用的时间计时器。
//                    GeexekApplication.ResponseCurrentTime = SystemClock.elapsedRealtime();
//
//                }catch(Exception exception){
//                    exception.printStackTrace();
//                }
//            }
//        }
//    }
//}
