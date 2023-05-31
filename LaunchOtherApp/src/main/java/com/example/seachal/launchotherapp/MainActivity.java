package com.example.seachal.launchotherapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * https://blog.csdn.net/qq475703980/article/details/78941370
 * <p>
 * https://blog.csdn.net/hust_twj/article/details/73477454
 * <p>
 * https://www.jianshu.com/p/42ae7066f8f3
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * 启动另一个 app 的 activity.
     * 启动另一个 app 的 default activity， 主 activity，不能灵活指定 activity
     *
     * @param view
     */
    public void startOtherAppActivity1(View view) {
        Intent intent = new Intent();
        //这里是采用的自定义action
        intent.setAction("com.example.seachal.launchedapp.app");
        startActivity(intent);
    }


    /**
     * @Author zhangxc
     * @Description //TODO   启动另一个 指定 activity，
     * @Date 09:47 2023/5/16
     *
     * @return * @return null
     **/
    /**
     * @param view
     */
    public void startOtherAppActivity2(View view) {
        ComponentName componetName = new ComponentName(
                "com.example.seachal.launchedapp",  //这个是另外一个应用程序的包名
                "com.example.seachal.launchedapp.Main2Activity");   //这个参数是要启动的Activity的全路径名

        try {
            Intent intent = new Intent();
            intent.setComponent(componetName);
            intent.putExtra("wangluo", "这个参数是要启动的Activity的全路径名");
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }

     /**
      * @Author zhangxc
      * @Description //TODO  启动另一个 app 的指定 app.
      * @Date 09:48 2023/5/16
      *
      * @return * @return null
      **/
    /**
     * @param view
     */
    public void startOtherAppActivity22(View view) {
        ComponentName componetName = new ComponentName(
                "com.example.seachal.launchedapp",  //这个是另外一个应用程序的包名
                "com.example.seachal.launchedapp.Main2Activity");   //这个参数是要启动的Activity的全路径名

        try {
            Intent intent = new Intent();
            intent.setComponent(componetName);
            intent.putExtra("wangluo", "这个参数是要启动的Activity的全路径名");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            GlobalLication.getContext().startActivity(intent);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }





    //   使用 scheme 启动另一个 app.
    //       被启动 app 的 androidmanifest 中 activity 中要指定的数据， <data 数据。 ,
    public void startOtherAppActivity3(View view) {
        Uri uri = Uri.parse("app://my.test");// 对应被启动的 Main3Activity
        Intent intent = new Intent("com.example.seachal.launchedapp.app", uri);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }

    //    与第二种启动方式类似。
    public void startOtherAppActivity4(View view) {
        try {
            Intent intent = new Intent();
            //第二种方式
            intent.setClassName("com.example.seachal.launchedapp",  //这个是另外一个应用程序的包名
                    "com.example.seachal.launchedapp.Main4Activity");
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }

    //    与第二种启动方式类似。
    public void startOtherAppActivity42(View view) {
        try {
            Intent intent = new Intent();
            //第二种方式
            intent.setClassName("com.example.seachal.launchedapp",  //这个是另外一个应用程序的包名
                    "com.example.seachal.launchedapp.Main4Activity");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            GlobalLication.getContext().startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }







    /**
     * @param view
     * 通过包名启动  app.
     */
    public void startOtherAppActivity23(View view) {
        String packname = "com.example.seachal.launchedapp";
        PackageManager packageManager = getPackageManager();
        if (checkPackInfo(packname)) {
            Intent intent = packageManager.getLaunchIntentForPackage(packname);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "没有安装" + packname, Toast.LENGTH_LONG).show();
        }
    }


    /**
     * @param view
     * 通过包名启动  app.
     */
    public void startOtherAppActivity5(View view) {
        ComponentName componetName = new ComponentName(
                "com.example.seachal.launchedapp",  //这个是另外一个应用程序的包名
                "com.example.seachal.launchedapp.Main5Activity");   //这个参数是要启动的Activity的全路径名

        try {
            Intent intent = new Intent();
            intent.setComponent(componetName);
            intent.putExtra("wangluo", "这个参数是要启动的Activity的全路径名");
            intent.setFlags(Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * @param view
     *
     * 通过包名打开百度
     */
    public void startOtherAppActivity6(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

//        String tbPath="https://item.taobao.com/item.htm?spm=a1z0d.6639537.1997196601.14.45d07484uw9hPZ&id=565080000925";
 String tbPath= "http://www.baidu.com";
        intent.setData(Uri.parse(tbPath));
        startActivity(intent);
    }




    /**
     * @param view
     * 通过包名打开  淘宝
     */
    public void startOtherAppActivity61(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        String tbPath="https://item.taobao.com/item.htm?spm=a1z0d.6639537.1997196601.14.45d07484uw9hPZ&id=565080000925";

        //        String tbPath= "http://www.baidu.com";
//        String tbPath="http://item.taobao.com/&";

        intent.setData(Uri.parse(tbPath));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }





//     scheme 启动另一个 app.
    //      yc://ycbjie.cn:8888/from?type=yangchong ,   被启动 app 的 androidmanifest 中 activity 中要指定的数据， <data 数据。 ,
    public void startOtherAppActivityYc(View view) {
        Uri uri = Uri.parse("yc://ycbjie.cn:8888/from?type=yangchong");// 对应被启动的 Main3Activity
        Intent intent = new Intent("com.example.seachal.launchedapp.app", uri);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }

    public void startOtherAppActivity12_1(View view) {

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sc://seachal.me/native/?targetType=xxx&params=\"{\"key1\": \"value1\", \"key2\": \"value2\"}\""));
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sc://seachal.me"));

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("xkw://train/native/?targetType=172&params={\"courseId\":\"978593720889311232\",\"chapterId\": \"978597518969995264\"}"));


        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 不然新 app 会和被启动的 app 在一个栈里。
        List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        Toast.makeText(this,isValid+"",Toast.LENGTH_LONG).show();

        try {
            if (isValid) {
                startActivity(intent);
            } else {
                Toast.makeText(this,"没有安装",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }

    public void startOtherAppActivity12_2(View view) {

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sc://seachal.me/native/?targetType=xxx&params=\"{\"key1\": \"value1\", \"key2\": \"value2\"}\""));
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sc://seachal.me"));

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("xkw://train/native/?targetType=172&params={\"courseId\":\"978593720889311232\",\"chapterId\": \"978596699155529728\"}"));


        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 不然新 app 会和被启动的 app 在一个栈里。
        List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        Toast.makeText(this,isValid+"",Toast.LENGTH_LONG).show();

        try {
            if (isValid) {
                startActivity(intent);
            } else {
                Toast.makeText(this,"没有安装",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }


    public void startOtherAppActivity13_1(View view) {
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sc://seachal.me/macthDetail?macthId=222&time=10001"));

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sc://seachal.me/macthDetail?targetType=xxx&params=\"{\"key1\": \"value1\", \"key2\": \"value2\"}\""));

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("xkw://train/native/?targetType=172&params={\"courseId\":\"980140202226417664\",\"chapterId\": \"981936664908201984\"}"));

        List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        Toast.makeText(this,isValid+"",Toast.LENGTH_LONG).show();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            if (isValid) {
                startActivity(intent);
            } else {
                Toast.makeText(this,"没有安装",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }


    public void startOtherAppActivity13_2(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("xkw://train/native/?targetType=172&params={\"courseId\":\"980140202226417664\",\"chapterId\": \"981936664908201984\"}"));
        List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        Toast.makeText(this,isValid+"",Toast.LENGTH_LONG).show();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            if (isValid) {
                startActivity(intent);
            } else {
                Toast.makeText(this,"没有安装",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "可以在这里提示用户没有找到应用程序，或者是做其他的操作！", Toast.LENGTH_LONG).show();
        }
    }






    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }






}
