package com.cheng.baselib.mvpbase.baseImpl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.BaseView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import timber.log.Timber;


/*
 * 项目名:    BaseLib
 * 包名       com.zhon.baselib.mvpbase.baseImpl
 * 文件名:    BaseFragment
 * 创建时间:  2017/9/7 on 14:17
 * 描述:     TODO 基类Fragment
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    private View mRootView;
    protected P presenter;

    private boolean useLazyLoad = true;
    private boolean hadLoad = false;
    private boolean hadInit = false;
    protected boolean isVisibleToUser = false;
    private boolean isInViewPager = false;

    private long lastReloadTime = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setEventBusEnable()) {
            EventBus.getDefault().register(this);
        }
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        presenter = initPresenter();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hadInit = true;
        initView(savedInstanceState);
        useLazyLoad = setLazyLoadEnable();
        if (isInViewPager) {
            if (useLazyLoad) {
                setUserVisibleHint(isVisibleToUser);//初始化完成之后显示,解决第一个fragment不加载
            } else {
                loadData();
            }
        } else {
            loadData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getArguments() != null) {
            handleArguments(getArguments());
        }
        isInViewPager = true;
        this.isVisibleToUser = isVisibleToUser;
        if (hadInit && isVisibleToUser) {
            if (useLazyLoad && !hadLoad) {
                hadLoad = true;
                loadData();
            }
            reLoadData();
        }
    }

    protected void handleArguments(Bundle bundle) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isInViewPager || isVisibleToUser) {
            reLoadData();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Timber.v("onHiddenChanged hidden = %s", hidden);
        super.onHiddenChanged(hidden);
        if (!hidden) {
            reLoadData();
        }
    }

    public abstract int getLayoutId();

    @Override
    public void loadData() {

    }

    public void showToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 可见即加载数据，防抖使用{@link #reLoadDataDebounce()}<BR/>
     * 1.在TabHost中切换时，已创建的Fragment会隐藏，而再次显示时，不会调用onResume,
     * 在onResume中第一次loadData,在onHiddenChanged中再次loadData<BR/>
     * 2.配合使用ViewPager时，可见创建时会调用onResume,在切换可见时不会调用onResume只会调用setUserVisibleHint，
     * 并且setUserVisibleHint先于onAttach执行，<BR/>
     * 在onResume中第一次loadData，在ViewPager中切换可见时，如果初始化完成，在setUserVisibleHint中再次loadData.
     */
    @Override
    public void reLoadData() {
        if (System.currentTimeMillis() - lastReloadTime > setReloadDebounceTime()) {
            lastReloadTime = System.currentTimeMillis();
            reLoadDataDebounce();
        }
    }

    /**
     * 可见即加载 {@link #setReloadDebounceTime()}ms(默认500)内不会重复加载
     */
    public void reLoadDataDebounce() {
    }
    /**
     * 设置防抖时间，默认500ms
     */
    public long setReloadDebounceTime() {
        return 500;
    }


    public boolean setEventBusEnable() {
        return false;
    }
    /**
     * 在ViewPager中切换时，是否使用懒加载<br/>
     * 如果使用懒加载 ，在setUserVisibleHint中  -->初始化完成，没有加载过，可见，loadData
     * 如果不使用懒加载，在onActivityCreated中loadData
     */
    protected boolean setLazyLoadEnable() {
        return true;
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void onDestroyView() {
        if (setEventBusEnable()) {
            EventBus.getDefault().unregister(this);
        }
        if (presenter != null) {
            presenter.detach();
        }
        hadLoad = false;
        hadInit = false;
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    public abstract P initPresenter();


}
