package com.seachal.seachaltest.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;
import com.seachal.seachaltest.R;

public class CardViewActivity3 extends AppCompatActivity {

    MaterialCardView cardview;
    MaterialCardView card_view_menu_container1;
    MaterialCardView card_view_menu_container2;
    MaterialCardView card_view_menu_container6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view3);
        cardview = findViewById(R.id.card_view_menu_container);
        card_view_menu_container1 = findViewById(R.id.card_view_menu_container1);
        
        // Only try to find other views if they exist in the layout
        try {
            card_view_menu_container2 = findViewById(R.id.card_view_menu_container2);
            card_view_menu_container6 = findViewById(R.id.card_view_menu_container6);
            
            // Only setup click listener if the view exists
            if (card_view_menu_container6 != null) {
                card_view_menu_container6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            cardview.setOutlineAmbientShadowColor(ContextCompat.getColor(CardViewActivity3.this, R.color.gray3));
                            cardview.setOutlineSpotShadowColor(ContextCompat.getColor(CardViewActivity3.this, R.color.gray3));

                            card_view_menu_container1.setOutlineAmbientShadowColor(ContextCompat.getColor(CardViewActivity3.this, R.color.gray1));
                            card_view_menu_container1.setOutlineSpotShadowColor(ContextCompat.getColor(CardViewActivity3.this, R.color.gray1));
                        }
                    }
                });
            }
        } catch (Exception e) {
            // Handle case where some views might not exist in the layout
            e.printStackTrace();
        }
    }
}