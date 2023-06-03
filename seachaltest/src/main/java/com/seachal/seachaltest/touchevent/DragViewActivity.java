package com.seachal.seachaltest.touchevent;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;

import java.util.Random;

// activity_drag_view

// activity_drag_view
/**
 * @Author zhangxc
 * @Description //TODO
 * @Date 16:27 2023/6/3
 *  原文链接：https://blog.csdn.net/yu540135101/article/details/103810310
 *
 *  # 这个 demo 达不到我想要的效果。
 *  这个 demo  按下然后移动， 才算是长按。
 *  如果按下不移动， 一直按住，不认为是长按。
 *  通过   long durationMs = timeMove - timeDown; 更新 durationMs 的值。
 *  ---
 *  # 我想达到的效果是，长按 与 长按后拖动都算长按。
 *
 *
 * @return * @return null
 **/
public class DragViewActivity extends AppCompatActivity {
    private static final String TAG = "DragViewActivity";

    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view);
        initView();
    }

    //按下和移动时的时间，用于判断是否是长按事件
    long timeDown; // 按下的时间
    long   timeMove; // 移动时时间
    //是否是长按事件
    boolean isLongClick;
    //移动相关
    float downX, downY, moveX, moveY;

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: ACTION_DOWN");
//                       记录down时间
                        timeDown = System.currentTimeMillis();
//                       初始为非长按事件
                        isLongClick = false;
                        downX = event.getRawX();
                        downY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "onTouch: ACTION_MOVE");
//                       记录 move时间，多次 move 会更新
                        timeMove = System.currentTimeMillis();
                        long durationMs = timeMove - timeDown;
                        //Log.d(TAG, "onTouch: durationMs="+durationMs);
                        if (durationMs > 500) {
                            vibrate();
                            isLongClick = true;
                            Log.d(TAG, "onTouch: isLongClick=" + isLongClick);
                            //长按事件，可以移动
                            moveX = event.getRawX();
                            moveY = event.getRawY();
                            //移动的距离
                            float dx = moveX - downX;
                            float dy = moveY - downY;
                            //重新设置控件的位置。移动
                            ViewGroup.MarginLayoutParams params = (FrameLayout.MarginLayoutParams) tvTitle.getLayoutParams();
                            params.leftMargin += dx;
                            params.topMargin += dy;
                            tvTitle.setLayoutParams(params);
                            //重置
                            downX = moveX;
                            downY = moveY;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "onTouch: ACTION_UP");
                        if (!isLongClick)
                            tvTitle.setText(getRandomString(10));
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 震动
     */
    private void vibrate() {
    /*    if (!isLongClick) {
            Vibrator vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }*/
    }

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

