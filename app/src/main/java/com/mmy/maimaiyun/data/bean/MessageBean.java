package com.mmy.maimaiyun.data.bean;

/**
 * @创建者 lucas
 * @创建时间 2017/10/19 0019 17:03
 * @描述 TODO
 */

public class MessageBean {

    public int icon;
    public String title;
    public String msg;
    public long time;
    public int count;

    public MessageBean(int icon,String title, String msg, long time, int count) {
        this.icon = icon;
        this.title = title;
        this.msg = msg;
        this.time = time;
        this.count = count;
    }
}
