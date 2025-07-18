package com.orange.learning.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.xkw.client.R

/**
 * 自定义进度条
 * 包含渐变效果和圆圈指示器
 */
class OrangeProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ProgressBar(context, attrs, defStyleAttr) {

    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val thumbShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val thumbRadius = 12f * context.resources.displayMetrics.density // 12dp
    private val shadowRadius = 4f * context.resources.displayMetrics.density // 4dp
    
    init {
        // 设置基本进度条样式
        progressDrawable = ContextCompat.getDrawable(context, R.drawable.o_progress_bar_orange)
        
        // 初始化圆圈指示器画笔
        thumbPaint.apply {
            color = ContextCompat.getColor(context, R.color.o_primary)
            style = Paint.Style.FILL
        }
        
        // 初始化阴影画笔
        thumbShadowPaint.apply {
            color = Color.argb(50, 0, 0, 0) // 半透明黑色阴影
            style = Paint.Style.FILL
            maskFilter = BlurMaskFilter(shadowRadius, BlurMaskFilter.Blur.NORMAL)
        }
        
        // 设置图层类型以支持阴影
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        // 绘制基本进度条
        super.onDraw(canvas)
        
        // 绘制圆圈指示器
        drawThumb(canvas)
    }
    
    private fun drawThumb(canvas: Canvas) {
        if (max <= 0) return
        
        // 计算进度比例
        val progressRatio = progress.toFloat() / max.toFloat()
        
        // 计算圆圈指示器位置
        val progressWidth = (width - paddingLeft - paddingRight) * progressRatio
        val thumbX = paddingLeft + progressWidth
        val thumbY = height / 2f
        
        // 只在有进度时绘制指示器
        if (progress > 0) {
            // 绘制阴影
            canvas.drawCircle(thumbX + shadowRadius / 2, thumbY + shadowRadius / 2, thumbRadius, thumbShadowPaint)
            
            // 绘制圆圈指示器
            canvas.drawCircle(thumbX, thumbY, thumbRadius, thumbPaint)
            
            // 绘制内圈（白色）
            val innerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.WHITE
                style = Paint.Style.FILL
            }
            canvas.drawCircle(thumbX, thumbY, thumbRadius * 0.6f, innerPaint)
        }
    }
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        
        // 确保有足够的高度来绘制圆圈指示器
        val minHeight = (thumbRadius * 2 + shadowRadius * 2).toInt()
        val height = Math.max(measuredHeight, minHeight)
        setMeasuredDimension(measuredWidth, height)
    }
} 