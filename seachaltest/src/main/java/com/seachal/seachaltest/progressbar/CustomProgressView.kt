package com.seachal.seachaltest.progressbar

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R

/**
 * 自定义进度条View
 * 实现类似截图中的圆角橙色进度条效果
 * 
 * @author Seachal
 * @date 2025-01-27
 */
class CustomProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // 画笔
    private var backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var progressPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 颜色
    private var progressBackgroundColor = Color.parseColor("#E5E5E5")
    private var progressColor = Color.parseColor("#FF6B00")
    private var textColor = Color.parseColor("#333333")

    // 进度值
    private var progress = 0f
    private var maxProgress = 100f
    private var targetProgress = 0f

    // 尺寸
    private var cornerRadius = 0f
    private var progressHeight = 0f
    private var textSize = 0f

    // 动画
    private var progressAnimator: ValueAnimator? = null
    private val animationDuration = 300L

    // 绘制区域
    private var progressRect = RectF()
    private var backgroundRect = RectF()

    // 是否显示文字
    private var showProgressText = true

    init {
        initializeView(context, attrs)
    }

    /**
     * 初始化视图
     */
    private fun initializeView(context: Context, attrs: AttributeSet?) {
        // 获取屏幕密度
        val density = context.resources.displayMetrics.density
        
        // 设置默认值
        cornerRadius = 8f * density
        progressHeight = 12f * density
        textSize = 14f * density

        // 从资源文件获取颜色
        progressBackgroundColor = ContextCompat.getColor(context, R.color.progress_background_color)
        progressColor = ContextCompat.getColor(context, R.color.checkbox_checked_color)
        textColor = ContextCompat.getColor(context, R.color.text_black)

        // 初始化画笔
        setupPaints()

        // 处理自定义属性（如果需要的话）
        attrs?.let {
            // 这里可以添加自定义属性的解析
        }
    }

    /**
     * 设置画笔
     */
    private fun setupPaints() {
        // 背景画笔
        backgroundPaint.apply {
            color = progressBackgroundColor
            style = Paint.Style.FILL
        }

        // 进度条画笔
        progressPaint.apply {
            color = progressColor
            style = Paint.Style.FILL
        }

        // 文字画笔
        textPaint.apply {
            color = textColor
            textSize = this@CustomProgressView.textSize
            textAlign = Paint.Align.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> (300 * context.resources.displayMetrics.density).toInt().coerceAtMost(widthSize)
            else -> (300 * context.resources.displayMetrics.density).toInt()
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> {
                val preferredHeight = if (showProgressText) {
                    (progressHeight + textSize + 16 * context.resources.displayMetrics.density).toInt()
                } else {
                    progressHeight.toInt()
                }
                preferredHeight.coerceAtMost(heightSize)
            }
            else -> {
                if (showProgressText) {
                    (progressHeight + textSize + 16 * context.resources.displayMetrics.density).toInt()
                } else {
                    progressHeight.toInt()
                }
            }
        }

        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        
        val padding = 4f * context.resources.displayMetrics.density
        val progressTop = padding
        val progressBottom = progressTop + progressHeight

        backgroundRect.set(
            padding,
            progressTop,
            w - padding,
            progressBottom
        )

        updateProgressRect()
    }

    /**
     * 更新进度条绘制区域
     */
    private fun updateProgressRect() {
        val progressWidth = backgroundRect.width() * (progress / maxProgress)
        progressRect.set(
            backgroundRect.left,
            backgroundRect.top,
            backgroundRect.left + progressWidth,
            backgroundRect.bottom
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制背景
        canvas.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, backgroundPaint)

        // 绘制进度条
        if (progress > 0) {
            canvas.drawRoundRect(progressRect, cornerRadius, cornerRadius, progressPaint)
        }

        // 绘制进度文字
        if (showProgressText) {
            val progressText = "${progress.toInt()}%"
            val textY = backgroundRect.bottom + textSize + 8f * context.resources.displayMetrics.density
            canvas.drawText(progressText, width / 2f, textY, textPaint)
        }
    }

    /**
     * 设置进度值
     */
    fun setProgress(progress: Int) {
        setProgress(progress.toFloat())
    }

    /**
     * 设置进度值（带动画）
     */
    fun setProgress(progress: Float, animated: Boolean = true) {
        val newProgress = progress.coerceIn(0f, maxProgress)
        
        if (animated) {
            animateToProgress(newProgress)
        } else {
            this.progress = newProgress
            targetProgress = newProgress
            updateProgressRect()
            invalidate()
        }
    }

    /**
     * 动画到目标进度
     */
    private fun animateToProgress(targetProgress: Float) {
        progressAnimator?.cancel()
        
        this.targetProgress = targetProgress
        progressAnimator = ValueAnimator.ofFloat(progress, targetProgress).apply {
            duration = animationDuration
            addUpdateListener { animator ->
                progress = animator.animatedValue as Float
                updateProgressRect()
                invalidate()
            }
            start()
        }
    }

    /**
     * 获取当前进度
     */
    fun getProgress(): Float = progress

    /**
     * 设置最大进度值
     */
    fun setMaxProgress(maxProgress: Float) {
        this.maxProgress = maxProgress
        setProgress(progress, false)
    }

    /**
     * 设置进度条颜色
     */
    fun setProgressColor(color: Int) {
        progressColor = color
        progressPaint.color = color
        invalidate()
    }

    /**
     * 设置背景颜色
     */
    override  fun setBackgroundColor(color: Int) {
        progressBackgroundColor = color
        backgroundPaint.color = color
        invalidate()
    }

    /**
     * 设置是否显示进度文字
     */
    fun setShowProgressText(show: Boolean) {
        showProgressText = show
        requestLayout()
    }

    /**
     * 设置圆角半径
     */
    fun setCornerRadius(radius: Float) {
        cornerRadius = radius
        invalidate()
    }

    /**
     * 设置进度条高度
     */
    fun setProgressHeight(height: Float) {
        progressHeight = height
        requestLayout()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        progressAnimator?.cancel()
    }
} 