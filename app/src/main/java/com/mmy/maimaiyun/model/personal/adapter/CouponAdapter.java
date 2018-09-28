package com.mmy.maimaiyun.model.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.CouponBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/13 0013 13:57
 * @描述
 */

public class CouponAdapter extends BaseRecyclerViewAdapter {
    private final SimpleDateFormat mFormat;
    private       List<CouponBean> mData;
    private       Context          mContext;

    public CouponAdapter(Context context) {
        super(context);
        mContext = context;
        mFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_conpon, null);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        CouponBean bean = mData.get(i);
        holder.setCircleIV(R.id.shop_icon, R.mipmap.ic_def);
        holder.setStr2TV(R.id.clazz, "使用门槛：" + bean.name);
        holder.setStr2TV(R.id.time_scope, mFormat.format(new Date(bean.use_start_time * 1000)) + "~" +
                mFormat.format(new Date(bean.use_end_time * 1000)));
        holder.setStr2TV(R.id.money_text, bean.money);
        holder.setStr2TV(R.id.shop_name, bean.coupon_name);
        holder.setStr2TV(R.id.is_overdue, bean.isOverdue ? "已过期" : "未过期");
        holder.setVisibility(R.id.overdue_icon, bean.isOverdue ? View.VISIBLE : View.GONE);
        //修改item背景色
        View bg =  holder.findView(R.id.item_bg);
        if (bean.isUse && !bean.isOverdue)
            bg.setBackgroundResource(R.mipmap.conpon_bg3);
        if (!bean.isUse && !bean.isOverdue)
            bg.setBackgroundResource(R.mipmap.conpon_bg2);
    }

    public void setData(List<CouponBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
