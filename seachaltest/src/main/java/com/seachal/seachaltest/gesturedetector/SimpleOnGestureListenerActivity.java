package com.seachal.seachaltest.gesturedetector;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

/*
 * @Author zhangxc
 * @Description //TODO  OnDoubleTapListener
 * @Date 18:30 2023/5/15
 *
 * @return * @return null
 **/

public class SimpleOnGestureListenerActivity extends AppCompatActivity {


    TextView mTextView;
    GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector1);
        getSupportActionBar().setTitle(this.getClass().getSimpleName());
        // 步骤1：创建手势检测器实例 & 传入OnGestureListener接口（需要复写对应方法）
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            // OnGestureListener接口的函数
            // 1. 用户轻触触摸屏
            public boolean onDown(MotionEvent e) {
                Log.i("ScGesture1", "onDown");
                return false;
            }

            // 2. 用户轻触触摸屏，尚未松开或拖动
            // 与onDown()的区别：无松开 / 拖动
            // 即：当用户点击的时，onDown（）就会执行，在按下的瞬间没有松开 / 拖动时onShowPress就会执行
            public void onShowPress(MotionEvent e) {
                Log.i("ScGesture", "onShowPress");
            }

            // 3. 用户长按触摸屏
            public void onLongPress(MotionEvent e) {
                Log.i("ScGesture", "onLongPress");
            }

            // 4. 用户轻击屏幕后抬起
            public boolean onSingleTapUp(MotionEvent e) {
                Log.i("ScGesture", "onSingleTapUp");
                return true;
            }

            // 5. 用户按下触摸屏 & 拖动
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.i("ScGesture", "onScroll:");
                return true;
            }

            // 6. 用户按下触摸屏、快速移动后松开
            // 参数：
            // e1：第1个ACTION_DOWN MotionEvent
            // e2：最后一个ACTION_MOVE MotionEvent
            // velocityX：X轴上的移动速度，像素/秒
            // velocityY：Y轴上的移动速度，像素/秒
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("ScGesture", "onFling");
                return true;
            }

            // OnDoubleTapListener的函数
            // 1. 单击事件
            // 关于OnDoubleTapListener.onSingleTapConfirmed（）和 OnGestureListener.onSingleTapUp()的区别
            // onSingleTapConfirmed：再次点击（即双击），则不会执行
            // onSingleTapUp：手抬起就会执行
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.i("ScGesture", "onSingleTapConfirmed");
                return false;
            }

            // 2. 双击事件
            public boolean onDoubleTap(MotionEvent e) {
                Log.i("ScGesture", "onDoubleTap");
                return false;
            }

            // 3. 双击间隔中发生的动作
            // 指触发onDoubleTap后，在双击之间发生的其它动作，包含down、up和move事件；
            public boolean onDoubleTapEvent(MotionEvent e) {
                Log.i("ScGesture", "onDoubleTapEvent");
                return false;
            }
        });

        // 步骤2：重写View的onTouch函数，将触屏事件交给GestureDetector处理，从而对用户手势作出响应
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

//    // 步骤2-2：让某个Activity检测手势：重写Activity的dispatchTouchEvent函数，将触屏事件交给GestureDetector处理，从而对用户手势作出响应
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        mGestureDetector.onTouchEvent(ev); // 让GestureDetector响应触碰事件
//        super.dispatchTouchEvent(ev); // 让Activity响应触碰事件
//        return false;
//    }
}