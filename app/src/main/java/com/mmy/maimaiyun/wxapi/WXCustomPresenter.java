package com.mmy.maimaiyun.wxapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.WXBean;
import com.mmy.maimaiyun.data.bean.WXUserInfoBean;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/11/4 0004 10:38
 * @描述
 */

public class WXCustomPresenter extends BasePresenter<WXCustomView> {
    private WXCustomView mView;

    @Inject
    WXCustomModel mModel;
    @Inject
    Gson          mGson;

    @Inject
    public WXCustomPresenter(WXCustomView view) {
        mView = view;
    }

    public void getTokenOpenid(String code) {
        mModel.getTokenOpenid(new OnLoadDataListener<WXBean>() {
            @Override
            public void onResponse(String body, WXBean data) {
                //获取用户个人信息
                WXBean bean = mGson.fromJson(body, WXBean.class);
                getWxUserInfo(data.access_token,data.openid);
            }

            @Override
            public void onFailure(String body, Throwable t) {
                //获取用户个人信息
                WXBean bean = mGson.fromJson(body, WXBean.class);
                getWxUserInfo(bean.access_token,bean.openid);
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<WXBean>(){}.getType();
            }
        },code);
    }

    public void getWxUserInfo(String token, String openid){
        mModel.getWxUserInfo(new OnLoadDataListener<WXUserInfoBean>() {
            @Override
            public void onResponse(String body, WXUserInfoBean data) {
                WXUserInfoBean bean = mGson.fromJson(body, WXUserInfoBean.class);
                mView.authComplete();
                mView.finishSelf();
                EventBus.getDefault().post(bean);
            }

            @Override
            public void onFailure(String body, Throwable t) {
                WXUserInfoBean bean = mGson.fromJson(body, WXUserInfoBean.class);
                mView.authComplete();
                mView.finishSelf();
                EventBus.getDefault().post(bean);
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<WXUserInfoBean>(){}.getType();
            }
        },token,openid);
    }
}
