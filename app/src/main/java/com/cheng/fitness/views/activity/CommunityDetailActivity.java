package com.cheng.fitness.views.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.utils.CompatUtil;
import com.cheng.baselib.utils.TextViewUtil;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.common.constant.Constant;
import com.cheng.fitness.model.CommentBean;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.utils.ResUtil;
import com.cheng.fitness.utils.SystemUtil;
import com.cheng.fitness.views.widget.AutoScaleWidthImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    AutoScaleWidthImageView image;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.tvComment)
    TextView tvComment;
    @Bind(R.id.tvLike)
    TextView tvLike;
    @Bind(R.id.commentLayout)
    LinearLayout commentLayout;
    @Bind(R.id.etComment)
    EditText etComment;
    @Bind(R.id.tvPublish)
    TextView tvPublish;
    @Bind(R.id.publishLayout)
    RelativeLayout publishLayout;
    @Bind(R.id.tvBack)
    TextView tvBack;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvRight)
    TextView tvRight;

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

    //不用设置状态栏
    @Override
    protected boolean setupStatusBarEnable() {
        return false;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (mCommunityBean == null) return;
        tvTitle.setText("动态详情");
        TextViewUtil.setDrawable(tvRight, R.mipmap.icon_publish_add, TextViewUtil.ORIENTATION_RIGHT);
        tvName.setText(mCommunityBean.getName());
        tvTime.setText(mCommunityBean.getTime());
        tvContent.setText(mCommunityBean.getContent());
        tvComment.setText(String.valueOf(mCommunityBean.getComment()));
        tvLike.setText(String.valueOf(mCommunityBean.getLike()));
        setImage(mCommunityBean.getIsLike());
        if (mCommunityBean.getImage().startsWith("image")) {
            image.setImageResource(ResUtil.getMipmapId(CommunityDetailActivity.this, mCommunityBean.getImage()));
        } else {
            Glide.with(CommunityDetailActivity.this).load(mCommunityBean.getImage()).into(image);
        }
        if (!TextUtils.isEmpty(mCommunityBean.getAvatar())) {
            Glide.with(CommunityDetailActivity.this).load(mCommunityBean.getAvatar()).into(ivAvatar);
        }
        setComment();
    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
//        toolBar.setVisibility(View.VISIBLE);
//        toolBar.setTitleText("动态详情");
//        toolBar.setBackground(R.color.cfee13c);
////        toolBar.setBackgroundColor(CompatUtil.getColor(CommunityDetailActivity.this, R.color.cfee13c));
//        toolBar.setDrawable(ToolBarView.TEXT_RIGHT, R.mipmap.icon_publish_add);
//        toolBar.setOnRightClickListener(new ToolBarView.OnBarRightClickListener() {
//            @Override
//            public void onRightClick(View v) {
//                startActivity(new Intent(CommunityDetailActivity.this, PublishActivity.class));
//            }
//        });
    }

    @OnClick({R.id.tvLike, R.id.tvComment, R.id.tvPublish, R.id.tvBack, R.id.tvRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLike:
                mCommunityBean.setIsLike(!mCommunityBean.getIsLike());
                int likeNum = mCommunityBean.getIsLike() ? (mCommunityBean.getLike() + 1) : (mCommunityBean.getLike() - 1);
                mCommunityBean.setLike(likeNum);
                tvLike.setText(String.valueOf(likeNum));
                GreenDaoUtil.updateCommunity(mCommunityBean);
                setImage(mCommunityBean.getIsLike());
                break;
            case R.id.tvComment:
                publishLayout.setVisibility(publishLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                break;

            case R.id.tvPublish:
                if (TextUtils.isEmpty(etComment.getText().toString().trim())) {
                    showToast("请输入评论内容");
                    return;
                } else {
                    UserBean bean = GreenDaoUtil.getUser(ConfigConstant.getKeyUserNickname());
                    CommentBean commentBean = new CommentBean();
                    commentBean.setAvatar(bean.getAvatar());
                    commentBean.setName(bean.getNickname());
                    commentBean.setCommunityId(mCommunityBean.getId());
                    commentBean.setContent(etComment.getText().toString().trim());
                    commentBean.setDate(SystemUtil.getDate());
                    GreenDaoUtil.saveComment(commentBean);
                    //更新评论的数量
                    mCommunityBean.setComment(mCommunityBean.getComment() + 1);
                    tvComment.setText(String.valueOf(mCommunityBean.getComment()));
                    GreenDaoUtil.updateCommunity(mCommunityBean);
                    setComment();
                    etComment.setText("");
                    publishLayout.setVisibility(View.GONE);
                    hideKeyboard();
                    showToast("评论成功");
                }
                break;
            case R.id.tvBack:
                CommunityDetailActivity.this.finish();
                break;
            case R.id.tvRight:
                startActivity(new Intent(CommunityDetailActivity.this, PublishActivity.class));
                break;
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void setImage(boolean isLike) {
        Drawable drawable = getResources().getDrawable(isLike ? R.mipmap.icon_bottom_like_press : R.mipmap.icon_bottom_like);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicWidth());
        tvLike.setCompoundDrawables(drawable, null, null, null);
    }

    //显示评论
    private void setComment() {
        List<CommentBean> commentList = GreenDaoUtil.getComments(mCommunityBean.getId());
        if (commentList != null && commentList.size() != 0) {
            commentLayout.removeAllViews();
            for (CommentBean bean : commentList) {
                View view = LayoutInflater.from(CommunityDetailActivity.this).inflate(R.layout.item_comment, null);
                CircleImageView ivAvatar = (CircleImageView) view.findViewById(R.id.ivAvatar);
                TextView tvName = (TextView) view.findViewById(R.id.tvName);
                TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
                TextView tvContent = (TextView) view.findViewById(R.id.tvContent);
                if (!TextUtils.isEmpty(bean.getAvatar())) {
                    if (bean.getAvatar().startsWith("avatar")) {
                        ivAvatar.setImageResource(ResUtil.getMipmapId(CommunityDetailActivity.this, bean.getAvatar()));
                    } else {
                        Glide.with(this).load(bean.getAvatar()).into(ivAvatar);
                    }
                }
                tvName.setText(bean.getName());
                tvDate.setText(bean.getDate());
                tvContent.setText(bean.getContent());
                commentLayout.addView(view);
            }
        }
    }

}
