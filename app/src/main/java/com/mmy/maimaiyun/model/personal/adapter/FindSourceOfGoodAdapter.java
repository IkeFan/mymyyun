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
 * @创建时间 2017/9/1 0001 14:52
 * @描述 TODO
 */

public class FindSourceOfGoodAdapter extends BaseRecyclerViewAdapter {

    private List<Integer> mData;
    private OnShopClickListener mListener;

    public FindSourceOfGoodAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_source_of_good, null);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {

    }


    public void setOnShopClickListener(OnShopClickListener listener){
        mListener = listener;
    }

    public interface OnShopClickListener{
        void onShopClick(View view, int position);
    }

    public void setData(List<Integer> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class FindSourceOfHolder extends BaseRecyclerViewHolder{

        public FindSourceOfHolder(View view) {
            super(view);
        }

    }
}
