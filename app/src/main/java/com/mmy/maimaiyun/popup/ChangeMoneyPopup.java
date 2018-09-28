package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.mmy.maimaiyun.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @创建者 lucas
 * @创建时间 2017/9/16 0016 17:50
 * @描述 下架
 */

public class ChangeMoneyPopup extends PopupWindow {
    public ChangeMoneyPopup(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_change_money, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setOutsideTouchable(true);
        setFocusable(true);
    }

    @OnClick({R.id.confirm, R.id.cancel})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }
}
