package com.mmy.maimaiyun.model.main.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.data.bean.MainShopBean;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2018/1/30 0030 10:17
 * @描述 TODO
 */

public class ShopAdapter extends BaseQuickAdapter<MainShopBean.GoodsBean, BaseViewHolder> {
    public ShopAdapter(int layoutResId, @Nullable List<MainShopBean.GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainShopBean.GoodsBean item) {
        ImageView icon = helper.getView(R.id.hot_goods_icon);
        Picasso.with(mContext).load(item.logo)
                .error(R.mipmap.ic_launcher_trad_food)
                .placeholder(R.mipmap.ic_launcher_trad_food)
                .into(icon);
        helper.setText(R.id.hot_goods_title, item.goods_name);
        helper.setVisible(R.id.pay_count,true);
        helper.setText(R.id.pay_count, "销量"+item.people);
        helper.setText(R.id.hot_goods_money, "￥ " + item.shop_price);
    }
}
