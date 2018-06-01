package com.cheng.fitness.views.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.fitness.R;
import com.cheng.fitness.utils.DensityUtil;
import com.cheng.fitness.utils.inter.OnUpdateListener;
import com.cheng.fitness.views.adapter.FitnessAdapter;

import java.lang.reflect.Field;

import butterknife.Bind;
import timber.log.Timber;

/**
 * author: PengCheng
 * time: 2018/5/30 0030
 * desc: 训练
 */

public class TrainFragment extends BaseFragment {


    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    private FitnessFragment mFitnessFragment;
    private RunFragment mRunFragment;

    private OnUpdateListener mOnUpdateListener;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_train;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Timber.d("执行11111");
        String[] titles = {"健身", "跑步"};

        mFitnessFragment = new FitnessFragment();
        mFitnessFragment.setUpdateListener(mOnUpdateListener);
        mRunFragment = new RunFragment();
        Fragment[] fragments = {mFitnessFragment, mRunFragment};
        FitnessAdapter adapter = new FitnessAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(titles.length);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void loadData() {
        super.loadData();
        setTabLayout();
    }

    public void setUpdateListener(OnUpdateListener onUpdateListener) {
        mOnUpdateListener = onUpdateListener;
    }

    //用反射来设置tablayout宽度
    public void setTabLayout(){
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    Field mTabStripField = mTabLayout.getClass().getDeclaredField("mTabStrip");
                    mTabStripField.setAccessible(true);
                    LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(mTabLayout);
                    int dp10 = DensityUtil.dp2px(10);
//                    int dp10 = DensityUtil.dp2px(tabLayout.getContext(), 10);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
//                        params.leftMargin = dp10;
//                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
//                    setTabDivider();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setTabDivider() {
        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(DensityUtil.dp2px(5));
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.divider_vertical));
    }
}
