package com.mmy.maimaiyun.data.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/30 0030 11:15
 * @描述
 */

public class ParseCityBean implements IPickerViewData {


    /**
     * name : 北京市
     * city : [{"name":"北京市","areas":[{"name":"东城区"}]}]
     */
    public int            id;
    public String         name;
    public int            type;
    public List<CityBean> city;

    @Override
    public String getPickerViewText() {
        return name;
    }

    public static class CityBean {
        /**
         * name : 北京市
         * areas : [{"name":"东城区"}]
         */
        public int            id;
        public int            parent_id;
        public int            type;
        public String         name;
        public List<AreaBean> areas;

        public static class AreaBean {
            /**
             * name : 东城区
             */
            public int    id;
            public int    parent_id;
            public int    type;
            public String name;
        }
    }
}
