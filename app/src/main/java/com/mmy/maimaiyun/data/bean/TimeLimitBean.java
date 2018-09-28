package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/12 0012 15:34
 * @描述 TODO
 */

public class TimeLimitBean {


    /**
     * flashsale : {"id":"9","pid":"0","shop_id":"0","name":"每日抢购","desc":"每日抢购","logo":"","start_time":"1516896000",
     * "end_time":"1516982400","discount":"95","flash_spec":"1"}
     * today : [{"id":"386","logo":"http://www.ct1212.com/Public/Uploads/Goods/2017-12-29/5a45ebdf3dc03.jpg",
     * "shop_id":"0","goods_name":"善味阁鲜卤鸭脖102g卤制美味 零食特产小吃 真空包装","shop_price":"14.00","market_price":"18.00",
     * "flashsale_start":"1516896000","flashsale_end":"1516982400"},{"id":"387","logo":"http://www
     * .ct1212.com/Public/Uploads/Goods/2017-12-29/5a45f4171dbcb.jpg","shop_id":"0",
     * "goods_name":"善味阁秘制猪蹄125g熟食秘制猪蹄秘制真空包装肉类休闲零食小吃","shop_price":"25.00","market_price":"30.00",
     * "flashsale_start":"1516896000","flashsale_end":"1516982400"},{"id":"388","logo":"http://www
     * .ct1212.com/Public/Uploads/Goods/2017-12-29/5a45f6329dd1a.jpg","shop_id":"0",
     * "goods_name":"善味阁鲜卤鸭锁骨125g鸭架鸭肉类肉干零食独立包装","shop_price":"15.50","market_price":"19.00",
     * "flashsale_start":"1516896000","flashsale_end":"1516982400"}]
     * tomorrow : [{"id":"421","logo":"http://www.ct1212.com/Public/Uploads/Goods/2018-01-09/5a54924f7cb1b.jpg",
     * "shop_id":"0","goods_name":"棒棒糖巧克力礼盒情人圣诞节生日礼物送女友","shop_price":"210.00","market_price":"252.00",
     * "flashsale_start":"1516982400","flashsale_end":"1517068800"},{"id":"422","logo":"http://www
     * .ct1212.com/Public/Uploads/Goods/2018-01-09/5a54941787682.jpg","shop_id":"0","goods_name":"混合坚果大礼包孕妇干果仁零食组合",
     * "shop_price":"110.00","market_price":"132.00","flashsale_start":"1516982400","flashsale_end":"1517068800"},
     * {"id":"423","logo":"http://www.ct1212.com/Public/Uploads/Goods/2018-01-09/5a54959547835.jpg","shop_id":"0",
     * "goods_name":"牛肉零食大礼包12种口味麻辣牛肉牛肉粒牛肉干卤汁牛肉大礼包","shop_price":"190.00","market_price":"228.00",
     * "flashsale_start":"1516982400","flashsale_end":"1517068800"},{"id":"424","logo":"http://www
     * .ct1212.com/Public/Uploads/Goods/2018-01-09/5a5497478c6f2.jpg","shop_id":"0","goods_name":"德芙&amp;
     * 费列罗巧克力礼盒心意礼盒生日礼物","shop_price":"160.00","market_price":"192.00","flashsale_start":"1516982400",
     * "flashsale_end":"1517068800"}]
     */

    public FlashsaleBean      flashsale;
    public List<GoodInfo>     today;
    public List<GoodInfo> tomorrow;

    public static class FlashsaleBean {
        /**
         * id : 9
         * pid : 0
         * shop_id : 0
         * name : 每日抢购
         * desc : 每日抢购
         * logo :
         * start_time : 1516896000
         * end_time : 1516982400
         * discount : 95
         * flash_spec : 1
         */

        public String id;
        public String pid;
        public String shop_id;
        public String name;
        public String desc;
        public String logo;
        public String start_time;
        public long end_time;
        public String discount;
        public String flash_spec;
    }

    public static class GoodInfo {
        /**
         * id : 386
         * logo : http://www.ct1212.com/Public/Uploads/Goods/2017-12-29/5a45ebdf3dc03.jpg
         * shop_id : 0
         * goods_name : 善味阁鲜卤鸭脖102g卤制美味 零食特产小吃 真空包装
         * shop_price : 14.00
         * market_price : 18.00
         * flashsale_start : 1516896000
         * flashsale_end : 1516982400
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
