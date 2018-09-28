package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.AddressItemBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 9:44
 * @描述 TODO
 */

public class AddressManagerAdapter extends BaseRecyclerViewAdapter {

    private List<AddressItemBean>      mData;
    private OnAddressItemClickListener mListener;

    public static final int MODIFY  = 0;
    public static final int DELETE  = 1;
    public static final int DEFAULT = 2;
    private Context mContext;

    public AddressManagerAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_address_manager, null);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        View modify = holder.getRootView().findViewById(R.id.modify);
        CheckBox check = (CheckBox) holder.getRootView().findViewById(R.id.is_default);
        AddressItemBean bean = mData.get(i);
        holder.setStr2TV(R.id.address_info, bean.address);
        holder.setStr2TV(R.id.receipt_name, "收货人: " + bean.consignee);
        holder.setStr2TV(R.id.phone, "电话: " + bean.mobile);
        boolean checked = bean.is_default.equals("1");
        check.setChecked(checked);
        if (checked) {
            check.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            check.setTextColor(mContext.getResources().getColor(R.color.normal_text_color));
        }
        check.setOnClickListener((buttonView) -> {
            bean.is_default = "0".equals(bean.is_default) ? "1" : "0";
            check.setChecked(bean.is_default.equals("1"));
            if (mListener != null) {
                mListener.onClick(buttonView, DEFAULT, holder.getAdapterPosition());
            }
        });
        modify.setOnClickListener(v -> {
            if (mListener != null)
                mListener.onClick(v, MODIFY, holder.getAdapterPosition());
        });
        holder.findView(R.id.delete).setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onClick(v, DELETE, holder.getAdapterPosition());
            }
        });
    }

    public void setOnAddressClickListener(OnAddressItemClickListener listener) {
        mListener = listener;
    }

    public void setData(List<AddressItemBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public interface OnAddressItemClickListener {
        /**
         * @param view
         * @param type     类型
         * @param position 位置
         */
        void onClick(View view, int type, int position);
    }
}
