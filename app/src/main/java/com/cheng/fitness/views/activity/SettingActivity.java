package com.cheng.fitness.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author: PengCheng
 * time: 2018/5/28 0028
 * desc: 设置
 */

public class SettingActivity extends BaseActivity {

    @Bind(R.id.tvAbout)
    TextView tvAbout;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("设置");
    }



    @OnClick({R.id.tvAbout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAbout:
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                break;
        }
    }
}
