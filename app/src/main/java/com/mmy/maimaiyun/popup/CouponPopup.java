package com.mmy.maimaiyun.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.GoodsInfoBean;
import com.mmy.maimaiyun.popup.adapter.PopupCouponAdapter;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @创建者 lucas
 * @创建时间 2017/12/13 0013 18:02
 * @描述 TODO
 */

public class CouponPopup extends PopupWindow implements BaseRecyclerViewAdapter.OnItemClickListener {
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.close)
    TextView     mClose;
    private PopupCouponAdapter        mAdapter;
    private OnCouponItemClickListener mListener;

    public CouponPopup(Context context) {
        super(context);
        initView(context);
    }

    public CouponPopup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.popup_coupon, null);
        ButterKnife.bind(this, inflate);
        setContentView(inflate);
        setFocusable(true);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x00ffffff));
        mList.setLayoutManager(new LinearLayoutManager(context));
        int px = UIUtil.dp2px(context, 5);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new PopupCouponAdapter(context);
        mList.setAdapter(mAdapter);
        mAdapter.addOnItemCliclListener(this);
    }

    @OnClick({R.id.close})
    public void click(View view){
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
        }
    }

    public void setOnCouponItemClick(OnCouponItemClickListener listener) {
        mListener = listener;
    }

    public void setData(List<GoodsInfoBean.CouponsBean> coupons) {
        mAdapter.setData(coupons);
    }

    public interface OnCouponItemClickListener {
        void onCouponItemClick(View view, int position);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mListener != null) {
            mListener.onCouponItemClick(view, position);
        }
    }
}
