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
 * @创建时间 2017/9/9 0009 10:43
 * @描述 货物状态
 */

public class GoodStatusPopup extends PopupWindow {
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
    private ArrayList<CheckBox> mBoxes;
    private OnItemClickListener mListener;
    private String[] infos = {"未到货", "已发货", "已换货", "已退货", "已收货"};
    private int mPosition;


    public GoodStatusPopup(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.popup_good_status, null);
        ButterKnife.bind(this, view);
        mBoxes = new ArrayList<>();
        mBoxes.add(mCheckBox1);
        mBoxes.add(mCheckBox2);
        mBoxes.add(mCheckBox3);
        mBoxes.add(mCheckBox4);
        mBoxes.add(mCheckBox5);
        setContentView(view);
        setFocusable(true);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x00ffffff));
        update();
    }

    @OnClick({R.id.select1, R.id.select2, R.id.select3, R.id.select4, R.id.select5, R.id.close})
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
            case R.id.close:
                mPosition = -1;
                break;
        }
        if (mListener != null && mPosition != -1)
            mListener.onGoodStatusItemClick(infos[mPosition], mPosition);
        dismiss();
        refresh(mPosition);
    }

    private void refresh(int position) {
        for (int i = 0; i < mBoxes.size(); i++) {
            mBoxes.get(i).setChecked(position == i);
        }
    }

    public void setOnGoodStatusItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public interface OnItemClickListener {
        void onGoodStatusItemClick(String info, int position);
    }
}
