package com.cheng.baselib.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.io.File;

/**
 * 兼容新旧版本的方法集
 *
 */

public class CompatUtil {

    public static Uri buildUri(Context context, File output, String fileProvider) {
        Uri uri = null;
        if (isBelowVersion(24)) {
            uri = Uri.fromFile(output);
        } else {
            uri = FileProvider.getUriForFile(context, fileProvider, output);
        }
        return uri;
    }

    public static Uri takePhoto(Fragment context, File output, int resultCode, String fileProvider) {
        Uri uri = null;
        if (isBelowVersion(24)) {
            uri = Uri.fromFile(output);
        } else {
            uri = FileProvider.getUriForFile(context.getContext(), fileProvider, output);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        context.startActivityForResult(intent, resultCode);
        return uri;
    }

    /**
     * ViewPager页面监听
     */
    public static void setOnPageChangeListener(ViewPager v, ViewPager.OnPageChangeListener p) {
        if (isBelowVersion(23)) {
            v.setOnPageChangeListener(p);
        } else {
            v.addOnPageChangeListener(p);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(View view, Drawable drawable) {
        if (isBelowVersion(16)) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(View view, @DrawableRes int drawableRes) {
        if (isBelowVersion(16)) {
            view.setBackgroundDrawable(getDrawable(view.getContext(), drawableRes));
        } else {
            view.setBackground(getDrawable(view.getContext(), drawableRes));
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void setTextAppearance(TextView view, @StyleRes int appearanceRes) {
        if (isBelowVersion(23)) {
            view.setTextAppearance(view.getContext(), appearanceRes);
        } else {
            view.setTextAppearance(appearanceRes);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawable(Context context, @DrawableRes int drawableRes) {
        if (isBelowVersion(21)) {
            return context.getResources().getDrawable(drawableRes);
        } else {
            return context.getDrawable(drawableRes);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static String getString(Context context, @StringRes int stringRes) {
        if (isBelowVersion(21)) {
            return context.getResources().getString(stringRes);
        } else {
            return context.getString(stringRes);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static int getColor(Context context, @ColorRes int colorRes) {
        if (isBelowVersion(23)) {
            return context.getResources().getColor(colorRes);
        } else {
            return context.getResources().getColor(colorRes, null);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setPadding(TextView view, int left, int top, int right, int bottom) {
        if (isBelowVersion(15)) {
            view.setPaddingRelative(left, top, right, bottom);
        } else {
            view.setPadding(left, top, right, bottom);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void clearBackground(View... views) {
        if (views != null) {
            for (View view : views) {
                if (view != null) {
                    Drawable drawable = view.getBackground();
                    if (drawable != null) {
                        if (isBelowVersion(16)) {
                            view.setBackgroundDrawable(null);
                        } else {
                            view.setBackground(null);
                        }
                        drawable.setCallback(null);
                    }
                }
            }
        }

    }

    /**
     * 编译版本小于version的版本
     */
    public static boolean isBelowVersion(int version) {
        if (Build.VERSION.SDK_INT < version) {
            return true;
        } else
            return false;
    }
}
