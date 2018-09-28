package com.mmy.maimaiyun.model.main.view;

import com.mmy.maimaiyun.base.mvp.IView;

/**
 * @创建者 lucas
 * @创建时间 2017/8/16 0016 17:48
 * @描述 TODO
 */

public interface MainView extends IView {
    void onLocationCompleted();//定位
    void onVersionChange(String pakPath);//更新版本
}
