package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/11/16 0016 16:30
 * @描述
 */

public class SpreadCenterBean {


    /**
     * info : [{"id":"208","order_goods_id":"539","order_id":"482","shop_id":"0","goods_id":"298",
     * "goods_name":"零食休闲特产麻辣五香牛肉干120g ","member_id":"4241","level":"1","amount":"2.80","status":"0",
     * "add_time":"1516618047","order_sn":"201801221847242717","buy_nickname":"5.Gpx","buy_mobile":"13226333242"},
     * {"id":"202","order_goods_id":"537","order_id":"482","shop_id":"0","goods_id":"302",
     * "goods_name":"休闲零食小吃香麻辣牛肉干条80g","member_id":"4241","level":"1","amount":"0.54","status":"0",
     * "add_time":"1516618047","order_sn":"201801221847242717","buy_nickname":"5.Gpx","buy_mobile":"13226333242"},
     * {"id":"205","order_goods_id":"538","order_id":"482","shop_id":"0","goods_id":"268","goods_name":" 蓝美眉 蓝莓果汁
     * 紫色特惠装 245ml×24支/箱","member_id":"4241","level":"1","amount":"6.15","status":"0","add_time":"1516618047",
     * "order_sn":"201801221847242717","buy_nickname":"5.Gpx","buy_mobile":"13226333242"},{"id":"196",
     * "order_goods_id":"529","order_id":"475","shop_id":"0","goods_id":"424","goods_name":"德芙&amp;费列罗巧克力礼盒心意礼盒生日礼物",
     * "member_id":"4241","level":"1","amount":"8.96","status":"0","add_time":"1516369051",
     * "order_sn":"201801192137298550","buy_nickname":"5.Gpx","buy_mobile":"13226333242"},{"id":"193",
     * "order_goods_id":"527","order_id":"473","shop_id":"0","goods_id":"298","goods_name":"零食休闲特产麻辣五香牛肉干120g ",
     * "member_id":"4241","level":"1","amount":"0.70","status":"0","add_time":"1516348772",
     * "order_sn":"201801191559283901","buy_nickname":"5.Gpx","buy_mobile":"13226333242"},{"id":"187",
     * "order_goods_id":"525","order_id":"473","shop_id":"0","goods_id":"302","goods_name":"休闲零食小吃香麻辣牛肉干条80g",
     * "member_id":"4241","level":"1","amount":"0.54","status":"0","add_time":"1516348772",
     * "order_sn":"201801191559283901","buy_nickname":"5.Gpx","buy_mobile":"13226333242"},{"id":"184",
     * "order_goods_id":"524","order_id":"473","shop_id":"0","goods_id":"306","goods_name":"特色小吃香辣鸭掌100g ",
     * "member_id":"4241","level":"1","amount":"1.01","status":"0","add_time":"1516348772",
     * "order_sn":"201801191559283901","buy_nickname":"5.Gpx","buy_mobile":"13226333242"},{"id":"175",
     * "order_goods_id":"516","order_id":"470","shop_id":"0","goods_id":"421","goods_name":"棒棒糖巧克力礼盒情人圣诞节生日礼物送女友",
     * "member_id":"4241","level":"1","amount":"13.44","status":"0","add_time":"1516264937",
     * "order_sn":"201801181642162009","buy_nickname":"5.Gpx","buy_mobile":"13226333242"},{"id":"163",
     * "order_goods_id":"512","order_id":"468","shop_id":"0","goods_id":"268","goods_name":" 蓝美眉 蓝莓果汁 紫色特惠装
     * 245ml×24支/箱","member_id":"4241","level":"1","amount":"6.15","status":"0","add_time":"1516241865",
     * "order_sn":"201801181017427982","buy_nickname":"5.Gpx","buy_mobile":"13226333242"},{"id":"161",
     * "order_goods_id":"510","order_id":"466","shop_id":"0","goods_id":"298","goods_name":"零食休闲特产麻辣五香牛肉干120g ",
     * "member_id":"4241","level":"2","amount":"0.70","status":"0","add_time":"1516174635",
     * "order_sn":"201801171537102009","buy_nickname":"Abc","buy_mobile":"13510244227"}]
     * frozen_distribute : 120.50
     * first_num : 4
     * second_num : 0
     */

    public String frozen_distribute;
    public String         first_num;
    public String         second_num;
    public List<InfoBean> info;

    public static class InfoBean {
        /**
         * id : 208
         * order_goods_id : 539
         * order_id : 482
         * shop_id : 0
         * goods_id : 298
         * goods_name : 零食休闲特产麻辣五香牛肉干120g
         * member_id : 4241
         * level : 1
         * amount : 2.80
         * status : 0
         * add_time : 1516618047
         * order_sn : 201801221847242717
         * buy_nickname : 5.Gpx
         * buy_mobile : 13226333242
         */

        public String id;
        public String order_goods_id;
        public String order_id;
        public String shop_id;
        public String goods_id;
        public String goods_name;
        public String member_id;
        public String level;
        public String amount;
        public int status;
        public long add_time;
        public String order_sn;
        public String buy_nickname;
        public String buy_mobile;
    }
}
