package com.seachal.seachaltest.View.CustomSpan;

import android.content.Context;
import androidx.annotation.DrawableRes;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.TextView;

/**
 * *
 * *
 * Project_Name:SeachalDemos
 *
 * @author zhangxc
 * @date 2019-12-09 18:25
 * *
 */
public class SpannableStringUtils {

        public static void getLabelStyleText(
                Context context, String[] strArray
                , TextView textView, @DrawableRes int backgroudId) {
            String message = "";
            String[] strings = strArray;
            for (int i = 0; i < strings.length; i++) {
                message += strings[i];
                if (i != strings.length - 1) {
                    message += " ";
                }

            }


            SpannableStringBuilder ssb = new SpannableStringBuilder(message);
            int startX = 0 ;
            int endX = 0 ;
            for (int i = 0; i < strings.length; i++) {
                int length = strings[i].length();
                int lastLength = 0;
                if (i != 0) {
                    lastLength = strings[i - 1].length()-1;
                }
                endX = startX+strings[i].length();
                ssb.setSpan(new MySpan(context.getResources().getDrawable(backgroudId), textView)
                        , startX, endX, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                startX = endX+1;
            }
            textView.setText(ssb);


        }

}
