package com.cheng.baselib.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * @author Jewel
 * @version 1.0
 * @since 2016/11/10 0010
 */

public class TextViewSpanValues {

	public int colorStartPosition = -1;
	public int colorEndPosition = -1;
	public int colorRes = -1;

	public int sizeStartPosition = -1;
	public int sizeEndPosition = -1;
	public float sizeDpValue = -1;

	/**
	 * 设置文字大小
	 */
	void setSizeSpan(Context context, SpannableStringBuilder builder) {
		if(sizeDpValue < 0 || sizeStartPosition < 0 || sizeEndPosition < 0) {
			return;
		}
		if(sizeStartPosition > sizeEndPosition) {
			return;
		}
		// 文字大小设置
		AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(ScreenUtil.dp2px(context, sizeDpValue));
		builder.setSpan(sizeSpan, sizeStartPosition, sizeEndPosition, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
	}

	/**
	 * 设置颜色值
	 */
	void setColorSpan(Context context, SpannableStringBuilder builder) {
		if(colorRes < 0 || colorStartPosition < 0 || colorEndPosition < 0) {
			return;
		}
		if(colorStartPosition > colorEndPosition) {
			return;
		}
		//ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
		ForegroundColorSpan colorSpan = new ForegroundColorSpan(CompatUtil.getColor(context, colorRes));
		builder.setSpan(colorSpan, colorStartPosition, colorEndPosition, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
	}
}
