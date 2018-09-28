package com.mmy.maimaiyun.wxapi;

import com.mmy.maimaiyun.base.mvp.IView;


/**
 * @创建者 lucas
 * @创建时间 2017/10/16 0016 10:43
 * @描述 TODO
 */

public interface WXPayView extends IView {
    void onResult(int status);
    void printLog(String log);
}
