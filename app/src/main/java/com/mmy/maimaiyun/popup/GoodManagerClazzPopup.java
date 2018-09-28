package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.helper.VDataMakeHelper;
import com.mmy.maimaiyun.popup.adapter.GoodManageClazzPopupAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @创建者 lucas
 * @创建时间 2017/9/16 0016 15:55
 * @描述 商品管理-》分类至
 */

public class GoodManagerClazzPopup extends PopupWindow {

    @Bind(R.id.list)
    RecyclerView mList;

    private GoodManageClazzPopupAdapter mAdapter;

    public GoodManagerClazzPopup(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_good_manage_clazz, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setOutsideTouchable(true);
        setFocusable(true);
        mList.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new GoodManageClazzPopupAdapter(context);
        mList.setAdapter(mAdapter);
        mAdapter.setData(VDataMakeHelper.createStrList(10));
    }


    public GoodManageClazzPopupAdapter getAdapter() {
        return mAdapter;
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
