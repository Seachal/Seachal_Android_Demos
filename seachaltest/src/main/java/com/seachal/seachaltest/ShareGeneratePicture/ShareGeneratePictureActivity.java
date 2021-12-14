package com.seachal.seachaltest.ShareGeneratePicture;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2019-09-17 11:41
 * *
 */
public class ShareGeneratePictureActivity  extends AppCompatActivity {



    private ImageView iv_picture;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_share_view_layout);
        iv_picture = findViewById(R.id.iv_picture);
    }


    public void createShareImage(View view) {
        ShareView shareView = new ShareView(ShareGeneratePictureActivity.this);
//        iv_picture.getba

//        shareView.setIvPicture();

        shareView.setInfo("其他信息其他");

        final Bitmap image = shareView.createImage();
        final String path = saveImage(image);
        Log.e("xxx", path);

        if (image != null && !image.isRecycled()) {
            image.recycle();
        }
    }

    /**
     * 保存bitmap到本地
     *
     * @param bitmap
     * @return
     */
    private String saveImage(Bitmap bitmap) {

        File path = getCacheDir();

        String fileName = "shareImage.png";
        File file = new File(path, fileName);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(this,"保存图片",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }
}
