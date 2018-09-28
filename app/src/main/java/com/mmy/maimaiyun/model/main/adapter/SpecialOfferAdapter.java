package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 15:26
 * @描述 TODO
 */

public class SpecialOfferAdapter extends BaseRecyclerViewAdapter {

    private List<SubGoodsInfoBean> mData;

    public SpecialOfferAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_offer, parent, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        SubGoodsInfoBean bean = mData.get(position);
        if (!TextUtils.isEmpty(bean.logo))
            holder.setPath2IV(R.id.hot_goods_icon,bean.logo);
        holder.setStr2TV(R.id.hot_goods_title,bean.goods_name);
        holder.setStr2TV(R.id.hot_goods_money,"￥"+bean.shop_price);
//        holder.setStr2TV(R.id.hot_goods_money2,"￥"+bean.market_price);
    }

    public void setData(List<SubGoodsInfoBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
