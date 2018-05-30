package com.cheng.fitness.views.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cheng.baselib.utils.CompatUtil;
import com.cheng.fitness.R;


/**
 * 标题靠左，带输入框按钮
 */
public class InputDialog extends Dialog {

    public final static int LEFT = 1;
    public final static int RIGHT = 2;

    private Context mContext;
    private InputDialog.OnClickListener mListener;

    private View vContentView;
    private TextView vTitle;
    private EditText vContent;
    private Button vLeft, vRight;

    private int maxLength = Integer.MAX_VALUE;

    public InputDialog(@NonNull Context context) {
        super(context, R.style.center_content_dialog);
        mContext = context;
        vContentView = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);
        initViews();
        initListeners();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vContentView);
    }



    private void initViews() {
        vTitle = (TextView) vContentView.findViewById(R.id.dialog_input_title);
        vContent = (EditText) vContentView.findViewById(R.id.dialog_input_content);
        vLeft = (Button) vContentView.findViewById(R.id.dialog_input_button_left);
        vRight = (Button) vContentView.findViewById(R.id.dialog_input_button_right);
    }

    private void initListeners() {
        vLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onClick(InputDialog.this, vLeft, LEFT, vContent.getText().toString());
                }
                dismiss();
            }
        });
        vRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onClick(InputDialog.this, vRight, RIGHT, vContent.getText().toString());
                }
                dismiss();
            }
        });
    }

    /**
     * @dsec 高亮按钮
     * @author channagihong
     * @time 6/26/17 17:45
     */
    public InputDialog highlightLeft() {
        vLeft.setTextColor(CompatUtil.getColor(mContext, R.color.cfee13c));
        return this;
    }

    public InputDialog highlightLeft(int resColorId) {
        vLeft.setTextColor(CompatUtil.getColor(mContext, resColorId));
        return this;
    }

    public InputDialog highlightRight() {
        vRight.setTextColor(CompatUtil.getColor(mContext, R.color.cfee13c));
        return this;
    }

    public InputDialog highlightRight(int resColorId) {
        vRight.setTextColor(CompatUtil.getColor(mContext, resColorId));
        return this;
    }

    public InputDialog setTitle(String title) {
        vTitle.setText(title);
        return this;
    }

    public InputDialog setContent(String content) {
        if (TextUtils.isEmpty(content)) {
            return this;
        }
        vContent.setText(content);
        return this;
    }

    public InputDialog setLeft(String text) {
        vLeft.setText(text);
        return this;
    }

    public InputDialog setRight(String text) {
        vRight.setText(text);
        return this;
    }

    public InputDialog setListener(InputDialog.OnClickListener listener) {
        mListener = listener;
        return this;
    }

    public InputDialog setMaxLength(int length) {
        maxLength = length;
        vContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        return this;
    }

    public InputDialog setSingleLine(boolean singleLine) {
        vContent.setSingleLine(singleLine);
        return this;
    }

    public InputDialog setMaxLengthToast(final String string) {
        vContent.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == maxLength) {
                    Toast.makeText(mContext, string, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return this;
    }

    @Override
    public void show() {
        super.show();
        vContent.setSelection(vContent.length());
    }

    public interface OnClickListener {
        /**
         * @param which refer to {@link InputDialog#LEFT} and {@link InputDialog#RIGHT}
         */
        void onClick(Dialog dialog, View view, int which, String input);
    }

}
