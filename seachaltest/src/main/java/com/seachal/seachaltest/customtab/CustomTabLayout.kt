package com.seachal.seachaltest.customtab

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.seachal.seachaltest.R

/**
 * 自定义TabLayout，使用RecyclerView实现，可以更灵活地自定义indicator
 */
class CustomTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    // RecyclerView用于显示Tab项
    private val recyclerView: RecyclerView
    
    // 指示器View
    private val indicatorView: IndicatorView
    
    // Tab适配器
    private val tabAdapter: TabAdapter
    
    // 当前选中的位置
    private var currentPosition = 0
    
    // 监听器
    private var onTabSelectedListener: OnTabSelectedListener? = null
    
    // Tab项数据
    private val tabItems = mutableListOf<String>()
    
    // 指示器颜色
    private var indicatorColor = ContextCompat.getColor(context, R.color.colorAccent)
    
    // 指示器高度
    private var indicatorHeight = context.resources.getDimensionPixelSize(R.dimen.dp_px_4)
    
    // 指示器宽度模式（0: 固定宽度, 1: 跟随文本宽度）
    private var indicatorWidthMode = 1
    
    // 固定宽度值
    private var indicatorFixedWidth = context.resources.getDimensionPixelSize(R.dimen.dp_px_20)
    
    // 指示器圆角
    private var indicatorRadius = context.resources.getDimensionPixelSize(R.dimen.dp_px_2)
    
    // 选中文本颜色
    private var selectedTextColor = ContextCompat.getColor(context, R.color.colorAccent)
    
    // 未选中文本颜色
    private var normalTextColor = ContextCompat.getColor(context, android.R.color.black)
    
    // 关联的ViewPager2
    private var viewPager2: ViewPager2? = null
    
    // 页面切换回调
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            setCurrentItem(position, true)
        }
    }

    init {
        // 加载自定义属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTabLayout)
        indicatorColor = typedArray.getColor(R.styleable.CustomTabLayout_o_indicatorColor, indicatorColor)
        indicatorHeight = typedArray.getDimensionPixelSize(R.styleable.CustomTabLayout_o_indicatorHeight, indicatorHeight)
        indicatorWidthMode = typedArray.getInt(R.styleable.CustomTabLayout_o_indicatorWidthMode, indicatorWidthMode)
        indicatorFixedWidth = typedArray.getDimensionPixelSize(R.styleable.CustomTabLayout_o_indicatorFixedWidth, indicatorFixedWidth)
        indicatorRadius = typedArray.getDimensionPixelSize(R.styleable.CustomTabLayout_o_indicatorRadius, indicatorRadius)
        selectedTextColor = typedArray.getColor(R.styleable.CustomTabLayout_o_selectedTextColor, selectedTextColor)
        normalTextColor = typedArray.getColor(R.styleable.CustomTabLayout_o_normalTextColor, normalTextColor)
        typedArray.recycle()
        
        // 初始化布局
        val view = LayoutInflater.from(context).inflate(R.layout.o_layout_custom_tab, this, true)
        recyclerView = view.findViewById(R.id.o_rv_tabs)
        
        // 设置水平布局管理器
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        
        // 初始化适配器
        tabAdapter = TabAdapter()
        recyclerView.adapter = tabAdapter
        
        // 初始化指示器
        indicatorView = IndicatorView(context)
        addView(indicatorView)
    }
    
    /**
     * 设置Tab数据
     */
    fun setTabItems(items: List<String>) {
        tabItems.clear()
        tabItems.addAll(items)
        tabAdapter.notifyDataSetChanged()
        
        // 默认选中第一项
        if (tabItems.isNotEmpty() && currentPosition == 0) {
            post {
                updateIndicator(0, false)
            }
        }
    }
    
    /**
     * 设置当前选中项
     */
    fun setCurrentItem(position: Int, smoothScroll: Boolean = true) {
        if (position < 0 || position >= tabItems.size) return
        
        // 更新选中状态
        val oldPosition = currentPosition
        currentPosition = position
        tabAdapter.notifyItemChanged(oldPosition)
        tabAdapter.notifyItemChanged(currentPosition)
        
        // 滚动到可见位置
        recyclerView.smoothScrollToPosition(position)
        
        // 更新指示器位置
        updateIndicator(position, smoothScroll)
        
        // 通知监听器
        onTabSelectedListener?.onTabSelected(position)
        
        // 联动ViewPager2
        if (viewPager2?.currentItem != position) {
            viewPager2?.setCurrentItem(position, smoothScroll)
        }
    }
    
    /**
     * 更新指示器位置
     */
    private fun updateIndicator(position: Int, smoothScroll: Boolean) {
        if (position < 0 || position >= tabItems.size) return
        
        post {
            val targetView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
            targetView?.let {
                indicatorView.updateIndicator(it, smoothScroll)
            }
        }
    }
    
    /**
     * 设置Tab选中监听器
     */
    fun setOnTabSelectedListener(listener: OnTabSelectedListener) {
        this.onTabSelectedListener = listener
    }
    
    /**
     * 关联ViewPager2
     */
    fun setupWithViewPager2(viewPager2: ViewPager2) {
        // 移除旧的回调
        this.viewPager2?.unregisterOnPageChangeCallback(pageChangeCallback)
        
        // 设置新的ViewPager2
        this.viewPager2 = viewPager2
        viewPager2.registerOnPageChangeCallback(pageChangeCallback)
        
        // 初始同步状态
        setCurrentItem(viewPager2.currentItem, false)
    }
    
    /**
     * Tab适配器
     */
    inner class TabAdapter : RecyclerView.Adapter<TabAdapter.TabViewHolder>() {
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.o_item_tab, parent, false)
            return TabViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
            holder.bind(tabItems[position], position == currentPosition)
        }
        
        override fun getItemCount(): Int = tabItems.size
        
        inner class TabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val textView: TextView = itemView.findViewById(R.id.o_tv_tab)
            
            init {
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        setCurrentItem(position)
                    }
                }
            }
            
            fun bind(title: String, isSelected: Boolean) {
                textView.text = title
                textView.setTextColor(if (isSelected) selectedTextColor else normalTextColor)
                // 可以在这里设置选中状态的其他样式，如字体粗细等
            }
        }
    }
    
    /**
     * 指示器View
     */
    inner class IndicatorView(context: Context) : View(context) {
        private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        private val rectF = RectF()
        
        // 当前指示器位置
        private var currentLeft = 0f
        private var currentWidth = 0f
        
        // 目标指示器位置
        private var targetLeft = 0f
        private var targetWidth = 0f
        
        // 动画进度
        private var animationProgress = 1f
        
        init {
            paint.color = indicatorColor
        }
        
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            
            // 计算当前指示器位置
            val left = currentLeft + (targetLeft - currentLeft) * animationProgress
            val width = currentWidth + (targetWidth - currentWidth) * animationProgress
            
            // 绘制指示器
            rectF.set(left, height - indicatorHeight.toFloat(), left + width, height.toFloat())
            canvas.drawRoundRect(rectF, indicatorRadius.toFloat(), indicatorRadius.toFloat(), paint)
        }
        
        /**
         * 更新指示器位置
         */
        fun updateIndicator(targetView: View, animate: Boolean) {
            // 保存当前位置作为动画起点
            currentLeft = targetLeft
            currentWidth = targetWidth
            
            // 计算目标位置
            targetLeft = targetView.left.toFloat()
            targetWidth = when (indicatorWidthMode) {
                0 -> indicatorFixedWidth.toFloat() // 固定宽度
                else -> targetView.width.toFloat() // 跟随文本宽度
            }
            
            // 居中显示
            if (indicatorWidthMode == 0) {
                targetLeft += (targetView.width - indicatorFixedWidth) / 2f
            }
            
            // 是否执行动画
            if (animate) {
                animationProgress = 0f
                animateIndicator()
            } else {
                animationProgress = 1f
                invalidate()
            }
        }
        
        /**
         * 执行指示器动画
         */
        private fun animateIndicator() {
            val animator = android.animation.ValueAnimator.ofFloat(0f, 1f)
            animator.duration = 250 // 动画时长
            animator.addUpdateListener { animation ->
                animationProgress = animation.animatedValue as Float
                invalidate()
            }
            animator.start()
        }
    }
    
    /**
     * Tab选中监听器接口
     */
    interface OnTabSelectedListener {
        fun onTabSelected(position: Int)
    }
}