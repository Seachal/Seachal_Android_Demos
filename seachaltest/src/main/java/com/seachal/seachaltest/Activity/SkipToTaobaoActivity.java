package com.seachal.seachaltest.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.seachal.seachaltest.R;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2019-11-08 16:32
 * *
 */
public class SkipToTaobaoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Button button = findViewById(R.id.btn_skip_to_tobao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPackage("com.taobao.taobao")) {
                    //测试商品url
                    String url = "https://item.taobao.com/item.htm?spm=a1z10.1-c-s.w13749380-17445896657.1.4277c9d6qNQCOD&id=559827840919&_u=tcg2dgree0c";
                    Intent intent = new Intent();
                    intent.setAction("Android.intent.action.VIEW");
                    Uri uri = Uri.parse(url); // 商品地址
                    intent.setData(uri);
                    intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
                    startActivity(intent);
                } else {
                    /**
                     * 可以使用webView进行打开
                     */
                    ToastUtils.showShort("请下载淘宝app在进行商品的购买!");
                }
            }
        });
    }


    public boolean checkPackage(String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            this.getPackageManager().getApplicationInfo(packageName, PackageManager
                    .GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }




    void totianmao( String tbPath){
        Intent intent = new Intent();
        intent.setAction("Android.intent.action.VIEW");
        Uri uri = Uri.parse(tbPath); // 商品地址
        intent.setData(uri);
        intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
        startActivity(intent);
    }

    void toshop(String tbPath){

//        String tbPath="https://detail.tmall.com/item.htm?spm=a1z0d.6639537.1997196601.3.45d07484uw9hPZ&id=565570128470";
        Intent intent = new Intent();
        intent.setAction("Android.intent.action.VIEW");
        Uri uri = Uri.parse(tbPath); // 商品地址
        intent.setData(uri);
        intent.setClassName("com.taobao.taobao", "com.taobao.android.shop.activity.ShopHomePageActivity");
        startActivity(intent);
    }
}
