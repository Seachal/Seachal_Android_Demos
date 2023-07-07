package com.seachal.seachaltest.TextView

import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import com.seachal.seachaltest.R
import kotlinx.android.synthetic.main.activity_text_size.textView
import kotlinx.android.synthetic.main.activity_text_size.textView4
import kotlinx.android.synthetic.main.activity_text_size.textView5
import kotlinx.android.synthetic.main.activity_text_size.textView6
import kotlinx.android.synthetic.main.activity_text_size.textView7

class TextSizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_size)
//    1
        textView4.textSize = 30f

//  2    这种设置的字体大小有问题。
         val pixelSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(R.dimen.dp_px_60),
            resources.displayMetrics
        )
//       pixelSize 是 px值， 然后仍然按照sp的方式设置字体大小， 会导致字体大小不对。
        textView5.textSize = pixelSize

// ============
        textView6.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixelSize)

//     DP  to  PX。  通过这种方式就可以成功设置字体大小了
        val  pixelSize7 =  resources.getDimension(R.dimen.dp_px_60)
        textView7.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixelSize7)
    }
}