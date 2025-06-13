package com.seachal.seachaltest.RecyclerViewTest;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 标准网格间距装饰器 - 为所有网格项添加统一间距
 * 
 * <p>这是一个通用的网格间距装饰器，为RecyclerView中的所有网格项添加统一的间距效果。
 * 适用于需要在所有网格项之间保持一致间距的场景。</p>
 * 
 * <h3>主要特性：</h3>
 * <ul>
 *   <li>为所有网格项（包括第一行、最后一行、第一列、最后一列）添加间距</li>
 *   <li>支持自定义内部间距（item之间的距离）和外部边距（整个网格的边距）</li>
 *   <li>支持垂直和水平两种布局方向</li>
 *   <li>自动计算并分配间距，确保所有item宽度一致</li>
 * </ul>
 * 
 * <h3>使用场景：</h3>
 * <ul>
 *   <li>普通的网格列表，如图片网格、商品网格等</li>
 *   <li>需要在整个网格周围添加边距的场景</li>
 *   <li>所有网格项都需要统一间距处理的场景</li>
 * </ul>
 *
 * 
 * <h3>扩展说明：</h3>
 * <p>可以通过继承该类来实现特殊的分割线效果。由于此类已经实现了
 * {@link #getItemOffsets(Rect, View, RecyclerView, RecyclerView.State)}方法，
 * 只需继承构造方法并重写{@link #onDraw(Canvas, RecyclerView, RecyclerView.State)}
 * 即可实现自定义的分割线绘制效果。</p>
 * 
 * <p><strong>注意：</strong>该装饰器目前只支持{@link GridLayoutManager}</p>
 * 
 * @see GridSpaceSkipItemDecoration 如需跳过第一行的左右间距，请使用此类
 */
public class GridSpaceDecoration extends RecyclerView.ItemDecoration {

    private int mHorizontal, mVertical, mLeft, mRight, mTop, mBottom;
    private boolean isFirst = true;
    protected GridLayoutManager mManager;
    protected int mSpanCount;
    protected int mChildCount;

    /**
     * @see #GridSpaceDecoration(int, int, int, int)
     */
    public GridSpaceDecoration(int horizontal, int vertical) {
        this(horizontal, vertical, 0, 0);
    }

    /**
     * @see #GridSpaceDecoration(int, int, int, int, int, int)
     */
    public GridSpaceDecoration(int horizontal, int vertical, int left, int right) {
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
    public GridSpaceDecoration(int horizontal, int vertical, int left, int right, int top, int bottom) {
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
        int spanSize = lp.getSpanSize();
        int spanIndex = lp.getSpanIndex();
        outRect.left = computeLeft(spanIndex, sizeAvg);
        if (spanSize == 0 || spanSize == mSpanCount) {
            outRect.right = sizeAvg - outRect.left;
        } else {
            outRect.right = computeRight(spanIndex + spanSize - 1, sizeAvg);
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
                    "while using GridSpaceDecoration");
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
