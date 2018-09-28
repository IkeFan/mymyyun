package com.mmy.maimaiyun.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.mmy.maimaiyun.base.able.PayIBean;
import com.mmy.maimaiyun.data.bean.PayBean;
import com.mmy.maimaiyun.utils.Constants;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 11:02
 * @描述 支付帮助类
 */

public class PayHelper {
    public static final int WEIXIN   = 0;
    public static final int ZFB      = 1;
    public static final int BANK_CAR = 2;
    public static final int BALANCE = 3;
    private IWXAPI msgApi;

    public static String order_sn = "";
    private OnPayListener mListener;
    @Inject
    Handler mHandler;

    @Inject
    public PayHelper() {
    }

    public void init(Context context) {
        //微信
        msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp(Constants.WEIXIN_APPKEY);
    }

    @Deprecated
    public void pay(int type, PayIBean data) {
        switch (type) {
            case WEIXIN:
                payWeixin(data);
                break;
        }
    }

    public void pay(Activity activity, int type, PayIBean data) {
        switch (type) {
            case WEIXIN:
                payWeixin(data);
                break;
            case ZFB:
                aliPay(activity, data);
                break;
        }
    }

    private void aliPay(Activity activity, PayIBean data) {
        Observable.just(data)
                .subscribeOn(Schedulers.newThread())
                .subscribe(payIBean -> {
                    PayTask payTask = new PayTask(activity);
                    Map<String, String> resData = payTask.payV2(payIBean.order_sn, true);
                    if (mListener != null) {
                        mListener.onSuccess(resData);
                    }
                    for (String s : resData.keySet()) {
                        String s1 = resData.get(s);
                        Log.d("PayHelper", s + ":" + s1);
                    }
                });
    }

    private void payWeixin(PayIBean data) {
        order_sn = data.order_sn;
        PayBean payBean = (PayBean) data;
        PayReq request = new PayReq();
        request.appId = Constants.WEIXIN_APPKEY;
        request.partnerId = payBean.mch_id;
        request.prepayId = payBean.prepay_id;
        request.packageValue = "Sign=WXPay";
        request.nonceStr = payBean.nonce_str;
        request.timeStamp = payBean.timestamp;
        request.sign = payBean.sign;
        msgApi.sendReq(request);
    }

    public void setOnPayListener(OnPayListener listener) {
        mListener = listener;
    }

    public interface OnPayListener {
        void onSuccess(Object data);
    }
}
