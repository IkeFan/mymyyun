package com.mmy.maimaiyun.model.personal.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.data.bean.RefundListBean;
import com.mmy.maimaiyun.utils.Constants;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 10:49
 * @描述 TODO
 */

public class RefundListAdapter extends BaseQuickAdapter<RefundListBean, BaseViewHolder> {

    private final SimpleDateFormat mFormat;

    public RefundListAdapter(int layoutResId, @Nullable List<RefundListBean> data) {
        super(layoutResId, data);
        mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    protected void convert(BaseViewHolder helper, RefundListBean item) {
        helper.setText(R.id.time, mFormat.format(new Date(item.add_time *1000)));
        helper.setText(R.id.name, item.goods_name);
        ImageView icon = helper.getView(R.id.icon);
        Picasso.with(mContext)
                .load(Constants.IMG_URL+item.logo)
                .placeholder(R.mipmap.ic_launcher_trad_food)
                .error(R.mipmap.ic_launcher_trad_food)
                .into(icon);
        helper.setText(R.id.clazz, item.goods_attr);
        helper.setText(R.id.money, "退款金额:￥" + item.refund_money);
        helper.setText(R.id.status, "状态：" + (item.status == 0 ? "处理中" : "已处理"));
    }
}
