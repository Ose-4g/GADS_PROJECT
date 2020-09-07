package com.ose4g.gadsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class LearnersListActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    private ArrayList<Fragment> mFragmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learners_list);

        //DOES SAME THING IT DOES IN THE SPLASH SCRREN ACTIVITY
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        mFragmentArrayList = new ArrayList<>();// List for the FragmentAdapter for the Viewpager2
        mFragmentArrayList.add(new LearnersFragment());
        mFragmentArrayList.add(new SkillIQListActivity());

        final String[] tabNames = {"LEARNING LEADERS", "SKILL IQ LEADERS"};//Titles for the Tab layout in the activity

        mTabLayout = findViewById(R.id.leaders_tabs);
        mViewPager2 = findViewById(R.id.viewpager);
        mViewPager2.setAdapter(new ViewPagerAdapter(this.getSupportFragmentManager(),getLifecycle(),mFragmentArrayList));

        //i had to use this as this was what worked for viewpager2
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout, mViewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabNames[position]);//set the tab names for each of the tabs
            }
        });
        tabLayoutMediator.attach();

        //button links to the submit activity
        ((Button) findViewById(R.id.to_submisison)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnersListActivity.this,SubmitActivity.class);
                startActivity(intent);
            }
        });


    }
}