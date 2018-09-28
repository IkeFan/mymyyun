package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/9/30 0030 16:09
 * @描述 TODO
 */

public class ShopCarListBean {


    /**
     * shopInfo : 麦麦云自营
     * shopId : 0
     * cartInfo : [{"id":"13","shop_id":"0","goods_id":"518","goods_attr_id":"","goods_number":"1",
     * "member_id":"2634","goods_name":"Mistine泰国羽翼丝滑口红持久保湿滋润 不脱色咬唇妆",
     * "logo":"http://192.168.2.125/Public/Uploads/goods/2017/07-31/597e87fc41e99.jpg","market_price":"35.00",
     * "shop_price":"28.00","fullcut_id":"0","store_num":"0"}]
     */

    public String             shopInfo;
    public String             shopId;
    public List<CartInfoBean> cartInfo;
    public boolean            isCheck;
    public boolean            isDel;

    public static class CartInfoBean {
        /**
         * id : 13
         * shop_id : 0
         * goods_id : 518
         * goods_attr_id :
         * goods_number : 1
         * member_id : 2634
         * goods_name : Mistine泰国羽翼丝滑口红持久保湿滋润 不脱色咬唇妆
         * logo : http://192.168.2.125/Public/Uploads/goods/2017/07-31/597e87fc41e99.jpg
         * market_price : 35.00
         * shop_price : 28.00
         * fullcut_id : 0
         * store_num : 0
         */

        public String  id;
        public String  shop_id;
        public String  goods_id;
        public String  goods_attr_id;
        public int     goods_number;
        public String  member_id;
        public String  goods_name;
        public String  logo;
        public String  market_price;
        public float   shop_price;
        public String  fullcut_id;
        public String  store_num;
        public String  goods_attr_value;
        public boolean isChecked;
        public boolean isDel;
    }
}
