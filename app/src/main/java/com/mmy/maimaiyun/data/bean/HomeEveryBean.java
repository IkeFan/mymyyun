package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/1/17 0017 18:01
 * @描述 TODO
 */

public class HomeEveryBean {


    /**
     * id : 10
     * pid : 0
     * shop_id : 0
     * name : 每日抢购
     * desc : 每日抢购
     * logo :
     * start_time : 0
     * end_time : null
     * discount : 95
     * flash_spec : 1
     * today : [{"id":"428","logo":"http://www.ct1212.com/Public/Uploads/Goods/2018-01-26/5a6abea987d04.jpg",
     * "shop_id":"6","goods_name":"百老匠方便速食自热盒饭4盒套餐","shop_price":"100.00","market_price":"120.00",
     * "flashsale_start":"1517155200","flashsale_end":"1517241600"}]
     * tomorrow : [{"id":"421","logo":"http://www.ct1212.com/Public/Uploads/Goods/2018-01-09/5a54924f7cb1b.jpg",
     * "shop_id":"0","goods_name":"棒棒糖巧克力礼盒情人圣诞节生日礼物送女友","shop_price":"210.00","market_price":"252.00",
     * "flashsale_start":"1517241600","flashsale_end":"1517328000"},{"id":"424","logo":"http://www
     * .ct1212.com/Public/Uploads/Goods/2018-01-09/5a5497478c6f2.jpg","shop_id":"0","goods_name":"德芙&amp;
     * 费列罗巧克力礼盒心意礼盒生日礼物","shop_price":"160.00","market_price":"192.00","flashsale_start":"1517241600","flashsale_end
     * ":"1517328000"}]
     */

    public String id;
    public String             pid;
    public String             shop_id;
    public String             name;
    public String             desc;
    public String             logo;
    public String             start_time;
    public Object             end_time;
    public String             discount;
    public String             flash_spec;
    public List<TodayBean>    today;
    public List<TomorrowBean> tomorrow;

    public static class TodayBean {
        /**
         * id : 428
         * logo : http://www.ct1212.com/Public/Uploads/Goods/2018-01-26/5a6abea987d04.jpg
         * shop_id : 6
         * goods_name : 百老匠方便速食自热盒饭4盒套餐
         * shop_price : 100.00
         * market_price : 120.00
         * flashsale_start : 1517155200
         * flashsale_end : 1517241600
         */

        public String id;
        public String logo;
        public String shop_id;
        public String goods_name;
        public String shop_price;
        public String market_price;
        public String flashsale_start;
        public String flashsale_end;
    }

    public static class TomorrowBean {
        /**
         * id : 421
         * logo : http://www.ct1212.com/Public/Uploads/Goods/2018-01-09/5a54924f7cb1b.jpg
         * shop_id : 0
         * goods_name : 棒棒糖巧克力礼盒情人圣诞节生日礼物送女友
         * shop_price : 210.00
         * market_price : 252.00
         * flashsale_start : 1517241600
         * flashsale_end : 1517328000
         */

        public String id;
        public String logo;
        public String shop_id;
        public String goods_name;
        public String shop_price;
        public String market_price;
        public String flashsale_start;
        public String flashsale_end;
    }
}
