package com.xiaolei.library.wdiget

import android.content.Context
import android.view.View
import android.view.ViewTreeObserver

/**
 * 当控件测量完成
 */
inline fun <T : View> T.afterMeasured(crossinline f: T.(width: Int, height: Int) -> Unit)
{
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener
    {
        override fun onGlobalLayout()
        {
            if (measuredWidth > 0 && measuredHeight > 0)
            {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f(measuredWidth, measuredHeight)
            }
        }
    })
}

/**
 * 获取状态栏的高度
 */
fun <T : Context> T.getStatbarHeight(): Int
{
    var result = 0
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0)
    {
        result = this.resources.getDimensionPixelSize(resourceId)
    }

    if(result == 0)
    {
        try
        {
            val c = Class.forName("com.android.internal.R\$dimen")
            val obj = c.newInstance()
            val field = c.getField("status_bar_height")
            val x = Integer.parseInt(field.get(obj).toString())
            result = this.resources.getDimensionPixelSize(x)
        } catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    return result
}
fun <T : Context> T.dp2px(dp: Int): Int
{
    val scale = this.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}