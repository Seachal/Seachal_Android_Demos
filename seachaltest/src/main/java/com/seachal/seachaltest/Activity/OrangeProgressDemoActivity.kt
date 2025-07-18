package com.seachal.seachaltest.activity

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import com.seachal.seachaltest.R
import com.seachal.seachaltest.customview.OrangeProgressBar
import kotlin.random.Random

/**
 * OrangeProgressBar 颜色自定义演示页面
 * 展示如何通过 XML 和代码设置自定义颜色
 * 
 * @author Seachal
 * @date 2025-01-27
 */
class OrangeProgressDemoActivity : Activity() {

    private lateinit var progressBar1: OrangeProgressBar
    private lateinit var progressBar2: OrangeProgressBar
    private lateinit var progressBar3: OrangeProgressBar
    private lateinit var progressBar4: OrangeProgressBar
    private lateinit var btnChangeColors: Button
    private lateinit var btnAnimate: Button

    // 预定义的颜色主题
    private val colorThemes = arrayOf(
        // 紫色主题
        Triple(Color.parseColor("#9C27B0"), Color.parseColor("#F3E5F5"), Color.parseColor("#7B1FA2")),
        // 红色主题
        Triple(Color.parseColor("#F44336"), Color.parseColor("#FFEBEE"), Color.parseColor("#D32F2F")),
        // 青色主题
        Triple(Color.parseColor("#00BCD4"), Color.parseColor("#E0F2F1"), Color.parseColor("#0097A7")),
        // 深橙色主题
        Triple(Color.parseColor("#FF5722"), Color.parseColor("#FBE9E7"), Color.parseColor("#D84315")),
        // 靛蓝色主题
        Triple(Color.parseColor("#3F51B5"), Color.parseColor("#E8EAF6"), Color.parseColor("#303F9F")),
        // 棕色主题
        Triple(Color.parseColor("#795548"), Color.parseColor("#EFEBE9"), Color.parseColor("#5D4037"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orange_progress_demo)

        initViews()
        setupListeners()
        setupInitialColors()
    }

    private fun initViews() {
        progressBar1 = findViewById(R.id.progressBar1)
        progressBar2 = findViewById(R.id.progressBar2)
        progressBar3 = findViewById(R.id.progressBar3)
        progressBar4 = findViewById(R.id.progressBar4)
        btnChangeColors = findViewById(R.id.btnChangeColors)
        btnAnimate = findViewById(R.id.btnAnimate)
    }

    private fun setupListeners() {
        // 随机颜色按钮
        btnChangeColors.setOnClickListener {
            changeRandomColors()
        }

        // 动画演示按钮
        btnAnimate.setOnClickListener {
            animateAllProgressBars()
        }
    }

    private fun setupInitialColors() {
        // 为第四个进度条设置紫色主题（代码设置）
        val purpleTheme = colorThemes[0]
        progressBar4.setColors(
            progressColor = purpleTheme.first,
            backgroundColor = purpleTheme.second,
            thumbColor = purpleTheme.third
        )
    }

    /**
     * 为所有进度条设置随机颜色主题
     */
    private fun changeRandomColors() {
        val randomThemes = colorThemes.toList().shuffled().take(4)
        
        // 为每个进度条设置不同的随机主题
        progressBar1.setColors(
            progressColor = randomThemes[0].first,
            backgroundColor = randomThemes[0].second,
            thumbColor = randomThemes[0].third
        )
        
        progressBar2.setColors(
            progressColor = randomThemes[1].first,
            backgroundColor = randomThemes[1].second,
            thumbColor = randomThemes[1].third
        )
        
        progressBar3.setColors(
            progressColor = randomThemes[2].first,
            backgroundColor = randomThemes[2].second,
            thumbColor = randomThemes[2].third
        )
        
        progressBar4.setColors(
            progressColor = randomThemes[3].first,
            backgroundColor = randomThemes[3].second,
            thumbColor = randomThemes[3].third
        )
    }

    /**
     * 为所有进度条执行动画演示
     */
    private fun animateAllProgressBars() {
        // 生成随机进度值
        val randomProgress1 = Random.nextInt(20, 100)
        val randomProgress2 = Random.nextInt(20, 100)
        val randomProgress3 = Random.nextInt(20, 100)
        val randomProgress4 = Random.nextInt(20, 100)

        // 执行动画，每个进度条的动画稍微延迟
        progressBar1.setProgressWithAnimation(randomProgress1, 800)
        
        progressBar1.postDelayed({
            progressBar2.setProgressWithAnimation(randomProgress2, 800)
        }, 200)
        
        progressBar1.postDelayed({
            progressBar3.setProgressWithAnimation(randomProgress3, 800)
        }, 400)
        
        progressBar1.postDelayed({
            progressBar4.setProgressWithAnimation(randomProgress4, 800)
        }, 600)
    }

    /**
     * 演示单独设置每个颜色属性的方法
     */
    private fun demonstrateIndividualColorSetting() {
        // 示例：单独设置每个颜色属性
        progressBar4.setProgressColor(Color.parseColor("#E91E63")) // 设置进度颜色为粉色
        progressBar4.setProgressBackgroundColor(Color.parseColor("#FCE4EC")) // 设置背景色为浅粉色
        progressBar4.setThumbColor(Color.parseColor("#C2185B")) // 设置指示器颜色为深粉色
        
        // 或者使用 getter 方法获取当前颜色
        val currentProgressColor = progressBar4.getProgressColor()
        val currentBackgroundColor = progressBar4.getProgressBackgroundColor()
        val currentThumbColor = progressBar4.getThumbColor()
        
        // 输出到日志查看
        android.util.Log.d("OrangeProgressDemo", 
            "当前颜色 - 进度: $currentProgressColor, 背景: $currentBackgroundColor, 指示器: $currentThumbColor")
    }
} 