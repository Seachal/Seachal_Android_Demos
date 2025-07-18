package com.seachal.seachaltest.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R

/**
 * 自定义橙色进度条
 * 包含渐变效果和圆形指示器
 * 
 * @author Seachal
 * @date 2025-01-27
 */
class OrangeProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // 可配置的属性
    private var progressBarHeight: Float = 16f // 进度条高度 (px)
    private var thumbSize: Float = 20f // 指示器大小 (px)
    private var progress: Int = 0 // 当前进度
    private var maxProgress: Int = 100 // 最大进度
    private var progressColor: Int = Color.parseColor("#FF8A50") // 进度色
    private var backgroundColor: Int = Color.parseColor("#E0E0E0") // 背景色
    private var thumbColor: Int = Color.parseColor("#FF5722") // 指示器颜色
    
    // 画笔
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val thumbPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val thumbShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val thumbInnerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    // 渐变着色器
    private var progressGradient: LinearGradient? = null
    
    init {
        // 读取自定义属性
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OrangeProgressBar)
            try {
                progressBarHeight = typedArray.getDimension(R.styleable.OrangeProgressBar_progressBarHeight, 16f)
                thumbSize = typedArray.getDimension(R.styleable.OrangeProgressBar_thumbSize, 20f)
                progress = typedArray.getInt(R.styleable.OrangeProgressBar_orangeProgress, 0)
                maxProgress = typedArray.getInt(R.styleable.OrangeProgressBar_orangeMaxProgress, 100)
                progressColor = typedArray.getColor(R.styleable.OrangeProgressBar_progressColor, Color.parseColor("#FF8A50"))
                backgroundColor = typedArray.getColor(R.styleable.OrangeProgressBar_backgroundColor, Color.parseColor("#E0E0E0"))
                thumbColor = typedArray.getColor(R.styleable.OrangeProgressBar_thumbColor, Color.parseColor("#FF5722"))
            } finally {
                typedArray.recycle()
            }
        }
        
        initPaints()
        
        // 设置图层类型以支持阴影
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }
    
    private fun initPaints() {
        // 背景画笔
        backgroundPaint.apply {
            color = backgroundColor
            style = Paint.Style.FILL
        }
        
        // 进度画笔
        progressPaint.apply {
            style = Paint.Style.FILL
        }
        
        // 指示器画笔
        thumbPaint.apply {
            color = thumbColor
            style = Paint.Style.FILL
        }
        
        // 指示器阴影画笔
        thumbShadowPaint.apply {
            color = Color.argb(50, 0, 0, 0) // 半透明黑色阴影
            style = Paint.Style.FILL
            maskFilter = BlurMaskFilter(4f, BlurMaskFilter.Blur.NORMAL)
        }
        
        // 指示器内圈画笔
        thumbInnerPaint.apply {
            color = Color.WHITE
            style = Paint.Style.FILL
        }
    }
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        
        // 创建进度条渐变效果
        if (w > 0) {
            progressGradient = LinearGradient(
                0f, 0f, w.toFloat(), 0f,
                intArrayOf(
                    Color.parseColor("#FF8A50"),
                    Color.parseColor("#FF7043"),
                    Color.parseColor("#FF5722")
                ),
                null,
                Shader.TileMode.CLAMP
            )
            progressPaint.shader = progressGradient
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        drawProgressBar(canvas)
        drawThumb(canvas)
    }
    
    private fun drawProgressBar(canvas: Canvas) {
        val centerY = height / 2f
        val barTop = centerY - progressBarHeight / 2
        val barBottom = centerY + progressBarHeight / 2
        val barLeft = paddingLeft.toFloat()
        val barRight = (width - paddingRight).toFloat()
        val cornerRadius = progressBarHeight / 2
        
        // 绘制背景
        val backgroundRect = RectF(barLeft, barTop, barRight, barBottom)
        canvas.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, backgroundPaint)
        
        // 绘制进度
        if (progress > 0 && maxProgress > 0) {
            val progressRatio = progress.toFloat() / maxProgress.toFloat()
            val progressRight = barLeft + (barRight - barLeft) * progressRatio
            
            val progressRect = RectF(barLeft, barTop, progressRight, barBottom)
            canvas.drawRoundRect(progressRect, cornerRadius, cornerRadius, progressPaint)
        }
    }
    
    private fun drawThumb(canvas: Canvas) {
        if (progress <= 0 || maxProgress <= 0) return
        
        // 计算进度比例和指示器位置
        val progressRatio = progress.toFloat() / maxProgress.toFloat()
        val barWidth = width - paddingLeft - paddingRight
        val thumbX = paddingLeft + barWidth * progressRatio
        val thumbY = height / 2f
        val thumbRadius = thumbSize / 2
        
        // 绘制阴影
        canvas.drawCircle(thumbX + 2f, thumbY + 2f, thumbRadius, thumbShadowPaint)
        
        // 绘制指示器外圈
        canvas.drawCircle(thumbX, thumbY, thumbRadius, thumbPaint)
        
        // 绘制指示器内圈（白色）
        canvas.drawCircle(thumbX, thumbY, thumbRadius * 0.6f, thumbInnerPaint)
    }
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeight = Math.max(progressBarHeight, thumbSize).toInt() + paddingTop + paddingBottom + 8 // 8px额外间距
        
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        
        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> Math.min(300, widthSize) // 默认最大宽度300dp
            else -> 300
        }
        
        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> Math.min(desiredHeight, heightSize)
            else -> desiredHeight
        }
        
        setMeasuredDimension(width, height)
    }
    
    // 公开方法
    
    /**
     * 设置进度
     * @param progress 进度值 (0 到 maxProgress)
     */
    fun setProgress(progress: Int) {
        this.progress = Math.max(0, Math.min(progress, maxProgress))
        invalidate()
    }
    
    /**
     * 获取当前进度
     */
    fun getProgress(): Int = progress
    
    /**
     * 设置最大进度
     * @param maxProgress 最大进度值
     */
    fun setMaxProgress(maxProgress: Int) {
        this.maxProgress = Math.max(1, maxProgress)
        this.progress = Math.min(this.progress, this.maxProgress)
        invalidate()
    }
    
    /**
     * 获取最大进度
     */
    fun getMaxProgress(): Int = maxProgress
    
    /**
     * 设置进度条高度 (px)
     * @param height 高度值
     */
    fun setProgressBarHeight(height: Float) {
        this.progressBarHeight = Math.max(1f, height)
        requestLayout()
    }
    
    /**
     * 设置指示器大小 (px)
     * @param size 大小值
     */
    fun setThumbSize(size: Float) {
        this.thumbSize = Math.max(1f, size)
        requestLayout()
    }
    
    /**
     * 设置进度颜色
     * @param color 颜色值
     */
    fun setProgressColor(color: Int) {
        this.progressColor = color
        thumbPaint.color = color
        invalidate()
    }
    
    /**
     * 设置背景颜色
     * @param color 颜色值
     */
    override fun setBackgroundColor(color: Int) {
        this.backgroundColor = color
        backgroundPaint.color = color
        invalidate()
    }
    
    /**
     * 带动画设置进度
     * @param targetProgress 目标进度
     * @param duration 动画时长 (毫秒)
     */
    fun setProgressWithAnimation(targetProgress: Int, duration: Long = 500) {
        val startProgress = this.progress
        val endProgress = Math.max(0, Math.min(targetProgress, maxProgress))
        
        val animator = android.animation.ValueAnimator.ofInt(startProgress, endProgress)
        animator.duration = duration
        animator.interpolator = android.view.animation.DecelerateInterpolator()
        animator.addUpdateListener { animation ->
            setProgress(animation.animatedValue as Int)
        }
        animator.start()
    }
} 