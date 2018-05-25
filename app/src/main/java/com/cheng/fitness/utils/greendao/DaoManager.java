package com.cheng.fitness.utils.greendao;

import android.database.sqlite.SQLiteDatabase;

import com.cheng.fitness.common.base.BaseApp;
import com.cheng.fitness.utils.greendao.gen.DaoMaster;
import com.cheng.fitness.utils.greendao.gen.DaoSession;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public class DaoManager {
    private volatile static DaoManager daoManager;
    private DaoMaster.DevOpenHelper mHelper;
    private String dbName = "fitness_db";
    private SQLiteDatabase mDb;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public DaoManager() {
        mHelper = new DaoMaster.DevOpenHelper(BaseApp.getInatance(), dbName, null);
        mDb = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();

    }

    public static DaoManager getInstance() {
        if (daoManager == null) {
            synchronized (DaoManager.class) {
                if (daoManager == null) {
                    daoManager = new DaoManager();
                }
            }
        }
        return daoManager;
    }

    public void close() {
        mDb.close();
        daoManager = null;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
