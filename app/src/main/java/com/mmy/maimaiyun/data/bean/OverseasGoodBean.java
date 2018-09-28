package com.mmy.maimaiyun.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/21 0021 17:10
 * @描述 TODO
 */

public class OverseasGoodBean {

    /**
     * id : 7
     * title : 海外购
     * start_time : 1508169600
     * end_time : 1509465540
     * topic_img : http://192.168.2.136/Public/Uploads/System/2017-10-18/59e732940909f.jpg
     * keywords : 全球大牌  随便购
     * description : adf1112
     * status : 2
     * subCate : [{"id":"3","activity_id":"7","cat_name":"分类一哈哈哈","cat_pic":"ActivityCat/2017-10-21/59eb0f176fe1d
     * .jpg","subGoodsInfo":[{"id":"1600","cat_id":"1335","agent_id":"0","shop_id":"0","type_id":"0","brand_id":"0",
     * "goods_name":"传统广式月饼纯手工制作零添加剂 红豆沙蛋黄净含量480克","market_price":"168.00","shop_price":"168.00","goods_desc":"",
     * "logo":"http://192.168.2.136/Public/Uploads/goods/2017/09-20/59c2268b528d2.jpg","is_on_sale":"1","is_new":"1",
     * "is_hot":"0","is_best":"0","is_recommend":"0","is_floor":"1","is_delete":"1","is_updated":"0","sort_num":"50",
     * "comment_num":"0","people":"12","goods_content":"&lt;p&gt;&lt;img src=&quot;
     * /Public/upload/goods/2017/09-20/59c226c0e9381.jpg&quot; style=&quot;float:none;&quot; title=&quot;
     * 48802165604073393_副本_副本.jpg&quot;/&gt;&lt;/p&gt;&lt;p&gt;&lt;img src=&quot;
     * /Public/upload/goods/2017/09-20/59c226c1b7513.jpg&quot;","goods_particulars":"&lt;p&gt;&lt;img src=&quot;
     * /Public/upload/goods/2017/09-20/59c226c0e9381.jpg&quot; style=&quot;float:none;&quot; title=&quot;
     * 48802165604073393_副本_副本.jpg&quot;/&gt;&lt;/p&gt;&lt;p&gt;&lt;img src=&quot;
     * /Public/upload/goods/2017/09-20/59c226c1b7513.jpg&quot;","addtime":"0000-00-00 00:00:00"}]}]
     * cat_pic : http://192.168.2.136/Public/Uploads/
     */

    public String id;
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
