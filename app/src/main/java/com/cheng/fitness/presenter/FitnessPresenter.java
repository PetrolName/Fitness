package com.cheng.fitness.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.cheng.baselib.mvpbase.baseImpl.BasePresenterImpl;
import com.cheng.fitness.contact.FitnessContact;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.utils.GreenDaoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public class FitnessPresenter extends BasePresenterImpl<FitnessContact.view> implements FitnessContact.presenter {
    Map<String, Object> item;
    private ArrayList<String> pagList = new ArrayList<>();

    public FitnessPresenter(FitnessContact.view view) {
        super(view);
    }

    @Override
    public void getMinePlan() {
        List<CourseBean> courseBeans = GreenDaoUtil.getMinePlanCourse(true);
        if (courseBeans != null) {
            view.onGetMinePlanSuccess(courseBeans);
        } else {
            view.onGetMinePlanFail("获取我的健身计划失败");
        }
    }

    @Override
    public void startAlarmClock(Context context) {
        listPackages(context);
        PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(pagList.get(0));
        view.onStartAlarmClockSuccess(intent);
    }

    private void listPackages(Context context) {
        ArrayList<PInfo> apps = getInstalledApps(context, false);
        final int max = apps.size();
        for (int i = 0; i < max; i++) {
            apps.get(i).prettyPrint();
            item = new HashMap<String, Object>();

            int aa = apps.get(i).pname.length();
            if (aa > 11) {
                if (apps.get(i).pname.indexOf("clock") != -1) {
                    if (!(apps.get(i).pname.indexOf("widget") != -1)) {
                        try {
                            PackageInfo pInfo = context.getPackageManager()
                                    .getPackageInfo(apps.get(i).pname, 0);
                            if (isSystemApp(pInfo) || isSystemUpdateApp(pInfo)) {
//                                Log.d("mxt", "是系统自带的");
                                Timber.d("mxt", "找到了" + apps.get(i).pname.substring(apps.get(i).pname.length() - 5)
                                                + "  全名：" + apps.get(i).pname + " " + apps.get(i).appname);
                                item.put("pname", apps.get(i).pname);
                                item.put("appname", apps.get(i).appname);
                                pagList.add(apps.get(i).pname);
                            }
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    }
                }
            }

        }
    }

    private ArrayList<PInfo> getInstalledApps(Context context, boolean getSysPackages) {
        ArrayList<PInfo> res = new ArrayList<PInfo>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue;
            }
            PInfo newInfo = new PInfo();
            newInfo.appname = p.applicationInfo.loadLabel(context.getPackageManager())
                    .toString();
            newInfo.pname = p.packageName;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(context.getPackageManager());
            res.add(newInfo);
        }
        return res;
    }

    public boolean isSystemApp(PackageInfo pInfo) {
        return ((pInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public boolean isSystemUpdateApp(PackageInfo pInfo) {
        return ((pInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0);
    }

    class PInfo {
        private String appname = "";
        private String pname = "";
        private String versionName = "";
        private int versionCode = 0;
        private Drawable icon;

        private void prettyPrint() {
            Timber.d("AlarmClock: " + appname + " " + pname + " " + versionName + " " + versionCode);
        }
    }

}
