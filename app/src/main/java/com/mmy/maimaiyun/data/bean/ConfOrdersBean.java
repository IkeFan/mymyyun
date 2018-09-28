package com.mmy.maimaiyun.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/7 0007 9:31
 * @描述 TODO
 */

public class ConfOrdersBean implements Serializable {


    /**
     * shop : [{"shopId":11,"shopName":"1号店铺11 银耳","totalPrice":188,"goodsInfo":[{"id":"1607","goods_name":"测试商品1",
     * "logo":"http://192.168.2.125/Public/Uploads/Goods/2017-11-09/5a040cf3d6128.jpg","market_price":"588.00",
     * "shop_price":"188.00","fullcut_id":"3","store_num":"200","cart_id":0,"goods_number":"1","goods_attr_id":"405,
     * 407","goods_attr_value":"白色,123"}],"cutPrice":75.2,"fullcutInfo":[{"fullcutId":3,"total":188,
     * "cutPrice":"75.20","name":"双十一满立减折扣","desc":"满1000打8折","condition":"100.00","status":1}],
     * "couponInfo":[{"id":"110","cid":"10","uid":"2636","shop_id":"11","name":"双11情人券","money":"100.00","code":"",
     * "start_time":"1509935550","end_time":"1512528009","use_time":"0","status":"1"},{"id":"111","cid":"12",
     * "uid":"2636","shop_id":"11","name":"双11情人券haha","money":"100.00","code":"","start_time":"1509935550",
     * "end_time":"1512528009","use_time":"0","status":"1"}]}]
     * address : null
     */

    public AddressItemBean address;
    public List<ShopBean>  shop;

    public static class ShopBean implements Serializable {
        /**
         * shopId : 11
         * shopName : 1号店铺11 银耳
         * totalPrice : 188
         * goodsInfo : [{"id":"1607","goods_name":"测试商品1",
         * "logo":"http://192.168.2.125/Public/Uploads/Goods/2017-11-09/5a040cf3d6128.jpg","market_price":"588.00",
         * "shop_price":"188.00","fullcut_id":"3","store_num":"200","cart_id":0,"goods_number":"1",
         * "goods_attr_id":"405,407","goods_attr_value":"白色,123"}]
         * cutPrice : 75.2
         * fullcutInfo : [{"fullcutId":3,"total":188,"cutPrice":"75.20","name":"双十一满立减折扣","desc":"满1000打8折",
         * "condition":"100.00","status":1}]
         * couponInfo : [{"id":"110","cid":"10","uid":"2636","shop_id":"11","name":"双11情人券","money":"100.00",
         * "code":"","start_time":"1509935550","end_time":"1512528009","use_time":"0","status":"1"},{"id":"111",
         * "cid":"12","uid":"2636","shop_id":"11","name":"双11情人券haha","money":"100.00","code":"",
         * "start_time":"1509935550","end_time":"1512528009","use_time":"0","status":"1"}]
         */

        public int                     shopId;
        public String                  shopName;
        public float                   totalPrice;
        public float                   cutPrice;
        public List<GoodsInfoBean>     goodsInfo;
        public List<FullcutInfoBean>   fullcutInfo;
        public List<CouponInfoBean>    couponInfo;
        public DuducardInfoBean        duducardInfo;
        public String        duducardNum;
        //被选择的优惠券
        public ShopBean.CouponInfoBean selectShopCoupon;
        public boolean                 selectDudu;

        public static class DuducardInfoBean implements Serializable {

            /**
             * id : 2048
             * bonus_type_id : 3
             * bonus_sn : 1000002048
             * user_id : 2646
             * send_user_id : 0
             * end_time : 1539397151
             * used_time : 0
             * order_id : 0
             * emailed : 0
             * is_act : 0
             * send_order_id : 0
             * send_id : 0
             * suppliers_id : 0
             * is_attention_send : 0
             */

            public String id;
            public String bonus_type_id;
            public String bonus_sn;
            public String user_id;
            public String send_user_id;
            public String end_time;
            public String used_time;
            public String order_id;
            public String emailed;
            public String is_act;
            public String send_order_id;
            public String send_id;
            public String suppliers_id;
            public String is_attention_send;
        }

        public static class GoodsInfoBean implements Serializable {
            /**
             * id : 1607
             * goods_name : 测试商品1
             * logo : http://192.168.2.125/Public/Uploads/Goods/2017-11-09/5a040cf3d6128.jpg
             * market_price : 588.00
             * shop_price : 188.00
             * fullcut_id : 3
             * store_num : 200
             * cart_id : 0
             * goods_number : 1
             * goods_attr_id : 405,407
             * goods_attr_value : 白色,123
             */

            public String id;
            public String goods_name;
            public String logo;
            public String market_price;
            public String shop_price;
            public String fullcut_id;
            public String store_num;
            public int    cart_id;
            public int    goods_number;
            public String goods_attr_id;
            public String goods_attr_value;
            public float  logistics_money;
        }

        public static class FullcutInfoBean implements Serializable {
            /**
             * fullcutId : 3
             * total : 188
             * cutPrice : 75.20
             * name : 双十一满立减折扣
             * desc : 满1000打8折
             * condition : 100.00
             * status : 1
             */

            public int    fullcutId;
            public int    total;
            public String cutPrice;
            public String name;
            public String desc;
            public String condition;
            public int    status;
        }

        public static class CouponInfoBean implements Serializable {

            /**
             * id : 129
             * cid : 10
             * uid : 2642
             * shop_id : 11
             * name : 1号店铺优惠券
             * code :
             * start_time : 1509552000
             * end_time : 1523462400
             * use_time : 0
             * status : 1
             * condition : [{"money":"5","full":"1"},{"money":"10","full":"100"},{"money":"50","full":"500"}]
             * money : 10
             */

            public String id;
            public String              cid;
            public String              uid;
            public String              shop_id;
            public String              name;
            public String              code;
            public long              start_time;
            public long              end_time;
            public long              use_time;
            public String              status;
            public float              money;
            public List<ConditionBean> condition;

            public static class ConditionBean implements Serializable {
                /**
                 * money : 5
                 * full : 1
                 */

                public String money;
                public String full;
            }
        }
    }
}
