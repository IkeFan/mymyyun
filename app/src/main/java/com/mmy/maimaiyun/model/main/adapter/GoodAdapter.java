package com.mmy.maimaiyun.model.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.GoodsBestBean;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 14:52
 * @描述 TODO
 */

public class GoodAdapter extends BaseRecyclerViewAdapter {

    private List<GoodsBestBean> mData;
    private OnShopClickListener mListener;

    public GoodAdapter(Context context) {
        super(context);
    }


    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_good, parent,false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        GoodsBestBean bean = mData.get(position);
        holder.setPath2IV(R.id.good_img,bean.logo);
        holder.setStr2TV(R.id.good_text,bean.goods_name);
        holder.setStr2TV(R.id.good_money,"￥"+bean.shop_price);
    }


    public void setOnShopClickListener(OnShopClickListener listener){
        mListener = listener;
    }

    public interface OnShopClickListener{
        void onShopClick(View view, int position);
    }

    public void setData(List<GoodsBestBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

//    public class GoodHolder extends BaseRecyclerViewHolder{
//
//        private final ImageView mShop;
//
//        public GoodHolder(View view) {
//            super(view);
//            mShop = (ImageView) view.findViewById(R.id.add_shop_car);
//            mShop.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            mListener.onShopClick(v,getAdapterPosition());
//            //修改显示的状态
//            mShop.setImageResource(R.mipmap.shopping_car);
//            super.onClick(v);
//        }
//    }
}
