package com.seachal.seachaltest.TextView.spannableString;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by 冯涛 on 2020/6/13
 * describe:
 */
public class RoundBackgroundColorSpan extends ReplacementSpan {

    private int bgColor;
    private int textColor;
    private float textSizePx;
    private int radius ;

    public RoundBackgroundColorSpan( int radius,  int bgColor, int textColor, int textSizePx) {
        super();
        this.radius = radius;
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.textSizePx = textSizePx;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        int i = (int) paint.measureText(text, start, end);
        return i - 3;

    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        int color1 = paint.getColor();
//      画笔文字
        paint.setColor(textColor);
        paint.setTextSize(textSizePx);
        canvas.drawText(text, start, end, x + radius, y - radius, paint);
        // 画笔背景

        // 设置背景颜色和线宽
        paint.setColor(bgColor);
        paint.setStrokeWidth(1.5f); // 线宽
        paint.setStyle(Paint.Style.STROKE);

        // 计算文本宽度
        float textWidth = paint.measureText(text, start, end);

        // 创建一个路径
        Path path = new Path();

        // 定义矩形的四个角的半径
        float[] radii = new float[]{
                radius, radius, // 左上角
                0, 0,           // 右上角
                0, 0,           // 右下角
                radius, radius  // 左下角
        };

        // 创建一个矩形
        RectF rect = new RectF(x + 2, top + 11, x + textWidth + radius + 3, bottom - 10);

        // 添加圆角矩形到路径
        path.addRoundRect(rect, radii, Path.Direction.CW);

        // 绘制路径
        canvas.drawPath(path, paint);

        // 填充颜色
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color1);
    }

}
