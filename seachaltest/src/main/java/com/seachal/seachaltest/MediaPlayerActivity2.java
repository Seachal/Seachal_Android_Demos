package com.seachal.seachaltest;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 *  [Android 媒体播放器无法通过 HTTP 播放声音文件 - 堆栈溢出](https://stackoverflow.com/questions/60538374/android-media-player-does-not-play-sound-files-over-http)
 * @author zhangxc
 * @Description: TODO
 * @date 2024/7/11 11:21
 * @Version：1.0
 */
public class MediaPlayerActivity2 extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    // 网络音频URL
    String audioUrl = "http://seachal-blog-picture-host.oss-cn-beijing.aliyuncs.com/%E8%BF%99%E6%98%AF%E4%B8%80%E6%AE%B5%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%EF%BC%8C%E4%B8%BA%E4%BA%86%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%E7%BB%93%E6%9D%9F%E5%90%8E%E5%8F%AF%E4%BB%A5%E9%87%8D%E6%96%B0%E6%92%AD%E6%94%BE.MP3";

    // 这是一段较短的音频 总共十几秒。
    String test2  ="http://seachal-blog-picture-host.oss-cn-beijing.aliyuncs.com/%E8%BF%99%E6%98%AF%E4%B8%80%E6%AE%B5%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%EF%BC%8C%E4%B8%BA%E4%BA%86%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%E7%BB%93%E6%9D%9F%E5%90%8E%E5%8F%AF%E4%BB%A5%E9%87%8D%E6%96%B0%E6%92%AD%E6%94%BE.MP3";


    // 这是一段较长超过一分钟的音频
    String test3  =
            "http://seachal-blog-picture-host.oss-cn-beijing.aliyuncs.com/%E4%B8%80%E6%AE%B5%E8%BE%83%E9%95%BF%E7%9A%84%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%EF%BC%8C%E5%A4%A7%E4%BA%8E%201%20%E5%88%86%E9%92%9F7%E6%9C%885%E6%97%A5.MP3";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        TextView textViewOut1 = findViewById(R.id.textView_out1);

        textViewOut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 创建MediaPlayer对象
                mediaPlayer = new MediaPlayer();
                try {
                    // 设置音频流类型
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    // 设置数据源为网络音频URL
                    mediaPlayer.setDataSource(test3);
                    // 准备播放器（异步）
                    mediaPlayer.prepareAsync();
                    // 设置准备监听器
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // 准备完成后，开始播放
                            mediaPlayer.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            // 释放MediaPlayer资源
            mediaPlayer.release();
        }
    }
}
