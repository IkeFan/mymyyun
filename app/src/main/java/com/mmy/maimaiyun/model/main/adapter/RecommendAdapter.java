package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.HotGoodsRecommemdBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/2 0002 10:43
 * @描述 TODO
 */

public class RecommendAdapter extends BaseRecyclerViewAdapter {

    private List<HotGoodsRecommemdBean> mData;

    public RecommendAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent,false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        HotGoodsRecommemdBean bean = mData.get(position);
        holder.setPath2IV(R.id.recommend_icon,bean.logo);
        holder.setStr2TV(R.id.recommend_text,bean.goods_name);
    }

    public void setData(List<HotGoodsRecommemdBean> data){
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }
}
