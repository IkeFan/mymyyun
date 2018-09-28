package com.mmy.maimaiyun.wxapi;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.CheckOrderStatusBean;
import com.mmy.maimaiyun.helper.PayHelper;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/10/16 0016 10:44
 * @描述 TODO
 */

public class WXPayPresenter extends BasePresenter<WXPayView> {
    private WXPayView mView;

    public static final int SUCCESS = 1;
    public static final int FAIL    = 0;
    public static final int PAYING  = -1;//支付中

    public static final int CHECK_RATE  = 1000;//查询频率
    public              int CHECK_COUNT = 3;//查询次数


    @Inject
    WXPayModel mModel;

    @Inject
    public WXPayPresenter(WXPayView view) {
        mView = view;
    }

    //查询支付结果
    public void checkPay() {
        String order_sn = PayHelper.order_sn;
        if (TextUtils.isEmpty(order_sn))
            return;
        mModel.checkPayStatus(new OnLoadDataListener<BaseBean<CheckOrderStatusBean>>() {
            @Override
            public void onResponse(String body, BaseBean<CheckOrderStatusBean> data) {
                Log.d("WXPayPresenter", "支付结果查询剩余次数:" + CHECK_COUNT);
                if (data.data.pay_status != SUCCESS) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            CHECK_COUNT--;
                            if (CHECK_COUNT == 0) {
                                mHandler.removeCallbacks(this);
                                mView.onResult(FAIL);
                                return;
                            }
                            checkPay();
                        }
                    }, CHECK_RATE);
                }
                if (data.data.pay_status >= 1)
                    mView.onResult(SUCCESS);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<CheckOrderStatusBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, order_sn);
    }


}
