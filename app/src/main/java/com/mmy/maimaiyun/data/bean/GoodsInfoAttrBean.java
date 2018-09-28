package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/9/30 0030 17:06
 * @描述 TODO
 */

public class GoodsInfoAttrBean {


    public List<AttributeBean> attribute;
    public List<List<SpecBean>> spec;

    public static class AttributeBean {
        /**
         * id : 376
         * type : 0
         * attr_value : 2007
         * attr_id : 4
         * goods_id : 516
         * attr_name : 出厂日期
         * is_show_img : 0
         */

        public String id;
        public String type;
        public String attr_value;
        public String attr_id;
        public String goods_id;
        public String attr_name;
        public String is_show_img;
    }

    public static class SpecBean {
        /**
         * id : 380
         * type : 1
         * attr_value : 白色
         * attr_id : 1
         * goods_id : 516
         * attr_name : 颜色
         * is_show_img : 0
         */

        public String id;
        public String type;
        public String attr_value;
        public String attr_id;
        public String goods_id;
        public String attr_name;
        public String is_show_img;
    }
}
