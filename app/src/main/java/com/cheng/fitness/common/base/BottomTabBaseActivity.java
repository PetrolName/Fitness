package com.cheng.fitness.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.cheng.baselib.utils.ActivityManager;
import com.cheng.fitness.R;
import com.cheng.fitness.views.widget.BottomTabView;
import com.cheng.fitness.views.widget.NoScrollViewPager;

import java.util.List;

public abstract class BottomTabBaseActivity extends AppCompatActivity {

    public NoScrollViewPager viewPager;
    protected BottomTabView bottomTabView;
    FragmentPagerAdapter adapter;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
//        }
        ActivityManager.getAppInstance().addActivity(this);//将当前activity添加进入管理栈
        setContentView(R.layout.activity_base_bottom_tab);

        viewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        bottomTabView = (BottomTabView) findViewById(R.id.bottomTabView);
        mFragments = getFragments();
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        viewPager.setOffscreenPageLimit(2);
        viewPager.setNoScroll(true);
        viewPager.setAdapter(adapter);

//        bottomTabView.setTabItemViews(getTabViews());
        if (getCenterView() == null) {
            bottomTabView.setTabItemViews(getTabViews());
        } else {
            bottomTabView.setTabItemViews(getTabViews(), getCenterView());
        }

        bottomTabView.setOnTabItemSelectListener(new BottomTabView.OnTabItemSelectListener() {
            @Override
            public void onTabItemSelect(int position) {
                viewPager.setCurrentItem(position, false);
            }
        });

        bottomTabView.setOnSecondSelectListener(new BottomTabView.OnSecondSelectListener() {
            @Override
            public void onSecondSelect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomTabView.updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        updatePosition(0);
    }

    protected abstract List<BottomTabView.TabItemView> getTabViews();

    protected abstract List<Fragment> getFragments();

    protected View getCenterView() {
        return null;
    }

    public void updatePosition(int position) {
        bottomTabView.updatePosition(position);
    }

}
