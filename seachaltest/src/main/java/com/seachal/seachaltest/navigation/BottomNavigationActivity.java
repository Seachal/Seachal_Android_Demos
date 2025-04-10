package com.seachal.seachaltest.navigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.seachal.seachaltest.R;
import com.seachal.seachaltest.navigation.fragments.ExamFragment;
import com.seachal.seachaltest.navigation.fragments.ProfileFragment;
import com.seachal.seachaltest.navigation.fragments.StudyFragment;



public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView bottomNavigationViewMaterial;
    private BottomNavigationView bottomNavigationViewCard;
    private BottomNavigationView bottomNavigationViewElevation;
    
    private RadioGroup shadowOptions;
    private FrameLayout containerLayerList;
    private MaterialCardView containerMaterialCard;
    private CardView containerCardView;
    private FrameLayout containerElevation;
    
    // 防止无限递归的标志
    private boolean isHandlingNavigation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        
        setTitle("底部导航圆角阴影测试");

        // 找到所有底部导航视图
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationViewMaterial = findViewById(R.id.bottom_navigation_material);
        bottomNavigationViewCard = findViewById(R.id.bottom_navigation_card);
        bottomNavigationViewElevation = findViewById(R.id.bottom_navigation_elevation);
        
        // 为所有底部导航设置监听器
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationViewMaterial.setOnNavigationItemSelectedListener(this);
        bottomNavigationViewCard.setOnNavigationItemSelectedListener(this);
        bottomNavigationViewElevation.setOnNavigationItemSelectedListener(this);
        
        // 找到所有容器
        containerLayerList = findViewById(R.id.container_layer_list);
        containerMaterialCard = findViewById(R.id.container_material_card);
        containerCardView = findViewById(R.id.container_card_view);
        containerElevation = findViewById(R.id.container_elevation);
        
        // 找到单选按钮组并设置监听器
        shadowOptions = findViewById(R.id.shadow_options);
        shadowOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateNavigationStyle(checkedId);
            }
        });

        // 设置默认片段
        loadFragment(StudyFragment.newInstance());
        
        // 确保初始状态显示Layer-List方案
        updateNavigationStyle(R.id.option_layer_list);
    }
    
    /**
     * 根据选择的单选按钮更新底部导航样式
     */
    private void updateNavigationStyle(int checkedId) {
        // 隐藏所有容器
        containerLayerList.setVisibility(View.GONE);
        containerMaterialCard.setVisibility(View.GONE);
        containerCardView.setVisibility(View.GONE);
        containerElevation.setVisibility(View.GONE);
        
        // 显示选中的容器
        if (checkedId == R.id.option_layer_list) {
            containerLayerList.setVisibility(View.VISIBLE);
        } else if (checkedId == R.id.option_material_card) {
            containerMaterialCard.setVisibility(View.VISIBLE);
        } else if (checkedId == R.id.option_card_view) {
            containerCardView.setVisibility(View.VISIBLE);
        } else if (checkedId == R.id.option_elevation) {
            containerElevation.setVisibility(View.VISIBLE);
        }
        
        // 同步当前选中的菜单项
        syncNavigationSelections();
    }
    
    /**
     * 同步所有底部导航的选中状态
     */
    private void syncNavigationSelections() {
        isHandlingNavigation = true;
        
        try {
            int currentItemId = bottomNavigationView.getSelectedItemId();
            bottomNavigationViewMaterial.setSelectedItemId(currentItemId);
            bottomNavigationViewCard.setSelectedItemId(currentItemId);
            bottomNavigationViewElevation.setSelectedItemId(currentItemId);
        } finally {
            isHandlingNavigation = false;
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // 如果已经在处理导航事件，跳过以避免无限递归
        if (isHandlingNavigation) {
            return false;
        }
        
        isHandlingNavigation = true;
        try {
            Fragment fragment = null;
            
            int itemId = item.getItemId();
            if (itemId == R.id.nav_study) {
                fragment = StudyFragment.newInstance();
            } else if (itemId == R.id.nav_exam) {
                fragment = ExamFragment.newInstance();
            } else if (itemId == R.id.nav_profile) {
                fragment = ProfileFragment.newInstance();
            }
    
            // 同步所有导航的选择状态
            bottomNavigationView.setSelectedItemId(itemId);
            bottomNavigationViewMaterial.setSelectedItemId(itemId);
            bottomNavigationViewCard.setSelectedItemId(itemId);
            bottomNavigationViewElevation.setSelectedItemId(itemId);
    
            return loadFragment(fragment);
        } finally {
            isHandlingNavigation = false;
        }
    }
} 