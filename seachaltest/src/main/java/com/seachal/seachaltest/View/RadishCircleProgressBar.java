package com.seachal.seachaltest.View;

/**
 * @author zhouwenjun
 * @date $date$
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.seachal.seachaltest.R;

import static android.graphics.Paint.Style.STROKE;

/**
 * 萝卜圈进度条
 *
 * @author zhouwenjun
 */
public class RadishCircleProgressBar extends View {

    /**
     * 进度的颜色
     */
    private int outsideColor;
    /**
     * 外圆半径大小
     */
    private float outsideRadius;
    /**
     * 背景颜色
     */
    private int insideColor;
    /**
     * 圆环的宽度
     */
    private float progressWidth;
    /**
     * 最大进度
     */
    private int maxProgress;
    /**
     * 当前进度
     */
    private float progress;
    /**
     * 进度从哪里开始(设置了4个值,上左下右)
     */
    private int direction;
    /**
     *
     */
    private Paint paint;
    private Bitmap mBitmap;

    enum DirectionEnum {
        /**
         *
         */
        LEFT(0, 180.0f),
        TOP(1, 270.0f),
        RIGHT(2, 0.0f),
        BOTTOM(3, 90.0f);

        private final int direction;
        private final float degree;

        DirectionEnum(int direction, float degree) {
            this.direction = direction;
            this.degree = degree;
        }

        public int getDirection() {
            return direction;
        }

        public float getDegree() {
            return degree;
        }

        public boolean equalsDescription(int direction) {
            return this.direction == direction;
        }

        public static DirectionEnum getDirection(int direction) {
            for (DirectionEnum enumObject : values()) {
                if (enumObject.equalsDescription(direction)) {
                    return enumObject;
                }
            }
            return RIGHT;
        }

        public static float getDegree(int direction) {
            DirectionEnum enumObject = getDirection(direction);
            if (enumObject == null) {
                return 0;
            }
            return enumObject.getDegree();
        }
    }

    public RadishCircleProgressBar(Context context) {
        super(context);
        init(context,null,0);
    }

    public RadishCircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }


    public RadishCircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);

    }

    private void  init(Context context ,AttributeSet attrs, int defStyleAttr){
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RadishCircleProgressBar, defStyleAttr, 0);
        outsideColor = a.getColor(R.styleable.RadishCircleProgressBar_outside_color, ContextCompat.getColor(getContext(), R.color.color_radish_progress));
        outsideRadius = a.getDimension(R.styleable.RadishCircleProgressBar_outside_radius, 60.0f);
        insideColor = a.getColor(R.styleable.RadishCircleProgressBar_inside_color, ContextCompat.getColor(getContext(), R.color.inside_color));
        progressWidth = a.getDimension(R.styleable.RadishCircleProgressBar_progress_width, 4.0f);
        progress = a.getFloat(R.styleable.RadishCircleProgressBar_progress, 0f);
        maxProgress = a.getInt(R.styleable.RadishCircleProgressBar_max_progress, 100);
        direction = a.getInt(R.styleable.RadishCircleProgressBar_direction, 1);

        a.recycle();

        paint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_radish);
        outsideRadius = (mBitmap.getWidth() >> 1);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int circlePoint = getWidth() / 2;
        //第一步:画背景(即内层圆)
        //设置圆的颜色
        paint.setColor(insideColor);
        //设置空心
        paint.setStyle(STROKE);
        //设置圆的宽度
        paint.setStrokeWidth(progressWidth);
        //消除锯齿
        paint.setAntiAlias(true);
        //画出圆
        canvas.drawCircle(circlePoint, circlePoint, outsideRadius, paint);
        //第二步:画进度(圆弧)
        //设置进度的颜色
        //用于定义的圆弧的形状和大小的界限
        paint.setColor(outsideColor);
        RectF oval = new RectF(circlePoint - outsideRadius, circlePoint - outsideRadius, circlePoint + outsideRadius, circlePoint + outsideRadius);
        //根据进度画圆弧
        canvas.drawArc(oval, DirectionEnum.getDegree(direction), 360 * (progress / maxProgress), false, paint);
//        //第三步:画圆环内萝卜
        canvas.drawBitmap(mBitmap, progressWidth / 2, progressWidth / 2, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else {
            width = (int) (2 * outsideRadius + progressWidth);
        }
        size = MeasureSpec.getSize(heightMeasureSpec);
        mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else {
            height = (int) (2 * outsideRadius + progressWidth);
        }
        setMeasuredDimension(width, height);
    }

    public float getProgressWidth() {
        return progressWidth;
    }

    public void setProgressWidth(float progressWidth) {
        this.progressWidth = progressWidth;
    }

    public synchronized int getMaxProgress() {
        return maxProgress;
    }

    public synchronized void setMaxProgress(int maxProgress) {
        if (maxProgress < 0) {
            //此为传递非法参数异常
            throw new IllegalArgumentException("maxProgress should not be less than 0");
        }
        this.maxProgress = maxProgress;
    }

    public synchronized float getProgress() {
        return progress;
    }

    /**
     * 加锁保证线程安全,能在线程中使用
     *
     * @param progress 进度
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress should not be less than 0");
        }
        if (progress > maxProgress) {
            progress = maxProgress;
        }
        RadishCircleProgressBar.this.progress = progress;
        postInvalidate();
    }
}
