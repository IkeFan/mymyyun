package com.mmy.maimaiyun.model.shopping.presenter;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.model.main.model.MainModel;
import com.mmy.maimaiyun.utils.GlobalUtil;
import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.able.PayIBean;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.CheckOrderStatusBean;
import com.mmy.maimaiyun.data.bean.PayBean;
import com.mmy.maimaiyun.helper.PayHelper;
import com.mmy.maimaiyun.model.shopping.model.PayModel;
import com.mmy.maimaiyun.model.shopping.view.PayView;
import com.mmy.maimaiyun.wxapi.WXPayModel;

import java.lang.reflect.Type;
import java.util.Map;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 10:29
 * @描述 TODO
 */

public class PayPresenter extends BasePresenter<PayView> {
    private PayView mView;

    public static final int CHECK_RATE  = 500;//查询频率
    public              int CHECK_COUNT = 3;//查询次数
    public static final int SUCCESS     = 1;
    public static final int FAIL        = 0;
    public static final int PAYING      = -1;//支付中

    @Inject
    PayModel   mModel;
    @Inject
    MainModel mMainModel;


    @Inject
    PayHelper  mPayHelper;
    @Inject
    WXPayModel mWXPayModel;
    @Inject
    Handler    mHandler;

    private final BaseActivity mActivity;

    private PayHelper.OnPayListener mPayListener = data -> {
        Map<String, String> resData = (Map<String, String>) data;
        String status = resData.get("resultStatus");
        if ("6001".equals(status)) {
            //支付取消
            mHandler.post(() -> {
                Toast.makeText(App.getAppComponent().getApp(), "支付已取消", Toast.LENGTH_SHORT).show();
            });
            return;
        }
        if ("9000".equals(status)) {
            //支付成功
            //查询支付结果
            checkPay();
        }
    };
    private String mOrder_sn;

    @Inject
    public PayPresenter(PayView view) {
        mView = view;
        mActivity = (BaseActivity) view;
    }

    private BaseBean<PayBean> mData;

    public void pay(int payType, String order_sn, String order_aount) {
        switch (payType) {
            case PayHelper.WEIXIN:
                payWx(payType, order_sn, order_aount);
                break;
            case PayHelper.ZFB:
                payZFB(payType, order_sn, order_aount);
                break;
            case PayHelper.BALANCE:
                payBalance(payType, order_sn, order_aount);
                break;
            case PayHelper.BANK_CAR:
                break;
        }
    }

    private void payZFB(final int payType, String order_sn, String order_aount) {
        mOrder_sn = order_sn;
        mModel.loadAliPayInfo(new OnLoadDataListener<BaseBean<String>>() {
            @Override
            public void onResponse(String body, BaseBean<String> data, IBean iBean) {
                if (iBean.status == 1) {
                    PayIBean payIBean = new PayIBean();
                    payIBean.order_sn = data.data;
                    mPayHelper.setOnPayListener(mPayListener);
                    mPayHelper.pay(mActivity, payType, payIBean);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.hideLoading();
                mView.getOrderFail();
            }

            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<String>>() {
                }.getType();
            }
        }, mView.getUserBean().token, order_sn, order_aount);
    }

    private void payWx(final int payType, final String order_sn, String order_aount) {
        mModel.loadWxPayInfo(new OnLoadDataListener<BaseBean<PayBean>>() {
            @Override
            public void onResponse(String body, BaseBean<PayBean> data) {
                mData = data;
                mData.data.order_sn = order_sn;
                mPayHelper.pay(payType, mData.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.hideLoading();
                mView.getOrderFail();
            }

            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<PayBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, order_sn, order_aount);
    }

    private void payBalance(final int payType, final String order_sn, String order_aount) {
        mModel.loadBalancePayInfo(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data, IBean iBean) {
                super.onResponse(body, data, iBean);
                if (iBean.status == 1) {
                    //刷新用户信息
                    refreshUserInfo();
                    mView.finishSelf();

                }
                    Toast.makeText(mActivity, iBean.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.hideLoading();
                mView.getOrderFail();
            }

            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, order_sn, order_aount);
    }

    //查询支付结果
    private void checkPay() {
        mWXPayModel.checkPayStatus(new OnLoadDataListener<BaseBean<CheckOrderStatusBean>>() {
            @Override
            public void onResponse(String body, BaseBean<CheckOrderStatusBean> data, IBean iBean) {
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
                mView.hideLoading();
                mView.showLoading("支付结果查询失败!");
            }

            @Override
            public void onStart() {
                mView.showLoading("正在查询支付结果..");
            }

            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<CheckOrderStatusBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mOrder_sn);
    }

    private void refreshUserInfo() {
        mMainModel.getUserInfo(new OnLoadDataListener<BaseBean<UserBean>>() {
            @Override
            public void onResponse(String body, BaseBean<UserBean> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                GlobalUtil.getInstance().refreshUserInfo(data.data,mView);
//                mView.refreshUserInfo();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<UserBean>>() {
                }.getType();
            }
        }, mView.getUserBean().member_id);
    }
}
