package com.example.materialdesign.mvp.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> views;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> views) {
        super(fm);

        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }


    @Override
    public Fragment getItem(int i) {
        return views.get(i);
    }

}
