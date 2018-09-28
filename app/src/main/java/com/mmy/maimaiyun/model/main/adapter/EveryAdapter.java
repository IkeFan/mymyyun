package com.mmy.maimaiyun.model.main.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.data.bean.HomeEveryBean;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2018/1/17 0017 18:33
 * @描述 TODO
 */

public class EveryAdapter extends BaseQuickAdapter<HomeEveryBean.TodayBean,BaseViewHolder> {

    public EveryAdapter(int layoutResId, @Nullable List<HomeEveryBean.TodayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,HomeEveryBean.TodayBean item) {
        ImageView icon = helper.getView(R.id.icon);
        Picasso.with(mContext).
                load(item.logo).
                placeholder(R.mipmap.ic_def).
                error(R.mipmap.ic_def).
                into(icon);
        helper.setText(R.id.title,item.goods_name);
        helper.setText(R.id.price,"抢购价 ￥"+item.shop_price);
    }
}
