package com.mmy.maimaiyun.model.main.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.data.bean.TimeLimitBean;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 17:24
 * @描述 TODO
 */

public class TimeLimitAdapter extends BaseQuickAdapter<TimeLimitBean.GoodInfo, BaseViewHolder> {

    private String mTextStr = "立即抢购";

    public TimeLimitAdapter(int layoutResId, @Nullable List<TimeLimitBean.GoodInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TimeLimitBean.GoodInfo item) {
        ImageView icon = helper.getView(R.id.icon);
        Picasso.with(mContext).
                load(item.logo).
                placeholder(R.mipmap.ic_def).
                error(R.mipmap.ic_def).
                into(icon);
        helper.setText(R.id.title, item.goods_name);
        helper.setText(R.id.original_price, "原价 ￥" + item.market_price);
        helper.setText(R.id.discount_price, "抢购价 ￥" + item.shop_price);
        helper.setText(R.id.buy, mTextStr);
    }

    public void setTextStr(String textStr) {
        mTextStr = textStr;
    }
}
