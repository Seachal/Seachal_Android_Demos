package com.seachal.seachaltest.customtab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.seachal.seachaltest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义TabLayout，使用RecyclerView实现，可以更灵活地自定义indicator
 * 支持直接使用图片作为指示器，无需绘制形状
 */
public class CustomTabLayout extends FrameLayout {

    // RecyclerView用于显示Tab项
    private RecyclerView recyclerView;
    
    // Tab适配器
    private TabAdapter tabAdapter;
    
    // 当前选中的位置
    private int currentPosition = 0;
    
    // 监听器
    private OnTabSelectedListener onTabSelectedListener;
    
    // Tab项数据
    private List<String> tabItems = new ArrayList<>();
    
    // 指示器颜色
    private int indicatorColor;
    
    // 指示器高度
    private int indicatorHeight;
    
    // 指示器宽度模式（0: 固定宽度, 1: 跟随文本宽度）
    private int indicatorWidthMode = 1;
    
    // 固定宽度值
    private int indicatorFixedWidth;
    
    // 指示器圆角
    private int indicatorRadius;
    
    // 选中文本颜色
    private int selectedTextColor;
    
    // 未选中文本颜色
    private int normalTextColor;
    
    // 关联的ViewPager2
    private ViewPager2 viewPager2;
    
    // 自定义指示器图片
    private Drawable indicatorDrawable;
    
    // 指示器宽度和高度
    private int indicatorWidth = -1;
    private int indicatorHeight2 = -1;
    
    // 指示器工厂
    private TabIndicatorFactory indicatorFactory = new TabIndicatorFactory() {
        @Override
        public TabIndicator createTabIndicator() {
            // 默认使用图片指示器
            return new DefaultImageTabIndicator();
        }
    };
    
    // 当前使用的指示器
    private TabIndicator tabIndicator;
    
    // 指示器视图
    private View indicatorView;
    
    // 目标指示器位置
    private float targetLeft = 0f;
    private float targetWidth = 0f;
    
