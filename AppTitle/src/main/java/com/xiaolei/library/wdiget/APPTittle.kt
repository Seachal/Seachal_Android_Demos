package com.xiaolei.library.wdiget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import com.xiaolei.library.R

/**
 * 自定义标题栏
 * [左边图标 左边文字 [提示红点]中间文字[下拉按钮] 右边文字 右边图标]
 */
class APPTittle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImmersiveView(context, attrs, defStyleAttr)
{
    private val constraintSet: ConstraintSet = ConstraintSet()
    private var leftImgViewBlock: ((View) -> Unit)? = null
    private var leftTextViewBlock: ((View) -> Unit)? = null
    private var titleLeftIconImageViewBlock: ((View) -> Unit)? = null
    private var titleTextViewBlock: ((View) -> Unit)? = null
    private var titleRightIconImageViewBlock: ((View) -> Unit)? = null
    private var rightTextViewBlock: ((View) -> Unit)? = null
    private var rightImgViewBlock: ((View) -> Unit)? = null
    private val clickListener = OnClickListener { view ->
        when (view)
        {
            leftImgView -> leftImgViewBlock?.invoke(view)
            leftTextView -> leftTextViewBlock?.invoke(view)
            titleLeftIconImageView -> titleLeftIconImageViewBlock?.invoke(view)
            titleTextView -> titleTextViewBlock?.invoke(view)
            titleRightIconImageView -> titleRightIconImageViewBlock?.invoke(view)
            rightTextView -> rightTextViewBlock?.invoke(view)
            rightImgView -> rightImgViewBlock?.invoke(view)
        }
    }

    val leftImgView by lazy {
        ImageView(context).apply {
            id = View.generateViewId()
            scaleType = ImageView.ScaleType.CENTER
            setPaddingRelative(context.dp2px(8), 0, context.dp2px(8), 0)
        }
    }
    val leftTextView: TextView by lazy {
        TextView(context).apply {
            id = View.generateViewId()
            gravity = Gravity.CENTER
        }
    }
    val titleLeftIconImageView by lazy {
        ImageView(context).apply {
            id = View.generateViewId()
            scaleType = ImageView.ScaleType.CENTER
        }
    }
    val titleTextView by lazy {
        TextView(context).apply {
            id = View.generateViewId()
            gravity = Gravity.CENTER
        }
    }
    val titleRightIconImageView by lazy {
        ImageView(context).apply {
            id = View.generateViewId()
            scaleType = ImageView.ScaleType.CENTER
        }
    }
    val rightTextView by lazy {
        TextView(context).apply {
            id = View.generateViewId()
            gravity = Gravity.CENTER
        }
    }
    val rightImgView by lazy {
        ImageView(context).apply {
            id = View.generateViewId()
            scaleType = ImageView.ScaleType.CENTER
            setPaddingRelative(context.dp2px(8), 0, context.dp2px(8), 0)
        }
    }

    init
    {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.APPTittle)
        // 左边图片是否显示
        leftImgView.visibility = typedArray.getInt(R.styleable.APPTittle_left_img_visible, VISIBLE)
        // 左边文字是否显示
        leftTextView.visibility = typedArray.getInt(R.styleable.APPTittle_left_text_visible, VISIBLE)
        // 标题左边icon是否显示
        titleLeftIconImageView.visibility = typedArray.getInt(R.styleable.APPTittle_title_left_icon_visible, GONE)
        // 标题是否显示
        titleTextView.visibility = typedArray.getInt(R.styleable.APPTittle_title_text_visible, VISIBLE)
        // 标题右边icon是否显示,默认为不显示
        titleRightIconImageView.visibility = typedArray.getInt(R.styleable.APPTittle_title_right_icon_visible, GONE)
        // 右边文字是否显示
        rightTextView.visibility = typedArray.getInt(R.styleable.APPTittle_right_text_visible, GONE)
        // 右边图片是否显示
        rightImgView.visibility = typedArray.getInt(R.styleable.APPTittle_right_img_visible, GONE)


