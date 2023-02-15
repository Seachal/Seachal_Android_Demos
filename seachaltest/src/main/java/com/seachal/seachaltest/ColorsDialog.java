package com.seachal.seachaltest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


public class ColorsDialog extends Dialog
        implements View.OnClickListener {

    private final Context context;



    private boolean isSize = false;

    public ColorsDialog(Context context){
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.colors_dialog);




        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        getWindow().setGravity(Gravity.BOTTOM);


        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (d.getWidth() * 0.75);
        // 设置背景层透明度
        lp.dimAmount = 0.3f;
        getWindow().setAttributes(lp);
        getWindow().setWindowAnimations( R.style.train_search_filter_anim);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
            dismiss();
            return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {

    }
}
