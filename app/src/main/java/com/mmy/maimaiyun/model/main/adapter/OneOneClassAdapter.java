package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.SubCateBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/12/1 0001 17:42
 * @描述 TODO
 */

public class OneOneClassAdapter extends BaseRecyclerViewAdapter {

    private List<SubCateBean> mData;
    private int mSelectPosition;
    private Context mContext;

    public OneOneClassAdapter(Context context) {
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
        SubCateBean bean = mData.get(position);
        holder.setStr2TV(R.id.name,bean.cat_name);
        holder.getRootView().setEnabled(mSelectPosition != position);
        TextView view = (TextView) holder.findView(R.id.name);
        view.setTextColor(mSelectPosition == position ? 0xffffffff :
                mContext.getResources().getColor(R.color.normal_text_color));
    }

    public void setData(List<SubCateBean> data) {
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
