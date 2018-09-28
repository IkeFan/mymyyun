package com.mmy.maimaiyun.model.personal.presenter;

import android.util.Log;
import android.widget.Toast;

import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.able.PayIBean;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.CheckOrderStatusBean;
import com.mmy.maimaiyun.data.bean.PayBean;
import com.mmy.maimaiyun.data.bean.RechargeBean;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.helper.PayHelper;
import com.mmy.maimaiyun.model.main.model.MainModel;
import com.mmy.maimaiyun.model.personal.model.RecharWithdrawalsModel;
import com.mmy.maimaiyun.model.personal.view.RecharWithdrawalsView;
import com.mmy.maimaiyun.model.shopping.model.PayModel;
import com.mmy.maimaiyun.utils.GlobalUtil;
import com.mmy.maimaiyun.wxapi.WXPayModel;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/11/17 0017 16:03
 * @描述
 */

public class RecharWithdrawalsPresenter extends BasePresenter<RecharWithdrawalsView> {
    private RecharWithdrawalsView mView;
    @Inject
    RecharWithdrawalsModel mModel;
    @Inject
    MainModel              mMainModel;
    @Inject
    PayHelper              mPayHelper;
    @Inject
    WXPayModel             mWXPayModel;
    @Inject
    PayModel               mPayModel;
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
            //提现成功,刷新用户数据
            refreshUserInfo();
            //查询支付结果
            checkPay();
        }
    };
    private String mOrder_sn;

    @Inject
    public RecharWithdrawalsPresenter(RecharWithdrawalsView view) {
        mView = view;
        mActivity = (BaseActivity) view;
    }

    public void submit(String bank_id, String money) {
        mModel.withdraw(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data, IBean iBean) {
                super.onResponse(body, data, iBean);
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
                if (iBean.status == 1) {
                    //提现成功,刷新用户数据
                    refreshUserInfo();
                }else {
                    mView.hideLoading();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading();
            }

            @Override
            public void onFailure(String body, Throwable t) {
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, bank_id, money);
    }

    //充值
    public void recharge(String type, String money) {
        mModel.rechargeMember(new OnLoadDataListener<BaseBean<RechargeBean>>() {
            @Override
            public void onResponse(String body, BaseBean<RechargeBean> data, IBean iBean) {
                if (iBean.status == 1) {
                    mOrder_sn = data.data.order_sn;
                    Toast.makeText(mApp, "正在打开支付界面", Toast.LENGTH_SHORT).show();
                    if (type.equals("wxpay")) {
                        getWxPayInfo(data.data.order_sn);
                        return;
                    }
                    if (type.equals("alipay")) {
                        payZFB(data.data.order_sn);
                    }
                } else
                    Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<RechargeBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, mView.getUserBean().member_id, money, type);
    }

    private void payZFB(String order_sn) {
        mOrder_sn = order_sn;
        mPayModel.loadAliPayInfo(new OnLoadDataListener<BaseBean<String>>() {
            @Override
            public void onResponse(String body, BaseBean<String> data, IBean iBean) {
                if (iBean.status == 1) {
                    PayIBean payIBean = new PayIBean();
                    payIBean.order_sn = data.data;
                    mPayHelper.setOnPayListener(mPayListener);
                    mPayHelper.pay(mActivity, PayHelper.ZFB, payIBean);
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
        }, mView.getUserBean().token, order_sn, null);
    }

    private void getWxPayInfo(String order_sn) {
        mPayModel.loadWxPayInfo(new OnLoadDataListener<BaseBean<PayBean>>() {
            @Override
            public void onResponse(String body, BaseBean<PayBean> data) {
                data.data.order_sn = order_sn;
                mPayHelper.pay(PayHelper.WEIXIN, data.data);
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mView.getOrderFail();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<PayBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token, order_sn, null);
    }

    public void refreshUserInfo() {
        mMainModel.getUserInfo(new OnLoadDataListener<BaseBean<UserBean>>() {
            @Override
            public void onResponse(String body, BaseBean<UserBean> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                GlobalUtil.getInstance().refreshUserInfo(data.data, mView);
                mView.refreshUserInfo();
                mView.finishSelf();
                mView.hideLoading();
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

    public static final int CHECK_RATE  = 500;//查询频率
    public              int CHECK_COUNT = 3;//查询次数
    public static final int SUCCESS     = 1;
    public static final int FAIL        = 0;
    public static final int PAYING      = -1;//支付中

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

}
