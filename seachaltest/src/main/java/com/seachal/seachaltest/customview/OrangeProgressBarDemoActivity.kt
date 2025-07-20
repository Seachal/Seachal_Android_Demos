package com.seachal.seachaltest.customview

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R

/**
 * OrangeProgressBar 演示Activity
 * 展示自定义进度条的各种功能和配置
 * 
 * @author Seachal
 * @date 2025-01-27
 */
class OrangeProgressBarDemoActivity : AppCompatActivity() {

    private lateinit var orangeProgressBar1: OrangeProgressBar
    private lateinit var orangeProgressBar2: OrangeProgressBar
    private lateinit var orangeProgressBar3: OrangeProgressBar
    private lateinit var orangeProgressBar4: OrangeProgressBar
    
    private lateinit var seekBarController: SeekBar
    private lateinit var tvProgress: TextView
    private lateinit var btnSetProgress: Button
    private lateinit var btnAnimate: Button
    private lateinit var btnReset: Button
    private lateinit var btnChangeColors: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orange_progress_bar_demo)
        
        initViews()
        setupListeners()
        setupDefaultValues()
    }
    
    private fun initViews() {
        // 进度条
        orangeProgressBar1 = findViewById(R.id.orange_progress_bar_1)
        orangeProgressBar2 = findViewById(R.id.orange_progress_bar_2)
        orangeProgressBar3 = findViewById(R.id.orange_progress_bar_3)
        orangeProgressBar4 = findViewById(R.id.orange_progress_bar_4)
        
        // 控制组件
        seekBarController = findViewById(R.id.seek_bar_controller)
        tvProgress = findViewById(R.id.tv_progress)
        btnSetProgress = findViewById(R.id.btn_set_progress)
        btnAnimate = findViewById(R.id.btn_animate)
        btnReset = findViewById(R.id.btn_reset)
        btnChangeColors = findViewById(R.id.btn_change_colors)
    }
    
    private fun setupListeners() {
        // SeekBar控制器监听
        seekBarController.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    updateAllProgressBars(progress)
                }
            }
            
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        
        // 设置进度按钮
        btnSetProgress.setOnClickListener {
            val randomProgress = (0..100).random()
            updateAllProgressBars(randomProgress)
            seekBarController.progress = randomProgress
        }
        
        // 动画按钮
        btnAnimate.setOnClickListener {
            val targetProgress = (50..100).random()
            orangeProgressBar1.setProgressWithAnimation(targetProgress, 1000)
            orangeProgressBar2.setProgressWithAnimation(targetProgress, 1500)
            orangeProgressBar3.setProgressWithAnimation(targetProgress, 2000)
            orangeProgressBar4.setProgressWithAnimation(targetProgress, 2500)
            seekBarController.progress = targetProgress
        }
        
        // 重置按钮
        btnReset.setOnClickListener {
            updateAllProgressBars(0)
            seekBarController.progress = 0
        }
        
        // 改变颜色按钮
        btnChangeColors.setOnClickListener {
            changeRandomColors()
        }
    }
    
    private fun setupDefaultValues() {
        // 设置不同的配置来展示各种效果
        
        // 第一个：默认配置
        orangeProgressBar1.setProgress(30)
        
        val density = resources.displayMetrics.density
        
        // 第二个：大指示器（thumbSize 28dp）
        orangeProgressBar2.setThumbSize(28f * density)
        orangeProgressBar2.setProgress(50)
        
        // 第三个：高进度条（progressBarHeight 24dp）
        orangeProgressBar3.setProgressBarHeight(24f * density)
        orangeProgressBar3.setProgress(70)
        
        // 第四个：自定义颜色
        orangeProgressBar4.setProgressColor(Color.parseColor("#4CAF50"))
        orangeProgressBar4.setProgressBackgroundColor(Color.parseColor("#E8F5E8"))
        orangeProgressBar4.setProgress(85)
        
        // 同步SeekBar
        seekBarController.progress = 30
        updateProgressText(30)
    }
    
    private fun updateAllProgressBars(progress: Int) {
        orangeProgressBar1.setProgress(progress)
        orangeProgressBar2.setProgress(progress)
        orangeProgressBar3.setProgress(progress)
        orangeProgressBar4.setProgress(progress)
        updateProgressText(progress)
    }
    
    private fun updateProgressText(progress: Int) {
        tvProgress.text = "当前进度: ${progress}%"
    }
    
    private fun changeRandomColors() {
        val colors = arrayOf(
            Color.parseColor("#FF5722"), // 橙红
            Color.parseColor("#2196F3"), // 蓝色
            Color.parseColor("#4CAF50"), // 绿色
            Color.parseColor("#9C27B0"), // 紫色
            Color.parseColor("#FF9800"), // 橙色
            Color.parseColor("#F44336")  // 红色
        )
        
        val backgrounds = arrayOf(
            Color.parseColor("#FFF3E0"), // 浅橙
            Color.parseColor("#E3F2FD"), // 浅蓝
            Color.parseColor("#E8F5E8"), // 浅绿
            Color.parseColor("#F3E5F5"), // 浅紫
            Color.parseColor("#FFF8E1"), // 浅黄
            Color.parseColor("#FFEBEE")  // 浅红
        )
        
        val randomIndex = colors.indices.random()
        
        orangeProgressBar1.setProgressColor(colors[randomIndex])
        orangeProgressBar1.setProgressBackgroundColor(backgrounds[randomIndex])
        
        val randomIndex2 = colors.indices.random()
        orangeProgressBar2.setProgressColor(colors[randomIndex2])
        orangeProgressBar2.setProgressBackgroundColor(backgrounds[randomIndex2])
        
        val randomIndex3 = colors.indices.random()
        orangeProgressBar3.setProgressColor(colors[randomIndex3])
        orangeProgressBar3.setProgressBackgroundColor(backgrounds[randomIndex3])
        
        val randomIndex4 = colors.indices.random()
        orangeProgressBar4.setProgressColor(colors[randomIndex4])
        orangeProgressBar4.setProgressBackgroundColor(backgrounds[randomIndex4])
    }
} 