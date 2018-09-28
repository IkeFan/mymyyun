package com.mmy.maimaiyun.base.mvp;

import android.os.Handler;

import com.mmy.maimaiyun.App;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017/8/11 0011 17:43
 * @描述 基本逻辑层
 */

public class BasePresenter<V extends IView> {
    private V mV;

    @Inject
    protected App mApp;

    @Inject
    protected Handler mHandler;


    //关联视图
    public void attach(V v) {

        mV = v;
    }

    //解除关联
    public void detach(){
        mV = null;
    }

    //判断是否关联
    public boolean isAttached(){
        return mV!=null;
    }



}
