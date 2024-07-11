package com.seachal.seachaltest.TextView.spannableString

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.graphics.Path
import android.graphics.RectF
import android.text.style.ReplacementSpan
import android.util.Log
import com.seachal.seachaltest.BaseApp.getContext
import com.seachal.seachaltest.R

/**
 *
 * describe:
 */
class LeftRoundBackgroundColorSpan(
    private val radius: Int,
    private val bgColor: Int,
    private val textColor: Int,
    private val textSizePx: Int,
//     与右侧的间距
    private val marginRight: Int
) : ReplacementSpan() {
    /**
     * 这里会影响和其他没有打标签的文字的距离
     * @Author zhangxc
     * @Description //TODO
     * @Date 10:47 2024/5/29
     * @param paint
     * @param text
     * @param start
     * @param end
     * @param fm
     * @return int
     */
    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: FontMetricsInt?
    ): Int {
        val i = paint.measureText(text, start, end).toInt()
        //
        return i + marginRight
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
//       用 Log打印start  end、   x,top,y,bottom
        Log.d("TAG", "start:$start end:$end x:$x top:$top y:$y bottom:$bottom")
        val color1 = paint.color
        //      画笔文字
        paint.color = textColor
        paint.textSize = textSizePx.toFloat()
        canvas.drawText(text, start, end, x + radius, (y - radius/2).toFloat(), paint)
        // 画笔背景

        // 设置背景颜色和线宽
        paint.color = bgColor
        paint.strokeWidth = 1.5f // 线宽
        paint.style = Paint.Style.STROKE

        // 计算文本宽度
        val textWidth = paint.measureText(text, start, end)

        // 创建一个路径
        val path = Path()

        // 定义矩形的四个角的半径
        val radii = floatArrayOf(
            radius.toFloat(), radius.toFloat(),  // 左上角
            0f, 0f,  // 右上角
            0f, 0f,  // 右下角
            radius.toFloat(), radius // 左下角
                .toFloat()
        )

        // 创建一个矩形 , top + radius 矩形顶部的y坐标，  bottom - radius 矩形底部的 y坐标。
        val rect = RectF(
            x + 1,
            (top + radius).toFloat(),
            x + textWidth + radius * 2,
            (bottom - radius).toFloat()
        )

        // 添加圆角矩形到路径
        path.addRoundRect(rect, radii, Path.Direction.CW)

        // 绘制路径
        canvas.drawPath(path, paint)

        // 填充颜色
        paint.style = Paint.Style.FILL
        paint.color = color1
    }

//   定义一个内部枚举类，用于表示左侧边有圆角，右边有圆角， 左右都有圆角
    enum class CornerType {
        LEFT, RIGHT, ALL
    }


}
