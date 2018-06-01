package com.cheng.fitness.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.baselib.utils.ScreenUtil;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.Constant;
import com.cheng.fitness.contact.CommunityContact;
import com.cheng.fitness.model.CommentBean;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.presenter.CommunityPresenter;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.utils.RxRecyclerViewDividerTool;
import com.cheng.fitness.views.activity.CommunityDetailActivity;
import com.cheng.fitness.views.adapter.CommunityAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class CommunityFragment extends BaseFragment<CommunityContact.presenter> implements CommunityContact.view {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private CommunityAdapter mAdapter;
    private List<CommunityBean> mCommunityBeans = new ArrayList<>();

    @Override
    public CommunityContact.presenter initPresenter() {
        return new CommunityPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mAdapter = new CommunityAdapter(mCommunityBeans);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.addItemDecoration(new RxRecyclerViewDividerTool(ScreenUtil.dp2px(getActivity(), 5f)));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), CommunityDetailActivity.class).putExtra(Constant.KEY_COMMUNITY, mCommunityBeans.get(position)));
            }
        });
    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        presenter.getCommunities();
    }

    @Override
    public void onGetCommunitiesSuccess(List<CommunityBean> beans) {
        if (beans.size() == 0) return;
        mCommunityBeans.clear();
        mCommunityBeans.addAll(beans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetCommunitiesFail(String msg) {
        showToast(msg);
    }
}
