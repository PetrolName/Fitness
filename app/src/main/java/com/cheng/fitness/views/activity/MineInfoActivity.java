package com.cheng.fitness.views.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.utils.ActivityManager;
import com.cheng.baselib.utils.CompatUtil;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.views.widget.dialog.InputDialog;
import com.cheng.fitness.views.widget.dialog.MLAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author: PengCheng
 * time: 2018/5/28 0028
 * desc: 我的信息
 */

public class MineInfoActivity extends BaseActivity {

    @Bind(R.id.avatarLayout)
    LinearLayout avatarLayout;
    @Bind(R.id.tvNickname)
    TextView tvNickname;
    @Bind(R.id.nicknameLayout)
    LinearLayout nicknameLayout;
    @Bind(R.id.tvGender)
    TextView tvGender;
    @Bind(R.id.genderLayout)
    LinearLayout genderLayout;
    @Bind(R.id.tvLogout)
    TextView tvLogout;

    private UserBean mUserBean;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void loadData() {
        super.loadData();
        mUserBean = GreenDaoUtil.getUser(ConfigConstant.getKeyUserNickname());
    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        tvNickname.setText(ConfigConstant.getKeyUserNickname());
        if (!TextUtils.isEmpty(ConfigConstant.getKeyUserGender()))
            tvGender.setText(ConfigConstant.getKeyUserGender());
    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("我的信息");
    }

    @OnClick({R.id.avatarLayout, R.id.nicknameLayout, R.id.genderLayout, R.id.tvLogout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatarLayout:

                break;
            case R.id.nicknameLayout:
                handleNickname();
                break;
            case R.id.genderLayout:
                handleGender();
                break;
            case R.id.tvLogout:
                handleLogout();
                break;
        }
    }

    private void handleNickname() {
        InputDialog dialog = new InputDialog(context)
                .setTitle("编辑昵称")
                .setContent(ConfigConstant.getKeyUserNickname())
                .setMaxLength(15)
                .setSingleLine(true)
                .highlightRight()
                .setListener(new InputDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, View view, int which, String input) {
                        if (which == InputDialog.LEFT) return;
                        if (TextUtils.isEmpty(input)) {
                            showToast("请输入昵称");
                        } else {
                            tvNickname.setText(input);
                            if (mUserBean != null) {
                                mUserBean.setNickname(input);
                                GreenDaoUtil.updateUser(mUserBean);
                                ConfigConstant.setKeyUserNickname(input);
                            }
                        }
                    }
                });
        dialog.show();
    }

    private void handleGender() {
        List<String> items = new ArrayList<>();
        items.add("男");
        items.add("女");
        MLAlertDialog mlAlertDialog = new MLAlertDialog(MineInfoActivity.this, null, items);
        mlAlertDialog.init(new MLAlertDialog.OnItemClickListner() {
            @Override
            public void onClick(int position) {
                String genderStr = null;
                switch (position) {
                    case 0:
                        genderStr = "男";
                        break;
                    case 1:
                        genderStr = "女";
                        break;
                }
                tvGender.setText(genderStr);
                if (mUserBean != null) {
                    mUserBean.setGender(genderStr);
                    GreenDaoUtil.updateUser(mUserBean);
                    ConfigConstant.setKeyUserGender(genderStr);
                }
            }
        });
    }

    private void handleLogout() {
        ConfigConstant.setKeyUserPassword("");
        ConfigConstant.setKeyUserGender("");
        ActivityManager.getAppInstance().finishAllActivity();
        startActivity(new Intent(MineInfoActivity.this, LoginActivity.class));
    }
}
