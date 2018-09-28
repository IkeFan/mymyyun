package com.mmy.maimaiyun.data.bean;

/**
 * @创建者 lucas
 * @创建时间 2017/11/6 0006 10:20
 * @描述
 */

public class CouponBean {

    /**
     * id : 106
     * cid : 11
     * shop_id : 0
     * uid : 2657
     * name : 双11情人券
     * code :
     * start_time : 0
     * end_time : 0
     * use_time : 0
     * status : 1
     * number : 100
     * remain_number : 0
     * money : 100.00
     * use_start_time : 0
     * use_end_time : 0
     * add_time : 1509507446
     */

    public String id;
    public String cid;
    public String shop_id;
    public String uid;
    public String name;
    public String coupon_name;
    public String code;
    public String start_time;
    public String end_time;
    public String use_time;
    public int status;
    public String number;
    public String remain_number;
    public String money;
    public long use_start_time;
    public long use_end_time;
    public String add_time;

    //自定义标记
    public boolean isUse;//是否已使用
    public boolean isOverdue;//是否过期
}
