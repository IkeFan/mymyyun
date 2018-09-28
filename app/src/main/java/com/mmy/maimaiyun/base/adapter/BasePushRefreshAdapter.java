package com.mmy.maimaiyun.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.helper.PushRefreshScrollHelper;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/21 0021 14:20
 * @描述 上啦加载更多
 */

public abstract class BasePushRefreshAdapter<T> extends BaseRecyclerViewAdapter {

    public static final int PUSH_ITEM = -100;
    protected List<T>                 mData;
    private   PushRefreshScrollHelper mHelper;

    public BasePushRefreshAdapter(Context context, PushRefreshScrollHelper helper) {
        super(context);
        mHelper = helper;
        mHelper.getRecyclerView().addOnScrollListener(mHelper);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == PUSH_ITEM) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_push, parent, false);
            return new BaseRecyclerViewHolder(inflate);
        }
        return onCreateViewHolder2(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {

    }

    protected abstract BaseRecyclerViewHolder onCreateViewHolder2(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return  mData == null ? getItemCount2() : getItemCount2() + 1;
    }

    protected abstract int getItemCount2();

    public void setData(List<T> data) {
        mData = data;
        if (mData != null)
            notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        if (mData != null && mData.size() != 0) {
            mData.addAll(data);
            notifyDataSetChanged();
        }
        //加载结束
        mHelper.setIsLoading(false);
    }

    @Override
    public int getItemViewType(int position) {
        if (mData != null && mData.size() != 0 && position == getItemCount() - 1) {
            return PUSH_ITEM;
        }
        return getItemViewType2(position);
    }

    public abstract int getItemViewType2(int position);

    public void setOnPushLoadMoreListener(PushRefreshScrollHelper.OnPushLoadMoreListener listener) {
        mHelper.setOnPushLoadMoreListener(listener);
    }
}
