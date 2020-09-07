package com.ose4g.gadsproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {
    /*
    Adapter for the viewpager2 which moves between the fragments
    It switched between fragments
     */

    private ArrayList<Fragment> mFragmentArrayList;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<Fragment> fragmentArrayList) {
        super(fragmentManager, lifecycle);
        mFragmentArrayList = fragmentArrayList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentArrayList.size();
    }
}
