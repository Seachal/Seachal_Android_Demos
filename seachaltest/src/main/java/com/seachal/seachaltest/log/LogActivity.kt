package com.seachal.seachaltest.log

import android.content.Intent
import android.content.res.Configuration
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 **
 * *
 * Project_Name:Seachal_Android_Demos
 * @Description: TODO
 * @author zhangxc
 * @date 2024/1/2 11:11
 * @Version：1.0
 */
open class LogActivity : AppCompatActivity() {

//  TimerActivity 生命周期

    protected var TAG = javaClass.simpleName


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState-->${outState.toString()}")
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG, "onViewStateRestored-->${newConfig.toString()}")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy ")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent-->${intent.toString()}")
    }

}