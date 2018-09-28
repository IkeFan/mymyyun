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
import com.mmy.maimaiyun.customview.RecycleViewDivider;
import com.mmy.maimaiyun.data.bean.GoodsInfoAttrBean;
import com.mmy.maimaiyun.popup.adapter.ParameterAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @创建者 lucas
 * @创建时间 2017/9/5 0005 16:18
 * @描述 TODO
 */

public class ParameterPopup extends PopupWindow {
    private List<GoodsInfoAttrBean.AttributeBean> mData;
    @Bind(R.id.list)
    RecyclerView mList;

    public ParameterPopup(Context context, List<GoodsInfoAttrBean.AttributeBean> data) {
        super(context);
        mData = data;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.parameter_popup, null);
        ButterKnife.bind(this, view);
        mList.setLayoutManager(new LinearLayoutManager(context));
        mList.addItemDecoration(new RecycleViewDivider(context,LinearLayoutManager.HORIZONTAL));
        mList.setAdapter(new ParameterAdapter(context, mData));
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x00ffffff));
    }

    @OnClick({R.id.cancel})
    public void click(View view){
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
        }
    }
}
