package com.cheng.fitness.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.utils.ActivityManager;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.contact.RegisterContact;
import com.cheng.fitness.presenter.RegisterPresenter;
import com.cheng.fitness.utils.VerifyUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author: PengCheng
 * time: 2018/5/28 0028
 * desc: 注册
 */

public class RegisterActivity extends BaseActivity<RegisterContact.presenter> implements RegisterContact.view {
    @Bind(R.id.etNickname)
    EditText etNickname;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.ivVisible)
    ImageView ivVisible;
    @Bind(R.id.tvRegister)
    TextView tvRegister;

    private boolean isPasswordVisible = false;

    @Override
    public RegisterContact.presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("注册");
    }

    @OnClick({R.id.ivVisible, R.id.tvRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivVisible:
                isPasswordVisible = !isPasswordVisible;
                if (isPasswordVisible) {
                    ivVisible.setImageResource(R.mipmap.icon_eye_close);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ivVisible.setImageResource(R.mipmap.icon_eye_open);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                etPassword.setSelection(etPassword.getText().toString().trim().length());
                break;
            case R.id.tvRegister:
                if (check()) mPresenter.register(etNickname.getText().toString().trim(), etPassword.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    /**
     * 检查输入的昵称和密码是否合法
     *
     * @return
     */
    private boolean check() {
        if (TextUtils.isEmpty(etNickname.getText().toString().trim())) {
            showToast("请输入用户昵称");
            return false;
        } else if (!VerifyUtils.verifyPassword(etPassword.getText().toString().trim())) {
            showToast("密码为6-16个字符");
            return false;
        }
        return true;
    }

    @Override
    public void onRegisterSuccess() {
        showToast("注册成功");
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        ActivityManager.getAppInstance().finishActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void onRegisterFail(String msg) {
        showToast(msg);
    }
}
