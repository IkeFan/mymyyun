package com.mmy.maimaiyun.model.shopping.ui.activity;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.EventBean;
import com.mmy.maimaiyun.data.bean.SubOrderBean;
import com.mmy.maimaiyun.helper.PayHelper;
import com.mmy.maimaiyun.model.shopping.component.DaggerPayComponent;
import com.mmy.maimaiyun.model.shopping.module.PayModule;
import com.mmy.maimaiyun.model.shopping.presenter.PayPresenter;
import com.mmy.maimaiyun.model.shopping.view.PayView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.mmy.maimaiyun.R.id.pay;

/**
 * 支付界面
 */
public class PayActivity extends BaseActivity<PayPresenter> implements PayView {

    @Bind(R.id.title_center_text)
    TextView mTitleCenterText;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.money_text)
    TextView mMoney;
    @Bind(R.id.type)
    TextView mType;
    @Bind(pay)
    Button   mPay;
    @Bind(R.id.cb_zfb)
    CheckBox mCbZfb;
    @Bind(R.id.cb_weixin)
    CheckBox mCbWeixin;
    @Bind(R.id.cb_bank_car)
    CheckBox mCbBankcar;
    @Bind(R.id.cb_balance)
    CheckBox mBalance;
    @Bind(R.id.my_balance)
    TextView mMyBalance;
    @Bind(R.id.textView)
    TextView mTextView;
    private int mPayType = -1;
    private SubOrderBean     mBean;
    private SweetAlertDialog mSweetAlertDialog;
    private SweetAlertDialog mSweetAlertDialog2;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerPayComponent.builder()
                .appComponent(appComponent)
                .payModule(new PayModule(this))
                .build().inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("支付");
        String money = getIntent().getStringExtra("money");
        mMoney.setText(money);
        mPay.setText("立即支付 " + money + "元");
        mMyBalance.setText("(可用余额:￥ " + getUserBean().member_money + ")");
        new CountDown().start();
    }

    @Override
    public void initData() {
        mBean = (SubOrderBean) getIntent().getSerializableExtra("bean");
    }

    @OnClick({R.id.pay, R.id.zfb, R.id.weixin, R.id.bank_car, R.id.balance})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.pay:
                if (mPayType == -1) {
                    Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.pay(mPayType, mBean.order_sn, mBean.order_amount + "");
                break;
            case R.id.zfb:
                mPayType = PayHelper.ZFB;
                mCbZfb.setChecked(true);
                mCbBankcar.setChecked(false);
                mCbWeixin.setChecked(false);
                mBalance.setChecked(false);
                break;
            case R.id.weixin:
                mPayType = PayHelper.WEIXIN;
                mCbZfb.setChecked(false);
                mCbBankcar.setChecked(false);
                mBalance.setChecked(false);
                mCbWeixin.setChecked(true);
                break;
            case R.id.bank_car:
                mPayType = PayHelper.BANK_CAR;
                mCbZfb.setChecked(false);
                mCbBankcar.setChecked(true);
                mCbWeixin.setChecked(false);
                mBalance.setChecked(false);
                break;
            case R.id.balance:
                mPayType = PayHelper.BALANCE;
                mBalance.setChecked(true);
                mCbZfb.setChecked(false);
                mCbBankcar.setChecked(false);
                mCbWeixin.setChecked(false);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_pay;
    }

    @Override
    public void refreshView(String msg) {
        mTextView.setText(msg);
    }

    @Override
    public void getOrderFail() {
        if (mSweetAlertDialog == null)
            mSweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("错误")
                    .setContentText("获取订单信息失败，请尝试重新提交.")
                    .setConfirmText("确认")
                    .setConfirmClickListener(Dialog::dismiss);
        mSweetAlertDialog.show();
    }

    //支付宝回调
    @Override
    public void onResult(int type) {
        if (mSweetAlertDialog2 == null)
            mSweetAlertDialog2 = new SweetAlertDialog(this, type == PayPresenter.SUCCESS ? SweetAlertDialog
                    .SUCCESS_TYPE : SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("支付结果")
                    .setContentText(type == PayPresenter.SUCCESS ? "支付成功!" : "支付失败!")
                    .setConfirmText("确认")
                    .setConfirmClickListener(sweetAlertDialog -> {
                        if (type == PayPresenter.SUCCESS) {
                            finish();
                        }
                        sweetAlertDialog.dismiss();
                    });
        mSweetAlertDialog2.show();
    }


    //微信支付回调
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBean event) {
        if ("pay_success".equals(event.getMeg()))
            finish();
    }


    public class CountDown implements Runnable {

        int h = 15;
        int s = 0;

        @Override
        public void run() {
            if (s == 0) {
                h--;
                s = 60;
                if (h < 0) {
                    mTime.setText("支付已过期");
                    return;
                }
            } else {
                s--;
            }
            mTime.setText(h + "分" + s + "秒");
            mHandler.postDelayed(this, 1000);
        }

        public void start() {
            mHandler.post(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
