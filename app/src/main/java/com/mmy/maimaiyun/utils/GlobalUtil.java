package com.mmy.maimaiyun.utils;

import android.text.TextUtils;

import com.mmy.maimaiyun.data.bean.EventBean;
import com.google.gson.Gson;
import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.base.mvp.IView;
import com.mmy.maimaiyun.data.bean.UserBean;

import org.greenrobot.eventbus.EventBus;

/**
 * @创建者 lucas
 * @创建时间 2017/8/17 0017 17:37
 * @描述 全局工具
 */

public class GlobalUtil {

    Gson mGson = App.getAppComponent().getGson();
    App  mApp  = App.getAppComponent().getApp();
    public static String downloadApkUrl = "";

    public static final String REFRESH_USER_INFO = "refresh_user_info";


    private static GlobalUtil mUtil;
    public static final String USER = "user";

    private GlobalUtil() {
    }

    public static GlobalUtil getInstance() {
        if (mUtil == null)
            synchronized (GlobalUtil.class) {
                if (mUtil == null)
                    mUtil = new GlobalUtil();
            }
        return mUtil;
    }

    //更新用户信息
    public void refreshUserInfo(UserBean bean,IView view) {
        String json = mGson.toJson(bean);
        SPUtils.getInstance().put(USER, json);
        view.setUserBean(bean);
        EventBus.getDefault().post(new EventBean(REFRESH_USER_INFO));
    }

    //清除用户信息缓存
    public void clearUserInfo() {
        SPUtils.getInstance().put(USER, "");
    }

    //判断用户是否存在本地缓存,并且刷新
    public boolean isExistUserInfo(IView view) {
        String string = SPUtils.getInstance().getString(USER);
        if (!TextUtils.isEmpty(string))
            view.setUserBean(mGson.fromJson(string, UserBean.class));
        return !TextUtils.isEmpty(string);
    }

    public void saveUserPwd(String pwd) {
        SPUtils.getInstance().put("pwd",pwd);
    }

    public String getUserPwd(){
        return SPUtils.getInstance().getString("pwd");
    }
}
