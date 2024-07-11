package com.seachal.seachaltest.TextView

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.seachal.seachaltest.R
import com.seachal.seachaltest.TextView.spannableString.LeftRoundBackgroundColorSpan
import com.seachal.seachaltest.TextView.spannableString.RadiusBackgroundSpan
import com.seachal.seachaltest.TextView.spannableString.RoundBackgroundColorSpan2
import com.seachal.seachaltest.TextView.spannableString.UserRoleUtils
import com.xiaolei.library.wdiget.dp2px


class SpannableStringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable_stringctivity)
        mode1()
        mode2()
        mode3()
        mode4()
        mode5()
        mode6()
        mode7()
        mode8()
        mode9()
        //mode10();
        mode1101()
        mode1102()
        mode1103()
        mode12()
        mode13()
    }

    /**
     * 使用SpannableString设置样式——字体颜色
     */
    private fun mode1() {
        val spannableString = SpannableString("2022年7月20培周将进行一场精彩的直播")
        val colorSpan = ForegroundColorSpan(Color.parseColor("#009ad6"))
        spannableString.setSpan(colorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        (findViewById<View>(R.id.mode1) as TextView).text = spannableString
    }

    /**
     * 使用SpannableStringBuilder设置样式——字体颜色
     */
    private fun mode2() {
        val spannableString = SpannableStringBuilder()
        spannableString.append("通识")
        spannableString.append("2022年7月20培周")
        val colorSpan = ForegroundColorSpan(Color.parseColor("#009ad6"))
        spannableString.setSpan(colorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        (findViewById<View>(R.id.mode2) as TextView).text = spannableString
    }

    /**
     * 使用SpannableStringBuilder设置样式——背景颜色
     */
    private fun mode3() {
        val spannableString = SpannableStringBuilder()
        spannableString.append("2022年7月20培周将进行一场精彩的直播")
        val bgColorSpan = BackgroundColorSpan(Color.parseColor("#009ad6"))
        spannableString.setSpan(bgColorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        (findViewById<View>(R.id.mode3) as TextView).text = spannableString
    }

    /**
     * 使用SpannableStringBuilder设置样式——字体大小
     */
    private fun mode4() {
        val spannableString = SpannableStringBuilder()
        spannableString.append("2022年7月20培周将进行一场精彩的直播")
        val absoluteSizeSpan = AbsoluteSizeSpan(20)
        spannableString.setSpan(absoluteSizeSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        (findViewById<View>(R.id.mode4) as TextView).text = spannableString
    }

    /**
     * 使用SpannableStringBuilder设置样式——粗体\斜体
     */
    private fun mode5() {
        val spannableString = SpannableStringBuilder()
        spannableString.append("2022年7月20培周将进行一场精彩的直播")
        //setSpan可多次使用
        val styleSpan = StyleSpan(Typeface.BOLD) //粗体
        spannableString.setSpan(styleSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        val styleSpan2 = StyleSpan(Typeface.ITALIC) //斜体
        spannableString.setSpan(styleSpan2, 3, 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        val styleSpan3 = StyleSpan(Typeface.BOLD_ITALIC) //粗斜体
        spannableString.setSpan(styleSpan3, 6, 9, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        (findViewById<View>(R.id.mode5) as TextView).text = spannableString
    }

    /**
     * 使用SpannableStringBuilder设置样式——删除线
     */
    private fun mode6() {
        val spannableString = SpannableStringBuilder()
        spannableString.append("2022年7月20培周将进行一场精彩的直播")
        val strikethroughSpan = StrikethroughSpan()
        spannableString.setSpan(strikethroughSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        (findViewById<View>(R.id.mode6) as TextView).text = spannableString
    }

    /**
     * 使用SpannableStringBuilder设置样式——下划线
     */
    private fun mode7() {
        val spannableString = SpannableStringBuilder()
        spannableString.append("2022年7月20培周将进行一场精彩的直播")
        val underlineSpan = UnderlineSpan()
        spannableString.setSpan(underlineSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        (findViewById<View>(R.id.mode7) as TextView).text = spannableString
    }

    /**
     * 使用SpannableStringBuilder设置样式——图片
     */
    private fun mode8() {
        val spannableString = SpannableStringBuilder()
        spannableString.append("2022年7月20培周将进行一场精彩的直播")
        val imageSpan = ImageSpan(this, R.mipmap.ic_launcher)
        //也可以这样
        //Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        //drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //ImageSpan imageSpan1 = new ImageSpan(drawable);
        //将index为6、7的字符用图片替代
        spannableString.setSpan(imageSpan, 6, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        (findViewById<View>(R.id.mode8) as TextView).text = spannableString
    }

    /**
     * 使用SpannableStringBuilder设置点击事件
     */
    private fun mode9() {
        val spannableString = SpannableStringBuilder()
        spannableString.append("2022年7月20培周将进行一场精彩的直播")
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                Toast.makeText(this@SpannableStringActivity, "请不要点我", Toast.LENGTH_SHORT).show()
            }
        }
        spannableString.setSpan(clickableSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        val textView = findViewById<View>(R.id.mode9) as TextView
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * 使用SpannableStringBuilder事件组合使用
     */
    private fun mode10() {
        val spannableString = SpannableStringBuilder()
        spannableString.append("2022年7月20培周将进行一场精彩的直播")
        //图片
        val imageSpan = ImageSpan(this, R.mipmap.ic_launcher)
        spannableString.setSpan(imageSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        //点击事件
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                Toast.makeText(this@SpannableStringActivity, "请不要点我", Toast.LENGTH_SHORT).show()
            }
        }
        spannableString.setSpan(clickableSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        //文字颜色
        val colorSpan = ForegroundColorSpan(Color.parseColor("#FFFFFF"))
        spannableString.setSpan(colorSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        //文字背景颜色
        val bgColorSpan = BackgroundColorSpan(Color.parseColor("#009ad6"))
        spannableString.setSpan(bgColorSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        val textView = findViewById<View>(R.id.mode10) as TextView
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun mode1101() {
        val pxvalue1 = getResources().getDimensionPixelOffset(R.dimen.dp_px_8)
        val pxvalue2 = getResources().getDimensionPixelOffset(R.dimen.dp_px_20)
        val pxvalue3 = getResources().getDimensionPixelOffset(R.dimen.dp_px_4)
        var bss = LeftRoundBackgroundColorSpan( pxvalue1,  ContextCompat.getColor(this@SpannableStringActivity,
            R.color.color_D22E1B),
            ContextCompat.getColor(this@SpannableStringActivity, R.color.color_D22E1B),pxvalue2,pxvalue3
        )
        val spannableString = SpannableStringBuilder()
        spannableString.append("通识dasdada")
        spannableString.setSpan(bss, 0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        val textView = findViewById<View>(R.id.mode1101) as TextView
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun mode1102() {
        val pxvalue1 = getResources().getDimensionPixelOffset(R.dimen.dp_px_8)
        val pxvalue2 = getResources().getDimensionPixelOffset(R.dimen.dp_px_20)
        val pxvalue3 = getResources().getDimensionPixelOffset(R.dimen.dp_px_6)
        var bss = LeftRoundBackgroundColorSpan( pxvalue1,  ContextCompat.getColor(this@SpannableStringActivity,
            R.color.color_D22E1B),
            ContextCompat.getColor(this@SpannableStringActivity, R.color.color_D22E1B),pxvalue2,pxvalue3
        )
        val spannableString = SpannableStringBuilder()
        spannableString.append("通识2022年72022年7月20培周将进行一场精彩的直播")
        spannableString.setSpan(bss, 0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        val textView = findViewById<View>(R.id.mode1102) as TextView
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }



    private fun mode1103() {
        val pxvalue1 = getResources().getDimensionPixelOffset(R.dimen.dp_px_8)
        val pxvalue2 = getResources().getDimensionPixelOffset(R.dimen.dp_px_20)
        val pxvalue3 = getResources().getDimensionPixelOffset(R.dimen.dp_px_6)
        var bss = LeftRoundBackgroundColorSpan( pxvalue1,  ContextCompat.getColor(this@SpannableStringActivity,
            R.color.color_D22E1B),
            ContextCompat.getColor(this@SpannableStringActivity, R.color.color_D22E1B),pxvalue2,pxvalue3
        )
        val spannableString = SpannableStringBuilder()
        spannableString.append("通识精彩直播2022年7月20培周将进行一场精彩的直播")
        spannableString.setSpan(bss, 0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        val textView = findViewById<View>(R.id.mode1103) as TextView
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }


    private fun mode12() {
        val roleName = "【管理员】"
        val userName = "张三老师"
        val msg: String = roleName + userName + ": "
        val ss = SpannableString(msg)
        if (!TextUtils.isEmpty(roleName)) {
            val span: RadiusBackgroundSpan =
                UserRoleUtils.getVerticalUserRoleLabelSpan("publisher")
            ss.setSpan(span, 0, roleName.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val textView = findViewById<View>(R.id.mode12) as TextView
        textView.text = ss
    }

    private fun mode13() {
        var bss: RadiusBackgroundSpan
        bss = RadiusBackgroundSpan(ContextCompat.getColor(this@SpannableStringActivity,
            R.color.color_D22E1B),
            20
        )
        val spannableString = SpannableStringBuilder()
        spannableString.append("2022年7月20培周将进行一场精彩的直播")
        spannableString.setSpan(bss, 0, 2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        val textView = findViewById<View>(R.id.mode13) as TextView
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}