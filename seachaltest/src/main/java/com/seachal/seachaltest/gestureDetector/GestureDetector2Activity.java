package com.seachal.seachaltest.gestureDetector;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

public class GestureDetector2Activity extends AppCompatActivity {




    TextView mTextView;
    GestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector1);

        // 步骤1：创建手势检测器实例 & 传入OnGestureListener接口（需要复写对应方法）
        mGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            // 1. 用户轻触触摸屏
            public boolean onDown(MotionEvent e) {
                Log.i("MyGesture", "onDown");
                return false;
            }

            // 2. 用户轻触触摸屏，尚未松开或拖动
            // 与onDown()的区别：无松开 / 拖动
            // 即：当用户点击的时，onDown（）就会执行，在按下的瞬间没有松开 / 拖动时onShowPress就会执行
            public void onShowPress(MotionEvent e) {
                Log.i("MyGesture", "onShowPress");
            }

            // 3. 用户长按触摸屏
            public void onLongPress(MotionEvent e) {
                Log.i("MyGesture", "onLongPress");
            }

            // 4. 用户轻击屏幕后抬起
            public boolean onSingleTapUp(MotionEvent e) {
                Log.i("MyGesture", "onSingleTapUp");
                return true;
            }

            // 5. 用户按下触摸屏 & 拖动
            public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                    float distanceX, float distanceY) {
                Log.i("MyGesture", "onScroll:");
                return true;
            }

            // 6. 用户按下触摸屏、快速移动后松开
            // 参数：
            // e1：第1个ACTION_DOWN MotionEvent
            // e2：最后一个ACTION_MOVE MotionEvent
            // velocityX：X轴上的移动速度，像素/秒
            // velocityY：Y轴上的移动速度，像素/秒
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                Log.i("MyGesture", "onFling");
                return true;
            }

        });

        // 步骤2：创建 & 设置OnDoubleTapListener接口实现类
        mGestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {

            // 1. 单击事件
            // 关于OnDoubleTapListener.onSingleTapConfirmed（）和 OnGestureListener.onSingleTapUp()的区别
            // onSingleTapConfirmed：再次点击（即双击），则不会执行
            // onSingleTapUp：手抬起就会执行
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.i("MyGesture", "onSingleTapConfirmed");
                return false;
            }

            // 2. 双击事件
            public boolean onDoubleTap(MotionEvent e) {
                Log.i("MyGesture", "onDoubleTap");
                return false;
            }
            // 3. 双击间隔中发生的动作
            // 指触发onDoubleTap后，在双击之间发生的其它动作，包含down、up和move事件；
            public boolean onDoubleTapEvent(MotionEvent e) {
                Log.i("MyGesture", "onDoubleTapEvent");
                return false;
            }
        });


        // 步骤2：让TextView检测手势：重写View的onTouch函数，将触屏事件交给GestureDetector处理，从而对用户手势作出响应
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
//                return true;表示该事件已经被消费，不会再向下传递，
//                return false;表示该事件没有被消费，会再向下传递，这里返回false，会再向下传递给Activity的onTouchEvent()方法
                return false;
            }
        });
    }
}