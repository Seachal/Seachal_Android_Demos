package com.seachal.seachaltest.RecyclerViewTest;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seachal.seachaltest.R;
import com.seachal.seachaltest.RecyclerViewTest.grid.GridSpaceDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 🌟 GridSpaceDecoration 完整使用案例
 * 
 * <p>这个Activity演示了GridSpaceDecoration的各种使用场景，包括：</p>
 * <ul>
 *   <li>🎯 基础网格间距：简单的2列网格，统一间距</li>
 *   <li>🎨 带边距的网格：网格周围有边距，内部有间距</li>
 *   <li>🔧 复杂网格布局：不同spanSize的混合布局</li>
 *   <li>📱 响应式网格：根据屏幕宽度自适应列数</li>
 *   <li>🔄 水平滚动网格：演示水平方向的网格布局</li>
 * </ul>
 * 
 * <h3>📋 演示场景说明：</h3>
 * <ol>
 *   <li><strong>场景1 - 基础网格：</strong>2列网格，item间距16dp，无边距</li>
 *   <li><strong>场景2 - 带边距网格：</strong>2列网格，item间距16dp，边距24dp</li>
 *   <li><strong>场景3 - 复杂布局：</strong>3列网格，包含不同spanSize的item</li>
 *   <li><strong>场景4 - 响应式网格：</strong>根据屏幕宽度自动计算列数</li>
 *   <li><strong>场景5 - 水平网格：</strong>水平滚动的网格布局</li>
 * </ol>
 * 
 * @author zhangxc
 * @version 1.0
 * @since 2024-12-27
 */
public class GridSpaceDecorationDemoActivity extends AppCompatActivity implements View.OnClickListener {

    // ==================== UI组件 ====================
    private RecyclerView recyclerView;
    private TextView tvDescription;
    private Button btnBasicGrid;
    private Button btnGridWithMargin;
    private Button btnComplexGrid;
    private Button btnResponsiveGrid;
    private Button btnHorizontalGrid;

    // ==================== 数据和适配器 ====================
    private GridDemoAdapter adapter;
    private List<GridDemoItem> dataList;

