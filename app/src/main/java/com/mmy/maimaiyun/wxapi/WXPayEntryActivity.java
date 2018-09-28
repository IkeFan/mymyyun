package com.mmy.maimaiyun.wxapi;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.EventBean;
import com.mmy.maimaiyun.utils.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class WXPayEntryActivity extends BaseActivity<WXPayPresenter> implements IWXAPIEventHandler, WXPayView {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    @Bind(R.id.title_center_text)
    TextView   titleCenterText;
    @Bind(R.id.icon)
    ImageView  icon;
    @Bind(R.id.msg)
    TextView   msg;
    @Bind(R.id.log)
    TextView mLog;


    private IWXAPI           api;
    private SweetAlertDialog dialog;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerWXPayComponent.builder()
                .appComponent(appComponent)
                .wXPayModule(new WXPayModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("等待支付结果..");
        dialog.show();
        api = WXAPIFactory.createWXAPI(this, Constants.WEIXIN_APPKEY);
        api.handleIntent(getIntent(), this);
        titleCenterText.setText("支付结果");
    }

    @Override
    public void initData() {
    }

    @Override
    public int getContentViewId() {
        return R.layout.pay_result;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        dialog.dismiss();
        pay(resp);
    }

    private void pay(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK://支付成功
                    //查询支付结果
                    mPresenter.checkPay();
                    break;
                case BaseResp.ErrCode.ERR_COMM:
                    icon.setImageResource(R.mipmap.ic_error);
                    msg.setText("支付借口异常！");
                    Log.e("lucas", "支付借口异常");
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://支付取消
                    Toast.makeText(this, "支付已取消", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                default://支付失败
                    icon.setImageResource(R.mipmap.ic_error);
                    msg.setText("支付失败！");
                    break;
            }
        }
    }

    @Override
    public void onResult(int status) {
        switch (status) {
            case WXPayPresenter.FAIL:
                icon.setImageResource(R.mipmap.ic_error);
                msg.setText("支付失败！");
                break;
            case WXPayPresenter.SUCCESS:
                icon.setImageResource(R.mipmap.pay_success);
                msg.setText("支付成功！");
                EventBus.getDefault().post(new EventBean("pay_success"));
                finishSelf();
                break;
        }
    }

    @Override
    public void printLog(String log) {
        mLog.setText(log);
    }
}