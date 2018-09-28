package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;

import java.util.LinkedList;


/**
 * @创建者 lucas
 * @创建时间 2017/9/11 0011 9:21
 * @描述 TODO
 */

public class RefundInfoAdapter extends BaseRecyclerViewAdapter {

    private LinkedList<PhotoUploadAdapter.PhotoBean> mData;
    private View                  deleteView;
    static final int ADD = 0xa0;
    static final int DEF = 0xa1;

    public RefundInfoAdapter(Context context) {
        super(context);
        notifyDataSetChanged();
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_refund_info, null);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        //影藏最后一个item的删除键
        deleteView = holder.getRootView().findViewById(R.id.delete);
        if (getItemViewType(i) == ADD) {
            deleteView.setVisibility(View.GONE);
            holder.clearTVBG(R.id.icon);
            holder.setVisibility(R.id.name, View.GONE);
            return;
        }
        holder.setVisibility(R.id.name, View.VISIBLE);
        holder.setStr2TV(R.id.name, mData.get(i).msg);
        holder.setBitmap2IV(R.id.icon, BitmapFactory.decodeFile(mData.get(i).path));
        deleteView.setVisibility(View.VISIBLE);
        deleteView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            mData.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemViewType(int position) {
        if ((getItemCount() == 1) && mData == null) {
            return ADD;
        }
        if (mData.size() == position) {
            return ADD;
        } else {
            return DEF;
        }
    }

    public void setData(LinkedList<PhotoUploadAdapter.PhotoBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 1 : mData.size() + 1;
    }

}
