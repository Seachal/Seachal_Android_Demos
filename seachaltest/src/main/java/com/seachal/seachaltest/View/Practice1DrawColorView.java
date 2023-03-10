package com.seachal.seachaltest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Practice1DrawColorView extends View {

    public Practice1DrawColorView(Context context) {
        super(context);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        [Canvas的save()、restore()、rotate()等方法理解 - 简书](https://www.jianshu.com/p/bd649613bdd3)
        canvas.drawColor(Color.RED); //将画布颜色填充为红色
        canvas.save(); //保存当前画布大小

        canvas.clipRect(new Rect(100,100,400,400));
        canvas.drawColor(Color.BLUE);

        //恢复画布
        canvas.restore();  // sca 恢复到红色保存的画布大小
        canvas.drawColor(Color.YELLOW); //将画布颜色填充为黄色色

//[(98条消息) Canvas的save和restore_s740088128的博客-CSDN博客](https://blog.csdn.net/s740088128/article/details/48346589)
/*        Paint paint_green = new Paint();
        paint_green.setColor(Color.GREEN);
        Paint paint_red   =  new Paint();
        paint_red.setColor(Color.RED);

        Rect rect1 = new Rect(300,10,500,100);
        canvas.drawRect(rect1, paint_red); //画出原轮廓

        canvas.rotate(30);//顺时针旋转画布
        canvas.drawRect(rect1, paint_green);//画出旋转后的矩形*/

/*        Paint paint_green = new Paint();
        paint_green.setColor(Color.GREEN);
        Paint paint_red   =  new Paint();
        paint_red.setColor(Color.RED);

        Rect rect1 = new Rect(300,10,500,100);
        canvas.drawRect(rect1, paint_red); //画出原轮廓

        canvas.save(); // 保存当前未旋转状态

        canvas.rotate(30);//顺时针旋转画布
        canvas.restore();  // 恢复之前保存的画布状态，那么就看不到"canvas.rotate(30)"的效果
        canvas.drawRect(rect1, paint_green);//画出旋转后的矩形*/


        Paint paint_green = new Paint();
        paint_green.setColor(Color.GREEN);
        Paint paint_red   =  new Paint();
        paint_red.setColor(Color.RED);

        Rect rect1 = new Rect(300,10,500,100);
        canvas.drawRect(rect1, paint_red); //画出原轮廓

        canvas.save(); // 保存当前未旋转状态

        canvas.rotate(30);//顺时针旋转画布
        canvas.restore();  // 恢复之前保存的画布状态，那么就看不到"canvas.rotate(30)"的效果

        canvas.translate(100,100);
        canvas.drawRect(rect1, paint_green);//画出旋转后的矩形

    }
}
