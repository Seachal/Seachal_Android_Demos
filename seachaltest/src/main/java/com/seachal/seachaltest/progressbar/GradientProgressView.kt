package com.seachal.seachaltest.progressbar

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R

/**
 * 渐变效果自定义进度条View
 * 实现带有渐变色彩的现代化进度条效果
 * 
 * @author Seachal
 * @date 2025-01-27
 */
class GradientProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // 画笔
    private var backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var progressPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var shadowPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 颜色
    private var progressBackgroundColor = Color.parseColor("#F0F0F0")
    private var startColor = Color.parseColor("#FF6B00")
    private var endColor = Color.parseColor("#FFB347")
    private var textColor = Color.parseColor("#333333")
    private var shadowColor = Color.parseColor("#33FF6B00")

    // 进度值
    private var progress = 0f
    private var maxProgress = 100f
    private var targetProgress = 0f

    // 尺寸
    private var cornerRadius = 0f
    private var progressHeight = 0f
    private var textSize = 0f
    private var shadowOffset = 0f

    // 动画
    private var progressAnimator: ValueAnimator? = null
    private var shimmerAnimator: ValueAnimator? = null
    private val animationDuration = 500L

    // 绘制区域
    private var progressRect = RectF()
    private var backgroundRect = RectF()
    private var shadowRect = RectF()

    // 渐变和光影效果
    private var gradientShader: LinearGradient? = null
    private var shimmerPosition = 0f
    private var enableShimmer = true

    // 是否显示文字和阴影
    private var showProgressText = true
    private var showShadow = true

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
        cornerRadius = 12f * density
        progressHeight = 16f * density
        textSize = 14f * density
        shadowOffset = 2f * density

        // 从资源文件获取颜色
        progressBackgroundColor = ContextCompat.getColor(context, R.color.progress_background_color)
        startColor = ContextCompat.getColor(context, R.color.checkbox_checked_color)
        endColor = Color.parseColor("#FFB347") // 浅橙色
        textColor = ContextCompat.getColor(context, R.color.text_black)

        // 初始化画笔
        setupPaints()
        
        // 启动光影动画
        startShimmerAnimation()
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
            style = Paint.Style.FILL
            // shader会在onSizeChanged中设置
        }

        // 阴影画笔
        shadowPaint.apply {
            color = shadowColor
            style = Paint.Style.FILL
            maskFilter = BlurMaskFilter(4f, BlurMaskFilter.Blur.NORMAL)
        }

        // 文字画笔
        textPaint.apply {
            color = textColor
            textSize = this@GradientProgressView.textSize
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
                    (progressHeight + textSize + shadowOffset + 20 * context.resources.displayMetrics.density).toInt()
                } else {
                    (progressHeight + shadowOffset + 8 * context.resources.displayMetrics.density).toInt()
                }
                preferredHeight.coerceAtMost(heightSize)
            }
            else -> {
                if (showProgressText) {
                    (progressHeight + textSize + shadowOffset + 20 * context.resources.displayMetrics.density).toInt()
                } else {
                    (progressHeight + shadowOffset + 8 * context.resources.displayMetrics.density).toInt()
                }
            }
        }

        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        
        val padding = 8f * context.resources.displayMetrics.density
        val progressTop = padding + shadowOffset
        val progressBottom = progressTop + progressHeight

        // 背景区域
        backgroundRect.set(
            padding,
            progressTop,
            w - padding,
            progressBottom
        )

        // 阴影区域（稍微偏移）
        shadowRect.set(
            backgroundRect.left + shadowOffset,
            backgroundRect.top + shadowOffset,
            backgroundRect.right + shadowOffset,
            backgroundRect.bottom + shadowOffset
        )

        // 创建渐变shader
        createGradientShader()
        
        updateProgressRect()
    }

    /**
     * 创建渐变着色器
     */
    private fun createGradientShader() {
        if (backgroundRect.width() > 0) {
            gradientShader = LinearGradient(
                backgroundRect.left,
                0f,
                backgroundRect.right,
                0f,
                intArrayOf(startColor, endColor, startColor),
                floatArrayOf(0f, 0.5f, 1f),
                Shader.TileMode.CLAMP
            )
            progressPaint.shader = gradientShader
        }
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

        // 绘制阴影（如果启用）
        if (showShadow && progress > 0) {
            val shadowProgressWidth = shadowRect.width() * (progress / maxProgress)
            val shadowProgressRect = RectF(
                shadowRect.left,
                shadowRect.top,
                shadowRect.left + shadowProgressWidth,
                shadowRect.bottom
            )
            canvas.drawRoundRect(shadowProgressRect, cornerRadius, cornerRadius, shadowPaint)
        }

        // 绘制背景
        canvas.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, backgroundPaint)

        // 绘制进度条
        if (progress > 0) {
            // 保存canvas状态
            canvas.save()
            
            // 裁剪到进度区域
            canvas.clipRect(progressRect)
            
            // 如果启用了光影效果，添加光影位移
            if (enableShimmer) {
                val matrix = Matrix()
                matrix.setTranslate(shimmerPosition, 0f)
                gradientShader?.setLocalMatrix(matrix)
            }
            
            // 绘制进度条
            canvas.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, progressPaint)
            
            // 恢复canvas状态
            canvas.restore()
        }

        // 绘制进度文字
        if (showProgressText) {
            val progressText = "${progress.toInt()}%"
            val textY = backgroundRect.bottom + textSize + 12f * context.resources.displayMetrics.density
            
            // 绘制文字阴影
            textPaint.setShadowLayer(2f, 1f, 1f, Color.parseColor("#33000000"))
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
     * 启动光影动画
     */
    private fun startShimmerAnimation() {
        if (!enableShimmer) return
        
        shimmerAnimator = ValueAnimator.ofFloat(-backgroundRect.width(), backgroundRect.width() * 2).apply {
            duration = 2000L
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener { animator ->
                shimmerPosition = animator.animatedValue as Float
                if (progress > 0) {
                    invalidate()
                }
            }
            start()
        }
    }

    /**
     * 停止光影动画
     */
    private fun stopShimmerAnimation() {
        shimmerAnimator?.cancel()
        shimmerAnimator = null
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
     * 设置渐变起始颜色
     */
    fun setStartColor(color: Int) {
        startColor = color
        createGradientShader()
        invalidate()
    }

    /**
     * 设置渐变结束颜色
     */
    fun setEndColor(color: Int) {
        endColor = color
        createGradientShader()
        invalidate()
    }

    /**
     * 设置是否启用光影效果
     */
    fun setShimmerEnabled(enabled: Boolean) {
        enableShimmer = enabled
        if (enabled) {
            startShimmerAnimation()
        } else {
            stopShimmerAnimation()
        }
    }

    /**
     * 设置是否显示阴影
     */
    fun setShowShadow(show: Boolean) {
        showShadow = show
        invalidate()
    }

    /**
     * 设置是否显示进度文字
     */
    fun setShowProgressText(show: Boolean) {
        showProgressText = show
        requestLayout()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        progressAnimator?.cancel()
        stopShimmerAnimation()
    }
} 