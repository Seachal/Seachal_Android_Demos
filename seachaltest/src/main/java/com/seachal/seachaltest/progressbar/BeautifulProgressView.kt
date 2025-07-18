package com.seachal.seachaltest.progressbar

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R
import kotlin.math.cos
import kotlin.math.sin

/**
 * 美观的渐变进度条View
 * 特性：
 * - 橙色到红色的渐变效果
 * - 圆形指示器带阴影和高光
 * - 平滑动画效果
 * - 可自定义颜色和尺寸
 */
class BeautifulProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // 进度相关
    private var progress = 0f
    private var maxProgress = 100f
    
    // 画笔
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val thumbShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val thumbHighlightPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    // 颜色配置
    private val startColor = Color.parseColor("#FF8A50")
    private val centerColor = Color.parseColor("#FF7043")
    private val endColor = Color.parseColor("#FF5722")
    private val backgroundColor = Color.parseColor("#E8E8E8")
    
    // 尺寸配置
    private val trackHeight = 12f.dpToPx()
    private val trackRadius = trackHeight / 2f
    private val thumbRadius = 14f.dpToPx()
    private val thumbShadowRadius = thumbRadius + 4f.dpToPx()
    
    // 渐变对象
    private var progressGradient: LinearGradient? = null
    
    // 动画
    private var progressAnimator: ValueAnimator? = null
    
    init {
        setupPaints()
    }
    
    private fun setupPaints() {
        // 背景画笔
        backgroundPaint.apply {
            color = backgroundColor
            style = Paint.Style.FILL
        }
        
        // 进度画笔
        progressPaint.apply {
            style = Paint.Style.FILL
        }
        
        // 指示器阴影画笔
        thumbShadowPaint.apply {
            color = Color.parseColor("#40000000")
            style = Paint.Style.FILL
        }
        
        // 指示器主体画笔
        thumbPaint.apply {
            style = Paint.Style.FILL
        }
        
        // 指示器高光画笔
        thumbHighlightPaint.apply {
            color = Color.parseColor("#60FFFFFF")
            style = Paint.Style.FILL
        }
    }
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        
        // 创建进度渐变
        val trackTop = (height - trackHeight) / 2f
        val trackBottom = trackTop + trackHeight
        val trackLeft = thumbRadius
        val trackRight = width - thumbRadius
        
        progressGradient = LinearGradient(
            trackLeft, trackTop,
            trackRight, trackTop,
            intArrayOf(startColor, centerColor, endColor),
            null,
            Shader.TileMode.CLAMP
        )
        progressPaint.shader = progressGradient
        
        // 创建指示器渐变
        val thumbGradient = RadialGradient(
            0f, 0f, thumbRadius,
            intArrayOf(startColor, endColor),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
        thumbPaint.shader = thumbGradient
    }
    
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        val trackTop = (height - trackHeight) / 2f
        val trackBottom = trackTop + trackHeight
        val trackLeft = thumbRadius
        val trackRight = width - thumbRadius
        
        // 绘制背景轨道
        canvas.drawRoundRect(
            trackLeft, trackTop,
            trackRight, trackBottom,
            trackRadius, trackRadius,
            backgroundPaint
        )
        
        // 绘制进度
        if (progress > 0) {
            val progressWidth = (trackRight - trackLeft) * (progress / maxProgress)
            canvas.drawRoundRect(
                trackLeft, trackTop,
                trackLeft + progressWidth, trackBottom,
                trackRadius, trackRadius,
                progressPaint
            )
        }
        
        // 绘制指示器
        val thumbX = trackLeft + (trackRight - trackLeft) * (progress / maxProgress)
        val thumbY = height / 2f
        
        // 绘制阴影
        canvas.drawCircle(thumbX + 2f, thumbY + 2f, thumbShadowRadius, thumbShadowPaint)
        
        // 绘制主体
        canvas.drawCircle(thumbX, thumbY, thumbRadius, thumbPaint)
        
        // 绘制高光
        val highlightRadius = thumbRadius * 0.6f
        canvas.drawCircle(
            thumbX - thumbRadius * 0.3f, 
            thumbY - thumbRadius * 0.3f, 
            highlightRadius, 
            thumbHighlightPaint
        )
    }
    
    /**
     * 设置进度（带动画）
     */
    fun setProgressWithAnimation(targetProgress: Float, duration: Long = 1000) {
        progressAnimator?.cancel()
        
        progressAnimator = ValueAnimator.ofFloat(progress, targetProgress).apply {
            this.duration = duration
            addUpdateListener { animator ->
                progress = animator.animatedValue as Float
                invalidate()
            }
            start()
        }
    }
    
    /**
     * 设置进度（无动画）
     */
    fun setProgress(newProgress: Float) {
        progress = newProgress.coerceIn(0f, maxProgress)
        invalidate()
    }
    
    /**
     * 获取当前进度
     */
    fun getProgress(): Float = progress
    
    /**
     * 设置最大进度
     */
    fun setMaxProgress(max: Float) {
        maxProgress = max
        invalidate()
    }
    
    /**
     * dp转px
     */
    private fun Float.dpToPx(): Float {
        return this * context.resources.displayMetrics.density
    }
} 