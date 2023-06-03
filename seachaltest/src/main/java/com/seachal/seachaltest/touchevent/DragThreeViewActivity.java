package com.seachal.seachaltest.touchevent;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

import java.util.Random;

public class DragThreeViewActivity extends AppCompatActivity {
    private static final String TAG = "DragTwoViewActivity";

    private TextView tvTitle;




    // Default long press time threshold.  默认的长按时间阈值
    private static final long LONG_PRESS_TIME_THRESHOLD = 500;
    // Long press event message handler.   长按事件消息处理程序。
    private Handler mHandler = new Handler();
    // The long press time threshold.  长按时间阈值。
    private long mPressTimeThreshold;
    // Record start point and end point to judge whether user has moved while performing long press event.
//     记录起点和终点以判断用户是否在执行长按事件时移动。
    private DragThreeViewActivity.DoublePoint mTouchStartPoint = new DragThreeViewActivity.DoublePoint();
    private DragThreeViewActivity.DoublePoint mTouchEndPoint = new DragThreeViewActivity.DoublePoint();
    // The long press thread.   长按线程。
    private final DragThreeViewActivity.LongPressThread mLongPressThread = new DragThreeViewActivity.LongPressThread();
    // Inset in pixels to look for touchable content when the user touches the edge of the screen.
//    当用户触摸屏幕边缘时，插入像素以查找可触摸内容。
    private final float mTouchSlop = 10f;
    // The long press callback.  长按回调。
    private DragThreeViewActivity.OnLongPressListener listener;



    /**
     * Reset the long press event. 重置长按事件。
     */
    private void resetLongPressEvent() {
        if (mLongPressThread.mAdded) {
            mHandler.removeCallbacks(mLongPressThread);
            mLongPressThread.mAdded = false;
        }
        mLongPressThread.mLongPressing = false;
        Log.d(TAG, "onTouch: 长按事件取消了");
    }

    /**
     * Add long press event handler. 添加长按事件处理程序。
     */
    private void addLongPressCallback() {
        if (!mLongPressThread.mAdded) {
            mLongPressThread.mLongPressing = false;
//             发送一个延迟的消息，以便在长按时间阈值之后激活长按事件。
            mHandler.postDelayed(mLongPressThread, mPressTimeThreshold);
            mLongPressThread.mAdded = true;
        }
    }

    /**
     * Calculate distance between two point. 计算两点之间的距离。
     *
     * @param before previous point
     * @param after next point
     * @return the distance
     */
    private double calculateDistanceBetween(DragThreeViewActivity.DoublePoint before, DragThreeViewActivity.DoublePoint after) {
        return Math.sqrt(Math.pow((before.x - after.x), 2) + Math.pow((before.y - after.y), 2));
    }

    /**
     * Judge whether the long press event happens.
     * // 判断长按事件是否发生。
     *
     * The time threshold of default activated event is {@see DragThreeViewActivity#LONG_PRESS_TIME_THRESHOLD}
     *  // 默认激活事件的时间阈值为{@see DragThreeViewActivity#LONG_PRESS_TIME_THRESHOLD}
     */
    private static class LongPressThread implements Runnable {
        // A flag to set whether the long press event happens.
//        一个标志，用于设置长按事件是否发生。
        boolean mLongPressing = false;
        // A flag to set whether this thread has been added to the handler.  一个标志，用于设置此线程是否已添加到处理程序。

        boolean mAdded = false;

        @Override
        public void run() {
            mLongPressing = true;
            if (mLongPressing && mAdded){
                Log.d(TAG, " onTouch 长按事件被激活");
            }
        }
    }

    public interface OnLongPressListener {
        boolean onLongPressed(MotionEvent event);
    }

    class DoublePoint {
        public double x;
        public double y;

        public void set(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_two_view);
        initView();
    }

    //按下和移动时的时间，用于判断是否是长按事件
    long timeDown, timeMove;
    //是否是长按事件
    boolean isLongClick;
    //移动相关
    float downX, downY, moveX, moveY;

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        mPressTimeThreshold = LONG_PRESS_TIME_THRESHOLD;


        tvTitle.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: ACTION_DOWN");
                   /*     mTouchStartPoint.set(event.getRawX(), event.getRawY());
                        addLongPressCallback();*/
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "onTouch: ACTION_MOVE");
                        mTouchEndPoint.set(event.getRawX(), event.getRawY());
                        // If user is pressing and dragging, then we make a callback. 如果用户按下并拖动，那么我们就回调。
                        if (mLongPressThread.mLongPressing) {
                            if (listener != null) {
                                return listener.onLongPressed(event);
                            }
                            break;
                        }
                        // If user has moved before activating long press event, then the event should be reset.
//                如果用户在激活长按事件之前移动了，那么事件应该被重置。
                        if (calculateDistanceBetween(mTouchStartPoint, mTouchEndPoint) > mTouchSlop) {
                            resetLongPressEvent();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "onTouch: ACTION_UP");
                        if (mLongPressThread.mLongPressing) {
                            resetLongPressEvent();
                            // Must set true and left the child know we have handled this event.
//                    必须设置为true并留下子节点知道我们已经处理了此事件。
                            return true;
                        }
                    default:
                        resetLongPressEvent();
                        break;
                }
//                 如果这里 return  false，  就收不到 move up事件。
                return true;
            }
        });
    }

    /**
     * 震动
     */


    /**
     * 获取一条随机字符串
     *
     * @param length
     * @return
     */
    public String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        Log.e(TAG, "getRandomString: " + sb.toString());
        return sb.toString();
    }
}