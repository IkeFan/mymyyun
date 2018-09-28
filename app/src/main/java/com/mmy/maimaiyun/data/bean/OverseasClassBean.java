package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/21 0021 17:02
 * @描述 TODO
 */

public class OverseasClassBean {

    /**
     * id : 7
     * title : 海外购
     * start_time : 1508169600
     * end_time : 1509465540
     * topic_img : http://192.168.2.136/Public/Uploads/System/2017-10-18/59e732940909f.jpg
     * keywords : 全球大牌  随便购
     * description : adf1112
     * status : 2
     * subCate : [{"id":"3","activity_id":"7","cat_name":"分类一哈哈哈",
     * "cat_pic":"ActivityCat/2017-10-21/59eb039c96308.jpg"},{"id":"4","activity_id":"7","cat_name":"分类二32",
     * "cat_pic":"ActivityCat/2017-10-21/59eb0365b1be5.jpg"},{"id":"5","activity_id":"7","cat_name":"分类三",
     * "cat_pic":"ActivityCat/2017-10-21/59eb03876d9c6.jpg"}]
     * cat_pic : http://192.168.2.136/Public/Uploads/
     */

    public int id;
    public String            title;
    public String            start_time;
    public String            end_time;
    public String            topic_img;
    public String            keywords;
    public String            description;
    public String            status;
    public String            cat_pic;
    public List<SubCateBean> subCate;

}