    // ==================== 常量定义 ====================
    private static final int BASIC_SPAN_COUNT = 2;
    private static final int COMPLEX_SPAN_COUNT = 3;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_space_decoration_demo);
        
        initViews();
        initData();
        setupBasicGrid(); // 默认显示基础网格
    }

    /**
     * 🔧 初始化UI组件
     */
    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        tvDescription = findViewById(R.id.tv_description);
        btnBasicGrid = findViewById(R.id.btn_basic_grid);
        btnGridWithMargin = findViewById(R.id.btn_grid_with_margin);
        btnComplexGrid = findViewById(R.id.btn_complex_grid);
        btnResponsiveGrid = findViewById(R.id.btn_responsive_grid);
        btnHorizontalGrid = findViewById(R.id.btn_horizontal_grid);

        // 设置点击监听器
        btnBasicGrid.setOnClickListener(this);
        btnGridWithMargin.setOnClickListener(this);
        btnComplexGrid.setOnClickListener(this);
        btnResponsiveGrid.setOnClickListener(this);
        btnHorizontalGrid.setOnClickListener(this);
    }

    /**
     * 📊 初始化演示数据
     */
    private void initData() {
        dataList = new ArrayList<>();
        
        // 创建30个演示item
        for (int i = 0; i < 30; i++) {
            GridDemoItem item = new GridDemoItem();
            item.title = "Item " + (i + 1);
            item.position = i;
            
            // 为复杂布局设置不同的spanSize
            if (i % 7 == 0) {
                item.spanSize = 3; // 占满整行
                item.type = GridDemoItem.TYPE_FULL_WIDTH;
            } else if (i % 5 == 0) {
                item.spanSize = 2; // 占2列
                item.type = GridDemoItem.TYPE_DOUBLE_WIDTH;
            } else {
                item.spanSize = 1; // 占1列
                item.type = GridDemoItem.TYPE_NORMAL;
            }
            
            dataList.add(item);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_basic_grid) {
            setupBasicGrid();
        } else if (id == R.id.btn_grid_with_margin) {
            setupGridWithMargin();
        } else if (id == R.id.btn_complex_grid) {
            setupComplexGrid();
        } else if (id == R.id.btn_responsive_grid) {
            setupResponsiveGrid();
        } else if (id == R.id.btn_horizontal_grid) {
            setupHorizontalGrid();
        }
    }

    /**
     * 🎯 场景1：基础网格布局
     * 
     * <p>演示最简单的网格间距设置：</p>
     * <ul>
     *   <li>2列网格布局</li>
     *   <li>item之间间距16dp</li>
     *   <li>无边距</li>
     * </ul>
     */
    private void setupBasicGrid() {
        // 🧹 清除之前的ItemDecoration
        clearItemDecorations();
        
        // 🏗️ 设置GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(this, BASIC_SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);
        
        // 🎨 添加基础间距装饰器
        int spacing = dpToPx(16); // 16dp间距
        GridSpaceDecoration decoration = new GridSpaceDecoration(spacing, spacing);
        recyclerView.addItemDecoration(decoration);
        
        // 📊 设置适配器
        setupAdapter(GridDemoAdapter.MODE_BASIC);
        
        // 🔄 更新UI状态
        updateButtonStates(btnBasicGrid);
        tvDescription.setText("🎯 基础网格：2列布局，item间距16dp，无边距\\n" +
                "适用场景：简单的图片网格、商品列表等");
    }

    /**
     * 🎨 场景2：带边距的网格布局
     * 
     * <p>演示带有外边距的网格间距设置：</p>
     * <ul>
     *   <li>2列网格布局</li>
     *   <li>item之间间距16dp</li>
     *   <li>整个网格的边距24dp</li>
     * </ul>
     */
    private void setupGridWithMargin() {
        // 🧹 清除之前的ItemDecoration
        clearItemDecorations();
        
        // 🏗️ 设置GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(this, BASIC_SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);
        
        // 🎨 添加带边距的间距装饰器
        int spacing = dpToPx(16);  // 内部间距16dp
        int margin = dpToPx(24);   // 外边距24dp
        GridSpaceDecoration decoration = new GridSpaceDecoration(
                spacing, spacing,  // 内部水平和垂直间距
                margin, margin,    // 左右边距
                margin, margin     // 上下边距
        );
        recyclerView.addItemDecoration(decoration);
        
        // 📊 设置适配器
        setupAdapter(GridDemoAdapter.MODE_WITH_MARGIN);
        
        // 🔄 更新UI状态
        updateButtonStates(btnGridWithMargin);
        tvDescription.setText("🎨 带边距网格：2列布局，item间距16dp，边距24dp\\n" +
                "适用场景：需要与容器保持距离的网格布局");
    }

    /**
     * 🔧 场景3：复杂网格布局
     * 
     * <p>演示包含不同spanSize的复杂网格布局：</p>
     * <ul>
     *   <li>3列网格布局</li>
     *   <li>包含占1列、2列、3列的不同item</li>
     *   <li>item间距12dp，边距16dp</li>
     * </ul>
     */
    private void setupComplexGrid() {
        // 🧹 清除之前的ItemDecoration
        clearItemDecorations();
        
        // 🏗️ 设置GridLayoutManager，支持不同spanSize
        GridLayoutManager layoutManager = new GridLayoutManager(this, COMPLEX_SPAN_COUNT);
        
        // 🎯 设置SpanSizeLookup来处理不同的spanSize
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 📊 根据数据返回对应的spanSize
                if (position < dataList.size()) {
                    return dataList.get(position).spanSize;
                }
                return 1; // 默认占1列
            }
        });
        
        recyclerView.setLayoutManager(layoutManager);
        
        // 🎨 添加复杂布局的间距装饰器
        int spacing = dpToPx(12);  // 内部间距12dp
        int margin = dpToPx(16);   // 外边距16dp
        GridSpaceDecoration decoration = new GridSpaceDecoration(
                spacing, spacing,  // 内部间距
                margin, margin,    // 左右边距
                margin, margin     // 上下边距
        );
        recyclerView.addItemDecoration(decoration);
        
        // 📊 设置适配器
        setupAdapter(GridDemoAdapter.MODE_COMPLEX);
        
        // 🔄 更新UI状态
        updateButtonStates(btnComplexGrid);
        tvDescription.setText("🔧 复杂网格：3列布局，支持不同spanSize，间距12dp\\n" +
                "适用场景：瀑布流、复杂的卡片布局等");
    }

    /**
     * 📱 场景4：响应式网格布局
     * 
     * <p>演示根据屏幕宽度自动计算列数的响应式网格：</p>
     * <ul>
     *   <li>根据屏幕宽度自动计算列数</li>
     *   <li>每个item最小宽度120dp</li>
     *   <li>自适应间距</li>
     * </ul>
     */
    private void setupResponsiveGrid() {
        // 🧹 清除之前的ItemDecoration
        clearItemDecorations();
        
        // 📏 计算响应式列数
        int spanCount = calculateResponsiveSpanCount();
        
        // 🏗️ 设置GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        
        // 🎨 添加响应式间距装饰器
        int spacing = dpToPx(8);   // 较小的间距
        int margin = dpToPx(12);   // 较小的边距
        GridSpaceDecoration decoration = new GridSpaceDecoration(
                spacing, spacing,
                margin, margin,
                margin, margin
        );
        recyclerView.addItemDecoration(decoration);
        
        // 📊 设置适配器
        setupAdapter(GridDemoAdapter.MODE_RESPONSIVE);
        
        // 🔄 更新UI状态
        updateButtonStates(btnResponsiveGrid);
        tvDescription.setText("📱 响应式网格：" + spanCount + "列布局，自适应屏幕宽度\\n" +
                "适用场景：需要适配不同屏幕尺寸的应用");
    }

    /**
     * 🔄 场景5：水平滚动网格布局
     * 
     * <p>演示水平方向滚动的网格布局：</p>
     * <ul>
     *   <li>水平滚动方向</li>
     *   <li>3行布局</li>
     *   <li>特殊的间距处理</li>
     * </ul>
     */
    private void setupHorizontalGrid() {
        // 🧹 清除之前的ItemDecoration
        clearItemDecorations();
        
        // 🏗️ 设置水平方向的GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(
                this, 
                3,  // 3行
                GridLayoutManager.HORIZONTAL,  // 水平方向
                false
        );
        recyclerView.setLayoutManager(layoutManager);
        
        // 🎨 添加水平网格的间距装饰器
        int spacing = dpToPx(12);
        int margin = dpToPx(16);
        GridSpaceDecoration decoration = new GridSpaceDecoration(
                spacing, spacing,
                margin, margin,
                margin, margin
        );
        recyclerView.addItemDecoration(decoration);
        
        // 📊 设置适配器
        setupAdapter(GridDemoAdapter.MODE_HORIZONTAL);
        
        // 🔄 更新UI状态
        updateButtonStates(btnHorizontalGrid);
        tvDescription.setText("🔄 水平网格：3行水平滚动布局\\n" +
                "适用场景：横向滚动的内容展示");
    }

    // ==================== 工具方法 ====================

    /**
     * 🧹 清除所有ItemDecoration
     */
    private void clearItemDecorations() {
        while (recyclerView.getItemDecorationCount() > 0) {
            recyclerView.removeItemDecorationAt(0);
        }
    }

    /**
     * 📊 设置适配器
     * 
     * @param mode 适配器模式
     */
    private void setupAdapter(int mode) {
        if (adapter == null) {
            adapter = new GridDemoAdapter(this, dataList);
        }
        adapter.setMode(mode);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 🔄 更新按钮状态
     * 
     * @param activeButton 当前激活的按钮
     */
    private void updateButtonStates(Button activeButton) {
        Button[] buttons = {btnBasicGrid, btnGridWithMargin, btnComplexGrid, 
                           btnResponsiveGrid, btnHorizontalGrid};
        
        for (Button button : buttons) {
            button.setSelected(button == activeButton);
        }
    }

    /**
     * 📏 计算响应式列数
     * 
     * @return 计算出的列数
     */
    private int calculateResponsiveSpanCount() {
        // 📱 获取屏幕宽度
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        
        // 🎯 每个item最小宽度120dp，间距和边距总共40dp
        float itemMinWidth = 120f;
        float totalMargin = 40f;
        
        // 🧮 计算可容纳的列数
        int spanCount = (int) ((screenWidthDp - totalMargin) / itemMinWidth);
        
        // 🛡️ 确保至少有1列，最多6列
        return Math.max(1, Math.min(spanCount, 6));
    }

    /**
     * 📐 dp转px工具方法
     * 
     * @param dp dp值
     * @return px值
     */
    private int dpToPx(int dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return (int) (dp * metrics.density);
    }

    // ==================== 内部类：演示数据模型 ====================

    /**
     * 📊 网格演示数据项
     */
    public static class GridDemoItem {
        public static final int TYPE_NORMAL = 1;
        public static final int TYPE_DOUBLE_WIDTH = 2;
        public static final int TYPE_FULL_WIDTH = 3;

        public String title;
        public int position;
        public int spanSize = 1;
        public int type = TYPE_NORMAL;
    }

    // ==================== 内部类：演示适配器 ====================

    /**
     * 🎨 网格演示适配器
     */
    public static class GridDemoAdapter extends RecyclerView.Adapter<GridDemoAdapter.ViewHolder> {
        
        // 适配器模式常量
        public static final int MODE_BASIC = 1;
        public static final int MODE_WITH_MARGIN = 2;
        public static final int MODE_COMPLEX = 3;
        public static final int MODE_RESPONSIVE = 4;
        public static final int MODE_HORIZONTAL = 5;

        private Context context;
        private List<GridDemoItem> dataList;
        private int mode = MODE_BASIC;

        public GridDemoAdapter(Context context, List<GridDemoItem> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        public void setMode(int mode) {
            this.mode = mode;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_grid_demo, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            GridDemoItem item = dataList.get(position);
            holder.bind(item, mode);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        /**
         * 🎨 ViewHolder类
         */
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text_view);
            }

            public void bind(GridDemoItem item, int mode) {
                // 🏷️ 设置文本内容
                String text = item.title;
                if (mode == MODE_COMPLEX && item.spanSize > 1) {
                    text += "\\n(span:" + item.spanSize + ")";
                }
                textView.setText(text);

                // 🎨 根据模式和类型设置背景色
                int backgroundColor = getBackgroundColor(item, mode);
                textView.setBackgroundColor(backgroundColor);
            }

            /**
             * 🎨 根据模式和item类型获取背景色
             */
            private int getBackgroundColor(GridDemoItem item, int mode) {
                switch (mode) {
                    case MODE_BASIC:
                        return 0xFF4CAF50; // 绿色
                    case MODE_WITH_MARGIN:
                        return 0xFF2196F3; // 蓝色
                    case MODE_COMPLEX:
                        // 根据spanSize设置不同颜色
                        switch (item.type) {
                            case GridDemoItem.TYPE_FULL_WIDTH:
                                return 0xFFE91E63; // 粉色
                            case GridDemoItem.TYPE_DOUBLE_WIDTH:
                                return 0xFFFF9800; // 橙色
                            default:
                                return 0xFF9C27B0; // 紫色
                        }
                    case MODE_RESPONSIVE:
                        return 0xFF00BCD4; // 青色
                    case MODE_HORIZONTAL:
                        return 0xFFFF5722; // 深橙色
                    default:
                        return 0xFF607D8B; // 灰色
                }
            }
        }
    }
} 