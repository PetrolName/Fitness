package com.cheng.fitness.views.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.utils.SystemUtil;
import com.cheng.fitness.views.activity.MineInfoActivity;
import com.cheng.fitness.views.activity.MyDynamicActivity;
import com.cheng.fitness.views.activity.RunRecordActivity;
import com.cheng.fitness.views.activity.SettingActivity;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc: 个人中心
 */

public class MineFragment extends BaseFragment {
    @Bind(R.id.avatarImage)
    CircleImageView avatarImage;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvRunRecord)
    TextView tvRunRecord;
    @Bind(R.id.tvMineDynamic)
    TextView tvMineDynamic;
    @Bind(R.id.tvSetting)
    TextView tvSetting;
    @Bind(R.id.tvBirthday)
    TextView tvBirthday;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        tvName.setText(ConfigConstant.getKeyUserNickname());
        setImage();
        setData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.avatarImage, R.id.tvRunRecord, R.id.tvMineDynamic, R.id.tvSetting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatarImage:
                startActivity(new Intent(getActivity(), MineInfoActivity.class));
                break;
            case R.id.tvRunRecord:
                startActivity(new Intent(getActivity(), RunRecordActivity.class));
                break;
            case R.id.tvMineDynamic:
                startActivity(new Intent(getActivity(), MyDynamicActivity.class));
                break;
            case R.id.tvSetting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }

    private void setImage() {
        String genderStr = ConfigConstant.getKeyUserGender();
        if (TextUtils.isEmpty(genderStr)) return;
        Drawable drawable = getActivity().getResources().getDrawable(TextUtils.equals("男", genderStr) ? R.mipmap.icon_man : R.mipmap.icon_woman);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvName.setCompoundDrawables(null, null, drawable, null);
    }

    private void setData() {
        UserBean bean = GreenDaoUtil.getUser(ConfigConstant.getKeyUserNickname());
        if (bean != null && !TextUtils.isEmpty(bean.getBirthday())) {
            try {
                tvBirthday.setText(String.valueOf(SystemUtil.getAge(SystemUtil.parse(bean.getBirthday()))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (bean !=null && !TextUtils.isEmpty(bean.getAvatar())) {
            Glide.with(getActivity()).load(bean.getAvatar()).into(avatarImage);
        }
    }

}
