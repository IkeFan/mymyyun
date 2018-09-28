package com.mmy.maimaiyun.model.classity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.ClassityBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 18:09
 * @描述 TODO
 */

public class ClassityInfoAdapter extends BaseRecyclerViewAdapter {

    private List<ClassityBean> mData;
    private boolean mIsShow;

    public ClassityInfoAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_good, parent, false);
        inflate.findViewById(R.id.price2).setVisibility(mIsShow?View.VISIBLE:View.GONE);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ClassityBean bean = mData.get(position);
        holder.setPath2IV(R.id.good_img, bean.logo);
        holder.setStr2TV(R.id.good_text, bean.goods_name);
        holder.setStr2TV(R.id.good_money, "￥" + bean.shop_price);
        if (mIsShow)
            holder.setStr2TV(R.id.price2,"￥" + bean.market_price);
    }

    public void setData(List<ClassityBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void showPrice2(boolean isShow){
        mIsShow = isShow;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
