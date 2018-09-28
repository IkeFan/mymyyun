package com.mmy.maimaiyun.data.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/9/13 0013 15:16
 * @描述 TODO
 */

public class CouponListBean {

    public List<CouponBean> data1;
    public List<CouponBean> data2;
    public List<CouponBean> data3;

    public CouponListBean(ArrayList<CouponBean> data1, ArrayList<CouponBean> data2, ArrayList<CouponBean> data3) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }
}
