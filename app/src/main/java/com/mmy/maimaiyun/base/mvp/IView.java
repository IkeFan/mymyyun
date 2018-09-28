package com.mmy.maimaiyun.base.mvp;

import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.data.bean.UserBean;

/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 18:05
 * @描述 activity 视图层
 */

public interface IView {
    void showLoading();
    void hideLoading();
    void showLoading(String msg);
    UserBean getUserBean();
    void setUserBean(UserBean bean);
    //打开一个activity
    <A extends BaseActivity> void openActivity(Class<A> activityClass);

    //打开一个activity并且传入参数 格式 ：key=value,key=value...
    <A extends BaseActivity> void openActivity(Class<A> activityClass, String param);

    //关闭当前activity
    void finishSelf();

    //查找并返回一个LoadingSmartLayout
    LoadingSmartLayout findLoadingSmartLayout();
}
