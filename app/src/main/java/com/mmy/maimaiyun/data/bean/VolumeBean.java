package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 16:38
 * @描述 TODO
 */

public class VolumeBean {

    /**
     * id : 57
     * cat_name : 生鲜美食
     * parent_id : 55
     * cate_pic :
     * is_floor : 否
     * cat_pic : http://192.168.2.136/Public/Uploads/
     * goodsList : [{"id":"36","agent_id":"0","shop_id":"0","goods_name":"暗夜猎手","market_price":"5555.00",
     * "shop_price":"5.00","goods_desc":"","is_on_sale":"是","is_delete":"否","addtime":"2017-10-13 16:10:47",
     * "logo":"http://192.168.2.136/Public/Uploads/Goods/2017-10-13/59e0750674551.jpg",
     * "sm_logo":"http://192.168.2.136/Public/Uploads/Goods/2017-10-13/thumb_3_59e0750674551.jpg",
     * "mid_logo":"http://192.168.2.136/Public/Uploads/Goods/2017-10-13/thumb_2_59e0750674551.jpg",
     * "big_logo":"http://192.168.2.136/Public/Uploads/Goods/2017-10-13/thumb_1_59e0750674551.jpg",
     * "mbig_logo":"http://192.168.2.136/Public/Uploads/Goods/2017-10-13/thumb_0_59e0750674551.jpg","brand_id":"4",
     * "cat_id":"57","type_id":"0","promote_price":"0.00","promote_start_date":"0000-00-00 00:00:00",
     * "promote_end_date":"0000-00-00 00:00:00","is_new":"否","is_recommend":"否","is_hot":"否","is_best":"否",
     * "sort_num":"100","is_floor":"否","is_delete1":"0","is_updated":"0","people":"1000","goods_content":"vn",
     * "goods_particulars":"Goods/2017-10-13/59e0750715204.jpg"}]
     */

    public String id;
    public String              cat_name;
    public String              parent_id;
    public String              cate_pic;
    public String              is_floor;
    public String              cat_pic;
    public List<GoodsListBean> goodsList;

    public static class GoodsListBean {
        /**
         * id : 36
         * agent_id : 0
         * shop_id : 0
         * goods_name : 暗夜猎手
         * market_price : 5555.00
         * shop_price : 5.00
         * goods_desc :
         * is_on_sale : 是
         * is_delete : 否
         * addtime : 2017-10-13 16:10:47
         * logo : http://192.168.2.136/Public/Uploads/Goods/2017-10-13/59e0750674551.jpg
         * sm_logo : http://192.168.2.136/Public/Uploads/Goods/2017-10-13/thumb_3_59e0750674551.jpg
         * mid_logo : http://192.168.2.136/Public/Uploads/Goods/2017-10-13/thumb_2_59e0750674551.jpg
         * big_logo : http://192.168.2.136/Public/Uploads/Goods/2017-10-13/thumb_1_59e0750674551.jpg
         * mbig_logo : http://192.168.2.136/Public/Uploads/Goods/2017-10-13/thumb_0_59e0750674551.jpg
         * brand_id : 4
         * cat_id : 57
         * type_id : 0
         * promote_price : 0.00
         * promote_start_date : 0000-00-00 00:00:00
         * promote_end_date : 0000-00-00 00:00:00
         * is_new : 否
         * is_recommend : 否
         * is_hot : 否
         * is_best : 否
         * sort_num : 100
         * is_floor : 否
         * is_delete1 : 0
         * is_updated : 0
         * people : 1000
         * goods_content : vn
         * goods_particulars : Goods/2017-10-13/59e0750715204.jpg
         */

        public String id;
        public String agent_id;
        public String shop_id;
        public String goods_name;
        public String market_price;
        public String shop_price;
        public String goods_desc;
        public String is_on_sale;
        public String is_delete;
        public String addtime;
        public String logo;
        public String sm_logo;
        public String mid_logo;
        public String big_logo;
        public String mbig_logo;
        public String brand_id;
        public String cat_id;
        public String type_id;
        public String promote_price;
        public String promote_start_date;
        public String promote_end_date;
        public String is_new;
        public String is_recommend;
        public String is_hot;
        public String is_best;
        public String sort_num;
        public String is_floor;
        public String is_delete1;
        public String is_updated;
        public String people;
        public String goods_content;
        public String goods_particulars;
    }
}
