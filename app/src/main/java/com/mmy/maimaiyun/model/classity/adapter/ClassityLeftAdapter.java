package com.mmy.maimaiyun.model.classity.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.ClassBean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/24 0024 13:58
 * @描述 TODO
 */

public class ClassityLeftAdapter extends BaseRecyclerViewAdapter {

    private List<ClassBean> mData;
    private int             mSelectPosition;
    private Context         mContext;

    public ClassityLeftAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classity2_left, parent, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        TextView view = (TextView) holder.findView(R.id.name);
        view.setText(mData.get(position).cat_name);
        holder.getRootView().setEnabled(mSelectPosition != position);
        view.setTextColor(mSelectPosition == position ? 0xffffffff :
                mContext.getResources().getColor(R.color.normal_text_color));
    }

    public void setData(List<ClassBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void selectItem(int position) {
        mSelectPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
