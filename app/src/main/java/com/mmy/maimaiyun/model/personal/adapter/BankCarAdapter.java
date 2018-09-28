package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.BankCarBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 14:58
 * @描述 TODO
 */

public class BankCarAdapter extends BaseRecyclerViewAdapter implements BaseRecyclerViewAdapter.OnItemClickListener {
    private List<BankCarBean> mData;
    static final int ADD    = 0;
    static final int NORMAL = 1;
    private boolean mIsSelect;
    private boolean mIsSelectAll;

    public BankCarAdapter(Context context) {
        super(context);
        addOnItemCliclListener(this);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                type == NORMAL ? R.layout.item_bank_car :
                        R.layout.item_bank_car_add, viewGroup, false);
        return new BankCarHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        if (getItemViewType(i) == NORMAL) {
            BankCarBean bean = mData.get(i);
            holder.setStr2TV(R.id.bank_name, bean.deposit);
            holder.setStr2TV(R.id.car_number, bean.bank_card);

            CheckBox checkBox = (CheckBox) holder.findView(R.id.select);
            if (checkBox != null) {
                checkBox.setVisibility(mIsSelect ? View.VISIBLE : View.GONE);
                checkBox.setChecked(mIsSelectAll);
            }
        }
    }

    public void setData(List<BankCarBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    //全选
    public void selectAll(boolean isSelectAll) {
        mIsSelectAll = isSelectAll;
        if (mData != null) {
            for (BankCarBean bean : mData) {
                bean.isDelete = mIsSelectAll;
            }
        }
        notifyDataSetChanged();
    }

    //是否可编辑
    public void select(boolean isSelect) {
        mIsSelect = isSelect;
        notifyDataSetChanged();
    }

    public String getSelectCarId() {
        String ids = "";
        for (BankCarBean bean : mData) {
            if (bean.isDelete)
                ids = ids + (TextUtils.isEmpty(ids) ? "" : ",") + bean.id;
        }
        return ids;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return ADD;
        } else {
            return NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 1 : mData.size() + 1;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mIsSelect && mData.size() > 0) {
            mData.get(position).isDelete = !mData.get(position).isDelete;
        }
    }

    public BankCarBean getItemBean(int position) {
        BankCarBean bean = mData.get(position);
        return bean;
    }

    class BankCarHolder extends BaseRecyclerViewHolder {

        public BankCarHolder(View view) {
            super(view);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            CheckBox checkBox = (CheckBox) findView(R.id.select);

            if (mIsSelect && checkBox != null)
                checkBox.setChecked(!checkBox.isChecked());
        }
    }
}
