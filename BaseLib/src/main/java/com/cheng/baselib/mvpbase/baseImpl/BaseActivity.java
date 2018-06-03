package com.cheng.baselib.mvpbase.baseImpl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cheng.baselib.R;
import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.BaseView;
import com.cheng.baselib.utils.ActivityManager;
import com.cheng.baselib.utils.LoadingDialogUtils;
import com.cheng.baselib.view.ToolBarView;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import timber.log.Timber;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P mPresenter;
    public Context context;
    private ToolBarView mToolBar;
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setupStatusBarEnable()) setupStatusBar();
        if (setEventBusEnable()) {
            EventBus.getDefault().register(this);
        }
        context = this;
        ActivityManager.getAppInstance().addActivity(this);//将当前activity添加进入管理栈
        mPresenter = initPresenter();
        if (null != getIntent()) handleIntent(getIntent());
        initLayoutRes();

        ButterKnife.bind(this);
        initView(savedInstanceState);
        loadData();
    }

    protected boolean setupStatusBarEnable() {
        return true;
    }

    protected void setupStatusBar() {
        mImmersionBar = ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f);
        mImmersionBar.init();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (null != intent) handleIntent(intent);
    }

    public void handleIntent(Intent intent) {

    }

    public abstract P initPresenter();

    public abstract int getLayoutId();

    private void initLayoutRes() {
        if (showToolBar()) {
            setContentView(R.layout.layout_base_view);
            mToolBar = (ToolBarView) findViewById(R.id.toolBar);
            mToolBar.setFocusableInTouchMode(true);
            handleToolBar(mToolBar);
            FrameLayout fl_container = (FrameLayout) findViewById(R.id.fl_container);//子布局容器
            LayoutInflater.from(this).inflate(getLayoutId(), fl_container, true);//子布局
        } else {
            setContentView(getLayoutId());
        }
    }

    protected void handleToolBar(ToolBarView toolBar) {
        toolBar.setBackPressed(this);
    }
//    //获取自定义toolbarview 资源id 默认为-1，showToolBar()方法必须返回true才有效
//    public int getToolBarResId() {
//        return R.layout.layout_common_toolbar;
//    }
    public boolean setEventBusEnable() {
        return false;
    }

    /**
     * 是否显示通用toolBar
     */
    public boolean showToolBar() {
        return true;
    }

    public abstract void initView(Bundle savedInstanceState);

    public void loadData() {

    }

    public void reLoadData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        reLoadData();
    }

    @Override
    public void showLoadingDialog(String msg) {
        LoadingDialogUtils.showLoadingDialog(this, msg);
        Timber.d("MainActivity_showLoadingDialog：" + msg);
    }

    @Override
    public void dismissLoadingDialog() {
        LoadingDialogUtils.cancelLoadingDialog();
        Timber.d("MainActivity_dismissLoadingDialog");
    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if (setEventBusEnable()) {
            EventBus.getDefault().unregister(this);
        }
        //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        if (mImmersionBar != null) mImmersionBar.destroy();
        ActivityManager.getAppInstance().removeActivity(this);//将当前activity移除管理栈
        if (mPresenter != null) {
            mPresenter.detach();//在presenter中解绑释放view
            mPresenter = null;
        }
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
