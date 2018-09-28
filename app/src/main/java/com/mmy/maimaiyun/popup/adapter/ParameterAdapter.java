package com.mmy.maimaiyun.popup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.GoodsInfoAttrBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/5 0005 16:32
 * @描述 TODO
 */

public class ParameterAdapter extends BaseRecyclerViewAdapter {

    private List<GoodsInfoAttrBean.AttributeBean> mData;

    public ParameterAdapter(Context context, List<GoodsInfoAttrBean.AttributeBean> data) {
        super(context);
        mData = data;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parameter, parent,false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        holder.setStr2TV(R.id.name,mData.get(position).attr_name+"  : ");
        holder.setStr2TV(R.id.content,mData.get(position).attr_value);
    }


    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }
}
