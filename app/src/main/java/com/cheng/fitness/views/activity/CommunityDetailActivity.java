package com.cheng.fitness.views.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.Constant;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.utils.GreenDaoUtil;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author: PengCheng
 * time: 2018/5/29 0029
 * desc: 社区详情
 */

public class CommunityDetailActivity extends BaseActivity {
    @Bind(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvComment)
    TextView tvComment;
    @Bind(R.id.tvLike)
    TextView tvLike;

    private CommunityBean mCommunityBean;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_community_detail;
    }

    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);
        mCommunityBean = (CommunityBean) getIntent().getSerializableExtra(Constant.KEY_COMMUNITY);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (mCommunityBean == null) return;
        tvName.setText(mCommunityBean.getName());
        tvTime.setText(mCommunityBean.getTime());
        tvContent.setText(mCommunityBean.getContent());
        tvComment.setText(String.valueOf(mCommunityBean.getComment()));
        tvLike.setText(String.valueOf(mCommunityBean.getLike()));
        setImage(mCommunityBean.getIsLike());
    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("动态详情");
        toolBar.setDrawable(ToolBarView.TEXT_RIGHT, R.mipmap.icon_publish_add);
        toolBar.setOnRightClickListener(new ToolBarView.OnBarRightClickListener() {
            @Override
            public void onRightClick(View v) {
                startActivity(new Intent(CommunityDetailActivity.this, PublishActivity.class));
            }
        });
    }

    @OnClick(R.id.tvLike)
    public void onClick(View view) {
        mCommunityBean.setIsLike(!mCommunityBean.getIsLike());
        int likeNum = mCommunityBean.getIsLike()? (mCommunityBean.getLike() + 1):(mCommunityBean.getLike() - 1);
        mCommunityBean.setLike(likeNum);
        tvLike.setText(String.valueOf(likeNum));
        GreenDaoUtil.updateCommunity(mCommunityBean);
        setImage(mCommunityBean.getIsLike());
    }

    private void setImage(boolean isLike) {
        Drawable drawable = getResources().getDrawable(isLike? R.mipmap.icon_bottom_like_press :R.mipmap.icon_bottom_like);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicWidth());
        tvLike.setCompoundDrawables(drawable, null, null, null);
    }
}
