package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/12/1 0001 17:45
 * @描述 TODO
 */

public class OneOneGoodsAdapter extends BaseRecyclerViewAdapter {

    private List<SubGoodsInfoBean> mData;

    public OneOneGoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one_good, parent, false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        SubGoodsInfoBean bean = mData.get(position);
        holder.setPath2IV(R.id.icon, bean.logo);
        holder.setStr2TV(R.id.name, bean.goods_name);
        holder.setStr2TV(R.id.price, "￥"+bean.shop_price);
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
