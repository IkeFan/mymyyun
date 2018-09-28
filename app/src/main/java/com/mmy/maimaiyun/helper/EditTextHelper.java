package com.mmy.maimaiyun.helper;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 11:02
 * @描述 关联edittext与icon图标
 */

public class EditTextHelper implements TextWatcher, View.OnClickListener, View.OnFocusChangeListener{
    private EditText mEditText;
    private View     mView;

    public EditTextHelper() {
    }

    public void setupEditTextAndIcon(EditText editText, View view) {
        mEditText = editText;
        mView = view;
        init();
    }

    private void init() {
        //影藏icon
        mView.setVisibility(View.INVISIBLE);
        mEditText.setOnFocusChangeListener(this);
        mEditText.addTextChangedListener(this);
        mView.setOnClickListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        mView.setVisibility(hasFocus ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //显示icon
        mView.setVisibility(TextUtils.isEmpty(s) ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mView.setVisibility(TextUtils.isEmpty(s) ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onClick(View v) {
        //clear text
        mEditText.setText("");
    }

}
