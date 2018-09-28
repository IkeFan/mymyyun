package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.CollectionBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 11:47
 * @描述 TODO
 */

public class CollectionAdapter extends BaseRecyclerViewAdapter implements BaseRecyclerViewAdapter.OnItemClickListener {

    private List<CollectionBean> mData;
    private boolean              mIsEdit;


    public CollectionAdapter(Context context) {
        super(context);
        addOnItemCliclListener(this);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_collection, null);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        holder.getRootView().findViewById(R.id.select)
                .setVisibility(mIsEdit ? View.VISIBLE : View.GONE);
        CollectionBean bean = mData.get(i);
        holder.setPath2IV(R.id.icon, bean.logo);
        holder.setStr2TV(R.id.name, bean.goods_name);
        holder.setStr2TV(R.id.money_text, "￥ " + bean.shop_price);
        CheckBox collect = (CheckBox) holder.findView(R.id.select);
        collect.setChecked(bean.isSelect);
    }

    public void setData(List<CollectionBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void deleteSelect() {

    }

    public void isEdit(boolean isEidt) {
        mIsEdit = isEidt;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public Set<Integer> positions = new HashSet<>();

    @Override
    public void onItemClick(View view, int position) {
        if (!mIsEdit) return;
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.select);
        checkBox.setChecked(!checkBox.isChecked());
        mData.get(position).isSelect = checkBox.isChecked();
        if (checkBox.isChecked()) {
            positions.add(position);
        } else {
            positions.remove(position);
        }
    }

    public String getCheckedIds() {
        String ids = "";
        for (Integer integer : positions) {
            String id = mData.get(integer).id;
            ids = TextUtils.isEmpty(ids) ? id : (ids + "," + id);
        }
        return ids;
    }
}
