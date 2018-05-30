package com.cheng.fitness.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: PengCheng
 * time: 2018/5/8 0008
 * desc:
 */

public class VerifyUtils {
    /**
     * 手机号号段校验，
     第1位：1；
     第2位：{3、4、5、6、7、8}任意数字；
     第3—11位：0—9任意数字
     * @param value
     * @return
     */
    public static boolean isTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }


    /**
     * 密码6-16个字符
     * @return
     */
    public static boolean verifyPassword(String password) {
        if (password.length() >= 6 && password.length() <= 16) {
            return true;
        }
        return false;
    }
}
