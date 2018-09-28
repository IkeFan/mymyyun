package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.OnlyNewBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2018/1/17 0017 19:08
 * @描述 TODO
 */

public class OnlyNewAdapter extends BaseRecyclerViewAdapter {

    private List<OnlyNewBean.GoodsBean> mData;

    public OnlyNewAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ac_only_new, parent, false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        OnlyNewBean.GoodsBean bean = mData.get(position);
        holder.setPath2IV(R.id.icon,  bean.logo);
        holder.setStr2TV(R.id.title, bean.goods_name);
        holder.setStr2TV(R.id.price, "￥"+bean.shop_price);
        holder.setStr2TV(R.id.price2,"￥"+ bean.market_price);
    }

    public void setData(List<OnlyNewBean.GoodsBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
