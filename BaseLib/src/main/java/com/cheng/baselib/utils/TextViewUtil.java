package com.cheng.baselib.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @author Jewel
 * @version 1.0
 * @since 2016/9/26 0026
 */

public class TextViewUtil {

    public static final int ORIENTATION_LEFT = 0;
    public static final int ORIENTATION_RIGHT = 1;
    public static final int ORIENTATION_TOP = 2;
    public static final int ORIENTATION_BOTTOM = 3;

    /**
     * 添加横线
     */
    public static void strike(TextView textView) {
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 更改部分字体颜色
     *
     * @param start    开始位置
     * @param end      结束位置
     * @param colorRes 颜色资源ID
     */
    public static void setColor(TextView textView, int start, int end, int colorRes) {
        SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());

        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(CompatUtil.getColor(textView.getContext(), colorRes));
        builder.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(builder);
    }

    /**
     * 更改部分字体大小和颜色
     *
     * @param values TextView属性类{@link TextViewSpanValues}
     */
    public static void setColorAndSize(TextView textView, TextViewSpanValues... values) {
        SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());

        for (TextViewSpanValues value : values) {
            // 文字大小设置
            value.setColorSpan(textView.getContext(), builder);
            //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
            value.setSizeSpan(textView.getContext(), builder);
        }

