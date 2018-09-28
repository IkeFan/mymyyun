package com.mmy.maimaiyun.popup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;


/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 14:17
 * @描述 TODO
 */

public class GoodsPopupAdapter extends BaseRecyclerViewAdapter {
    private ArrayList<String> mData;

    private int currentSelect = 0;
    private Context mContext;

    public GoodsPopupAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popup_goods, null);
        return new GoodsPopupHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        holder.setStr2TV(R.id.item_text, mData.get(position));
        if (position == currentSelect){
            holder.setColor2TV(R.id.item_text,mContext.getResources().getColor(R.color.colorPrimary));
            holder.setVisibility(R.id.selected,View.VISIBLE);
        }else {
            holder.setColor2TV(R.id.item_text,mContext.getResources().getColor(R.color.normal_text_color));
            holder.setVisibility(R.id.selected,View.GONE);
        }
    }

    public void setData(ArrayList<String> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public int getCurrentSelect() {
        return currentSelect;
    }

    public class GoodsPopupHolder extends BaseRecyclerViewHolder{

        public GoodsPopupHolder(View view) {
            super(view);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            currentSelect  = getAdapterPosition();
            notifyDataSetChanged();
        }
    }
}
