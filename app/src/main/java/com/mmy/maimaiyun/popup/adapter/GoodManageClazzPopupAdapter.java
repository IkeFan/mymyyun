package com.mmy.maimaiyun.popup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.OnItemInnerClickListener;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/16 0016 16:24
 * @描述 TODO
 */

public class GoodManageClazzPopupAdapter extends BaseRecyclerViewAdapter {

    private List<String>             mData;
    private OnItemInnerClickListener mListener;
    private int                      mSelectPosition;


    public GoodManageClazzPopupAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_good_manage_clazz,
                viewGroup, false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        CheckBox select = (CheckBox) holder.findView(R.id.select);
        select.setChecked((mSelectPosition == i));
    }

    public void setData(List<String> data) {
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
