package com.example.alessandrofsouza.santanderapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

class PageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    public PageAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    public void add(Fragment frag, String title) {
        this.fragments.add(frag);
        this.title.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.title.get(position);
    }
}