package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/16 0016 17:55
 * @描述 TODO
 */

public class ScreeningBean {
    /**
     * key : brand_id
     * name : 品牌
     * content : [{"id":"2","name":"苹果","site_url":"","logo":"Brand/2015-10-13/561cc92ba6c33.jpg"},
     * {"id":"6","name":"酷派","site_url":"","logo":""}]
     * type : 0
     */

    public String            key;
    public String            name;
    public int               type;
    public List<ContentBean> content;

    public static class ContentBean {
        /**
         * id : 2
         * name : 苹果
         * site_url :
         * logo : Brand/2015-10-13/561cc92ba6c33.jpg
         */

        public String id;
        public String name;
        public String site_url;
        public String logo;
    }

}
