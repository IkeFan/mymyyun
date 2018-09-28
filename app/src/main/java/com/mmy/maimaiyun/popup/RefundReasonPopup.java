package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupWindow;

import com.mmy.maimaiyun.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @创建者 lucas
 * @创建时间 2017/9/9 0009 9:46
 * @描述 退款原因
 */

public class RefundReasonPopup extends PopupWindow {

    @Bind(R.id.check1)
    CheckBox mCheckBox1;
    @Bind(R.id.check2)
    CheckBox mCheckBox2;
    @Bind(R.id.check3)
    CheckBox mCheckBox3;
    @Bind(R.id.check4)
    CheckBox mCheckBox4;
    @Bind(R.id.check5)
    CheckBox mCheckBox5;
    @Bind(R.id.check6)
    CheckBox mCheckBox6;
    private ArrayList<CheckBox>       mBoxes;
    private OnRefundItemClickListener mListener;
    private String[] infos={"多拍/错拍/不想要","快递一直未送到","未按约定时间发货","空包裹","快递无跟踪记录","其他(将在退款原因中说明)"};
    private int mPosition;

    public RefundReasonPopup(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.popup_refund_reason, null);
        ButterKnife.bind(this, view);
        mBoxes = new ArrayList<>();
        mBoxes.add(mCheckBox1);
        mBoxes.add(mCheckBox2);
        mBoxes.add(mCheckBox3);
        mBoxes.add(mCheckBox4);
        mBoxes.add(mCheckBox5);
        mBoxes.add(mCheckBox6);
        setContentView(view);
        setFocusable(true);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x00ffffff));
    }

    @OnClick({R.id.select1, R.id.select2, R.id.select3, R.id.select4, R.id.select5, R.id.select6, R.id.close})
    public void click(View view) {
        mPosition = -1;
        switch (view.getId()) {
            case R.id.select1:
                mPosition = 0;
                break;
            case R.id.select2:
                mPosition = 1;
                break;
            case R.id.select3:
                mPosition = 2;
                break;
            case R.id.select4:
                mPosition = 3;
                break;
            case R.id.select5:
                mPosition = 4;
                break;
            case R.id.select6:
                mPosition = 5;
                break;
            case R.id.close:
                break;
        }
        if (mPosition != -1) {
            refresh(mPosition);
            if (mListener != null)
                mListener.onRefundItemClick(infos[mPosition], mPosition);
        }
        dismiss();
    }

    private void refresh(int position) {
        for (int i = 0; i < mBoxes.size(); i++) {
            mBoxes.get(i).setChecked(position == i);
        }
    }

    public void setOnItemClickListener(OnRefundItemClickListener listener) {
        mListener = listener;
    }


    public interface OnRefundItemClickListener {
        void onRefundItemClick(String str, int position);
    }
}
