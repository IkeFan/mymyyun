package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/9/30 0030 10:26
 * @描述 TODO
 */

public class GoodsInfoBean {


    /**
     * id : 516
     * agent_id : 0
     * activity_id : 0
     * shop_id : 0
     * goods_name : Vinaera醒酒器
     * market_price : 0.00
     * shop_price : 762.00
     * goods_desc : Vinaera醒酒器
     * is_on_sale : 1
     * is_delete : 1
     * addtime : 0000-00-00 00:00:00
     * logo : http://192.168.2.125/Public/Uploads/goods/2017/07-29/597c5ef73c130.jpg
     * sm_logo : goods/2017/07-29/597c5ef73c130.jpg
     * mid_logo : goods/2017/07-29/597c5ef73c130.jpg
     * big_logo : goods/2017/07-29/597c5ef73c130.jpg
     * mbig_logo : goods/2017/07-29/597c5ef73c130.jpg
     * brand_id : 0
     * cat_id : 1090
     * type_id : 0
     * promote_price : 533.33
     * promote_start_date : 0000-00-00 00:00:00
     * promote_end_date : 0000-00-00 00:00:00
     * is_new :
     * is_recommend :
     * is_hot : 是
     * is_best :
     * sort_num : 50
     * is_floor : 是
     * is_updated : 0
     * people : 25
     * goods_content : &lt;p&gt;&lt;img src=&quot;/Public/upload/goods/2017/07-29/597c6003846ca.jpg&quot;
     * style=&quot;float:none;&quot; title=&quot;1.jpg&quot;/&gt;&lt;/p&gt;&lt;p&gt;&lt;img src=&quot;
     * /Public/upload/goods/2017/07-29/597c6003de847.jpg&quot; style=&quot;float:non
     * goods_particulars : http://192.168.2.125/Public/Uploads/&lt;p&gt;&lt;img src=&quot;
     * /Public/upload/goods/2017/07-29/597c6003846ca.jpg&quot; style=&quot;float:none;&quot; title=&quot;1.jpg&quot;
     * /&gt;&lt;/p&gt;&lt;p&gt;&lt;img src=&quot;/Public/upload/goods/2017/07-29/597c6003de847.jpg&quot; style=&quot;
     * float:non
     * collect_num : 1
     * collect_type : 1
     */

    public String id;
    public String member_id;
    public String member_name;
    public String agent_id;
    public String activity_id;
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
    public String is_updated;
    public String people;
    public String goods_content;
    public String goods_particulars;
    public String collect_num;
    public List<CouponsBean>   coupons;
    public int    collect_type;

    public static class CouponsBean{

        /**
         * id : 10
         * shop_id : 11
         * name : 1号店铺优惠券
         * type : 4
         * number : 200
         * remain_number : 198
         * send_start_time : 1509465600
         * send_end_time : 1519833600
         * use_start_time : 1509552000
         * use_end_time : 1523462400
         * add_time : 1509504230
         * status : 1
         * condition : [{"money":"5","full":"1"},{"money":"10","full":"100"},{"money":"50","full":"500"}]
         * isCash : 1
         */

        public String id;
        public String              shop_id;
        public String              name;
        public String              type;
        public String              number;
        public String              remain_number;
        public long              send_start_time;
        public long              send_end_time;
        public long              use_start_time;
        public long              use_end_time;
        public String              add_time;
        public String              status;
        public int                 isCash;
        public List<ConditionBean> condition;

        public static class ConditionBean {
            /**
             * money : 5
             * full : 1
             */

            public String money;
            public String full;
        }
    }
}
