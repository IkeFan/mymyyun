package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.OnItemInnerClickListener;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/16 0016 14:33
 * @描述 商品管理
 */

public class GoodManageAdapter extends BaseRecyclerViewAdapter {

    private List<String> mData;
    private boolean      mSelect;
    public static final int OFF_SHELF           = 0;
    public static final int CANCEL_DISTRIBUTION = 1;
    public static final int CHANGE              = 2;
    public static final int CLASSIFICATION      = 3;
    public static final int PREVIEW             = 4;
    private OnItemInnerClickListener mListener;

    public GoodManageAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_good_manage, viewGroup,false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        holder.setVisibility(R.id.select, mSelect ? View.VISIBLE : View.GONE);
        holder.findView(R.id.off_shelf).setOnClickListener(v -> {
            if (mListener != null)
                mListener.onItemInnerClick(v, holder.getAdapterPosition(), OFF_SHELF);
        });
        holder.findView(R.id.cancel_distribution).setOnClickListener(v -> {
            if (mListener != null)
                mListener.onItemInnerClick(v, holder.getAdapterPosition(), CANCEL_DISTRIBUTION);
        });
        holder.findView(R.id.change).setOnClickListener(v -> {
            if (mListener != null)
                mListener.onItemInnerClick(v, holder.getAdapterPosition(), CHANGE);
        });
        holder.findView(R.id.classification).setOnClickListener(v -> {
            if (mListener != null)
                mListener.onItemInnerClick(v, holder.getAdapterPosition(), CLASSIFICATION);
        });
        holder.findView(R.id.preview).setOnClickListener(v -> {
            if (mListener != null)
                mListener.onItemInnerClick(v, holder.getAdapterPosition(), PREVIEW);
        });
    }

    public void setOnItemInnerClickListener(OnItemInnerClickListener listener) {
        mListener = listener;
    }

    public void setData(List<String> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setSelect(boolean select) {
        mSelect = select;
        notifyDataSetChanged();
    }
}
