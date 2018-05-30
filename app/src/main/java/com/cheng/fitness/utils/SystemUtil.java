package com.cheng.fitness.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: PengCheng
 * time: 2018/5/30 0030
 * desc:
 */

public class SystemUtil {

    public static String getDate() {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date());
    }

}
