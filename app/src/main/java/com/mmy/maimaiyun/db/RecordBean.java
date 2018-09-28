package com.mmy.maimaiyun.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * @创建者 lucas
 * @创建时间 2017/10/18 0018 10:52
 * @描述 TODO
 */
@Entity
public class RecordBean {
    @Id
    private Long   id;
    @Property(nameInDb = "good_id")
    private String good_id;
    @Property(nameInDb = "good_price")
    private String good_price;
    @Property(nameInDb = "is_collect")
    private int    is_collect;
    @Property(nameInDb = "record_time")
    private String    record_time;
    @Property(nameInDb = "shop_pic")
    private String shop_pic;
    @Property(nameInDb = "good_name")
    private String good_name;


    @Transient
    public boolean isDelete;


    @Generated(hash = 127411294)
    public RecordBean(Long id, String good_id, String good_price, int is_collect,
            String record_time, String shop_pic, String good_name) {
        this.id = id;
        this.good_id = good_id;
        this.good_price = good_price;
        this.is_collect = is_collect;
        this.record_time = record_time;
        this.shop_pic = shop_pic;
        this.good_name = good_name;
    }


    @Generated(hash = 96196931)
    public RecordBean() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getGood_id() {
        return this.good_id;
    }


    public void setGood_id(String good_id) {
        this.good_id = good_id;
    }


    public String getGood_price() {
        return this.good_price;
    }


    public void setGood_price(String good_price) {
        this.good_price = good_price;
    }


    public int getIs_collect() {
        return this.is_collect;
    }


    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }


    public String getRecord_time() {
        return this.record_time;
    }


    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }


    public String getShop_pic() {
        return this.shop_pic;
    }


    public void setShop_pic(String shop_pic) {
        this.shop_pic = shop_pic;
    }


    public String getGood_name() {
        return this.good_name;
    }


    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

}
