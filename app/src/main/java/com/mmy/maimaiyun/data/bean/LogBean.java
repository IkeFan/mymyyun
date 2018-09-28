package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/12/30 0030 15:01
 * @描述 TODO
 */

public class LogBean {

    /**
     * message : ok
     * status : 1
     * state : 3
     * data : [{"time":"2012-07-07 13:35:14","context":"客户已签收"},{"time":"2012-07-07 09:10:10",
     * "context":"离开[北京石景山营业厅]派送中，递送员[温]，电话[]"},{"time":"2012-07-06 19:46:38","context":"到达[北京石景山营业厅]"},
     * {"time":"2012-07-06 15:22:32","context":"离开[北京石景山营业厅]派送中，递送员[温]，电话[]"},{"time":"2012-07-06 15:05:00",
     * "context":"到达[北京石景山营业厅]"},{"time":"2012-07-06 13:37:52","context":"离开[北京_同城中转站]发往[北京石景山营业厅]"},
     * {"time":"2012-07-06 12:54:41","context":"到达[北京_同城中转站]"},{"time":"2012-07-06 11:11:03",
     * "context":"离开[北京运转中心驻站班组] 发往[北京_同城中转站]"},{"time":"2012-07-06 10:43:21","context":"到达[北京运转中心驻站班组]"},
     * {"time":"2012-07-05 21:18:53","context":"离开[福建_厦门支公司] 发往 [北京运转中心_航空]"},{"time":"2012-07-05 20:07:27",
     * "context":"已取件，到达 [福建_厦门支公司]"}]
     */

    public String com;
    public String nu;
    public String message;
    public String         status;
    public String         state;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * time : 2012-07-07 13:35:14
         * context : 客户已签收
         */

        public String time;
        public String context;
    }
}
