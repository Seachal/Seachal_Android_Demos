package com.seachal.seachaltest.navigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.seachal.seachaltest.R;
import com.seachal.seachaltest.navigation.fragments.ExamFragment;
import com.seachal.seachaltest.navigation.fragments.ProfileFragment;
import com.seachal.seachaltest.navigation.fragments.StudyFragment;


public class BottomNavigation2Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation2);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Set default fragment
        loadFragment(StudyFragment.newInstance());
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
        Fragment fragment = null;
        
        int itemId = item.getItemId();
        if (itemId == R.id.nav_study) {
            fragment = StudyFragment.newInstance();
        } else if (itemId == R.id.nav_exam) {
            fragment = ExamFragment.newInstance();
        } else if (itemId == R.id.nav_profile) {
            fragment = ProfileFragment.newInstance();
        }

        return loadFragment(fragment);
    }
} 