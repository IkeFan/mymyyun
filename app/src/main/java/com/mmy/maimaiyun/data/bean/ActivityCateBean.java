package com.mmy.maimaiyun.data.bean;

/**
 * @创建者 lucas
 * @创建时间 2017/10/20 0020 10:25
 * @描述 活动数据:一级分类bean  泛型用是obj代表不获取下级分类
 */

public class ActivityCateBean<T> {

    /**
     * id : 8
     * title : 一品一县
     * start_time : 1508169600
     * end_time : 1509120000
     * topic_img : System/2017-10-19/59e863d2cc5f8.jpg
     * keywords : 一村一文化 一县一品牌
     * description : 1111
     * status : 2
     * subCate : [{"id":"6","activity_id":"8","cat_name":"分类一"}]
     */

    public String id;
    public String title;
    public String start_time;
    public String end_time;
    public String topic_img;
    public String keywords;
    public String description;
    public String status;
    public T      subCate;

}
