package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mmy.maimaiyun.R;

import java.util.LinkedHashMap;


/**
 * @创建者 lucas
 * @创建时间 2017/9/19 0019 11:50
 * @描述 交易记录分类
 */

public class TradeRecordPopup extends PopupWindow {
    private Builder mBuilder;
    private OnTradeRecordPopupItemClickListener mListener;

    private TradeRecordPopup(Context context, Builder builder) {
        super(context);
        mBuilder = builder;
        initView(context);
    }

    private void initView(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinkedHashMap<Integer, String> data = mBuilder.getData();
        for (Integer integer : data.keySet()) {
            View view = LayoutInflater.from(context).inflate(R.layout.popup_trade_record, null);
            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(data.get(integer));
            linearLayout.addView(view);
            view.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onTradeItemClick(integer,data.get(integer));
                }
            });
        }
        setContentView(linearLayout);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setFocusable(true);
    }

    public interface OnTradeRecordPopupItemClickListener{
        void onTradeItemClick(int index, String msg);
    }

    public void setOnTradeRecordPopupItemClickListener(OnTradeRecordPopupItemClickListener listener){
        mListener = listener;
    }

    public static class Builder {
        private LinkedHashMap<Integer, String> mData;
        private Context      mContext;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setData(LinkedHashMap<Integer, String> data) {
            mData = data;
            return this;
        }

        public LinkedHashMap<Integer, String> getData() {
            return mData;
        }

        public TradeRecordPopup builde() {
            return new TradeRecordPopup(mContext, this);
        }
    }
}
