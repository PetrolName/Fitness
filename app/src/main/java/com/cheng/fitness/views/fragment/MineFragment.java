package com.cheng.fitness.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.views.activity.MineInfoActivity;
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

                break;
            case R.id.tvMineDynamic:

                break;
            case R.id.tvSetting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }
}
