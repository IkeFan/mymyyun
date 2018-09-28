package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/1/18 0018 17:28
 * @描述 TODO
 */

public class NewGoodBean {

    /**
     * goods : [{"id":"63","cat_id":"636","agent_id":"0","shop_id":"3","type_id":"16","spec_id":"0","brand_id":"50",
     * "fullcut_id":"0","logistics_id":"0","goods_name":"桂山缘自发热方便食品茶树菇排骨汤9罐*280克","keywords":"桂山缘 自发热 方便食品 茶树菇排骨汤",
     * "market_price":"326.40","shop_price":"272.00","goods_desc":"",
     * "logo":"images/201707/goods_img/63_G_1499804526057.jpg","is_on_sale":"1","is_new":"1","is_hot":"1",
     * "is_best":"0","is_recommend":"1","is_floor":"1","is_delete":"1","is_updated":"0","sort_num":"3",
     * "store_num":"1000","comment_num":"0","people":"2","ban_area_names":null,"ban_area_ids":null,
     * "goods_content":"&lt;p&gt;&lt;img alt=&quot;&quot; src=&quot;
     * /includes/kindeditor/php/../../../images/upload/image/20170712/20170712121853_32208.jpg&quot;/&gt;&lt;img
     * alt=&quot;&quot; src=&quot;/includes/kindeditor/php/../../../images/upload/image/20170712
     * /20170712121853_99701.jpg&quot;/&gt;&lt;img alt=&quot;&quot; src=&quot;
     * /includes/kindeditor/php/../../../images/upload/image/20170712/20170712121854_87710.jpg&quot;/&gt;&lt;img
     * alt=&quot;&quot; src=&quot;/includes/kindeditor/php/../../../images/upload/image/20170712
     * /20170712121855_88379.jpg&quot;/&gt;&lt;img alt=&quot;&quot; src=&quot;
     * /includes/kindeditor/php/../../../images/upload/image/20170712/20170712121938_68645.jpg&quot;/&gt;&lt;/p&gt;",
     * "goods_particulars":null,"addtime":"1499804526","is_distribute":"0","distribute_rate":"0","is_audit":"1",
     * "coupon_id":"0","flashsale_id":"0","give_integral":"0","integral_rate":"0","give_mcdull":"0"}]
     * _page_title : 新品推出
     * param : is_new
     */

    public String _page_title;
    public String          param;
    public List<GoodsBean> goods;

    public static class GoodsBean {
        /**
         * id : 63
         * cat_id : 636
         * agent_id : 0
         * shop_id : 3
         * type_id : 16
         * spec_id : 0
         * brand_id : 50
         * fullcut_id : 0
         * logistics_id : 0
         * goods_name : 桂山缘自发热方便食品茶树菇排骨汤9罐*280克
         * keywords : 桂山缘 自发热 方便食品 茶树菇排骨汤
         * market_price : 326.40
         * shop_price : 272.00
         * goods_desc :
         * logo : images/201707/goods_img/63_G_1499804526057.jpg
         * is_on_sale : 1
         * is_new : 1
         * is_hot : 1
         * is_best : 0
         * is_recommend : 1
         * is_floor : 1
         * is_delete : 1
         * is_updated : 0
         * sort_num : 3
         * store_num : 1000
         * comment_num : 0
         * people : 2
         * ban_area_names : null
         * ban_area_ids : null
         * goods_content : &lt;p&gt;&lt;img alt=&quot;&quot; src=&quot;
         * /includes/kindeditor/php/../../../images/upload/image/20170712/20170712121853_32208.jpg&quot;/&gt;&lt;img
         * alt=&quot;&quot; src=&quot;/includes/kindeditor/php/../../../images/upload/image/20170712
         * /20170712121853_99701.jpg&quot;/&gt;&lt;img alt=&quot;&quot; src=&quot;
         * /includes/kindeditor/php/../../../images/upload/image/20170712/20170712121854_87710.jpg&quot;/&gt;&lt;img
         * alt=&quot;&quot; src=&quot;/includes/kindeditor/php/../../../images/upload/image/20170712
         * /20170712121855_88379.jpg&quot;/&gt;&lt;img alt=&quot;&quot; src=&quot;
         * /includes/kindeditor/php/../../../images/upload/image/20170712/20170712121938_68645.jpg&quot;/&gt;&lt;/p&gt;
         * goods_particulars : null
         * addtime : 1499804526
         * is_distribute : 0
         * distribute_rate : 0
         * is_audit : 1
         * coupon_id : 0
         * flashsale_id : 0
         * give_integral : 0
         * integral_rate : 0
         * give_mcdull : 0
         */

        public String id;
        public String cat_id;
        public String agent_id;
        public String shop_id;
        public String type_id;
        public String spec_id;
        public String brand_id;
        public String fullcut_id;
        public String logistics_id;
        public String goods_name;
        public String keywords;
        public String market_price;
        public String shop_price;
        public String goods_desc;
        public String logo;
        public String is_on_sale;
        public String is_new;
        public String is_hot;
        public String is_best;
        public String is_recommend;
        public String is_floor;
        public String is_delete;
        public String is_updated;
        public String sort_num;
        public String store_num;
        public String comment_num;
        public String people;
        public Object ban_area_names;
        public Object ban_area_ids;
        public String goods_content;
        public Object goods_particulars;
        public String addtime;
        public String is_distribute;
        public String distribute_rate;
        public String is_audit;
        public String coupon_id;
        public String flashsale_id;
        public String give_integral;
        public String integral_rate;
        public String give_mcdull;
    }
}
