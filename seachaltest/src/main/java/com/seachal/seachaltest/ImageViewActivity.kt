package com.seachal.seachaltest

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_view.ivCoupon_4

class ImageViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        ivCoupon_4.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN)

    }
}