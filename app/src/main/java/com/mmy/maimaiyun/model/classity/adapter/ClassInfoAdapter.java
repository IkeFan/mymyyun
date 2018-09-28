package com.mmy.maimaiyun.model.classity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.ClassBean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/8/22 0022 9:47
 * @描述 TODO
 */

public class ClassInfoAdapter extends BaseRecyclerViewAdapter {

    private List<ClassBean> mData;

    public ClassInfoAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clazz_info, null);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        ClassBean bean = mData.get(position);
        if (!TextUtils.isEmpty(bean.cate_pic))
            holder.setPath2IV(R.id.clazz_info_icon, bean.cate_pic);
        holder.setStr2TV(R.id.clazz_info_name, bean.cat_name);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<ClassBean> data) {
        mData = data;
        notifyDataSetChanged();
    }


}
