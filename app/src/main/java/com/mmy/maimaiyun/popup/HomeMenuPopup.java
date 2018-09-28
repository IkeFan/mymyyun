package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.OnPopupMenuItemClickListener;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 10:54
 * @描述 主页右上角menu
 */

public class HomeMenuPopup extends PopupWindow {
    private OnPopupMenuItemClickListener mListener;

    public static final int MSG = 0;
    public static final int SCAN = 1;

    public HomeMenuPopup(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_home_menu, null);
        setContentView(view);
        view.findViewById(R.id.msg).setOnClickListener(v -> {
            if (mListener != null)
                mListener.onPopupItemClick(v, MSG);
        });
        view.findViewById(R.id.scan).setOnClickListener(v -> {
            if (mListener != null)
                mListener.onPopupItemClick(v, SCAN);
        });
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
    }

    public void setOnItemClickListener(OnPopupMenuItemClickListener listener) {
        mListener = listener;
    }
}
