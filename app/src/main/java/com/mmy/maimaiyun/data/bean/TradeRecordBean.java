package com.mmy.maimaiyun.data.bean;

/**
 * @创建者 lucas
 * @创建时间 2017/10/12 0012 11:28
 * @描述 TODO
 */

public class TradeRecordBean {

    /**
     * log_id : 2
     * member_id : 2624
     * amount : -14.75
     * frozen_money : 0.00
     * pay_points : -100
     * add_time : 1507600918
     * type : 1
     * desc : app充值
     * order_sn : 201710101001583142
     * order_id : 3
     */

    public String log_id;
    public String member_id;
    public String member_money;
    public String frozen_money;
    public String pay_points;
    public long change_time;
    public int type;
    public String desc;
    public String order_sn;
    public String order_id;
    public String bank_name;
    public String bank_card;

    @Override
    public boolean equals(Object o) {
        TradeRecordBean that = (TradeRecordBean) o;
        return order_id != null ? order_id.equals(that.order_id) : that.order_id == null;
    }

    @Override
    public int hashCode() {
        return order_id != null ? order_id.hashCode() : 0;
    }
}
