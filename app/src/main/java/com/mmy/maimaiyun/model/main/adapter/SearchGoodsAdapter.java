package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.SearchBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/11/10 0010 15:46
 * @描述
 */

public class SearchGoodsAdapter extends BaseRecyclerViewAdapter {
    private List<SearchBean> mData;

    public SearchGoodsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend, parent, false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        SearchBean bean = mData.get(position);
        holder.setPath2TVSize(R.id.icon, bean.logo,200,200);
        holder.setStr2TV(R.id.name, bean.goods_name);
        holder.setStr2TV(R.id.price, bean.shop_price);
        holder.setStr2TV(R.id.count,bean.people+"人已购买");
//        holder.setStr2TV(R.id.original_price, bean.market_price);
    }

    public void setData(List<SearchBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
