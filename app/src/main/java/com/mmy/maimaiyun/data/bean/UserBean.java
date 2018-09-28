package com.mmy.maimaiyun.data.bean;


import com.mmy.maimaiyun.base.able.IBean;

/**
 * @创建者 lucas
 * @创建时间 2017/8/15 0015 16:44
 * @描述
 */
public class UserBean extends IBean {


    /**
     * member_id : 2624
     * nickname : null
     * password : 27316fd19568144f8def2d36c87d5db4
     * email :
     * email_validated : 0
     * phone : 18825204205
     * mobile_validated : 0
     * sex : 0
     * birthday : 0
     * amount : 84.25//余额
     * frozen_money : 0.00
     * distribut_money : 0.00
     * rank_points : 0
     * pay_points : 0//积分
     * address_id : 0
     * reg_time : 0
     * last_login : 0
     * last_ip :
     * qq :
     * oauth :
     * openid : null
     * head_pic : null
     * province : 0
     * city : 0
     * district : 0
     * level : 1
     * discount : 1.00
     * total_amount : 0.00
     * is_lock : 0
     * is_distribut : 0
     * first_leader : 0
     * second_leader : 0
     * third_leader : 0
     * token : 150760012459dc26fc0dffb
     * salt : 59ccb84c801bc
     */

    public String member_id;
    public String nickname;
    public String password;
    public String email;
    public String email_validated;
    public String phone;
    public String mobile_validated;
    public int sex;
    public String birthday;
    public float member_money;
    public float frozen_money;
    public String distribut_money;
    public String rank_points;
    public int pay_points;
    public String address_id;
    public long reg_time;
    public String last_login;
    public String last_ip;
    public String qq;
    public String oauth;
    public String openid;
    public String head_pic;
    public int province;
    public int city;
    public int district;
    public String level;
    public String discount;
    public String total_amount;
    public String is_lock;
    public String is_distribut;
    public String first_leader;
    public String second_leader;
    public String third_leader;
    public String token;
    public String salt;
    public String invite_code;
    public String headimgurl;
    public int is_dudu;
    public int is_audit;//0未认证，1认证中，2已认证
    public float mcdull;

    public String getAuthStatusName(){
        String[] auths = {"未认证","认证中","已认证"};
        return auths[is_audit];
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "member_id='" + member_id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", email_validated='" + email_validated + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile_validated='" + mobile_validated + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", amount=" + member_money +
                ", frozen_money='" + frozen_money + '\'' +
                ", distribut_money='" + distribut_money + '\'' +
                ", rank_points='" + rank_points + '\'' +
                ", pay_points=" + pay_points +
                ", address_id='" + address_id + '\'' +
                ", reg_time=" + reg_time +
                ", last_login='" + last_login + '\'' +
                ", last_ip='" + last_ip + '\'' +
                ", qq='" + qq + '\'' +
                ", oauth='" + oauth + '\'' +
                ", openid='" + openid + '\'' +
                ", head_pic='" + head_pic + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", level='" + level + '\'' +
                ", discount='" + discount + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", is_lock='" + is_lock + '\'' +
                ", is_distribut='" + is_distribut + '\'' +
                ", first_leader='" + first_leader + '\'' +
                ", second_leader='" + second_leader + '\'' +
                ", third_leader='" + third_leader + '\'' +
                ", token='" + token + '\'' +
                ", salt='" + salt + '\'' +
                ", invite_code='" + invite_code + '\'' +
                '}';
    }
}
