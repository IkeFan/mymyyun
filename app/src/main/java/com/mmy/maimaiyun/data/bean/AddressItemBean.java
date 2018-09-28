package com.mmy.maimaiyun.data.bean;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * @创建者 lucas
 * @创建时间 2017/10/7 0007 11:47
 * @描述 TODO
 */

public class AddressItemBean implements Serializable,Comparable{

    /**
     * address_id : 1
     * member_id : 2624
     * consignee : 56113
     * email :
     * country : 0
     * province : 北京市
     * city : 北京市
     * district : 东城区
     * twon : 0
     * address : 321321321
     * zipcode :
     * mobile : 2132132131
     * is_default : 0
     * is_pickup : 0
     */

    public String address_id;
    public String member_id;
    public String consignee;
    public String email;
    public String country;
    public String province;
    public String city;
    public String district;
    public String twon;
    public String address;
    public String zipcode;
    public String mobile;
    public String is_default;
    public String is_pickup;


    @Override
    public int compareTo(@NonNull Object o) {
        AddressItemBean bean = (AddressItemBean) o;
        return this.address_id.compareTo(bean.address_id);
    }
}
