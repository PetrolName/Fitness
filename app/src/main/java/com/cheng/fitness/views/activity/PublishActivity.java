package com.cheng.fitness.views.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.contact.PublishContact;
import com.cheng.fitness.presenter.PublishPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: PengCheng
 * time: 2018/5/30 0030
 * desc: 发布动态
 */

public class PublishActivity extends BaseActivity<PublishContact.presenter> implements PublishContact.view {

    @Bind(R.id.etPublishContent)
    EditText etPublishContent;

    @Override
    public PublishContact.presenter initPresenter() {
        return new PublishPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("发布动态");
        toolBar.setRightText("发布");
        toolBar.setOnRightClickListener(new ToolBarView.OnBarRightClickListener() {
            @Override
            public void onRightClick(View v) {
                if (check()) mPresenter.publish(etPublishContent.getText().toString().trim());
            }
        });
    }

    private boolean check() {
        if (TextUtils.isEmpty(etPublishContent.getText().toString().trim())) {
            showToast("动态内容不能为空");
            return false;
        }
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onPublishSuccess() {
        showToast("发布成功");
        finish();
    }

    @Override
    public void onPublishFail(String msg) {
        showToast(msg);
    }
}
