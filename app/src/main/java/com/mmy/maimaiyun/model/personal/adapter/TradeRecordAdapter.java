package com.mmy.maimaiyun.model.personal.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.data.bean.TradeRecordBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @创建者 lucas
 * @创建时间 2017/9/19 0019 11:36
 * @描述 TODO
 */

public class TradeRecordAdapter extends BaseQuickAdapter<TradeRecordBean, BaseViewHolder> {

    private List<TradeRecordBean> mAllData;

    public TradeRecordAdapter(int layoutResId, @Nullable List<TradeRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TradeRecordBean item) {
        helper.setText(R.id.type_name, item.desc);
        helper.setText(R.id.time, TimeUtils.date2String(new Date(item.change_time * 1000)));
        if (item.type == -2)
            helper.setText(R.id.money_text, "冻结金额 ￥" + item.frozen_money);
        else
            helper.setText(R.id.money_text, "￥" + item.member_money);
        TextView money = helper.getView(R.id.money_text);
        money.setTextColor(Float.parseFloat(item.member_money) < 0 ? 0xddff0000 : 0xdd00ff00);

        if (!StringUtils.isEmpty(item.bank_card)) {
            String bank_card = item.bank_card;
            if (bank_card == null)
                return;
            if (bank_card.length() >= 4)
                bank_card = bank_card.substring(bank_card.length() - 4);
            helper.setText(R.id.bank, item.bank_name + "(" + bank_card + ")");
            helper.setVisible(R.id.bank,true);
        }else {
            helper.setVisible(R.id.bank,false);
        }
    }

    public void refreshAllData() {
        mAllData = getData();
    }

    //分类
    public void order(int index) {
        ArrayList<TradeRecordBean> list = new ArrayList<>();
        for (TradeRecordBean bean : mAllData) {
            if (bean.type == index)
                list.add(bean);
        }
        setNewData(index == 9999 ? mAllData : list);
    }
}
