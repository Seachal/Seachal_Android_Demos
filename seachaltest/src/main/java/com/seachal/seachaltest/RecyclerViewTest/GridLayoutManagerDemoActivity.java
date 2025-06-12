package com.seachal.seachaltest.RecyclerViewTest;

import android.content.Context;
import android.graphics.Rect;
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

import java.util.ArrayList;
import java.util.List;

/**
 * GridLayoutManager 演示Activity
 * 演示三种不同的使用场景：
 * 1. 基础场景（无特殊设置）
 * 2. 带ItemDecoration的场景
 * 3. 精确间距控制的场景
 * 
 * @author zhangxc
 * @date 2024-12-27
 */
public class GridLayoutManagerDemoActivity extends AppCompatActivity implements View.OnClickListener {
    
    private RecyclerView recyclerView;
    private GridDemoAdapter adapter;
    private List<DemoItem> dataList;
    private TextView tvDescription;
    
    private Button btnBasic, btnWithDecoration, btnPreciseSpacing;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_manager_demo);
        
        initViews();
        initData();
        setupBasicGrid(); // 默认显示基础场景
    }
    
    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        tvDescription = findViewById(R.id.tv_description);
        btnBasic = findViewById(R.id.btn_basic);
        btnWithDecoration = findViewById(R.id.btn_with_decoration);
        btnPreciseSpacing = findViewById(R.id.btn_precise_spacing);
        
        btnBasic.setOnClickListener(this);
        btnWithDecoration.setOnClickListener(this);
        btnPreciseSpacing.setOnClickListener(this);
    }
    
    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add(new DemoItem("Item " + (i + 1), i));
        }
    }
    
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_basic) {
            setupBasicGrid();
        } else if (id == R.id.btn_with_decoration) {
            setupGridWithDecoration();
        } else if (id == R.id.btn_precise_spacing) {
            setupPreciseSpacingGrid();
        }
    }
    
    /**
     * 场景1：基础GridLayoutManager，无特殊设置
     */
    private void setupBasicGrid() {
        // 清除之前的ItemDecoration
        clearItemDecorations();
        
        // 设置基础的GridLayoutManager，spanCount=2
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        
        if (adapter == null) {
            adapter = new GridDemoAdapter(this, dataList, GridDemoAdapter.TYPE_BASIC);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setItemType(GridDemoAdapter.TYPE_BASIC);
            adapter.notifyDataSetChanged();
        }
        
        updateButtonStates(btnBasic);
        tvDescription.setText("基础场景：GridLayoutManager(this, 2) 无特殊设置，item自带margin");
    }
    
    /**
     * 场景2：带ItemDecoration的GridLayoutManager
     */
    private void setupGridWithDecoration() {
        // 清除之前的ItemDecoration
        clearItemDecorations();
        
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        
        // 添加ItemDecoration
        recyclerView.addItemDecoration(new GridItemDecoration(
                dpToPx(16), // 水平间距
                dpToPx(16)  // 垂直间距
        ));
        
        if (adapter == null) {
            adapter = new GridDemoAdapter(this, dataList, GridDemoAdapter.TYPE_WITH_DECORATION);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setItemType(GridDemoAdapter.TYPE_WITH_DECORATION);
            adapter.notifyDataSetChanged();
        }
        
        updateButtonStates(btnWithDecoration);
        tvDescription.setText("带ItemDecoration：使用GridItemDecoration设置16dp水平和垂直间距");
    }
    
    /**
     * 场景3：精确间距控制
     * 屏幕宽度假设为750dp，item宽度335dp，水平间距16dp，边距32dp，垂直间距16dp
     */
    private void setupPreciseSpacingGrid() {
        // 清除之前的ItemDecoration
        clearItemDecorations();
        
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        
        // 添加精确间距的ItemDecoration
        recyclerView.addItemDecoration(new PreciseSpacingDecoration(
                dpToPx(32), // 左右边距
                dpToPx(16), // 水平间距
                dpToPx(16)  // 垂直间距
        ));
        
        if (adapter == null) {
            adapter = new GridDemoAdapter(this, dataList, GridDemoAdapter.TYPE_PRECISE_SPACING);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setItemType(GridDemoAdapter.TYPE_PRECISE_SPACING);
            adapter.notifyDataSetChanged();
        }
        
        updateButtonStates(btnPreciseSpacing);
        tvDescription.setText("精确间距：屏幕750dp，item 335dp，水平间距16dp，边距32dp，垂直间距16dp");
    }
    
    private void clearItemDecorations() {
        while (recyclerView.getItemDecorationCount() > 0) {
            recyclerView.removeItemDecorationAt(0);
        }
    }
    
    private void updateButtonStates(Button activeButton) {
        btnBasic.setSelected(false);
        btnWithDecoration.setSelected(false);
        btnPreciseSpacing.setSelected(false);
        activeButton.setSelected(true);
    }
    
    private int dpToPx(int dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return (int) (dp * metrics.density);
    }
    
    /**
     * 基础的GridItemDecoration
     */
    public static class GridItemDecoration extends RecyclerView.ItemDecoration {
        private int horizontalSpacing;
        private int verticalSpacing;
        
        public GridItemDecoration(int horizontalSpacing, int verticalSpacing) {
            this.horizontalSpacing = horizontalSpacing;
            this.verticalSpacing = verticalSpacing;
        }
        
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            if (layoutManager == null) return;
            
            int position = parent.getChildAdapterPosition(view);
            int spanCount = layoutManager.getSpanCount();
            int column = position % spanCount;
            
            // 水平间距
            outRect.left = column * horizontalSpacing / spanCount;
            outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount;
            
            // 垂直间距
            if (position >= spanCount) {
                outRect.top = verticalSpacing;
            }
        }
    }
    
    /**
     * 精确间距控制的ItemDecoration
     * 实现：边缘item和容器之间无间距，item之间有间距
     */
    public static class PreciseSpacingDecoration extends RecyclerView.ItemDecoration {
        private int edgeMargin;      // 左右边距
        private int horizontalSpacing; // 水平间距
        private int verticalSpacing;   // 垂直间距
        
        public PreciseSpacingDecoration(int edgeMargin, int horizontalSpacing, int verticalSpacing) {
            this.edgeMargin = edgeMargin;
            this.horizontalSpacing = horizontalSpacing;
            this.verticalSpacing = verticalSpacing;
        }
        
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            if (layoutManager == null) return;
            
            int position = parent.getChildAdapterPosition(view);
            int spanCount = layoutManager.getSpanCount();
            int column = position % spanCount; // 列索引
            int row = position / spanCount;    // 行索引
            
            // 左右间距：边缘item和容器之间用edgeMargin，item之间用horizontalSpacing
            if (column == 0) {
                // 第一列：左边距为edgeMargin，右边距为horizontalSpacing的一半
                outRect.left = edgeMargin;
                outRect.right = horizontalSpacing / 2;
            } else if (column == spanCount - 1) {
                // 最后一列：左边距为horizontalSpacing的一半，右边距为edgeMargin
                outRect.left = horizontalSpacing / 2;
                outRect.right = edgeMargin;
            } else {
                // 中间列：左右都是horizontalSpacing的一半
                outRect.left = horizontalSpacing / 2;
                outRect.right = horizontalSpacing / 2;
            }
            
            // 上下间距：第一行无上边距，其他行上边距为verticalSpacing
            if (row == 0) {
                outRect.top = 0;
            } else {
                outRect.top = verticalSpacing;
            }
            
            // 下边距始终为0（或者可以设置底部边距）
            outRect.bottom = 0;
        }
    }
    
    /**
     * 演示数据类
     */
    public static class DemoItem {
        public String title;
        public int index;
        
        public DemoItem(String title, int index) {
            this.title = title;
            this.index = index;
        }
    }
    
    /**
     * Grid演示适配器
     */
    public static class GridDemoAdapter extends RecyclerView.Adapter<GridDemoAdapter.ViewHolder> {
        public static final int TYPE_BASIC = 1;
        public static final int TYPE_WITH_DECORATION = 2;
        public static final int TYPE_PRECISE_SPACING = 3;
        
        private Context context;
        private List<DemoItem> dataList;
        private int itemType;
        
        public GridDemoAdapter(Context context, List<DemoItem> dataList, int itemType) {
            this.context = context;
            this.dataList = dataList;
            this.itemType = itemType;
        }
        
        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
        
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            switch (itemType) {
                case TYPE_BASIC:
                    view = LayoutInflater.from(context).inflate(R.layout.item_grid_basic, parent, false);
                    break;
                case TYPE_WITH_DECORATION:
                    view = LayoutInflater.from(context).inflate(R.layout.item_grid_with_decoration, parent, false);
                    break;
                case TYPE_PRECISE_SPACING:
                    view = LayoutInflater.from(context).inflate(R.layout.item_grid_precise_spacing, parent, false);
                    break;
                default:
                    view = LayoutInflater.from(context).inflate(R.layout.item_grid_basic, parent, false);
                    break;
            }
            return new ViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            DemoItem item = dataList.get(position);
            holder.textView.setText(item.title);
            
            // 根据不同类型设置不同的背景色用于区分
            switch (itemType) {
                case TYPE_BASIC:
                    holder.textView.setBackgroundColor(0xFF4CAF50); // 绿色
                    break;
                case TYPE_WITH_DECORATION:
                    holder.textView.setBackgroundColor(0xFF2196F3); // 蓝色
                    break;
                case TYPE_PRECISE_SPACING:
                    holder.textView.setBackgroundColor(0xFFFF9800); // 橙色
                    break;
            }
        }
        
        @Override
        public int getItemCount() {
            return dataList.size();
        }
        
        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text_view);
            }
        }
    }
} 