package com.seachal.seachaltest.RecyclerViewTest.grid;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 跳过首行网格间距装饰器 - 为网格项添加间距但跳过第一行的左右间距
 * 
 * <p>这是一个特殊的网格间距装饰器，与{@link GridSpaceDecoration}的主要区别在于：
 * <strong>第一行的网格项不会添加左右间距</strong>，通常用于第一行是标题、横幅或特殊布局的场景。</p>
 * 
 * <h3>主要特性：</h3>
 * <ul>
 *   <li><strong>跳过第一行：</strong>第一行的网格项不添加左右间距(left/right)</li>
 *   <li><strong>保留垂直间距：</strong>第一行仍然保留顶部和底部间距</li>
 *   <li><strong>其他行正常：</strong>除第一行外，其他行的间距处理与标准装饰器相同</li>
 *   <li>支持垂直和水平两种布局方向</li>
 * </ul>
 * 
 * <h3>使用场景：</h3>
 * <ul>
 *   <li>第一行是标题或横幅，需要占满整个宽度的场景</li>
 *   <li>第一行是特殊布局（如搜索框、广告位），不需要左右间距的场景</li>
 *   <li>混合布局：第一行单独处理，其余行按网格布局的场景</li>
 *   <li>头部内容需要与容器边缘对齐的场景</li>
 * </ul>
 * 
 * <h3>与GridSpaceDecoration的区别：</h3>
 * <table border="1">
 *   <tr>
 *     <th>装饰器</th>
 *     <th>第一行处理</th>
 *     <th>其他行处理</th>
 *     <th>适用场景</th>
 *   </tr>
 *   <tr>
 *     <td>GridSpaceDecoration</td>
 *     <td>添加完整间距</td>
 *     <td>添加完整间距</td>
 *     <td>标准网格布局</td>
 *   </tr>
 *   <tr>
 *     <td>GridSpaceSkipItemDecoration</td>
 *     <td><strong>跳过左右间距</strong></td>
 *     <td>添加完整间距</td>
 *     <td>混合布局/特殊头部</td>
 *   </tr>
 * </table>
 * 
 * <h3>构造参数说明：</h3>
 * @param horizontal 内部水平距离(px) - 网格项之间的水平间距（第一行除外）
 * @param vertical   内部竖直距离(px) - 网格项之间的垂直间距
 * @param left       最左边距离(px) - 整个网格左侧的边距（第一行除外）
 * @param right      最右边距离(px) - 整个网格右侧的边距（第一行除外）
 * @param top        最顶端距离(px) - 整个网格顶部的边距
 * @param bottom     最底端距离(px) - 整个网格底部的边距
 * 
 * <h3>扩展说明：</h3>
 * <p>可以通过继承该类来实现特殊的分割线效果。由于此类已经实现了
 * {@link #getItemOffsets(Rect, View, RecyclerView, RecyclerView.State)}方法，
 * 只需继承构造方法并重写{@link #onDraw(Canvas, RecyclerView, RecyclerView.State)}
 * 即可实现自定义的分割线绘制效果。</p>
 * 
 * <p><strong>注意：</strong>该装饰器目前只支持{@link GridLayoutManager}</p>
 * 
 * @see GridSpaceDecoration 如需为所有行添加统一间距，请使用此类
 */
public class GridSpaceSkipItemDecoration extends RecyclerView.ItemDecoration {

    private int mHorizontal, mVertical, mLeft, mRight, mTop, mBottom;
    private boolean isFirst = true;
    protected GridLayoutManager mManager;
    protected int mSpanCount;
    protected int mChildCount;

    /**
     * @see #GridSpaceSkipItemDecoration(int, int, int, int)
     */
    public GridSpaceSkipItemDecoration(int horizontal, int vertical) {
        this(horizontal, vertical, 0, 0);
    }

    /**
     * @see #GridSpaceSkipItemDecoration(int, int, int, int, int, int)
     */
    public GridSpaceSkipItemDecoration(int horizontal, int vertical, int left, int right) {
        this(horizontal, vertical, left, right, 0, 0);
    }

    /**
     * @param horizontal 内部水平距离(px)  (两个 item 之间的距离，  )
     * @param vertical   内部竖直距离(px)   (两个 item 之间的距离)
     * @param left       最左边距离(px)，默认为0
     * @param right      最右边距离(px),默认为0
     * @param top        最顶端距离(px),默认为0
     * @param bottom     最底端距离(px),默认为0
     */
    public GridSpaceSkipItemDecoration(int horizontal, int vertical, int left, int right, int top, int bottom) {
        mHorizontal = horizontal;
        mVertical = vertical;
        mLeft = left;
        mRight = right;
        mTop = top;
        mBottom = bottom;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (isFirst) {
            init(parent);
            isFirst = false;
        }
        if (mManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            handleVertical(outRect, view, parent, state);
        } else {
            handleHorizontal(outRect, view, parent, state);
        }
    }

    /**
     * 计算Item的左边Decoration(outRect.left)尺寸,当orientation为Vertical时调用
     * @param spanIndex 需要计算的Item对应的spanIndex
     * @param sizeAvg 每个Item需要填充的尺寸
     * @return outRect.left
     */
    private int computeLeft(int spanIndex, int sizeAvg) {
        if (spanIndex == 0) {
            return mLeft;
        } else if (spanIndex >= mSpanCount / 2) {
            //从右边算起
            return sizeAvg - computeRight(spanIndex, sizeAvg);
        } else {
            //从左边算起
            return mHorizontal - computeRight(spanIndex - 1, sizeAvg);
        }
    }

    /**
     * 计算Item的右边Decoration(outRect.right)尺寸,当orientation为Vertical时调用
     * @param spanIndex 需要计算的Item对应的spanIndex
     * @param sizeAvg 每个Item需要填充的尺寸
     * @return outRect.right
     */
    private int computeRight(int spanIndex, int sizeAvg) {
        if (spanIndex == mSpanCount - 1) {
            return mRight;
        } else if (spanIndex >= mSpanCount / 2) {
            //从右边算起
            return mHorizontal - computeLeft(spanIndex + 1, sizeAvg);
        } else {
            //从左边算起
            return sizeAvg - computeLeft(spanIndex, sizeAvg);
        }
    }

    /**
     * 计算Item的顶部边Decoration(outRect.Top)尺寸,当orientation为Horizontal时调用
     * @param spanIndex 需要计算的Item对应的spanIndex
     * @param sizeAvg 每个Item需要填充的尺寸
     * @return outRect.top
     */
    private int computeTop(int spanIndex, int sizeAvg) {
        if (spanIndex == 0) {
            return mTop;
        } else if (spanIndex >= mSpanCount / 2) {
            //从底部算起
            return sizeAvg - computeBottom(spanIndex, sizeAvg);
        } else {
            //从顶端算起
            return mVertical - computeBottom(spanIndex - 1, sizeAvg);
        }
    }

    /**
     * 计算Item的底部Decoration(outRect.bottom)尺寸,当orientation为Horizontal时调用
     * @param spanIndex 需要计算的Item对应的spanIndex
     * @param sizeAvg 每个Item需要填充的尺寸
     * @return outRect.bottom
     */
    private int computeBottom(int spanIndex, int sizeAvg) {
        if (spanIndex == mSpanCount - 1) {
            return mBottom;
        } else if (spanIndex >= mSpanCount / 2) {
            //从底部算起
            return mVertical - computeTop(spanIndex + 1, sizeAvg);
        } else {
            //从顶端算起
            return sizeAvg - computeTop(spanIndex, sizeAvg);
        }
    }

    /**
     * orientation为Vertical时调用，处理Vertical下的Offset
     * {@link #getItemOffsets(Rect, View, RecyclerView, RecyclerView.State)}
     */
    private void handleVertical(Rect outRect, View view, RecyclerView parent,
                                RecyclerView.State state) {
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int childPos = parent.getChildAdapterPosition(view);
        int sizeAvg = (int) ((mHorizontal * (mSpanCount - 1) + mLeft + mRight) * 1f / mSpanCount);
        //用来获取当前 item 所占用的跨度大小的方法
        int spanSize = lp.getSpanSize();
        int spanIndex = lp.getSpanIndex();
        if (!isFirstRaw(childPos)) {
            outRect.left = computeLeft(spanIndex, sizeAvg);
        }
        //
        if (spanSize == 0 || spanSize == mSpanCount) {
            if (!isFirstRaw(childPos)) {
                outRect.right = sizeAvg - outRect.left;
            }
        } else {
            //if (spanSize != 1) {
            //    outRect.right = computeRight(spanIndex + spanSize - 1, sizeAvg);
            //}
        }
        outRect.top = mVertical / 2;
        outRect.bottom = mVertical / 2;
        if (isFirstRaw(childPos)) {
            outRect.top = mTop;
        }
        if (isLastRaw(childPos)) {
            outRect.bottom = mBottom;
        }
    }

    /**
     * orientation为Horizontal时调用，处理Horizontal下的Offset
     * {@link #getItemOffsets(Rect, View, RecyclerView, RecyclerView.State)}
     */
    private void handleHorizontal(Rect outRect, View view, RecyclerView parent,
                                  RecyclerView.State state) {
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int childPos = parent.getChildAdapterPosition(view);
        int spanSize = lp.getSpanSize();
        int spanIndex = lp.getSpanIndex();
        int sizeAvg = (int) ((mVertical * (mSpanCount - 1) + mTop + mBottom) * 1f / mSpanCount);
        outRect.top = computeTop(spanIndex,sizeAvg);
        if (spanSize == 0 || spanSize == mSpanCount) {
            outRect.bottom = sizeAvg - outRect.top;
        } else {
            outRect.bottom = computeBottom(spanIndex + spanSize - 1, sizeAvg);
        }
        outRect.left = mHorizontal/2;
        outRect.right = mHorizontal/2;
        if (isFirstRaw(childPos)){
            outRect.left = mLeft;
        }
        if (isLastRaw(childPos)){
            outRect.right = mRight;
        }
    }

    /**
     * 初始化
     */
    private void init(RecyclerView parent) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (!(manager instanceof GridLayoutManager)) {
            throw new IllegalArgumentException("LayoutManger must instance of GridLayoutManager " +
                    "while using GridSpaceSkipItemDecoration");
        }
        mManager = (GridLayoutManager) manager;
        mSpanCount = getSpanCount();
        mChildCount = parent.getAdapter().getItemCount();
    }

    protected int getSpanIndex(int pos) {
        int spanIndex;
        GridLayoutManager.SpanSizeLookup lookup = mManager.getSpanSizeLookup();
        lookup.setSpanIndexCacheEnabled(true);
        spanIndex = lookup.getSpanIndex(pos, mSpanCount);
        return spanIndex;
    }

    protected int getSpanCount() {
        return mManager.getSpanCount();
    }

    protected boolean isFirstColumn(GridLayoutManager.LayoutParams params, int pos) {
        return params.getSpanIndex() == 0;
    }

    protected boolean isFirstRaw(int pos) {
        if (mChildCount <= 0) {
            return false;
        }
        GridLayoutManager.SpanSizeLookup lookup = mManager.getSpanSizeLookup();
        return lookup.getSpanGroupIndex(pos, mSpanCount) == lookup.getSpanGroupIndex(0, mSpanCount);
    }

    protected boolean isLastRaw(int pos) {
        if (mChildCount <= 0) {
            return false;
        }
        GridLayoutManager.SpanSizeLookup lookup = mManager.getSpanSizeLookup();
        return lookup.getSpanGroupIndex(pos, mSpanCount) == lookup.getSpanGroupIndex(mChildCount - 1, mSpanCount);
    }

    protected boolean isLastColumn(GridLayoutManager.LayoutParams params, int pos) {
        int index = params.getSpanIndex();
        int size = params.getSpanSize();
        return index + size == mSpanCount;
    }


    public int getHorizontal() {
        return mHorizontal;
    }

    public void setHorizontal(int mHorizontal) {
        this.mHorizontal = mHorizontal;
    }

    public int getVertical() {
        return mVertical;
    }

    public void setVertical(int mVertical) {
        this.mVertical = mVertical;
    }

    public int getLeft() {
        return mLeft;
    }

    public void setLeft(int mLeft) {
        this.mLeft = mLeft;
    }

    public int getRight() {
        return mRight;
    }

    public void setRight(int mRight) {
        this.mRight = mRight;
    }

    public int getTop() {
        return mTop;
    }

    public void setmTop(int mTop) {
        this.mTop = mTop;
    }

    public int getmBottom() {
        return mBottom;
    }

    public void setmBottom(int mBottom) {
        this.mBottom = mBottom;
    }
}
