package com.example.sdllabproject1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class ManagerActivity extends AppCompatActivity {
    //private TabLayout tabLayout;
    private ViewPager viewPager;

    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new admin_fragment(), "Upcoming");
        adapter.addFragment(new admin_fragment2(), "Ongoing");

        viewPager.setAdapter(adapter);
    }
}