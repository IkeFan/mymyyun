package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/15 0015 16:11
 * @描述 TODO
 */

public class SubDistributionAdapter extends BaseRecyclerViewAdapter {

    private List<String> mData;

    public SubDistributionAdapter(Context context, List<String> data) {
        super(context);
        mData = data;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sub_dis, viewGroup,false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
