package com.cheng.fitness.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.utils.ScreenUtil;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.common.constant.Constant;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.utils.RxRecyclerViewDividerTool;
import com.cheng.fitness.views.adapter.CommunityAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: PengCheng
 * time: 2018/5/30 0030
 * desc: 我的动态
 */

public class MyDynamicActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.tvTip)
    TextView tvTip;

    private CommunityAdapter mAdapter;
    private List<CommunityBean> mCommunityBeans = new ArrayList<>();

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_dynamic;
    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("我的动态");
    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        getData();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mAdapter = new CommunityAdapter(mCommunityBeans);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MyDynamicActivity.this, 2));
        mRecyclerView.addItemDecoration(new RxRecyclerViewDividerTool(ScreenUtil.dp2px(MyDynamicActivity.this, 5f)));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(MyDynamicActivity.this, CommunityDetailActivity.class).putExtra(Constant.KEY_COMMUNITY, mCommunityBeans.get(position)));
            }
        });
    }

    private void getData() {
        List<CommunityBean> beans = GreenDaoUtil.getCommunitiesByName(ConfigConstant.getKeyUserNickname());
        if (beans.size() == 0) {
            mRecyclerView.setVisibility(View.GONE);
            tvTip.setVisibility(View.VISIBLE);
            return;
        }
        mCommunityBeans.clear();
        mCommunityBeans.addAll(beans);
        mAdapter.notifyDataSetChanged();
    }

}
