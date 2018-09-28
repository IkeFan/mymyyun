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
 * @创建时间 2017/9/1 0001 17:52
 * @描述 TODO
 */

public class OverseasAdapter extends BaseRecyclerViewAdapter {

    private List<SubGoodsInfoBean> mData;

    public OverseasAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_overseas, parent,false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
       SubGoodsInfoBean bean = mData.get(position);
        if (!TextUtils.isEmpty(bean.logo))
            holder.setPath2IV(R.id.overseas_icon, bean.logo);
        holder.setStr2TV(R.id.overseas_title, bean.goods_name);
        holder.setStr2TV(R.id.overseas_content, bean.goods_desc);
        holder.setStr2TV(R.id.overseas_money, bean.shop_price);
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
