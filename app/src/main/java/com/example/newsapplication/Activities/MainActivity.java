package com.example.newsapplication.Activities;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.newsapplication.Classes.PagerAdapter;
import com.example.newsapplication.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem nHome, mScience, mTechnology, mSports, mHealth, mEntertainment;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;
    Toolbar mToolbar;
    private static final String API = "8b8f8d34a8334771a6c2e576265be84c";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Setting Toolbar*/
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        /*tabLayout id*/
        tabLayout = findViewById(R.id.tabLayout);

        /*tabItems ids*/
        nHome = findViewById(R.id.home);
        mTechnology = findViewById(R.id.technology);
        mScience = findViewById(R.id.science);
        mSports = findViewById(R.id.sports);
        mHealth = findViewById(R.id.health);
        mEntertainment = findViewById(R.id.entertainment);

        /*ViewPager id*/
        viewPager = findViewById(R.id.fragmentContainer);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 6);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 ||
                        tab.getPosition() == 2 ||
                        tab.getPosition() == 3 ||
                        tab.getPosition() == 4 ||
                        tab.getPosition() == 5) {
                    pagerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}