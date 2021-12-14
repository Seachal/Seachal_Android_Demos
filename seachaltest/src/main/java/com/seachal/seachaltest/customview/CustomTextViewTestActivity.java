package com.seachal.seachaltest.customview;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seachal.seachaltest.R;


/**
 * TextView setText 的时候，onMeasure,会被调用吗?
 * <p>
 * 会被调用
 */
public class CustomTextViewTestActivity extends AppCompatActivity {

    private TextView textView;

    private TextView tvPrice1;
    private TextView tvPrice2;
    private TextView tvPrice3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_text_view_test);
        textView = findViewById(R.id.tv_measure);
        tvPrice1 = findViewById(R.id.tv_SpannableStringPrice_1);
        tvPrice2 = findViewById(R.id.tv_SpannableStringPrice_2);
        tvPrice3 = findViewById(R.id.tv_SpannableStringPrice_3);

        measureTextText();

        tvSpannableString();

        PriceUtils.setPriceFonts(tvPrice1, 12345.6789+"起", 18, 13);
        PriceUtils.setPriceFonts(tvPrice2, "12345.6789", 18, 13);
//        PriceUtils.setPriceFonts(tvPrice3, "5.6", 18, 13);
//
        PriceUtils.setPriceFonts( "¥"+"05.06",tvPrice3, 13, 13);
    }

    public void buttonClick(View view) {
        textView.setText("12343434344111111111111111111111111111111111111111111111111111111111111111111111111133333333333");
    }


    /**
     * 测量文字是否大于一行
     */
    public void  measureTextText(){
        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        ViewTreeObserver vto2 = tv1.getViewTreeObserver();
//        使用addOnGlobalLayoutListener是因为:普通情况getWidth 的到的数据是0。
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                TextPaint mTextPaint = tv1.getPaint();
                mTextPaint.setTextSize(tv1.getTextSize());
                int mTextViewWidth = (int) mTextPaint.measureText("《三体》刘慈欣1Ad的系列长篇小说");
                tv1.setText("《三体》刘慈欣1Ad的系列长篇小说");
                if (mTextViewWidth > tv1.getWidth()) {//超出一行
                    tv2.setText("超出一行");
                } else {
                    tv2.setText("未超出一行");
                }
            }
        });
    }


    /**
     * 大文字，小文字
     */
    private void  tvSpannableString(){
       TextView  tvText = (TextView) findViewById(R.id.tv_SpannableString);
        String text = "123y大.这是小文字?";

        int start = text.indexOf('.');
        Log.d("SpannableS", start+"");
        Log.d("SpannableS i 1", text.indexOf('1')+"");
        Log.d("SpannableS i ?", text.indexOf('?')+"");
        Log.d("SpannableS 大", "大".length()+"");
        Log.d("SpannableS s", "s".length()+"");

        int end = text.length();
        SpannableString textSpan = new SpannableString (text);
        textSpan.setSpan(new AbsoluteSizeSpan(50),0,start, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(32),start,end,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tvText.setText(textSpan);
    }
}
