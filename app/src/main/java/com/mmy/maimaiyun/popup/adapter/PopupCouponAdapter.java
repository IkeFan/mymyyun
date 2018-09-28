package com.mmy.maimaiyun.popup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.GoodsInfoBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/12/13 0013 18:20
 * @描述 TODO
 */

public class PopupCouponAdapter extends BaseRecyclerViewAdapter {

    private final SimpleDateFormat mFormat;
    private List<GoodsInfoBean.CouponsBean> mData;

    public PopupCouponAdapter(Context context) {
        super(context);
        mFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conpon, parent, false);
        return new BaseRecyclerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        GoodsInfoBean.CouponsBean bean = mData.get(position);
        holder.setCircleIV(R.id.shop_icon, R.mipmap.ic_def);
        holder.setStr2TV(R.id.clazz, "使用门槛：" + bean.name);
        holder.setStr2TV(R.id.time_scope, mFormat.format(new Date(bean.use_start_time * 1000)) + "~" +
                mFormat.format(new Date(bean.use_end_time * 1000)));
//        holder.setStr2TV(R.id.money_text, bean.);
        holder.setVisibility(R.id.money_text,View.GONE);
        holder.setStr2TV(R.id.shop_name, bean.name);
        holder.setStr2TV(R.id.is_overdue, false ? "已过期" : "未过期");
        holder.setVisibility(R.id.overdue_icon, false ? View.VISIBLE : View.GONE);
        //修改item背景色
        View bg =  holder.findView(R.id.item_bg);
//        if (bean.isUse && !bean.isOverdue)
//            bg.setBackgroundResource(R.mipmap.conpon_bg3);
//        if (!bean.isUse && !bean.isOverdue)
            bg.setBackgroundResource(R.mipmap.conpon_bg2);
    }

    public void setData(List<GoodsInfoBean.CouponsBean> data) {
        mData = data;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
