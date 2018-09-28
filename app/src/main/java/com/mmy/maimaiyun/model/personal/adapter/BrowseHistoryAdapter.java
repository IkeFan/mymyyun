package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.db.RecordBean;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 9:33
 * @描述 TODO
 */

public class BrowseHistoryAdapter extends BaseRecyclerViewAdapter {

    private final SimpleDateFormat mFormat;
    private       List<RecordBean> mData;
    private       boolean          mIsEdit;

    public BrowseHistoryAdapter(Context context) {
        super(context);
        mFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_browse_history, null);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        RecordBean bean = mData.get(i);
        holder.setPath2IV(R.id.icon, bean.getShop_pic());
        holder.setStr2TV(R.id.money_text, bean.getGood_price());
        holder.setStr2TV(R.id.good_name, bean.getGood_name());
        holder.setChecked(R.id.collection, 1 == bean.getIs_collect());
    }

    public void setData(List<RecordBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
