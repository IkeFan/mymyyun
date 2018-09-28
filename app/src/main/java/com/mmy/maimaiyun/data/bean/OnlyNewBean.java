package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/1/17 0017 19:09
 * @描述 TODO
 */

public class OnlyNewBean {


    /**
     * id : 11
     * pid : 0
     * shop_id : 0
     * name : 新人特惠
     * desc : 新人特惠
     * logo :
     * start_time : 0
     * end_time : null
     * discount : 88
     * flash_spec : 2
     * goods : [{"id":"391","logo":"http://www.ct1212.com/Public/Uploads/Goods/2017-12-29/5a461c094a16e.jpg",
     * "shop_id":"0","goods_name":"善味阁鲜卤鸭肫鸭胗190g真空独立小包装休闲零食卤味小吃鸭屯","shop_price":"26.00","market_price":"31.00",
     * "flashsale_start":"1516896000","flashsale_end":"1517328000"},{"id":"392","logo":"http://www
     * .ct1212.com/Public/Uploads/Goods/2018-01-03/5a4c93ee93046.jpg","shop_id":"0","goods_name":"善味阁新奥尔良烤鸡腿200g
     * 办公室休闲零食小吃","shop_price":"20.00","market_price":"24.00","flashsale_start":"1516896000",
     * "flashsale_end":"1517328000"},{"id":"413","logo":"http://www
     * .ct1212.com/Public/Uploads/Goods/2018-01-08/5a531e4849329.jpg","shop_id":"0","goods_name":"美国进口红提 进口葡萄 当季新品
     * 新鲜水果","shop_price":"198.00","market_price":"238.00","flashsale_start":"1516896000","flashsale_end":"1517328000"}]
     */

    public String id;
    public String          pid;
    public String          shop_id;
    public String          name;
    public String          desc;
    public String          logo;
    public String          start_time;
    public Object          end_time;
    public String          discount;
    public String          flash_spec;
    public List<GoodsBean> goods;

    public static class GoodsBean {
        /**
         * id : 391
         * logo : http://www.ct1212.com/Public/Uploads/Goods/2017-12-29/5a461c094a16e.jpg
         * shop_id : 0
         * goods_name : 善味阁鲜卤鸭肫鸭胗190g真空独立小包装休闲零食卤味小吃鸭屯
         * shop_price : 26.00
         * market_price : 31.00
         * flashsale_start : 1516896000
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
