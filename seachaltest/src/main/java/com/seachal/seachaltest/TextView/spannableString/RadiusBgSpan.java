package com.seachal.seachaltest.TextView.spannableString;

/**
 * *
 * *
 * Project_Name:Seachal_Android_Demos
 *[[Android]自定义ReplacementSpan实现带背景色的圆角SPAN_android 自定义replacementspan-CSDN博客](https://blog.csdn.net/runstoppable/article/details/86703163)
 * @author zhangxc
 * @Description: TODO
 * @date 2024/5/29 09:39
 * @Version：1.0
 */

import static com.seachal.seachaltest.BaseApp.getContext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

/**
 * 〈带背景色的圆角span〉
 */
public class RadiusBgSpan extends ReplacementSpan {

    private int mSize;
    private int mBgColor;
    private int mTxtColor;
    private int mRadius;
    private Paint mPaint;

    public static final int STYLE_FILL = 0;//填充
    public static final int STYLE_STROCK = 1;//扫边。扫边颜色默认和字体颜色一致
    private int mStyle = STYLE_FILL;

    /**
     * @param radius 圆角半径
     */
    public RadiusBgSpan(int bgcolor, int txtColor, int radius) {
        mBgColor = bgcolor;
        mTxtColor = txtColor;
        mRadius = radius;
        mPaint = new Paint();
    }

    /**
     * @param radius 圆角半径
     */
    public RadiusBgSpan(int bgcolor, int txtColor, int radius, int style) {
        mBgColor = bgcolor;
        mTxtColor = txtColor;
        mRadius = radius;
        mStyle = style;
        mPaint = new Paint();
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        paint.setTextSize(paint.getTextSize() - 4);
        mSize = (int) (paint.measureText(text, start, end));
        //mSize就是span的宽度，span有多宽，开发者可以在这里随便定义规则
        //我的规则：这里text传入的是SpannableString，start，end对应setSpan方法相关参数
        //可以根据传入起始截至位置获得截取文字的宽度，最后加上左右两个圆角的半径得到span宽度
        return mSize;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        mPaint.setAntiAlias(true);
        if (mStyle == STYLE_STROCK) {
            mPaint.setColor(mTxtColor);
            mPaint.setStyle(Paint.Style.STROKE);
        } else {
            mPaint.setColor(mBgColor);
            mPaint.setStyle(Paint.Style.FILL);
        }
        RectF oval = new RectF(x, y + paint.ascent(), x + mSize, y + paint.descent());
        //x.y均为原字符串文字的参数
        //设置背景矩形，x为文字左边缘的x值，y为文字的baseline的y值。paint.ascent()获得baseline到文字上边缘的值，paint.descent()获得baseline到文字下边缘
        canvas.drawRoundRect(oval, mRadius, mRadius, mPaint);//绘制圆角矩形，第二个参数是x半径，第三个参数是y半径
        //文字 -- 绘制的文字要比原文字小 , 默认小 4sp
        int originalSize = px2sp((int) paint.getTextSize());
        paint.setTextSize(originalSize - 4);
        paint.setColor(mTxtColor);
        int padding = (int) (mSize - paint.measureText(text.subSequence(start, end).toString()));
        canvas.drawText(text, start, end, x + padding / 2, y, paint);//绘制文字
    }

    /**
     * dp转px
     * @param
     * @return
     */
    public static int px2sp(int pxValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