        textView.setText(builder);
    }

    /**
     * 设置TextView的单个方向Drawable
     *
     * @param resID       资源ID
     * @param orientation 详情定义为{@link TextViewUtil#ORIENTATION_LEFT} || {@link TextViewUtil#ORIENTATION_RIGHT}
     */
    public static void setDrawable(TextView textView, @DrawableRes int resID, int orientation) {
        if (resID == -1){
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            return;
        }
        Drawable drawable = CompatUtil.getDrawable(textView.getContext(), resID);
        drawable.setBounds(0, 0, 0, 0);
        if (orientation == ORIENTATION_LEFT) {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        } else if (orientation == ORIENTATION_RIGHT) {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        } else if (orientation == ORIENTATION_BOTTOM) {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
        } else if (orientation == ORIENTATION_TOP) {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        }
    }

    /**
     * 顺序设置TextView的左右上下Drawable
     *
     * @param resID 资源ID集合，分别对应left, right, top， bottom
     */
    public static void setDrawables(TextView textView, @DrawableRes int... resID) {
        Drawable[] drawables = new Drawable[resID.length];
        for (int i = 0; i < resID.length; i++) {
            drawables[i] = CompatUtil.getDrawable(textView.getContext(), resID[i]);
            drawables[i].setBounds(0, 0, 0, 0);
        }
        switch (resID.length) {
            case 4:
                textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[2], drawables[1], drawables[3]);
                break;
            case 3:
                textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[2], drawables[1], null);
                break;

            case 2:
                textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], null, drawables[1], null);
                break;
            case 1:
                textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], null, null, null);
                break;
            default:
                break;
        }
    }


    /**
     * 获取textview字体颜色变化的属性
     *
     * @param color         字体颜色
     * @param startPosition 变化开始的位置
     * @param endPosition   变化结束的位置
     * @return {@link TextViewSpanValues}
     */
    @NonNull
    public static TextViewSpanValues getSpanValuesColor(int color, int startPosition, int endPosition) {
        return getSpanValues(-1, startPosition, endPosition, color, startPosition, endPosition);
    }

    /**
     * 获取textview字体大小变化的属性
     *
     * @param size          字体大小
     * @param startPosition 变化开始的位置
     * @param endPosition   变化结束的位置
     * @return {@link TextViewSpanValues}
     */
    @NonNull
    public static TextViewSpanValues getSpanValuesSize(int size, int startPosition, int endPosition) {
        return getSpanValues(size, startPosition, endPosition, -1, startPosition, endPosition);
    }


    /**
     * 获取textview字体大小和字体颜色变化的属性
     *
     * @param size          字体大小
     * @param color         字体颜色
     * @param startPosition 变化开始的位置
     * @param endPosition   变化结束的位置
     * @return {@link TextViewSpanValues}
     */
    @NonNull
    public static TextViewSpanValues getSpanValues(int size, int color, int startPosition, int endPosition) {
        return getSpanValues(size, startPosition, endPosition, color, startPosition, endPosition);
    }

    /**
     * 获取textview字体大小和字体颜色变化的属性
     *
     * @param size               字体大小
     * @param sizeStartPosition  字体大小变化开始的位置
     * @param sizeEndPosition    字体大小变化结束的位置
     * @param color              字体颜色
     * @param colorStartPosition 字体颜色变化开始的位置
     * @param colorEndPosition   字体颜色变化结束的位置
     * @return {@link TextViewSpanValues}
     */
    @NonNull
    public static TextViewSpanValues getSpanValues(int size, int sizeStartPosition, int sizeEndPosition, int color, int colorStartPosition, int colorEndPosition) {
        TextViewSpanValues spanValuesFirst = new TextViewSpanValues();
        if (size != -1) {
            spanValuesFirst.sizeDpValue = size;
        }
        spanValuesFirst.sizeStartPosition = sizeStartPosition;
        spanValuesFirst.sizeEndPosition = sizeEndPosition;
        if (color != -1) {
            spanValuesFirst.colorRes = color;
        }
        spanValuesFirst.colorStartPosition = colorStartPosition;
        spanValuesFirst.colorEndPosition = colorEndPosition;
        return spanValuesFirst;
    }


    /**
     * 获取ColorfulText
     *
     * @param stringRes 文字资源
     * @param color     文字颜色
     * @param size      文字大小(单位dp)
     * @return {@link ColorfulText}
     */
    public static ColorfulText getColorfulText(Context context, int stringRes, int color, int size) {
        return new ColorfulText(context.getString(stringRes), color, size);
    }


    /**
     * 获取ColorfulText
     *
     * @param text  文字内容
     * @param color 文字颜色
     * @param size  文字大小(单位dp)
     * @return {@link ColorfulText}
     */
    public static ColorfulText getColorfulText(String text, int color, int size) {
        return new ColorfulText(text, color, size);
    }

    public static float accordingTextSizeToSetting(Context context, int textSizeBasic) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        int screenHeight = wm.getDefaultDisplay().getHeight();
        float ratioWidth = (float) screenWidth / 480;
        float ratioHeight = (float) screenHeight / 800;
        float RATIO = Math.min(ratioWidth, ratioHeight);
        return Math.round(textSizeBasic * RATIO);
    }

    /**
     * 设置TextView样式
     */
    public static void setColorfulTextView(TextView textView, ColorfulText... colorfulTexts) {
        TextViewSpanValues[] spanValues = new TextViewSpanValues[colorfulTexts.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < colorfulTexts.length; i++) {
            sb.append(colorfulTexts[i].text);
            spanValues[i] = getSpanValues(colorfulTexts[i].size, colorfulTexts[i].color, getTextStartPosition(i, colorfulTexts), getTextEndPosition(i, colorfulTexts));
            //			Log.d("ColorText ", "text = " + colorfulTexts[i].text + "start = " + getTextStartPosition(i, colorfulTexts) + " end = " + getTextEndPosition(i, colorfulTexts));
        }
        textView.setText(sb.toString());
        setColorAndSize(textView, spanValues);
    }

    private static int getTextStartPosition(int textNo, ColorfulText... colorfulTexts) {
        return getTextEndPosition(textNo, colorfulTexts) - colorfulTexts[textNo].text.length();
    }

    private static int getTextEndPosition(int textNO, ColorfulText... colorfulTexts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= textNO; i++) {
            sb.append(colorfulTexts[i].text);
        }
        return sb.toString().length();
    }


    public static class ColorfulText {
        public String text;
        public int color;
        public int size;

        public ColorfulText(String text, int color, int size) {
            this.text = TextUtils.isEmpty(text) ? "" : text;
            this.color = color;
            this.size = size;
        }
    }
}
