package com.cheng.fitness.common.base;

import android.app.Application;

import com.cheng.fitness.utils.SharedPre;

import timber.log.Timber;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class BaseApp extends Application{
    private static BaseApp mBaseApp;

    public static BaseApp getInatance() {
        if (mBaseApp == null)
            throw new RuntimeException("The BaseApp is Null");
        return mBaseApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApp = this;
        initTimber();
        initSharePre();
    }

    // debug模式打印日志并使用本地内存泄漏跟踪
    private void initTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    //初始化sharepreference
    private void initSharePre() {
        SharedPre.init(this, getPackageName());
    }
}
