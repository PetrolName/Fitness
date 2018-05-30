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

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.contact.LoginContact;
import com.cheng.fitness.presenter.LoginPresenter;
import com.cheng.fitness.utils.VerifyUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author: PengCheng
 * time: 2018/5/28 0028
 * desc: 登录
 */

public class LoginActivity extends BaseActivity<LoginContact.presenter> implements LoginContact.view {
    @Bind(R.id.etNickname)
    EditText etNickname;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.ivVisible)
    ImageView ivVisible;
    @Bind(R.id.tvLogin)
    TextView tvLogin;
    @Bind(R.id.tvRegisterNow)
    TextView tvRegisterNow;

    private boolean isPasswordVisible = false;

    @Override
    public LoginContact.presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("登录");
        toolBar.getTvBack().setVisibility(View.GONE);
    }

    @Override
    public void loadData() {
        super.loadData();
        String nickname = ConfigConstant.getKeyUserNickname();
        String password = ConfigConstant.getKeyUserPassword();
        if (!TextUtils.isEmpty(nickname) && !TextUtils.isEmpty(password)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            etNickname.setText(nickname);
        }
    }

    @OnClick({R.id.ivVisible, R.id.tvLogin, R.id.tvRegisterNow})
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
            case R.id.tvLogin:
                if (check())
                    mPresenter.login(etNickname.getText().toString().trim(), etPassword.getText().toString().trim());
                break;
            case R.id.tvRegisterNow:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
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
    public void onLoginSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFail(String msg) {
        showToast(msg);
    }
}
