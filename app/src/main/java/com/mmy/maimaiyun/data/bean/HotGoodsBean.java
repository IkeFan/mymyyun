package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/9 0009 9:32
 * @描述 TODO
 */

public class HotGoodsBean {


    public List<ScreeningBean> screening;
    public List<GoodsBean> goods;

    public static class GoodsBean {
        /**
         * id : 516
         * logo : http://192.168.2.240/Public/Uploads//Public/upload/goods/2017/07-29/597c5ef73c130.jpg
         * goods_name : Vinaera醒酒器
         * shop_price : 762.00
         */

        public String id;
        public String logo;
        public String goods_name;
        public String shop_price;
    }
}
