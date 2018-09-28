package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/12/1 0001 16:28
 * @描述 TODO
 */

public class ActivityInfoBean {

    /**
     * id : 8
     * title : 一品一县
     * start_time : 1508169600
     * end_time : 1523721600
     * topic_img : http://www.rt99.cn/Public/Uploads/System/2017-10-20/59e969e94b5d1.jpg
     * keywords : 一村一文化 一县一品牌
     * description : 1111
     * status : 2
     * subCate : [{"id":"6","activity_id":"8","cat_name":"分类一","cat_pic":null},{"id":"7","activity_id":"8","cat_name":"分类二","cat_pic":null}]
     */

    public int               id;
    public String            title;
    public String            start_time;
    public String            end_time;
    public String            topic_img;
    public String            keywords;
    public String            description;
    public String            status;
    public List<SubCateBean> subCate;
}
