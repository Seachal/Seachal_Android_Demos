package com.seachal.seachaltest.checkbox_radiobutton

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R

/**
 * CheckBox 和 RadioButton 使用示例
 * 包含自定义样式和颜色
 * 
 * @author Seachal
 * @date 2025-01-27
 */
class CheckBoxRadioButtonActivity : AppCompatActivity() {

    // CheckBox 相关控件
    private lateinit var cbDefault: CheckBox
    private lateinit var cbCustom1: CheckBox
    private lateinit var cbCustom2: CheckBox
    private lateinit var cbCustom3: CheckBox
    private lateinit var cbSelectAll: CheckBox
    private lateinit var tvCheckBoxResult: TextView

    // RadioButton 相关控件
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var rbOther: RadioButton
    
    private lateinit var rgColor: RadioGroup
    private lateinit var rbRed: RadioButton
    private lateinit var rbGreen: RadioButton
    private lateinit var rbBlue: RadioButton
    private lateinit var tvRadioResult: TextView

    // 兴趣爱好相关控件
    private lateinit var cbSports: CheckBox
    private lateinit var cbMusic: CheckBox
    private lateinit var cbReading: CheckBox
    private lateinit var cbTravel: CheckBox

    // 兴趣爱好 CheckBox 列表
    private lateinit var hobbyCheckBoxes: List<CheckBox>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkbox_radiobutton)

        initViews()
        setupCheckBoxListeners()
        setupRadioButtonListeners()
        demonstrateCustomStyles()
    }

    /**
     * 初始化视图控件
     */
    private fun initViews() {
        // CheckBox 控件初始化
        cbDefault = findViewById(R.id.cb_default)
        cbCustom1 = findViewById(R.id.cb_custom1)
        cbCustom2 = findViewById(R.id.cb_custom2)
        cbCustom3 = findViewById(R.id.cb_custom3)
        cbSelectAll = findViewById(R.id.cb_select_all)
        tvCheckBoxResult = findViewById(R.id.tv_checkbox_result)

        // RadioButton 控件初始化
        rgGender = findViewById(R.id.rg_gender)
        rbMale = findViewById(R.id.rb_male)
        rbFemale = findViewById(R.id.rb_female)
        rbOther = findViewById(R.id.rb_other)
        
        rgColor = findViewById(R.id.rg_color)
        rbRed = findViewById(R.id.rb_red)
        rbGreen = findViewById(R.id.rb_green)
        rbBlue = findViewById(R.id.rb_blue)
        tvRadioResult = findViewById(R.id.tv_radio_result)

        // 兴趣爱好 CheckBox 初始化
        cbSports = findViewById(R.id.cb_sports)
        cbMusic = findViewById(R.id.cb_music)
        cbReading = findViewById(R.id.cb_reading)
        cbTravel = findViewById(R.id.cb_travel)
        
        // 创建兴趣爱好 CheckBox 列表
        hobbyCheckBoxes = listOf(cbSports, cbMusic, cbReading, cbTravel)
    }

    /**
     * 设置 CheckBox 监听器
     */
    private fun setupCheckBoxListeners() {
        // 默认 CheckBox 监听
        cbDefault.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "默认CheckBox已选中" else "默认CheckBox已取消"
            showToast(message)
        }

        // 自定义样式 CheckBox 监听
        cbCustom1.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "自定义样式1已选中" else "自定义样式1已取消"
            showToast(message)
        }

        cbCustom2.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "自定义样式2已选中" else "自定义样式2已取消"
            showToast(message)
        }

        cbCustom3.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "代码设置样式已选中" else "代码设置样式已取消"
            showToast(message)
        }

        // 全选 CheckBox 监听
        cbSelectAll.setOnCheckedChangeListener { _, isChecked ->
            // 同步所有兴趣爱好的选中状态
            hobbyCheckBoxes.forEach { checkBox ->
                checkBox.isChecked = isChecked
            }
            updateCheckBoxResult()
        }

        // 兴趣爱好 CheckBox 监听
        hobbyCheckBoxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, _ ->
                updateSelectAllStatus()
                updateCheckBoxResult()
            }
        }
    }

    /**
     * 设置 RadioButton 监听器
     */
    private fun setupRadioButtonListeners() {
        // 性别选择监听
        rgGender.setOnCheckedChangeListener { _, checkedId ->
            val selectedGender = when (checkedId) {
                R.id.rb_male -> "男性"
                R.id.rb_female -> "女性"
                R.id.rb_other -> "其他"
                else -> "未选择"
            }
            showToast("选择性别: $selectedGender")
            updateRadioResult()
        }

        // 颜色选择监听
        rgColor.setOnCheckedChangeListener { _, checkedId ->
            val selectedColor = when (checkedId) {
                R.id.rb_red -> "红色"
                R.id.rb_green -> "绿色"
                R.id.rb_blue -> "蓝色"
                else -> "未选择"
            }
            showToast("选择颜色: $selectedColor")
            updateRadioResult()
        }
    }

    /**
     * 更新全选状态
     */
    private fun updateSelectAllStatus() {
        val allChecked = hobbyCheckBoxes.all { it.isChecked }
        val noneChecked = hobbyCheckBoxes.none { it.isChecked }
        
        cbSelectAll.setOnCheckedChangeListener(null) // 临时移除监听，避免递归调用
        
        when {
            allChecked -> {
                cbSelectAll.isChecked = true
                // 全部选中状态
                cbSelectAll.text = "取消全选 ✅"
            }
            noneChecked -> {
                cbSelectAll.isChecked = false
                // 全部未选中状态
                cbSelectAll.text = "全选 ☑️"
            }
            else -> {
                // 部分选中状态 - 通过文本提示用户
                cbSelectAll.isChecked = false
                cbSelectAll.text = "部分选中 ⚪ (点击全选)"
            }
        }
        
        // 重新设置监听器
        cbSelectAll.setOnCheckedChangeListener { _, isChecked ->
            hobbyCheckBoxes.forEach { checkBox ->
                checkBox.isChecked = isChecked
            }
            updateCheckBoxResult()
        }
    }

    /**
     * 更新 CheckBox 结果显示
     */
    private fun updateCheckBoxResult() {
        val selectedHobbies = mutableListOf<String>()
        
        if (cbSports.isChecked) selectedHobbies.add("运动")
        if (cbMusic.isChecked) selectedHobbies.add("音乐")
        if (cbReading.isChecked) selectedHobbies.add("阅读")
        if (cbTravel.isChecked) selectedHobbies.add("旅行")
        
        val result = if (selectedHobbies.isEmpty()) {
            "未选择任何兴趣爱好"
        } else {
            "选择的兴趣爱好: ${selectedHobbies.joinToString(", ")}"
        }
        
        tvCheckBoxResult.text = result
    }

    /**
     * 更新 RadioButton 结果显示
     */
    private fun updateRadioResult() {
        val selectedGender = when (rgGender.checkedRadioButtonId) {
            R.id.rb_male -> "男性"
            R.id.rb_female -> "女性"
            R.id.rb_other -> "其他"
            else -> "未选择"
        }
        
        val selectedColor = when (rgColor.checkedRadioButtonId) {
            R.id.rb_red -> "红色"
            R.id.rb_green -> "绿色"
            R.id.rb_blue -> "蓝色"
            else -> "未选择"
        }
        
        tvRadioResult.text = "性别: $selectedGender, 喜欢的颜色: $selectedColor"
    }

    /**
     * 演示自定义样式设置（代码方式）
     */
    private fun demonstrateCustomStyles() {
        // 代码方式设置 CheckBox 样式
        cbCustom3.apply {
            // 设置选中时的颜色（需要 API 21+）
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                buttonTintList = ContextCompat.getColorStateList(this@CheckBoxRadioButtonActivity, R.color.checkbox_custom_tint)
            }
            
            // 设置文字颜色
            setTextColor(ContextCompat.getColor(this@CheckBoxRadioButtonActivity, R.color.cb_text_custom))
            
            // 设置文字大小
            textSize = 16f
        }

        // 代码方式设置 RadioButton 样式
        rbBlue.apply {
            // 设置选中时的颜色（需要 API 21+）
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                buttonTintList = ContextCompat.getColorStateList(this@CheckBoxRadioButtonActivity, R.color.radiobutton_blue_tint)
            }
            
            // 设置文字颜色
            setTextColor(Color.BLUE)
        }
    }

    /**
     * 显示提示信息
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * 重置所有选择
     */
    fun resetAllSelections() {
        // 重置 CheckBox
        hobbyCheckBoxes.forEach { it.isChecked = false }
        cbSelectAll.isChecked = false
        cbSelectAll.text = "全选 ☑️"  // 重置全选按钮文本
        cbDefault.isChecked = false
        cbCustom1.isChecked = false
        cbCustom2.isChecked = false
        cbCustom3.isChecked = false
        
        // 重置 RadioButton
        rgGender.clearCheck()
        rgColor.clearCheck()
        
        // 更新显示
        updateCheckBoxResult()
        updateRadioResult()
        
        showToast("已重置所有选择")
    }

    /**
     * 显示选择结果（按钮点击方法）
     */
    fun showSelectedResults() {
        val checkBoxResult = tvCheckBoxResult.text.toString()
        val radioResult = tvRadioResult.text.toString()
        
        val fullResult = """
            ===== 选择结果汇总 =====
            
            CheckBox 结果:
            $checkBoxResult
            
            RadioButton 结果:
            $radioResult
            
            =====================
        """.trimIndent()
        
        // 显示 Dialog 展示完整结果
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("选择结果汇总")
            .setMessage(fullResult)
            .setPositiveButton("确定", null)
            .show()
    }
} 