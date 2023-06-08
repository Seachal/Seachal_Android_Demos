package com.seachal.seachaltest.touchevent;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.oguzdev.circularfloatingactionmenu.library.LongPressHandler;
import com.seachal.seachaltest.R;

import java.util.Random;

public class DragTwoViewActivity extends AppCompatActivity {
    private static final String TAG = "DragTwoViewActivity";

    private TextView tvTitle;
    private LongPressHandler longPressHandler;

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
        longPressHandler = new LongPressHandler(tvTitle);
        longPressHandler.setOnLongPressListener(new LongPressHandler.OnLongPressListener() {
            @Override
            public boolean onLongPressed(MotionEvent event) {
                Log.d("longPressHandler", "onTouch: ACTION_MOVE onLongPressed");
              /*  switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        int x = (int) event.getRawX();
                        int y = (int) event.getRawY();
                        return true;
                }*/
                return false;
            }

            @Override
            public boolean onLongPressedUp(MotionEvent event) {
                Log.d("longPressHandler", "onTouch: ACTION_UP  onLongPressedUp");
                return false;
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