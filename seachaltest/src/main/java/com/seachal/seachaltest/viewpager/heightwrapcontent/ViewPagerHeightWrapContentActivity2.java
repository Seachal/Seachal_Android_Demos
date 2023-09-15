package com.seachal.seachaltest.viewpager.heightwrapcontent;

import androidx.appcompat.app.AppCompatActivity;

public class ViewPagerHeightWrapContentActivity2 extends AppCompatActivity {
//    private WrappingViewPager viewPager;
//    private TabLayout tabLayout;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.viewpager_activity_main2);
//
//        setupViewPager();
//    }
//
//    private void setupViewPager() {
//        viewPager = (WrappingViewPager) findViewById(R.id.viewpager);
//
//        // Using one of the provided adapter so we don't have to implement page changing logic ourselves
//        viewPager.setAdapter(new WrappingFragmentStatePagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                switch (position) {
//                    case 0:
//                        return ContentFragment.newInstance(getString(R.string.tab_foo_content));
//                    case 1:
//                        return ContentFragment.newInstance(getString(R.string.tab_foobar_content));
//                    case 2:
//                        return ContentFragment.newInstance(getString(R.string.tab_bar_content));
//                    default:
//                        return ContentFragment.newInstance(getString(R.string.tab_wtf));
//                }
//            }
//
//            @Override
//            public int getCount() {
//                return 3;
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                switch (position) {
//                    case 0:
//                        return "0";
//                    case 1:
//                        return "1";
//                    case 2:
//                        return "2";
//                    default:
//                        return "default";
//                }
//            }
//        });
//
//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        if (tabLayout != null) {
//            tabLayout.setupWithViewPager(viewPager);
//        }
//    }
}