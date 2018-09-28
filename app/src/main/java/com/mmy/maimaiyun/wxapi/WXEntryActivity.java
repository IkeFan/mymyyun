package com.mmy.maimaiyun.wxapi;

import android.content.Intent;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.utils.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class WXEntryActivity extends BaseActivity<WXCustomPresenter> implements IWXAPIEventHandler, WXCustomView {
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI           api;
    private SweetAlertDialog mSweetAlertDialog;

    public static final int WX_LOGIN = 0;//微信登录
    public static final int WX_SHARE = 1;//微信分享
    public static       int ACTION   = WX_LOGIN;


    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerWXCustomComponent.builder()
                .appComponent(appComponent)
                .wXCustomModule(new WXCustomModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        if (ACTION == WX_LOGIN) {
            mSweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("登录中...");
            mSweetAlertDialog.show();
        }
        api = WXAPIFactory.createWXAPI(this, null);
        api.registerApp(Constants.WEIXIN_APPKEY);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentViewId() {
        return 0;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK://支付成功
                    if (ACTION == WX_LOGIN) {
                        SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                        mPresenter.getTokenOpenid(sendResp.code);
                    }
                    break;
                case BaseResp.ErrCode.ERR_COMM:
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                default://
                    mSweetAlertDialog.dismiss();
                    break;
            }
        }

        //分享授权结束后关闭界面
        if (ACTION == WX_SHARE)
            finish();
    }

    @Override
    public void authComplete() {
        mSweetAlertDialog.dismiss();
    }
}