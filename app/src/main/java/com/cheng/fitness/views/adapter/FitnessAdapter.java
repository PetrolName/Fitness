package com.cheng.fitness.views.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: PengCheng
 * time: 2018/5/30 0030
 * desc:
 */

public class FitnessAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private Fragment[] fragments;
    private boolean[] fragmentsUpdateFlag;

    private String[] mTitles;
    public FitnessAdapter(FragmentManager fm, Fragment[] fragments, String[] mTitles) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
        this.mTitles = mTitles;
        fragmentsUpdateFlag = new boolean[fragments.length];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position % fragments.length];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container,
                position);
        String fragmentTag = fragment.getTag();
        if (fragmentsUpdateFlag[position % fragmentsUpdateFlag.length]) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fragment);
            fragment = fragments[position % fragments.length];
            ft.add(container.getId(), fragment, fragmentTag);
            ft.attach(fragment);
            ft.commit();
            fragmentsUpdateFlag[position % fragmentsUpdateFlag.length] = false;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
