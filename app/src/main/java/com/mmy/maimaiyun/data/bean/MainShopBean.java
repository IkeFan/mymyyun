package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/1/30 0030 14:15
 * @描述 TODO
 */

public class MainShopBean {

    /**
     * goods : [{"id":"10","goods_name":"香港·恒香老饼家蜂巢鸡蛋卷","shop_price":"39.90","logo":"http://www
     * .ct1212.com/Public/Uploads/images/201711/goods_img/10_G_1512002198213.jpg","people":"3"},{"id":"103",
     * "goods_name":"贵州茅台集团 記台1915 精品浓香","shop_price":"588.00","logo":"http://www
     * .ct1212.com/Public/Uploads/images/201707/goods_img/103_G_1501025590518.jpg","people":"25"},{"id":"104",
     * "goods_name":" 贵州茅台集团 記台1915 金卡酱香（带杯）","shop_price":"568.00","logo":"http://www
     * .ct1212.com/Public/Uploads/images/201707/goods_img/104_G_1501026001483.jpg","people":"49"},{"id":"105",
     * "goods_name":"贵州茅台集团 記台1915 御品浓香","shop_price":"588.00","logo":"http://www
     * .ct1212.com/Public/Uploads/images/201708/goods_img/105_G_1501712710547.jpg","people":"38"},{"id":"106",
     * "goods_name":"贵州茅台集团 記台1915 御品酱香","shop_price":"988.00","logo":"http://www
     * .ct1212.com/Public/Uploads/images/201708/goods_img/106_G_1501712656841.jpg","people":"16"},{"id":"108",
     * "goods_name":"塞上松  天然破壁油松花粉","shop_price":"228.00","logo":"http://www
     * .ct1212.com/Public/Uploads/images/201708/goods_img/108_G_1501546476909.jpg","people":"78"},{"id":"109",
     * "goods_name":"悦观潮 享醉 高端黄酒 ","shop_price":"298.00","logo":"http://www
     * .ct1212.com/Public/Uploads/images/201708/goods_img/109_G_1501610772343.jpg","people":"36"},{"id":"110",
     * "goods_name":"悦观潮 享乐 高端黄酒 ","shop_price":"98.00","logo":"http://www
     * .ct1212.com/Public/Uploads/images/201708/goods_img/110_G_1501610759862.jpg","people":"29"}]
     * count : 225
     * shop : {"shop_name":"中国食品自营","logo":"http://www.ct1212.com/Public/Uploads/System/2017-12-13/5a3136229433b.jpg"}
     */

    public String count;
    public ShopBean        shop;
    public List<GoodsBean> goods;

    public static class ShopBean {
        /**
         * shop_name : 中国食品自营
         * logo : http://www.ct1212.com/Public/Uploads/System/2017-12-13/5a3136229433b.jpg
         */

        public String shop_name;
        public String logo;
    }

    public static class GoodsBean {
        /**
         * id : 10
         * goods_name : 香港·恒香老饼家蜂巢鸡蛋卷
         * shop_price : 39.90
         * logo : http://www.ct1212.com/Public/Uploads/images/201711/goods_img/10_G_1512002198213.jpg
         * people : 3
         */

        public String id;
        public String goods_name;
        public String shop_price;
        public String logo;
        public String people;
    }
}
