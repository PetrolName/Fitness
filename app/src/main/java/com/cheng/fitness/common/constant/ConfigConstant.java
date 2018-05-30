package com.cheng.fitness.common.constant;

import com.cheng.fitness.utils.SharedPre;

/**
 * author: PengCheng
 * time: 2018/5/28 0028
 * desc:
 */

public class ConfigConstant {
    //判断是否是第一次进入app，第一次才需要初始化数据
    public static final String KEY_FIRST_TIME = "key_first_time";

    //用户的昵称
    public static final String KEY_USER_NICKNAME = "key_user_nickname";

    //用户的密码
    public static final String KEY_USER_PASSWORD = "key_user_password";

    //用户的性别
    public static final String KEY_USER_GENDER = "key_user_gender";

    public static boolean getKeyFirstTime() {
        return SharedPre.getBoolean(KEY_FIRST_TIME, true);
    }

    public static void setKeyFirstTime(boolean isFirstTime) {
        SharedPre.putBoolean(KEY_FIRST_TIME, isFirstTime);
    }

    public static String getKeyUserNickname() {
        return SharedPre.getString(KEY_USER_NICKNAME, "");
    }

    public static void setKeyUserNickname(String nickname) {
        SharedPre.putString(KEY_USER_NICKNAME, nickname);
    }

    public static String getKeyUserPassword() {
        return SharedPre.getString(KEY_USER_PASSWORD, "");
    }

    public static void setKeyUserPassword(String password) {
        SharedPre.putString(KEY_USER_PASSWORD, password);
    }

    public static String getKeyUserGender() {
        return SharedPre.getString(KEY_USER_GENDER, "");
    }

    public static void setKeyUserGender(String gender) {
        SharedPre.putString(KEY_USER_GENDER, gender);
    }
}
