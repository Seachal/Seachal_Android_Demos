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
import com.google.android.material.slider.Slider
import com.google.android.material.slider.RangeSlider
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
    private lateinit var seekBarCustomBeautiful: SeekBar // 新增的美观进度条
    private lateinit var seekBarDiscrete: SeekBar
    private lateinit var seekBarVolume: SeekBar
    private lateinit var seekBarTemperature: SeekBar
    private lateinit var seekBarRed: SeekBar
    private lateinit var seekBarGreen: SeekBar
    private lateinit var seekBarBlue: SeekBar
    
    // Material Design Slider
    private lateinit var materialSliderBasic: Slider
    private lateinit var materialSliderLabeled: Slider
    private lateinit var materialSliderStep: Slider
    private lateinit var materialRangeSlider: RangeSlider
    private lateinit var materialSliderCustom: Slider
    private lateinit var materialSliderVertical: Slider
    
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
    private lateinit var tvCustomProgress: TextView // 美观进度条的文本显示
    private lateinit var tvDiscreteValue: TextView
    private lateinit var tvVolumeValue: TextView
    private lateinit var tvTemperatureValue: TextView
    private lateinit var tvRedValue: TextView
    private lateinit var tvGreenValue: TextView
            private lateinit var tvBlueValue: TextView
        private lateinit var colorPreview: View
        
            // Material Design Slider 文本显示
    private lateinit var tvMaterialBasicValue: TextView
    private lateinit var tvMaterialStepValue: TextView
    private lateinit var tvMaterialRangeValue: TextView
    private lateinit var tvMaterialCustomValue: TextView
    private lateinit var tvMaterialVerticalValue: TextView
    
    // 美观渐变进度条
    private lateinit var progressBarGradient: ProgressBar
    private lateinit var seekBarGradient: SeekBar
    private lateinit var sliderGradientMaterial: Slider
    private lateinit var beautifulProgressView: BeautifulProgressView
    
    // 美观进度条文本显示
    private lateinit var tvGradientProgressValue: TextView
    private lateinit var tvGradientSeekBarValue: TextView
    private lateinit var tvGradientMaterialValue: TextView
    private lateinit var tvBeautifulProgressValue: TextView
    
    // 美观进度条按钮
    private lateinit var btnBeautifulAnimate: Button
    private lateinit var btnBeautifulReset: Button
    
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
        seekBarCustomBeautiful = findViewById(R.id.seekbar_custom_beautiful) // 美观进度条
        seekBarDiscrete = findViewById(R.id.seek_bar_discrete)
        seekBarVolume = findViewById(R.id.seek_bar_volume)
        seekBarTemperature = findViewById(R.id.seek_bar_temperature)
        seekBarRed = findViewById(R.id.seek_bar_red)
        seekBarGreen = findViewById(R.id.seek_bar_green)
        seekBarBlue = findViewById(R.id.seek_bar_blue)
        
        // Material Design Slider
        materialSliderBasic = findViewById(R.id.material_slider_basic)
        materialSliderLabeled = findViewById(R.id.material_slider_labeled)
        materialSliderStep = findViewById(R.id.material_slider_step)
        materialRangeSlider = findViewById(R.id.material_range_slider)
        materialSliderCustom = findViewById(R.id.material_slider_custom)
        materialSliderVertical = findViewById(R.id.material_slider_vertical)
        
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
        tvCustomProgress = findViewById(R.id.tv_custom_progress) // 美观进度条文本
        tvDiscreteValue = findViewById(R.id.tv_discrete_value)
        tvVolumeValue = findViewById(R.id.tv_volume_value)
        tvTemperatureValue = findViewById(R.id.tv_temperature_value)
        tvRedValue = findViewById(R.id.tv_red_value)
        tvGreenValue = findViewById(R.id.tv_green_value)
        tvBlueValue = findViewById(R.id.tv_blue_value)
        colorPreview = findViewById(R.id.color_preview)
        
        // Material Design Slider 文本显示
        tvMaterialBasicValue = findViewById(R.id.tv_material_basic_value)
        tvMaterialStepValue = findViewById(R.id.tv_material_step_value)
        tvMaterialRangeValue = findViewById(R.id.tv_material_range_value)
        tvMaterialCustomValue = findViewById(R.id.tv_material_custom_value)
        tvMaterialVerticalValue = findViewById(R.id.tv_material_vertical_value)
        
        // 美观渐变进度条
        progressBarGradient = findViewById(R.id.progress_bar_gradient)
        seekBarGradient = findViewById(R.id.seekbar_gradient)
        sliderGradientMaterial = findViewById(R.id.slider_gradient_material)
        beautifulProgressView = findViewById(R.id.beautiful_progress_view)
        
        // 美观进度条文本显示
        tvGradientProgressValue = findViewById(R.id.tv_gradient_progress_value)
        tvGradientSeekBarValue = findViewById(R.id.tv_gradient_seekbar_value)
        tvGradientMaterialValue = findViewById(R.id.tv_gradient_material_value)
        tvBeautifulProgressValue = findViewById(R.id.tv_beautiful_progress_value)
        
        // 美观进度条按钮
        btnBeautifulAnimate = findViewById(R.id.btn_beautiful_animate)
        btnBeautifulReset = findViewById(R.id.btn_beautiful_reset)
        
        // 设置初始进度
        val initialProgress = 30
        updateAllProgress(initialProgress)
        updateSpecialSliders()
        
        // 设置 Material Design Slider 初始值
        materialRangeSlider.values = listOf(25f, 75f)
        
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

        // 美观进度条监听器
        seekBarCustomBeautiful.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateAllProgress(progress)
                    tvProgress.text = "进度: ${progress}%"
                    tvSeekBarProgress.text = "SeekBar进度: $progress"
                }
                tvCustomProgress.text = "${progress}%"
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

        // Material Design Slider 监听器
        setupMaterialSliderListeners()
        
        // 美观渐变进度条监听器
        setupBeautifulProgressListeners()
    }

    /**
     * 设置美观渐变进度条监听器
     */
    private fun setupBeautifulProgressListeners() {
        // 渐变 SeekBar 监听器
        seekBarGradient.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvGradientSeekBarValue.text = "当前值: $progress"
                
                // 同步更新其他美观进度条
                if (fromUser) {
                    progressBarGradient.progress = progress
                    sliderGradientMaterial.value = progress.toFloat()
                    beautifulProgressView.setProgress(progress.toFloat())
                    
                    // 更新文本显示
                    tvGradientProgressValue.text = "进度: ${progress}%"
                    tvGradientMaterialValue.text = "Material 值: $progress"
                    tvBeautifulProgressValue.text = "精美进度: ${progress}%"
                }
            }
            
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        
        // Material Slider 监听器
        sliderGradientMaterial.addOnChangeListener { slider, value, fromUser ->
            tvGradientMaterialValue.text = "Material 值: ${value.toInt()}"
            
            if (fromUser) {
                val intValue = value.toInt()
                progressBarGradient.progress = intValue
                seekBarGradient.progress = intValue
                beautifulProgressView.setProgress(value)
                
                // 更新文本显示
                tvGradientProgressValue.text = "进度: ${intValue}%"
                tvGradientSeekBarValue.text = "当前值: $intValue"
                tvBeautifulProgressValue.text = "精美进度: ${intValue}%"
            }
        }
        
        // 美观自定义View按钮监听器
        btnBeautifulAnimate.setOnClickListener {
            val targetProgress = (0..100).random().toFloat()
            beautifulProgressView.setProgressWithAnimation(targetProgress, 2000)
            
            // 同步更新其他控件
            progressBarGradient.progress = targetProgress.toInt()
            seekBarGradient.progress = targetProgress.toInt()
            sliderGradientMaterial.value = targetProgress
            
            // 更新文本显示
            val intValue = targetProgress.toInt()
            tvGradientProgressValue.text = "进度: ${intValue}%"
            tvGradientSeekBarValue.text = "当前值: $intValue"
            tvGradientMaterialValue.text = "Material 值: $intValue"
            tvBeautifulProgressValue.text = "精美进度: ${intValue}%"
        }
        
        btnBeautifulReset.setOnClickListener {
            // 重置所有美观进度条
            progressBarGradient.progress = 0
            seekBarGradient.progress = 0
            sliderGradientMaterial.value = 0f
            beautifulProgressView.setProgressWithAnimation(0f, 500)
            
            // 重置文本显示
            tvGradientProgressValue.text = "进度: 0%"
            tvGradientSeekBarValue.text = "当前值: 0"
            tvGradientMaterialValue.text = "Material 值: 0"
            tvBeautifulProgressValue.text = "精美进度: 0%"
        }
    }

    /**
     * 设置 Material Design Slider 监听器
     */
    private fun setupMaterialSliderListeners() {
        // 基础 Material Slider
        materialSliderBasic.addOnChangeListener { slider, value, fromUser ->
            tvMaterialBasicValue.text = "当前值: ${value.toInt()}"
        }

        // 步进 Material Slider
        materialSliderStep.addOnChangeListener { slider, value, fromUser ->
            tvMaterialStepValue.text = "当前步进值: ${value.toInt()}"
        }

        // 范围选择 Material RangeSlider
        materialRangeSlider.addOnChangeListener { rangeSlider, value, fromUser ->
            val values = rangeSlider.values
            if (values.size >= 2) {
                val min = values[0].toInt()
                val max = values[1].toInt()
                tvMaterialRangeValue.text = "选择范围: $min - $max"
            }
        }

        // 自定义样式 Material Slider（音量控制）
        materialSliderCustom.addOnChangeListener { slider, value, fromUser ->
            tvMaterialCustomValue.text = "音量: ${value.toInt()}%"
        }

        // 垂直 Material Slider
        materialSliderVertical.addOnChangeListener { slider, value, fromUser ->
            tvMaterialVerticalValue.text = "${value.toInt()}%"
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
        seekBarCustomBeautiful.progress = clampedProgress // 美观进度条同步
        
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