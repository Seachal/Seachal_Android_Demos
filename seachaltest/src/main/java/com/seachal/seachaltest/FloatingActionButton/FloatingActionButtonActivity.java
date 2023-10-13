package com.seachal.seachaltest.FloatingActionButton;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.seachal.seachaltest.R;

public class FloatingActionButtonActivity extends AppCompatActivity {
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        rl = findViewById(R.id.rl_floating_button);
        FloatingActionButton button = findViewById(R.id.floating_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FabAttributes announce = new FabAttributes.Builder().setBackgroundTint(Color.parseColor("#FFFFFF")).setSrc(getResources().getDrawable(R.drawable.go_back)).setFabSize(FloatingActionButton.SIZE_MINI).setPressedTranslationZ(0).build();

                FabAttributes rtc = new FabAttributes.Builder().setBackgroundTint(Color.parseColor("#FFFFFF")).setSrc(getResources().getDrawable(R.drawable.go_back)).setFabSize(FloatingActionButton.SIZE_MINI).setPressedTranslationZ(0).build();

                FabAttributes chat = new FabAttributes.Builder().setBackgroundTint(Color.parseColor("#FFFFFF")).setSrc(getResources().getDrawable(R.drawable.go_back)).setFabSize(FloatingActionButton.SIZE_MINI).setPressedTranslationZ(0).build();

                addFab(announce, rtc, chat);
            }
        });

    }


    /**
     * 添加按钮
     *
     * @param attrs 按钮的参数
     *              添加的默认按钮显示在容器的最上方，后面添加的依次叠加在默认按钮的下方
     */
    public void addFab(FabAttributes... attrs) {
        if (attrs != null && attrs.length != 0) {
            for (FabAttributes attr : attrs) {
                FabAttributes.Builder b = attr.getBuilder();
                FloatingActionButton fab = new FloatingActionButton(FloatingActionButtonActivity.this);
//                fab.setOnClickListener(this);
                setVisible(fab, true);
                setAttributes(fab, b);
                //后面添加的按钮一直放在第一个位置。  在openAnimate（）中会按照添加的顺序展开
                rl.addView(fab, 0);
            }
        }
    }

    /**
     * 设置view的显示隐藏
     *
     * @param view      按钮
     * @param isVisible 是否显示
     */
    private void setVisible(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 设置按钮属性
     *
     * @param fab 按鈕
     * @param b   按鈕的参数
     */
    private void setAttributes(FloatingActionButton fab, FabAttributes.Builder b) {
        fab.setTag(b.tag);
        fab.setSize(b.fabSize);
        fab.setImageDrawable(b.src);
        fab.setRippleColor(b.rippleColor);
        fab.setScaleType(ImageView.ScaleType.CENTER);
        fab.setBackgroundTintList(ColorStateList.valueOf(b.backgroundTint));
  // 增加 margin ，解决 start end 的投影没裁剪的问题。
        // 获取子视图的布局参数
        RelativeLayout.LayoutParams layoutParams =       layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
// 设置margin
        int marginInPixels = getResources().getDimensionPixelSize(R.dimen.dp_px_10);
        
        layoutParams.setMargins(marginInPixels, marginInPixels, marginInPixels, marginInPixels);
// 应用新的布局参数
        fab.setLayoutParams(layoutParams);
        //  设置投影，但是投影被裁剪了。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setElevation(dip2px(b.elevation));
            fab.setTranslationZ(dip2px(b.pressedTranslationZ));
        }
    }


    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = FloatingActionButtonActivity.this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}