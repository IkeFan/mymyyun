package com.mmy.maimaiyun.model.msg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.model.msg.ui.activity.NotifyListActivity;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 11:45
 * @描述 TODO
 */

public class NotifyListAdapter extends BaseRecyclerViewAdapter {

    private List<String> mData;
    private int          mPageType;

    public NotifyListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                mPageType == NotifyListActivity.ORDER ? R.layout.item_notify_order_list :
                        R.layout.item_notify_system_list, viewGroup, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {

    }

    public void setData(List<String> data, int pageType) {
        mData = data;
        mPageType = pageType;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
