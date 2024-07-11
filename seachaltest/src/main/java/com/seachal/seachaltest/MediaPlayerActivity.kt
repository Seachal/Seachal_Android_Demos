package com.seachal.seachaltest

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

/**
 * Media player activity
 * [Android 媒体播放器无法通过 HTTP 播放声音文件 - 堆栈溢出](https://stackoverflow.com/questions/60538374/android-media-player-does-not-play-sound-files-over-http)
 * @constructor Create empty Media player activity
 */
class MediaPlayerActivity : AppCompatActivity() {
//        val url =  "http://seachal-blog-picture-host.oss-cn-beijing.aliyuncs.com/%E8%BF%99%E6%98%AF%E4%B8%80%E6%AE%B5%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%EF%BC%8C%E4%B8%BA%E4%BA%86%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%E7%BB%93%E6%9D%9F%E5%90%8E%E5%8F%AF%E4%BB%A5%E9%87%8D%E6%96%B0%E6%92%AD%E6%94%BE.MP3" // your URL here
//


    // 这是一段较短的音频 总共十几秒。
    var test2: String ="http://seachal-blog-picture-host.oss-cn-beijing.aliyuncs.com/%E8%BF%99%E6%98%AF%E4%B8%80%E6%AE%B5%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%EF%BC%8C%E4%B8%BA%E4%BA%86%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%E7%BB%93%E6%9D%9F%E5%90%8E%E5%8F%AF%E4%BB%A5%E9%87%8D%E6%96%B0%E6%92%AD%E6%94%BE.MP3"


    // 这是一段较长超过一分钟的音频
    var test3: String =
        "http://seachal-blog-picture-host.oss-cn-beijing.aliyuncs.com/%E4%B8%80%E6%AE%B5%E8%BE%83%E9%95%BF%E7%9A%84%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%EF%BC%8C%E5%A4%A7%E4%BA%8E%201%20%E5%88%86%E9%92%9F7%E6%9C%885%E6%97%A5.MP3"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
        val textViewOut1: TextView = findViewById<TextView>(R.id.textView_out1)

        textViewOut1.setOnClickListener {
            val mediaPlayer = MediaPlayer()
            try {
////                mediaPlayer.setDataSource("http://seachal-blog-picture-host.oss-cn-beijing.aliyuncs.com/%E8%BF%99%E6%98%AF%E4%B8%80%E6%AE%B5%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%EF%BC%8C%E4%B8%BA%E4%BA%86%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%E7%BB%93%E6%9D%9F%E5%90%8E%E5%8F%AF%E4%BB%A5%E9%87%8D%E6%96%B0%E6%92%AD%E6%94%BE.MP3")
//                mediaPlayer.setDataSource("http://144.7.95.184/amobile.music.tc.qq.com/C400002aaKRV0WZN3B.m4a?guid=922344758&vkey=F40244D56FA84F68CD63C63FBD344D72A7BF61571470811DF42BC2D7E8512B40CF1316D635D6F23B818ED53EDF7E951B964A9DD4E0FF01E1&uin=&fromtag=120032&src=C400000CNu2L3uzItY.m4a")
//                mediaPlayer.prepareAsync()// 同步准备
//                mediaPlayer.start()

                val url =  "http://seachal-blog-picture-host.oss-cn-beijing.aliyuncs.com/%E8%BF%99%E6%98%AF%E4%B8%80%E6%AE%B5%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%EF%BC%8C%E4%B8%BA%E4%BA%86%E6%B5%8B%E8%AF%95%E9%9F%B3%E9%A2%91%E7%BB%93%E6%9D%9F%E5%90%8E%E5%8F%AF%E4%BB%A5%E9%87%8D%E6%96%B0%E6%92%AD%E6%94%BE.MP3" // your URL here
                val mediaPlayer = MediaPlayer()
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mediaPlayer.setDataSource(test3)
                mediaPlayer.prepare() // might take long! (for buffering, etc)
                mediaPlayer.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}
