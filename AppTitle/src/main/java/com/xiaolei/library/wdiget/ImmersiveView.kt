package com.xiaolei.library.wdiget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * 沉浸式，兼容凹口屏的控件
 */
open class ImmersiveView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr)
{
    init
    {
        this.afterMeasured { _, _ ->
            val titleLocationOnScreens = IntArray(2)
            this.getLocationOnScreen(titleLocationOnScreens)
            // val titleLocationOnScreenX = titleLocationOnScreens[0]
            val titleLocationOnScreenY = titleLocationOnScreens[1]
            // 如果在屏幕顶部
            if (titleLocationOnScreenY == 0)
            {
                // 这里就需要对本身的高度进行改变啦
                val params = this.layoutParams
                val stateBarHeight = context.getStatbarHeight()
                params.height += stateBarHeight
                this.setPaddingRelative(
                    this.paddingStart,
                    this.paddingTop + stateBarHeight,
                    this.paddingEnd,
                    this.paddingBottom
                )
            }
        }
    }
}