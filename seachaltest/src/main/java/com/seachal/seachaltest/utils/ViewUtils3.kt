package com.seachal.seachaltest.utils

import android.graphics.Rect
import android.view.View

/**
 **
 * *
 * Project_Name:Seachal_Android_Demos
 * @Description: TODO
 * @author zhangxc
 * @date 2023/11/27 17:50
 * @Version：1.0
 */


/**
 * @param view
 * @return 当前视图可见比列
 */
fun getViewVisiblePercent(view: View?): Float {
    if (view == null) {
        return 0f
    }
    val height = view.height.toFloat()
    val rect = Rect()
    if (!view.getLocalVisibleRect(rect)) {
        return 0f
    }
    val visibleHeight = (rect.bottom - rect.top).toFloat()
    return visibleHeight / height
}