        // 左边图片，默认为返回图标
        val leftImgDrawId = typedArray.getResourceId(R.styleable.APPTittle_left_img, R.drawable.base_icon_back_default)
        leftImgView.setImageResource(leftImgDrawId)
        // 左边图片的内距,默认为8dp
        val leftImgPadding = typedArray.getDimensionPixelSize(R.styleable.APPTittle_left_img_padding, context.dp2px(8))
        leftImgView.setPaddingRelative(leftImgPadding, 0, leftImgPadding, 0)
        // 左边文字
        val leftTextStr = typedArray.getString(R.styleable.APPTittle_left_text)
        leftTextView.text = leftTextStr ?: "Back"
        // 左边文字大小,避免受到老年机字体的影响，默认单位是13DP
        val leftTextSize = typedArray.getDimensionPixelSize(R.styleable.APPTittle_left_text_size, context.dp2px(13))
        leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize.toFloat())
        // 左边的文字颜色，默认为白色
        val leftTextColor = typedArray.getColor(R.styleable.APPTittle_left_text_color, Color.WHITE)
        leftTextView.setTextColor(leftTextColor)
        // 标题左边的图标，默认为红点点
        val leftIcon =
            typedArray.getResourceId(R.styleable.APPTittle_title_left_icon, R.drawable.base_icon_point_default)
        titleLeftIconImageView.setImageResource(leftIcon)
        // 标题文字
        val titleTextStr = typedArray.getString(R.styleable.APPTittle_title_text)
        titleTextView.text = titleTextStr ?: "Title"
        // 标题文字大小，默认15，避免被老年机影响，默认单位使用DP
        val titleTextSize = typedArray.getDimensionPixelSize(R.styleable.APPTittle_title_text_size, context.dp2px(15))
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize.toFloat())
        // 获取标题文字颜色
        val titleTextColor = typedArray.getColor(R.styleable.APPTittle_title_text_color, Color.WHITE)
        titleTextView.setTextColor(titleTextColor)
        // 标题右边图标，默认为下拉图标
        val titleRightIcon =
            typedArray.getResourceId(R.styleable.APPTittle_title_right_icon, R.drawable.base_icon_dorp_down_default)
        titleRightIconImageView.setImageResource(titleRightIcon)
        // 获取右边图标的内距
        val rightImgPadding =
            typedArray.getDimensionPixelSize(R.styleable.APPTittle_right_img_padding, context.dp2px(8))
        rightImgView.setPaddingRelative(rightImgPadding, 0, rightImgPadding, 0)
        // 右边文字
        val rightText = typedArray.getString(R.styleable.APPTittle_right_text)
        rightTextView.text = rightText ?: "Menu"
        // 右边文字大小，默认为13，避免受老年机影响，单位为DP
        val rightTextSize = typedArray.getDimensionPixelSize(R.styleable.APPTittle_right_text_size, context.dp2px(13))
        rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize.toFloat())
        // 右边文字颜色，默认为白色
        val rightTextColor = typedArray.getColor(R.styleable.APPTittle_right_text_color, Color.WHITE)
        rightTextView.setTextColor(rightTextColor)
        // 右边图标，默认为菜单图标
        val rightImage = typedArray.getResourceId(R.styleable.APPTittle_right_img, R.drawable.base_icon_menu_default)
        rightImgView.setImageResource(rightImage)
        // 获取左右两个图标的内距
        val leftRightPadding = typedArray.getDimensionPixelSize(R.styleable.APPTittle_left_right_img_padding, -1)
        if (leftRightPadding != -1)
        {
            leftImgView.setPaddingRelative(leftRightPadding, 0, leftRightPadding, 0)
            rightImgView.setPaddingRelative(leftRightPadding, 0, leftRightPadding, 0)
        }

        typedArray.recycle()


        initView()
        initEvent()
    }

    /**
     * 初始化界面
     */
    private fun initView()
    {
        this.addView(leftImgView)
        this.addView(leftTextView)
        this.addView(titleLeftIconImageView)
        this.addView(titleTextView)
        this.addView(titleRightIconImageView)
        this.addView(rightTextView)
        this.addView(rightImgView)

        constraintSet.clone(this)
        // 左边图片的设置
        constraintSet.constrainWidth(leftImgView.id, ConstraintSet.WRAP_CONTENT)
        constraintSet.constrainHeight(leftImgView.id, 0)
        constraintSet.connect(leftImgView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(leftImgView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(leftImgView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        constraintSet.connect(leftImgView.id, ConstraintSet.END, leftTextView.id, ConstraintSet.START)

        // 左边文字的设置
        constraintSet.constrainWidth(leftTextView.id, ConstraintSet.WRAP_CONTENT)
        constraintSet.constrainHeight(leftTextView.id, 0)
        constraintSet.connect(leftTextView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(leftTextView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        constraintSet.connect(leftTextView.id, ConstraintSet.START, leftImgView.id, ConstraintSet.END)
        constraintSet.setGoneMargin(leftTextView.id, ConstraintSet.START, context.dp2px(8))

        // 标题左边图标的设置
        constraintSet.constrainWidth(titleLeftIconImageView.id, ConstraintSet.WRAP_CONTENT)
        constraintSet.constrainHeight(titleLeftIconImageView.id, ConstraintSet.WRAP_CONTENT)
        constraintSet.connect(
            titleLeftIconImageView.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )
        constraintSet.connect(titleLeftIconImageView.id, ConstraintSet.END, titleTextView.id, ConstraintSet.START)
        constraintSet.setHorizontalChainStyle(titleLeftIconImageView.id, ConstraintSet.CHAIN_PACKED)
        constraintSet.connect(
            titleLeftIconImageView.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        constraintSet.connect(titleLeftIconImageView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

        // 标题的设置
        constraintSet.constrainWidth(titleTextView.id, ConstraintSet.WRAP_CONTENT)
        constraintSet.constrainHeight(titleTextView.id, 0)
        constraintSet.connect(titleTextView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(titleTextView.id, ConstraintSet.END, titleRightIconImageView.id, ConstraintSet.START)
        constraintSet.connect(titleTextView.id, ConstraintSet.START, titleLeftIconImageView.id, ConstraintSet.END)
        constraintSet.connect(titleTextView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        constraintSet.setMargin(titleTextView.id, ConstraintSet.START, context.dp2px(5))
        constraintSet.setMargin(titleTextView.id, ConstraintSet.END, context.dp2px(5))

        // 标题右边的图标的设置
        constraintSet.constrainWidth(titleRightIconImageView.id, ConstraintSet.WRAP_CONTENT)
        constraintSet.constrainHeight(titleRightIconImageView.id, ConstraintSet.WRAP_CONTENT)
        constraintSet.connect(
            titleRightIconImageView.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )
        constraintSet.connect(titleRightIconImageView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        constraintSet.connect(titleRightIconImageView.id, ConstraintSet.START, titleTextView.id, ConstraintSet.END)
        constraintSet.connect(titleRightIconImageView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

        // 右边文字的设置
        constraintSet.constrainWidth(rightTextView.id, ConstraintSet.WRAP_CONTENT)
        constraintSet.constrainHeight(rightTextView.id, 0)
        constraintSet.connect(rightTextView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(rightTextView.id, ConstraintSet.END, rightImgView.id, ConstraintSet.START)
        constraintSet.connect(rightTextView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        constraintSet.setGoneMargin(rightTextView.id, ConstraintSet.END, context.dp2px(8))

        // 右边图片的设置
        constraintSet.constrainWidth(rightImgView.id, ConstraintSet.WRAP_CONTENT)
        constraintSet.constrainHeight(rightImgView.id, 0)
        constraintSet.connect(rightImgView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(rightImgView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        constraintSet.connect(rightImgView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

        constraintSet.applyTo(this)
    }

    /**
     * 初始化事件
     */
    private fun initEvent()
    {
        leftImgView.setOnClickListener(clickListener)
        leftTextView.setOnClickListener(clickListener)
        titleLeftIconImageView.setOnClickListener(clickListener)
        titleTextView.setOnClickListener(clickListener)
        titleRightIconImageView.setOnClickListener(clickListener)
        rightTextView.setOnClickListener(clickListener)
        rightImgView.setOnClickListener(clickListener)
    }


    /**
     * 当左边图片被点击
     */
    fun onLeftImgClick(block: (View) -> Unit)
    {
        leftImgViewBlock = block
    }

    /**
     * 当左边文字被点击
     */
    fun onLeftTextClick(block: (View) -> Unit)
    {
        leftTextViewBlock = block
    }

    /**
     * 当标题被点击
     */
    fun onTitleClick(block: (View) -> Unit)
    {
        titleTextViewBlock = block
    }

    /**
     * 当标题左边Icon被点击
     */
    fun onTitleLeftIconClick(block: (View) -> Unit)
    {
        titleLeftIconImageViewBlock = block
    }

    /**
     * 当标题右边Icon被点击
     */
    fun onTitleRightIconClick(block: (View) -> Unit)
    {
        titleRightIconImageViewBlock = block
    }

    /**
     * 当右边文字被点击
     */
    fun onRightTextClick(block: (View) -> Unit)
    {
        rightTextViewBlock = block
    }

    /**
     * 当右边图片被点击
     */
    fun onRightImgClick(block: (View) -> Unit)
    {
        rightImgViewBlock = block
    }
    

    companion object
    {
        val INVISIBLE = View.INVISIBLE
        val VISIBLE = View.VISIBLE
        val GONE = View.GONE
    }

}