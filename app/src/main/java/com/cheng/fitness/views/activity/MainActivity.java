package com.cheng.fitness.views.activity;

import android.support.v4.app.Fragment;

import com.cheng.fitness.R;
import com.cheng.fitness.common.base.BottomTabBaseActivity;
import com.cheng.fitness.utils.inter.OnUpdateListener;
import com.cheng.fitness.views.fragment.CommunityFragment;
import com.cheng.fitness.views.fragment.CourseFragment;
import com.cheng.fitness.views.fragment.TrainFragment;
import com.cheng.fitness.views.fragment.MineFragment;
import com.cheng.fitness.views.widget.BottomTabView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends BottomTabBaseActivity implements OnUpdateListener {

    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();
        BottomTabView.TabItemView tabTrain = new BottomTabView.TabItemView(this, "训练", R.color.c666666, R.color.c1f1f1f, R.mipmap.icon_tab_train_normal, R.mipmap.icon_tab_train_press, false);
        BottomTabView.TabItemView tabCommunity = new BottomTabView.TabItemView(this, "社区", R.color.c666666, R.color.c1f1f1f, R.mipmap.icon_tab_community_normal, R.mipmap.icon_tab_community_press, false);
        BottomTabView.TabItemView tabCourse = new BottomTabView.TabItemView(this, "课程", R.color.c666666, R.color.c1f1f1f, R.mipmap.icon_tab_course_normal, R.mipmap.icon_tab_course_press, false);
        BottomTabView.TabItemView tabMine = new BottomTabView.TabItemView(this, "我的", R.color.c666666, R.color.c1f1f1f, R.mipmap.icon_tab_mine_normal, R.mipmap.icon_tab_mine_press, false);
        tabItemViews.add(tabTrain);
        tabItemViews.add(tabCommunity);
        tabItemViews.add(tabCourse);
        tabItemViews.add(tabMine);
        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        TrainFragment trainFragment = new TrainFragment();
        trainFragment.setUpdateListener(this);
        fragments.add(trainFragment);
        fragments.add(new CommunityFragment());
        fragments.add(new CourseFragment());
        fragments.add(new MineFragment());
        return fragments;
    }

    @Override
    public void onUpdatePosition(int position) {
        updatePosition(position);
    }

}
