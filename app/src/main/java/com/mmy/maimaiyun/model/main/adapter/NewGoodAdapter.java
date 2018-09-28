package com.mmy.maimaiyun.model.main.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.data.bean.NewGoodBean;
import com.mmy.maimaiyun.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2018/1/18 0018 17:17
 * @描述 TODO
 */

public class NewGoodAdapter extends BaseQuickAdapter<NewGoodBean.GoodsBean,BaseViewHolder> {

    public NewGoodAdapter(int layoutResId, @Nullable List<NewGoodBean.GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewGoodBean.GoodsBean bean) {
        ImageView icon = helper.getView(R.id.icon);
        Picasso.with(mContext).
                load(Constants.IMG_URL+bean.logo).
                placeholder(R.mipmap.ic_def).
                error(R.mipmap.ic_def).
                into(icon);
        helper.setText(R.id.title, bean.goods_name);
        helper.getView(R.id.price).setVisibility(View.GONE);
        helper.setText(R.id.price2, bean.market_price);
    }
}
