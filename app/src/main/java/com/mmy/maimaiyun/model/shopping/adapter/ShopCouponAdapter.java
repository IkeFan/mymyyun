package com.mmy.maimaiyun.model.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.ConfOrdersBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/11/11 0011 14:04
 * @描述
 */

public class ShopCouponAdapter extends BaseRecyclerViewAdapter {
    private final SimpleDateFormat                             mFormat;
    private       List<ConfOrdersBean.ShopBean.CouponInfoBean> mData;
    private       String                                       mShopName;

    public ShopCouponAdapter(Context context) {
        super(context);
        mFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conpon, parent, false);
        TextView shopName = (TextView) inflate.findViewById(R.id.shop_name);
        shopName.setText(mShopName);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ConfOrdersBean.ShopBean.CouponInfoBean infoBean = mData.get(position);
        holder.setStr2TV(R.id.clazz, "使用门槛：" + infoBean.name);
        holder.setStr2TV(R.id.time_scope, mFormat.format(new Date(infoBean.use_time * 1000)) + "~" +
                mFormat.format(new Date(infoBean.end_time * 1000)));
        holder.setStr2TV(R.id.money_text, infoBean.money+"");
        holder.setVisibility(R.id.is_overdue, View.GONE);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<ConfOrdersBean.ShopBean.CouponInfoBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }
}
