package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/11/9 0009 16:55
 * @描述
 */

public class OrderAttr {

    /**
     * member_id : 1634
     * address_id : 1
     * member_money : 0
     * integral : 0
     * shop : [{"shop_id":11,"total":"112.8","coupon":1,"remark":"给卖家留言","fullcut_id":3,
     * "goods_info":[{"goods_id":1607,"goods_attr_id":"112,113","goods_number":6,"shop_price":11},{"goods_id":1607,
     * "goods_attr_id":"112,113","goods_number":6,"shop_price":11}]},{"shop_id":12,"total":"112.8","coupon":1,
     * "remark":"给卖家留言","fullcut_id":3,"goods_info":[{"goods_id":1607,"goods_attr_id":"112,113","goods_number":6,
     * "shop_price":11},{"goods_id":1607,"goods_attr_id":"112,113","goods_number":6,"shop_price":11}]}]
     */

    public String     member_id;
    public String     address_id;
    public float     member_money;
    public float     integral;
    public List<Shop> shop;

    public static class Shop {
        /**
         * shop_id : 11
         * total : 112.8
         * coupon : 1
         * remark : 给卖家留言
         * fullcut_id : 3
         * goods_info : [{"goods_id":1607,"goods_attr_id":"112,113","goods_number":6,"shop_price":11},{"goods_id":1607,
         * "goods_attr_id":"112,113","goods_number":6,"shop_price":11}]
         */

        public String                                 shop_id;
        public float                                  total;
        public ConfOrdersBean.ShopBean.CouponInfoBean coupon;
        public String                                 remark;
        public String                                 fullcut_id;
        public List<GoodsInfo>                        goods_info;
        public String                                 duducard;

        public static class GoodsInfo {
            /**
             * goods_id : 1607
             * goods_attr_id : 112,113
             * goods_number : 6
             * shop_price : 11
             */

            public String goods_id;
            public String goods_attr_id;
            public int goods_number;
            public String shop_price;
        }
    }
}
