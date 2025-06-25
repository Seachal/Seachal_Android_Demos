package com.seachal.seachaltest.progressbar

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R

/**
 * 进度条演示Activity
 * 包含ProgressBar、SeekBar、自定义View等多种进度条实现
 * 
 * @author Seachal
 * @date 2025-01-27
 */
class ProgressBarDemoActivity : AppCompatActivity() {

    // 水平进度条
    private lateinit var progressBarHorizontal: ProgressBar
    private lateinit var progressBarCustom: ProgressBar
    private lateinit var progressBarRounded: ProgressBar
    
    // 圆形进度条
    private lateinit var progressBarCircular: ProgressBar
    private lateinit var progressBarCircularCustom: ProgressBar
    
    // SeekBar
    private lateinit var seekBar: SeekBar
    private lateinit var seekBarCustom: SeekBar
    
    // 自定义进度条
    private lateinit var customProgressView: CustomProgressView
    private lateinit var gradientProgressView: GradientProgressView
    
    // 控制按钮
    private lateinit var btnStartProgress: Button
    private lateinit var btnStopProgress: Button
    private lateinit var btnResetProgress: Button
    
    // 进度显示
    private lateinit var tvProgress: TextView
    private lateinit var tvSeekBarProgress: TextView
    
    // 动画控制
    private var progressAnimator: ValueAnimator? = null
    private var isAnimating = false
    private val handler = Handler(Looper.getMainLooper())
    private var simulationRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar_demo)

        initViews()
        setupListeners()
        setupProgressAnimation()
    }

    /**
     * 初始化视图控件
     */
    private fun initViews() {
        // 水平进度条
        progressBarHorizontal = findViewById(R.id.progress_bar_horizontal)
        progressBarCustom = findViewById(R.id.progress_bar_custom)
        progressBarRounded = findViewById(R.id.progress_bar_rounded)
        
        // 圆形进度条
        progressBarCircular = findViewById(R.id.progress_bar_circular)
        progressBarCircularCustom = findViewById(R.id.progress_bar_circular_custom)
        
        // SeekBar
        seekBar = findViewById(R.id.seek_bar)
        seekBarCustom = findViewById(R.id.seek_bar_custom)
        
        // 自定义进度条
        customProgressView = findViewById(R.id.custom_progress_view)
        gradientProgressView = findViewById(R.id.gradient_progress_view)
        
        // 控制按钮
        btnStartProgress = findViewById(R.id.btn_start_progress)
        btnStopProgress = findViewById(R.id.btn_stop_progress)
        btnResetProgress = findViewById(R.id.btn_reset_progress)
        
        // 进度显示
        tvProgress = findViewById(R.id.tv_progress)
        tvSeekBarProgress = findViewById(R.id.tv_seekbar_progress)
        
        // 设置初始进度
        val initialProgress = 30
        updateAllProgress(initialProgress)
        tvProgress.text = "进度: ${initialProgress}%"
        tvSeekBarProgress.text = "SeekBar进度: ${seekBar.progress}"
    }

    /**
     * 设置监听器
     */
    private fun setupListeners() {
        // SeekBar监听器
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateAllProgress(progress)
                    tvProgress.text = "进度: ${progress}%"
                }
                tvSeekBarProgress.text = "SeekBar进度: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // 用户开始拖动时停止自动动画
                stopProgressAnimation()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // 拖动结束
            }
        })

        // 自定义SeekBar监听器
        seekBarCustom.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateAllProgress(progress)
                    tvProgress.text = "进度: ${progress}%"
                    tvSeekBarProgress.text = "SeekBar进度: $progress"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                stopProgressAnimation()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // 按钮监听器
        btnStartProgress.setOnClickListener {
            startProgressAnimation()
        }

        btnStopProgress.setOnClickListener {
            stopProgressAnimation()
        }

        btnResetProgress.setOnClickListener {
            resetProgress()
        }
    }

    /**
     * 设置进度动画
     */
    private fun setupProgressAnimation() {
        progressAnimator = ValueAnimator.ofInt(0, 100).apply {
            duration = 5000 // 5秒完成
            addUpdateListener { animator ->
                val progress = animator.animatedValue as Int
                updateAllProgress(progress)
                tvProgress.text = "进度: ${progress}%"
                tvSeekBarProgress.text = "SeekBar进度: $progress"
            }
        }
    }

    /**
     * 开始进度动画
     */
    private fun startProgressAnimation() {
        if (isAnimating) return
        
        isAnimating = true
        btnStartProgress.isEnabled = false
        btnStopProgress.isEnabled = true

        // 方式1：使用ValueAnimator
        progressAnimator?.start()

        // 方式2：模拟网络下载进度
        simulateNetworkProgress()
    }

    /**
     * 停止进度动画
     */
    private fun stopProgressAnimation() {
        isAnimating = false
        btnStartProgress.isEnabled = true
        btnStopProgress.isEnabled = false

        progressAnimator?.cancel()
        simulationRunnable?.let { handler.removeCallbacks(it) }
    }

    /**
     * 重置进度
     */
    private fun resetProgress() {
        stopProgressAnimation()
        updateAllProgress(0)
        tvProgress.text = "进度: 0%"
        tvSeekBarProgress.text = "SeekBar进度: 0"
    }

    /**
     * 更新所有进度条
     */
    private fun updateAllProgress(progress: Int) {
        val clampedProgress = progress.coerceIn(0, 100)
        
        // 更新官方控件
        progressBarHorizontal.progress = clampedProgress
        progressBarCustom.progress = clampedProgress
        progressBarRounded.progress = clampedProgress
        
        // SeekBar
        seekBar.progress = clampedProgress
        seekBarCustom.progress = clampedProgress
        
        // 自定义View
        customProgressView.setProgress(clampedProgress)
        gradientProgressView.setProgress(clampedProgress)
    }

    /**
     * 模拟网络下载进度
     */
    private fun simulateNetworkProgress() {
        var currentProgress = 0
        
        simulationRunnable = object : Runnable {
            override fun run() {
                if (!isAnimating) return
                
                // 模拟不规律的下载速度
                val increment = when {
                    currentProgress < 20 -> (1..3).random() // 开始较慢
                    currentProgress < 80 -> (2..5).random() // 中间较快
                    else -> (1..2).random() // 结束时较慢
                }
                
                currentProgress = (currentProgress + increment).coerceAtMost(100)
                
                if (currentProgress < 100 && isAnimating) {
                    handler.postDelayed(this, (100..300).random().toLong())
                } else {
                    // 完成
                    isAnimating = false
                    btnStartProgress.isEnabled = true
                    btnStopProgress.isEnabled = false
                    
                    if (currentProgress >= 100) {
                        showToast("下载完成！")
                    }
                }
            }
        }
        
        simulationRunnable?.let { handler.post(it) }
    }

    /**
     * 演示不同样式的进度条
     */
    private fun demonstrateProgressStyles() {
        // 设置不同的进度条样式
        progressBarCustom.apply {
            // 设置自定义颜色
            progressTintList = ContextCompat.getColorStateList(this@ProgressBarDemoActivity, R.color.progress_custom_color)
            progressBackgroundTintList = ContextCompat.getColorStateList(this@ProgressBarDemoActivity, R.color.progress_background_color)
        }

        // 圆形进度条样式
        progressBarCircularCustom.apply {
            indeterminateTintList = ContextCompat.getColorStateList(this@ProgressBarDemoActivity, R.color.progress_custom_color)
        }
    }

    /**
     * 显示Toast
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopProgressAnimation()
    }
} 