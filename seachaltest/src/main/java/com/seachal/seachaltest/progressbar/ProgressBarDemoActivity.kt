package com.seachal.seachaltest.progressbar

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R

/**
 * 进度条演示Activity
 * 包含ProgressBar、SeekBar、自定义View等多种进度条实现
 * 新增 Slider 控件演示：离散值、音量控制、温度调节、RGB颜色调节
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
    
    // SeekBar/Slider
    private lateinit var seekBar: SeekBar
    private lateinit var seekBarCustom: SeekBar
    private lateinit var seekBarDiscrete: SeekBar
    private lateinit var seekBarVolume: SeekBar
    private lateinit var seekBarTemperature: SeekBar
    private lateinit var seekBarRed: SeekBar
    private lateinit var seekBarGreen: SeekBar
    private lateinit var seekBarBlue: SeekBar
    
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
    private lateinit var tvDiscreteValue: TextView
    private lateinit var tvVolumeValue: TextView
    private lateinit var tvTemperatureValue: TextView
    private lateinit var tvRedValue: TextView
    private lateinit var tvGreenValue: TextView
    private lateinit var tvBlueValue: TextView
    private lateinit var colorPreview: View
    
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
        updateColorPreview()
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
        
        // SeekBar/Slider
        seekBar = findViewById(R.id.seek_bar)
        seekBarCustom = findViewById(R.id.seek_bar_custom)
        seekBarDiscrete = findViewById(R.id.seek_bar_discrete)
        seekBarVolume = findViewById(R.id.seek_bar_volume)
        seekBarTemperature = findViewById(R.id.seek_bar_temperature)
        seekBarRed = findViewById(R.id.seek_bar_red)
        seekBarGreen = findViewById(R.id.seek_bar_green)
        seekBarBlue = findViewById(R.id.seek_bar_blue)
        
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
        tvDiscreteValue = findViewById(R.id.tv_discrete_value)
        tvVolumeValue = findViewById(R.id.tv_volume_value)
        tvTemperatureValue = findViewById(R.id.tv_temperature_value)
        tvRedValue = findViewById(R.id.tv_red_value)
        tvGreenValue = findViewById(R.id.tv_green_value)
        tvBlueValue = findViewById(R.id.tv_blue_value)
        colorPreview = findViewById(R.id.color_preview)
        
        // 设置初始进度
        val initialProgress = 30
        updateAllProgress(initialProgress)
        updateSpecialSliders()
        tvProgress.text = "进度: ${initialProgress}%"
        tvSeekBarProgress.text = "SeekBar进度: ${seekBar.progress}"
    }

    /**
     * 设置监听器
     */
    private fun setupListeners() {
        // 主要SeekBar监听器
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateAllProgress(progress)
                    tvProgress.text = "进度: ${progress}%"
                }
                tvSeekBarProgress.text = "SeekBar进度: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                stopProgressAnimation()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
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

        // 离散值SeekBar监听器
        seekBarDiscrete.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvDiscreteValue.text = "当前值: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // 音量控制SeekBar监听器
        seekBarVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvVolumeValue.text = "${progress}%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // 温度调节SeekBar监听器
        seekBarTemperature.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val temperature = 16 + progress // 16°C - 30°C
                tvTemperatureValue.text = "${temperature}°C"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // RGB 颜色调节 SeekBar 监听器
        val colorChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (seekBar?.id) {
                    R.id.seek_bar_red -> tvRedValue.text = progress.toString()
                    R.id.seek_bar_green -> tvGreenValue.text = progress.toString()
                    R.id.seek_bar_blue -> tvBlueValue.text = progress.toString()
                }
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }

        seekBarRed.setOnSeekBarChangeListener(colorChangeListener)
        seekBarGreen.setOnSeekBarChangeListener(colorChangeListener)
        seekBarBlue.setOnSeekBarChangeListener(colorChangeListener)

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
        
        // 主要SeekBar
        seekBar.progress = clampedProgress
        seekBarCustom.progress = clampedProgress
        
        // 自定义View
        customProgressView.setProgress(clampedProgress)
        gradientProgressView.setProgress(clampedProgress)
    }

    /**
     * 更新特殊 Slider 的初始值
     */
    private fun updateSpecialSliders() {
        // 离散值滑块
        tvDiscreteValue.text = "当前值: ${seekBarDiscrete.progress}"
        
        // 音量控制
        tvVolumeValue.text = "${seekBarVolume.progress}%"
        
        // 温度调节
        val temperature = 16 + seekBarTemperature.progress
        tvTemperatureValue.text = "${temperature}°C"
        
        // RGB 值显示
        tvRedValue.text = seekBarRed.progress.toString()
        tvGreenValue.text = seekBarGreen.progress.toString()
        tvBlueValue.text = seekBarBlue.progress.toString()
    }

    /**
     * 更新颜色预览
     */
    private fun updateColorPreview() {
        val red = seekBarRed.progress
        val green = seekBarGreen.progress
        val blue = seekBarBlue.progress
        
        val color = Color.rgb(red, green, blue)
        colorPreview.setBackgroundColor(color)
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
                        showToast("进度演示完成！")
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