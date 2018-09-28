package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.ActvityGoodInfoBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 15:01
 * @描述 TODO
 */

public class VolumeListAdapter extends BaseRecyclerViewAdapter implements BaseRecyclerViewAdapter.OnItemClickListener {
    private List<ActvityGoodInfoBean> mData;
    private OnInnerClickListener mListener;

    public VolumeListAdapter(Context context) {
        super(context);
        addOnItemCliclListener(this);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_volume_lsit, null);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        ActvityGoodInfoBean bean = mData.get(i);
        holder.setPath2IV(R.id.icon,bean.logo);
        holder.setStr2TV(R.id.name,bean.goods_name);
        holder.setStr2TV(R.id.money_text,bean.shop_price);
    }

    public void setData(List<ActvityGoodInfoBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setOnInnerClickListener(OnInnerClickListener listener){
        mListener = listener;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mListener != null) {
            mListener.onInnerClick(mData.get(position).id);
        }
    }

    public interface OnInnerClickListener{
        void onInnerClick(String id);
    }
}