    // 页面切换回调
    private final ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            setCurrentItem(position, true);
        }
    };

    public CustomTabLayout(@NonNull Context context) {
        this(context, null);
    }

    public CustomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        // 加载自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTabLayout);
        indicatorColor = typedArray.getColor(R.styleable.CustomTabLayout_o_indicatorColor, 
                ContextCompat.getColor(context, R.color.colorAccent));
                
        indicatorHeight = typedArray.getDimensionPixelSize(R.styleable.CustomTabLayout_o_indicatorHeight, 
                context.getResources().getDimensionPixelSize(R.dimen.dp_px_4));
                
        indicatorWidthMode = typedArray.getInt(R.styleable.CustomTabLayout_o_indicatorWidthMode, indicatorWidthMode);
        
        indicatorFixedWidth = typedArray.getDimensionPixelSize(R.styleable.CustomTabLayout_o_indicatorFixedWidth, 
                context.getResources().getDimensionPixelSize(R.dimen.dp_px_20));
                
        indicatorRadius = typedArray.getDimensionPixelSize(R.styleable.CustomTabLayout_o_indicatorRadius, 
                context.getResources().getDimensionPixelSize(R.dimen.dp_px_2));
                
        selectedTextColor = typedArray.getColor(R.styleable.CustomTabLayout_o_selectedTextColor, 
                ContextCompat.getColor(context, R.color.colorAccent));
                
        normalTextColor = typedArray.getColor(R.styleable.CustomTabLayout_o_normalTextColor, 
                ContextCompat.getColor(context, android.R.color.black));
                
        // 获取自定义指示器图片
        indicatorDrawable = typedArray.getDrawable(R.styleable.CustomTabLayout_o_indicatorDrawable);
        typedArray.recycle();
        
        // 初始化布局
        View view = LayoutInflater.from(context).inflate(R.layout.o_layout_custom_tab, this, true);
        recyclerView = view.findViewById(R.id.o_rv_tabs);
        
        // 设置水平布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        
        // 初始化适配器
        tabAdapter = new TabAdapter();
        recyclerView.setAdapter(tabAdapter);
        
        // 初始化指示器
        initIndicator();
        
        // 延迟一下，确保初始位置正确
        post(new Runnable() {
            @Override
            public void run() {
                if (!tabItems.isEmpty()) {
                    updateIndicator(currentPosition, false);
                }
            }
        });
    }
    
    /**
     * 初始化指示器
     */
    private void initIndicator() {
        // 如果已经有指示器，先移除
        if (indicatorView != null) {
            removeView(indicatorView);
        }
        
        // 创建新的指示器
        tabIndicator = indicatorFactory.createTabIndicator();
        indicatorView = tabIndicator.initIndicator(getContext());
        
        // 设置指示器图片
        if (indicatorDrawable != null) {
            tabIndicator.setIndicatorDrawable(indicatorDrawable);
        } else {
            // 如果没有设置图片，使用默认图片
            indicatorDrawable = ContextCompat.getDrawable(getContext(), R.drawable.o_shape_tab_underline_png2);
            tabIndicator.setIndicatorDrawable(indicatorDrawable);
        }
        
        // 设置指示器尺寸
        if (indicatorWidth > 0 && indicatorHeight2 > 0) {
            tabIndicator.setIndicatorSize(indicatorWidth, indicatorHeight2);
        }
        
        // 添加到视图，确保绘制在最上层
        if (indicatorView != null) {
            LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            );
            addView(indicatorView, params);
        }
    }
    
    /**
     * 设置Tab数据
     */
    public void setTabItems(List<String> items) {
        tabItems.clear();
        tabItems.addAll(items);
        tabAdapter.notifyDataSetChanged();
        
        // 默认选中第一项
        if (!tabItems.isEmpty() && currentPosition == 0) {
            post(new Runnable() {
                @Override
                public void run() {
                    updateIndicator(0, false);
                }
            });
        }
    }
    
    /**
     * 设置指示器图片
     */
    public void setIndicatorDrawable(Drawable drawable) {
        this.indicatorDrawable = drawable;
        if (tabIndicator != null) {
            tabIndicator.setIndicatorDrawable(drawable);
        }
    }
    
    /**
     * 设置指示器尺寸
     */
    public void setIndicatorSize(int width, int height) {
        this.indicatorWidth = width;
        this.indicatorHeight2 = height;
        if (tabIndicator != null) {
            tabIndicator.setIndicatorSize(width, height);
        }
    }
    
    /**
     * 设置自定义的指示器工厂
     */
    public void setIndicatorFactory(TabIndicatorFactory factory) {
        indicatorFactory = factory;
        initIndicator();
        
        post(new Runnable() {
            @Override
            public void run() {
                updateIndicator(currentPosition, false);
            }
        });
    }
    
    /**
     * 设置当前选中项
     */
    public void setCurrentItem(int position, boolean smoothScroll) {
        if (position < 0 || position >= tabItems.size()) return;
        
        // 更新选中状态
        int oldPosition = currentPosition;
        currentPosition = position;
        tabAdapter.notifyItemChanged(oldPosition);
        tabAdapter.notifyItemChanged(currentPosition);
        
        // 滚动到可见位置
        recyclerView.smoothScrollToPosition(position);
        
        // 更新指示器位置
        updateIndicator(position, smoothScroll);
        
        // 通知监听器
        if (onTabSelectedListener != null) {
            onTabSelectedListener.onTabSelected(position);
        }
        
        // 联动ViewPager2
        if (viewPager2 != null && viewPager2.getCurrentItem() != position) {
            viewPager2.setCurrentItem(position, smoothScroll);
        }
    }
    
    /**
     * 更新指示器位置
     */
    private void updateIndicator(final int position, final boolean smoothScroll) {
        if (position < 0 || position >= tabItems.size()) return;
        
        post(new Runnable() {
            @Override
            public void run() {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                if (viewHolder != null) {
                    View targetView = viewHolder.itemView;
                    
                    // 计算指示器目标位置
                    calculateTargetPosition(targetView);
                    
                    // 更新指示器位置
                    if (tabIndicator != null) {
                        tabIndicator.updatePosition(targetLeft, targetWidth, smoothScroll);
                    }
                    
                    // 强制重新布局，确保指示器更新
                    requestLayout();
                    invalidate();
                }
            }
        });
    }
    
    /**
     * 计算目标位置
     */
    private void calculateTargetPosition(View targetView) {
        // 计算目标位置
        targetLeft = targetView.getLeft();
        targetWidth = indicatorWidthMode == 0 ? indicatorFixedWidth : targetView.getWidth();
        
        // 居中显示
        if (indicatorWidthMode == 0) {
            targetLeft += (targetView.getWidth() - indicatorFixedWidth) / 2f;
        } else if (indicatorWidth > 0) {
            // 如果设置了固定指示器宽度，居中显示
            targetLeft += (targetView.getWidth() - indicatorWidth) / 2f;
            targetWidth = indicatorWidth;
        }
    }
    
    /**
     * 设置Tab选中监听器
     */
    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        this.onTabSelectedListener = listener;
    }
    
    /**
     * 关联ViewPager2
     */
    public void setupWithViewPager2(ViewPager2 viewPager2) {
        // 移除旧的回调
        if (this.viewPager2 != null) {
            this.viewPager2.unregisterOnPageChangeCallback(pageChangeCallback);
        }
        
        // 设置新的ViewPager2
        this.viewPager2 = viewPager2;
        viewPager2.registerOnPageChangeCallback(pageChangeCallback);
        
        // 初始同步状态
        setCurrentItem(viewPager2.getCurrentItem(), false);
    }
    
    /**
     * Tab适配器
     */
    private class TabAdapter extends RecyclerView.Adapter<TabAdapter.TabViewHolder> {
        
        @NonNull
        @Override
        public TabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.o_item_tab, parent, false);
            return new TabViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(@NonNull TabViewHolder holder, int position) {
            holder.bind(tabItems.get(position), position == currentPosition);
        }
        
        @Override
        public int getItemCount() {
            return tabItems.size();
        }
        
        class TabViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;
            
            TabViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.o_tv_tab);
                
                itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            setCurrentItem(position, true);
                        }
                    }
                });
            }
            
            void bind(String title, boolean isSelected) {
                textView.setText(title);
                textView.setTextColor(isSelected ? selectedTextColor : normalTextColor);
                // 可以在这里设置选中状态的其他样式，如字体粗细等
            }
        }
    }
    
    /**
     * 默认绘制指示器实现
     */
    private class DefaultTabIndicator implements TabIndicator {
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final RectF rectF = new RectF();
        private final View indicatorView;
        
        // 动画进度
        private float animationProgress = 1f;
        private float currentLeft = 0f;
        private float currentWidth = 0f;
        
        DefaultTabIndicator() {
            indicatorView = new View(getContext()) {
                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);
                    
                    // 计算当前指示器位置
                    float left = currentLeft + (targetLeft - currentLeft) * animationProgress;
                    float width = currentWidth + (targetWidth - currentWidth) * animationProgress;
                    
                    // 绘制指示器
                    rectF.set(left, getHeight() - indicatorHeight, left + width, getHeight());
                    canvas.drawRoundRect(rectF, indicatorRadius, indicatorRadius, paint);
                }
            };
            
            paint.setColor(indicatorColor);
            indicatorView.setWillNotDraw(false);
        }
        
        @Override
        public View initIndicator(Context context) {
            return indicatorView;
        }
        
        @Override
        public void updatePosition(float left, float width, boolean animate) {
            if (animate) {
                // 保存当前位置作为动画起点
                currentLeft = targetLeft;
                currentWidth = targetWidth;
                
                // 设置新的目标位置
                targetLeft = left;
                targetWidth = width;
                
                // 执行动画
                animationProgress = 0f;
                animateIndicator();
            } else {
                // 直接设置位置
                currentLeft = left;
                currentWidth = width;
                targetLeft = left;
                targetWidth = width;
                animationProgress = 1f;
                indicatorView.invalidate();
            }
        }
        
        @Override
        public void setIndicatorDrawable(Drawable drawable) {
            // 默认指示器不支持图片，忽略
        }
        
        @Override
        public void setIndicatorSize(int width, int height) {
            // 默认指示器使用全局设置，忽略
        }
        
        /**
         * 执行指示器动画
         */
        private void animateIndicator() {
            android.animation.ValueAnimator animator = android.animation.ValueAnimator.ofFloat(0f, 1f);
            animator.setDuration(250); // 动画时长
            animator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(android.animation.ValueAnimator animation) {
                    animationProgress = (float) animation.getAnimatedValue();
                    indicatorView.invalidate();
                }
            });
            animator.start();
        }
    }
} 