package com.seachal.seachaltest.TextView.spannableString;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by 冯涛 on 2020/6/13
 * describe:
 */
public class RoundBackgroundColorSpan1 extends ReplacementSpan {

    private int bgColor;
    private int textColor;
    private int textSize;
    private int radius = 4;

    public RoundBackgroundColorSpan1(int bgColor, int textColor, int textSize) {
        super();
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.textSize = textSize;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        int i = (int) paint.measureText(text, start, end);
        return i-3;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        int color1 = paint.getColor();
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        canvas.drawText(text, start, end, x+5, y - 4, paint);
        paint.setColor(bgColor);
        paint.setStrokeWidth(1.5f);//线宽
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(new RectF(x+2, top+11, x + ((int) paint.measureText(text, start, end))+radius+3,
                bottom-10),
                radius,
                radius,
                paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color1);
    }

}
