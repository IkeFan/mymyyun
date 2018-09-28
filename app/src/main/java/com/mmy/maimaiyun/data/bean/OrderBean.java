package com.mmy.maimaiyun.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/10 0010 17:45
 * @描述 TODO
 */

public class OrderBean implements Serializable {

    public int my_order_status = -1;

    /**
     * order_id : 199
     * order_sn : 201711141023199533
     * member_id : 2636
     * order_type : 0
     * pay_status : 0
     * consignee : hgfhgf
     * mobile : 543543
     * country : 0
     * province : 2
     * city : 52
     * district : 500
     * twon : 0
     * address : gdfgfdg
     * zipcode :
     * pay_code : weixin
     * pay_name : 微信APP支付
     * trade_no : null
     * invoice_title :
     * goods_price : 101.00
     * shipping_price : 0.00
     * total_amount : 101.00
     * member_money : 0.00
     * coupon_price : 0.00
     * integral : 0
     * integral_money : 0.00
     * order_prom_amount : 40.40
     * order_amount : 60.60
     * add_time : 1510626199
     * pay_time : 0
     * id : 73
     * shop_id : 11
     * order_shop_sn : 201711141023198058
     * coupon_list_id : 0
     * shop_name : 1号店铺
     * fullcut_info : {"3":{"id":"3","shop_id":"2","name":"双十一满立减折扣","desc":"满1000打8折","condition":"100.00",
     * "rate":"6.00","rate_type":"1","start_time":"1509465600","end_time":"1511971200","status":"1","cutPrice":40.4}}
     * fullcut_price : 40.40
     * member_note :
     * admin_note : null
     * total_goods_price : 101.00
     * pay_price : 60.60
     * order_shop_status : 0
     * shipping_status : 0
     * coupon_info : null
     * goodsInfo : [{"id":"252","order_id":"199","shop_id":"11","goods_id":"1607","goods_name":"测试商品1",
     * "goods_attr_id":"405,407","goods_number":"1","price":"101.00","shipping_price":"0.00","give_integral":"0",
     * "invoice_no":"0","sku":"","status":"0","goods_attr_value":"白色,123","logo":"http://192.168.2.125/Public/Uploads
     * /Goods/2017-11-09/5a040cf3d6128.jpg"}]
     */

    public String              order_id;
    public String              order_sn;
    public String              member_id;
    public String              order_type;
    public int                 pay_status;
    public String              consignee;
    public String              mobile;
    public String              country;
    public String              province;
    public String              city;
    public String              district;
    public String              twon;
    public String              address;
    public String              zipcode;
    public String              pay_code;
    public String              pay_name;
    public Object              trade_no;
    public String              invoice_title;
    public String              goods_price;
    public String              shipping_price;
    public String              total_amount;
    public String              member_money;
    public String              coupon_price;
    public String              integral;
    public String              integral_money;
    public String              order_prom_amount;
    public String              order_amount;
    public String              add_time;
    public String              pay_time;
    public String              id;
    public String              shop_id;
    public String              order_shop_sn;
    public String              coupon_list_id;
    public String              shop_name;
    public String              fullcut_price;
    public String              member_note;
    public Object              admin_note;
    public String              total_goods_price;
    public String              pay_price;
    public int                 order_shop_status;
    public String              shipping_status;
    public Object              coupon_info;
    public String              shipping_name;
    public String              shipping_code;
    public String              shipping;
    public float              duducard_price;
    public List<GoodsInfoBean> goodsInfo;

    public static class GoodsInfoBean implements Serializable {
        /**
         * id : 252
         * order_id : 199
         * shop_id : 11
         * goods_id : 1607
         * goods_name : 测试商品1
         * goods_attr_id : 405,407
         * goods_number : 1
         * price : 101.00
         * shipping_price : 0.00
         * give_integral : 0
         * invoice_no : 0
         * sku :
         * status : 0
         * goods_attr_value : 白色,123
         * logo : http://192.168.2.125/Public/Uploads/Goods/2017-11-09/5a040cf3d6128.jpg
         */

        public String id;
        public String order_id;
        public String shop_id;
        public int    goods_id;
        public String goods_name;
        public String goods_attr_id;
        public String goods_number;
        public float price;
        public float shipping_price;
        public String give_integral;
        public String invoice_no;
        public String sku;
        public int    status;
        public String goods_attr_value;
        public String logo;
        public int    afterSale;
        public int    after_status;//0没有售后,1正在售后,2售后已处理
    }
}
