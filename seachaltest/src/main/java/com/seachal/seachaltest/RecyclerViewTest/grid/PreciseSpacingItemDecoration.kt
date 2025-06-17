package com.seachal.seachaltest.RecyclerViewTest.grid

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 **
 * *
 * Project_Name:TrainingCourseInfoActivity.kt
 * @Description: TODO
 *  * 精确间距控制的ItemDecoration
 *  * 实现：边缘item和容器之间无间距，item之间有间距
 * @author zhangxc
 * @date 2025/6/13 09:10
 * @Version：1.0
 */
class PreciseSpacingItemDecoration(
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