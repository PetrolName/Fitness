package com.cheng.fitness.views.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cheng.fitness.R;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.utils.ResUtil;
import com.cheng.fitness.views.activity.PublishActivity;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class CommunityAdapter extends BaseQuickAdapter<CommunityBean, BaseViewHolder> {

    public CommunityAdapter(@Nullable List<CommunityBean> data) {
        super(R.layout.item_fragment_community, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityBean item) {
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvContent, item.getContent());
        helper.setText(R.id.tvComment, String.valueOf(item.getComment()));
        helper.setText(R.id.tvLike, String.valueOf(item.getLike()));
        setImage((TextView) helper.getView(R.id.tvLike), item.getIsLike());
        ImageView imageView = helper.getView(R.id.image);
        ImageView avatarImage = helper.getView(R.id.ivAvatar);
        if (!TextUtils.isEmpty(item.getImage())) {
            if (item.getImage().startsWith("image")) {
                imageView.setImageResource(ResUtil.getMipmapId(mContext, item.getImage()));
            } else {
                Glide.with(mContext).load(item.getImage()).into(imageView);
            }
        } else{
            Glide.with(mContext).load(R.mipmap.image_fail).into(imageView);
        }
        if (!TextUtils.isEmpty(item.getAvatar())) {
            Glide.with(mContext).load(item.getAvatar()).into(avatarImage);
        }
    }

    private void setImage(TextView tvLike, boolean isLike) {
        Drawable drawable = mContext.getResources().getDrawable(isLike ? R.mipmap.icon_like_press : R.mipmap.icon_like);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvLike.setCompoundDrawables(drawable, null, null, null);
    }
}
