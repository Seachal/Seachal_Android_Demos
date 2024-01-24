package com.seachal.seachaltest.customview;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.seachal.seachaltest.R;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2021/11/27 16:16
 * *
 */
public class PriceUtils {


    public static void setPriceFonts(TextView textView, String price, int size , int smallSize) {
        ForegroundColorSpan forColor = new ForegroundColorSpan(textView.getResources().getColor(R.color.colorPrimary));
        SpannableString span = new SpannableString("¥" + price);
        if (price.length() > 3) {//判断是否带小数点
            span.setSpan(forColor, 0, span.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            span.setSpan(new AbsoluteSizeSpan(smallSize, true), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            span.setSpan(new AbsoluteSizeSpan(size, true), 1,
                    price.contains("起") ? span.length() - 3 : span.length() - 2,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            span.setSpan(new AbsoluteSizeSpan(smallSize, true),
                    price.contains("起") ? span.length() - 3 : span.length() - 2, span.length(),
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        textView.setText(span);
    }


    public static void setPriceFonts(String price,TextView textView, int smallSize1,int smallSize2) {
        if ( !TextUtils.isEmpty(price)) {
            SpannableString span = new SpannableString(price);
            int end = price.length();
            if (price.contains("¥")){
                int start1 = price.indexOf("¥");
                span.setSpan(new AbsoluteSizeSpan(smallSize1,true),start1,start1+1,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //  包含小数点
            if (price.contains(".")){
                int start2 = price.indexOf(".");
                //  SPAN_EXCLUSIVE_INCLUSIVE 开闭区间
                span.setSpan(new AbsoluteSizeSpan(smallSize2,true),start2,end,Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            textView.setText(span);
        }else{
            textView.setText(price);
        }
    }

    public static void setPriceFontsPx(String price,TextView textView, int smallSize1,int smallSize2) {
        if ( !TextUtils.isEmpty(price)) {
            SpannableString span = new SpannableString(price);
            int end = price.length();
            if (price.contains("¥")){
                int start1 = price.indexOf("¥");
                span.setSpan(new AbsoluteSizeSpan(smallSize1),start1,start1+1,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            //  包含小数点
            if (price.contains(".")){
                int start2 = price.indexOf(".");
                //  SPAN_EXCLUSIVE_INCLUSIVE 开闭区间
                span.setSpan(new AbsoluteSizeSpan(smallSize2),start2,end,Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            textView.setText(span);
        }else{
            textView.setText(price);
        }
    }


}
