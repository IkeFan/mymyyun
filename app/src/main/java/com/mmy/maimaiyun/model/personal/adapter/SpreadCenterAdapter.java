package com.mmy.maimaiyun.model.personal.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.data.bean.SpreadCenterBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * @创建者 lucas
 * @创建时间 2017/11/16 0016 16:37
 * @描述
 */

public class SpreadCenterAdapter extends BaseQuickAdapter<SpreadCenterBean.InfoBean,BaseViewHolder> {
    private final SimpleDateFormat                mDateFormat;
    private       List<SpreadCenterBean.InfoBean> mData;

    String[] status = {"冻结", "已发放", "已失效"};

    public SpreadCenterAdapter(int layoutResId, @Nullable List<SpreadCenterBean.InfoBean> data) {
        super(layoutResId, data);
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    @Override
    protected void convert(BaseViewHolder helper, SpreadCenterBean.InfoBean bean) {
        helper.setText(R.id.user_name, TextUtils.isEmpty(bean.buy_nickname) ? bean.buy_mobile : bean.buy_nickname);
        helper.setText(R.id.order_id, bean.order_sn);
        helper.setText(R.id.good_name, bean.goods_name);
        helper.setText(R.id.time, mDateFormat.format(new Date(bean.add_time * 1000)));
        helper.setText(R.id.money, "￥" + bean.amount);
        helper.setText(R.id.status, status[bean.status]);
    }
}
