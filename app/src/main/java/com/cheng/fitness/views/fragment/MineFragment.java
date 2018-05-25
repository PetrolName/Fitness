package com.cheng.fitness.views.fragment;

import android.os.Bundle;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.fitness.R;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class MineFragment extends BaseFragment{
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }
}
