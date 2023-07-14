package com.seachal.seachaltest.intentflag;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

import java.util.List;

public class LaunchOtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_other);

        TextView textView = findViewById(R.id.tv_1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOtherAppActivity1(v);
            }
        });
    }



    public void startOtherAppActivity1(View view) {
       Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sc://seachal.me/macthDetail?macthId=222&time=10001"));
        List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        Toast.makeText(this,isValid+"",Toast.LENGTH_LONG).show();
//        如果不加 new Task,   启动 App与被启动 App 会在同一个 Task 中,  会有一些问题,  比如,  从被启动 App 返回时,  会回到 App 的首页。
//        除了在这里用代码设置 Flag,  还可以在 AndroidManifest.xml 中设置,  但是,  两者不能同时设置,  否则会有冲突。
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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

    public void startOtherAppActivity2(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sc://seachal.me/macthDetail?macthId=222&time=10001"));
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

}