package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.BestBean;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/8/21 0021 15:56
 * @描述 精选商品
 */

public class BoutiqueAdapter extends BaseRecyclerViewAdapter {

    private final int            mPx;
    private       List<BestBean> mData;
    private       Context        mContext;

    public BoutiqueAdapter(Context context) {
        super(context);
        mContext = context;
        mPx = UIUtil.dp2px(context, 120);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView view = new ImageView(parent.getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(mPx, mPx);
        view.setLayoutParams(params);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ImageView view = (ImageView) holder.getRootView();
        if (mData != null && mData.get(position) != null && mData.get(position).logo != null)
            holder.setPath2IV(view, mData.get(position).logo);
    }

    public void setData(List<BestBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
