package com.mmy.maimaiyun.data.bean;

/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 13:52
 * @描述 TODO
 */

public class EventBean {
    String msg;

    public int getAction() {
        return action;
    }

    int action;

    public EventBean() {
    }

    public EventBean(String msg) {
        this.msg = msg;
    }

    public EventBean(String msg, int action) {
        this.msg = msg;
        this.action = action;
    }

    public String getMeg(){
        return msg;
    }
}
