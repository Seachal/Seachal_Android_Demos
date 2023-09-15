package com.seachal.seachaltest.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.seachal.seachaltest.R;

public class CardViewActivity extends AppCompatActivity {


    CardView cardview;


    CardView  card_view_menu_container1;
    CardView  card_view_menu_container2;

//
    private  int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        cardview = findViewById(R.id.card_view_menu_container);
        card_view_menu_container1 = findViewById(R.id.card_view_menu_container1);
        card_view_menu_container2 = findViewById(R.id.card_view_menu_container2);


        card_view_menu_container2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                   if (count %2 >0){
//                         会比 xml 中初始的颜色更浅一些
                       cardview.setOutlineAmbientShadowColor(ContextCompat.getColor(CardViewActivity.this, R.color.gray3));
                       cardview.setOutlineSpotShadowColor(ContextCompat.getColor(CardViewActivity.this, R.color.gray3));

                       card_view_menu_container1.setOutlineAmbientShadowColor(ContextCompat.getColor(CardViewActivity.this, R.color.gray1));
                       card_view_menu_container1.setOutlineSpotShadowColor(ContextCompat.getColor(CardViewActivity.this, R.color.gray1));

                   }else {
//                    看不到边框，  完全融为一体。

                       cardview.setOutlineAmbientShadowColor(ContextCompat.getColor(CardViewActivity.this, R.color.white));
                       cardview.setOutlineSpotShadowColor(ContextCompat.getColor(CardViewActivity.this, R.color.white));

                       card_view_menu_container1.setOutlineAmbientShadowColor(ContextCompat.getColor(CardViewActivity.this, R.color.white));
                       card_view_menu_container1.setOutlineSpotShadowColor(ContextCompat.getColor(CardViewActivity.this, R.color.white));

                   }
                    count++ ;
                }
            }
        });

    }
}