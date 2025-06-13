package com.seachal.seachaltest.RecyclerViewTest.grid

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seachal.seachaltest.R

/**
 * GridLayoutManager 演示Activity (Kotlin版本)
 * 演示三种不同的使用场景：
 * 1. 基础场景（无特殊设置）
 * 2. 带ItemDecoration的场景
 * 3. 精确间距控制的场景
 * 
 * @author zhangxc
 * @date 2024-12-27
 */
class GridLayoutManagerDemoActivity : AppCompatActivity(), View.OnClickListener {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvDescription: TextView
    private lateinit var btnBasic: Button
    private lateinit var btnWithDecoration: Button
    private lateinit var btnPreciseSpacing: Button
    
    private var adapter: GridDemoAdapter? = null
    private val dataList = mutableListOf<DemoItem>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_layout_manager_demo)
        
        initViews()
        initData()
        setupBasicGrid() // 默认显示基础场景
    }
    
    private fun initViews() {
        recyclerView = findViewById(R.id.recycler_view)
        tvDescription = findViewById(R.id.tv_description)
        btnBasic = findViewById(R.id.btn_basic)
        btnWithDecoration = findViewById(R.id.btn_with_decoration)
        btnPreciseSpacing = findViewById(R.id.btn_precise_spacing)
        
        // 使用apply简化代码
        listOf(btnBasic, btnWithDecoration, btnPreciseSpacing).forEach { 
            it.setOnClickListener(this) 
        }
    }
    
    private fun initData() {
        dataList.clear()
        repeat(20) { i ->
            dataList.add(DemoItem("Item ${i + 1}", i))
        }
    }
    
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_basic -> setupBasicGrid()
            R.id.btn_with_decoration -> setupGridWithDecoration()
            R.id.btn_precise_spacing -> setupPreciseSpacingGrid()
        }
    }
    
    /**
     * 场景1：基础GridLayoutManager，无特殊设置
     */
    private fun setupBasicGrid() {
        // 清除之前的ItemDecoration
        clearItemDecorations()
        
        // 设置基础的GridLayoutManager，spanCount=2
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        
        adapter?.let {
            it.itemType = GridDemoAdapter.TYPE_BASIC
            it.notifyDataSetChanged()
        } ?: run {
            adapter = GridDemoAdapter(this, dataList, GridDemoAdapter.TYPE_BASIC)
            recyclerView.adapter = adapter
        }
        
        updateButtonStates(btnBasic)
        tvDescription.text = "基础场景：GridLayoutManager(this, 2) 无特殊设置，item自带margin"
    }
    
    /**
     * 场景2：带ItemDecoration的GridLayoutManager
     */
    private fun setupGridWithDecoration() {
        // 清除之前的ItemDecoration
        clearItemDecorations()
        
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        
        // 添加基础ItemDecoration
//        recyclerView.addItemDecoration(GridItemDecoration(
//            horizontalSpacing = dpToPx(16),
//            verticalSpacing = dpToPx(16)
//        ))

        if (recyclerView.itemDecorationCount == 0) {
            recyclerView.run {
                addItemDecoration(
                    GridItemDecoration(
                        resources.getDimension(R.dimen.dp_px_8).toInt(),
                        resources.getDimension(R.dimen.dp_px_16).toInt()
                    )
                )
            }
        }
        
        adapter?.let {
            it.itemType = GridDemoAdapter.TYPE_WITH_DECORATION
            it.notifyDataSetChanged()
        } ?: run {
            adapter = GridDemoAdapter(this, dataList, GridDemoAdapter.TYPE_WITH_DECORATION)
            recyclerView.adapter = adapter
        }
        
        updateButtonStates(btnWithDecoration)
        tvDescription.text = "带ItemDecoration：使用GridItemDecoration设置16dp水平和垂直间距"
    }
    
    /**
     * 场景3：精确间距控制
     * 屏幕宽度假设为750dp，item宽度335dp，水平间距16dp，边距32dp，垂直间距16dp
     */
    private fun setupPreciseSpacingGrid() {
        // 清除之前的ItemDecoration
        clearItemDecorations()
        
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        
        // 添加精确间距的ItemDecoration
        recyclerView.addItemDecoration(
            PreciseSpacingDecoration(
            edgeMargin = resources.getDimension(R.dimen.dp_px_0).toInt(),     // 左右边距
            horizontalSpacing =resources.getDimension(R.dimen.dp_px_16).toInt(), // 水平间距
            verticalSpacing = resources.getDimension(R.dimen.dp_px_16).toInt()   // 垂直间距
        )
        )

        
        adapter?.let {
            it.itemType = GridDemoAdapter.TYPE_PRECISE_SPACING
            it.notifyDataSetChanged()
        } ?: run {
            adapter = GridDemoAdapter(this, dataList, GridDemoAdapter.TYPE_PRECISE_SPACING)
            recyclerView.adapter = adapter
        }
        
        updateButtonStates(btnPreciseSpacing)
        tvDescription.text = "精确间距：屏幕750dp，item 335dp，水平间距16dp，边距32dp，垂直间距16dp"
    }
    
    private fun clearItemDecorations() {
        while (recyclerView.itemDecorationCount > 0) {
            recyclerView.removeItemDecorationAt(0)
        }
    }
    
    private fun updateButtonStates(activeButton: Button) {
        listOf(btnBasic, btnWithDecoration, btnPreciseSpacing).forEach { 
            it.isSelected = false 
        }
        activeButton.isSelected = true
    }
    
    private fun dpToPx(dp: Int): Int {
        val metrics: DisplayMetrics = resources.displayMetrics
        return (dp * metrics.density).toInt()
    }
    
    /**
     * 基础的GridItemDecoration
     */
    class GridItemDecoration(
        private val horizontalSpacing: Int,
        private val verticalSpacing: Int
    ) : RecyclerView.ItemDecoration() {
        
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val layoutManager = parent.layoutManager as? GridLayoutManager ?: return
            
            val position = parent.getChildAdapterPosition(view)
            if (position == RecyclerView.NO_POSITION) return
            
            val spanCount = layoutManager.spanCount
            val column = position % spanCount
            
            // 水平间距（均分算法）
            outRect.left = column * horizontalSpacing / spanCount
            outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount
            
            // 垂直间距
            if (position >= spanCount) {
                outRect.top = verticalSpacing
            }
        }
    }
    
    /**
     * 精确间距控制的ItemDecoration
     * 实现：边缘item和容器之间无间距，item之间有间距
     */
    class PreciseSpacingDecoration(
        private val edgeMargin: Int,      // 左右边距
        private val horizontalSpacing: Int, // 水平间距
        private val verticalSpacing: Int   // 垂直间距
    ) : RecyclerView.ItemDecoration() {
        
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val layoutManager = parent.layoutManager as? GridLayoutManager ?: return
            
            val position = parent.getChildAdapterPosition(view)
            if (position == RecyclerView.NO_POSITION) return
            
            val spanCount = layoutManager.spanCount
            val column = position % spanCount // 列索引
            val row = position / spanCount    // 行索引
            
            // 左右间距：边缘item和容器之间用edgeMargin，item之间用horizontalSpacing
            when (column) {
                0 -> {
                    // 第一列：左边距为edgeMargin，右边距为horizontalSpacing的一半
                    outRect.left = edgeMargin
                    outRect.right = horizontalSpacing / 2
                }
                spanCount - 1 -> {
                    // 最后一列：左边距为horizontalSpacing的一半，右边距为edgeMargin
                    outRect.left = horizontalSpacing / 2
                    outRect.right = edgeMargin
                }
                else -> {
                    // 中间列：左右都是horizontalSpacing的一半
                    outRect.left = horizontalSpacing / 2
                    outRect.right = horizontalSpacing / 2
                }
            }
            
            // 上下间距：第一行无上边距，其他行上边距为verticalSpacing
            outRect.top = if (row == 0) 0 else verticalSpacing
            outRect.bottom = 0 // 下边距始终为0
        }
    }
    
    /**
     * 演示数据类
     */
    data class DemoItem(
        val title: String,
        val index: Int
    )
    
    /**
     * Grid演示适配器 (Kotlin版本)
     */
    class GridDemoAdapter(
        private val context: Context,
        private val dataList: List<DemoItem>,
        var itemType: Int
    ) : RecyclerView.Adapter<GridDemoAdapter.ViewHolder>() {
        
        companion object {
            const val TYPE_BASIC = 1
            const val TYPE_WITH_DECORATION = 2
            const val TYPE_PRECISE_SPACING = 3
        }
        
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutId = when (itemType) {
                TYPE_BASIC -> R.layout.item_grid_basic
                TYPE_WITH_DECORATION -> R.layout.item_grid_with_decoration
                TYPE_PRECISE_SPACING -> R.layout.item_grid_precise_spacing
                else -> R.layout.item_grid_basic
            }
            
            val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
            return ViewHolder(view)
        }
        
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataList[position]
            holder.bind(item, itemType)
        }
        
        override fun getItemCount(): Int = dataList.size
        
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val textView: TextView = itemView.findViewById(R.id.text_view)
            
            fun bind(item: DemoItem, itemType: Int) {
                textView.text = item.title
                
                // 根据不同类型设置不同的背景色用于区分
                val backgroundColor = when (itemType) {
                    TYPE_BASIC -> 0xFF4CAF50.toInt()        // 绿色
                    TYPE_WITH_DECORATION -> 0xFF2196F3.toInt() // 蓝色
                    TYPE_PRECISE_SPACING -> 0xFFFF9800.toInt() // 橙色
                    else -> 0xFF4CAF50.toInt()
                }
                textView.setBackgroundColor(backgroundColor)
            }
        }
    }
} 