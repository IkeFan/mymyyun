package com.mmy.maimaiyun.model.personal.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.BankCarBean;
import com.mmy.maimaiyun.data.bean.EventBean;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.model.personal.component.DaggerRecharWithdrawalsComponent;
import com.mmy.maimaiyun.model.personal.module.RecharWithdrawalsModule;
import com.mmy.maimaiyun.model.personal.presenter.RecharWithdrawalsPresenter;
import com.mmy.maimaiyun.model.personal.view.RecharWithdrawalsView;
import com.mmy.maimaiyun.model.shopping.presenter.PayPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 充值 提现
 */
public class RechargeWithdrawalsActivity extends BaseActivity<RecharWithdrawalsPresenter> implements
        RecharWithdrawalsView {

    @Bind(R.id.title_center_text)
    TextView  mTitle;
    @Bind(R.id.submit)
    Button    mSubmit;
    @Bind(R.id.money_text)
    EditText  mMoneyText;
    @Bind(R.id.type)
    TextView  mType;
    @Bind(R.id.money)
    TextView  mMoney;
    @Bind(R.id.bank_info)
    TextView  mBankInfo;
    @Bind(R.id.bank_icon)
    ImageView mBankIcon;

    @Bind(R.id.select_zfb)
    View mSelectzfb;
    @Bind(R.id.select_wx)
    View mSelectwx;
    @Bind(R.id.select_back)
    View mSelectBank;
    public static final int RECHARGE    = 0;
    public static final int WITHDRAWALS = 1;
    static              int currentType = -1;
    @Bind(R.id.cb_zfb)
    CheckBox mCbZfb;
    @Bind(R.id.cb_wx)
    CheckBox mCbWx;
    @Bind(R.id.cb_bk)
    CheckBox mCbBk;
    private BankCarBean      mBean;
    private int              mSelectId;
    private SweetAlertDialog mSweetAlertDialog;
    private SweetAlertDialog mSweetAlertDialog2;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerRecharWithdrawalsComponent.builder()
                .appComponent(appComponent)
                .recharWithdrawalsModule(new RecharWithdrawalsModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        mMoneyText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        currentType = getIntent().getIntExtra("pageType", -1);
        if (currentType == WITHDRAWALS) {
            mSelectwx.setVisibility(View.GONE);
            mSelectzfb.setVisibility(View.GONE);
        } else {
            mSelectBank.setVisibility(View.GONE);
        }
        mTitle.setText(currentType == RECHARGE ? "充值" : "提现");
        mSubmit.setText(currentType == RECHARGE ? "充值" : "提现");
        mType.setText(currentType == RECHARGE ? "充值方式" : "提现方式");
        mMoneyText.setHint(currentType == RECHARGE ? "充值金额" : "提现金额");
        refreshUserInfo();
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        mMoneyText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String money = s.toString();
                if (money.equals(".")) {
                    mMoneyText.setText("0" + money);
                    mMoneyText.setSelection(money.length()+1);
                    return;
                }
                if (money.contains(".") && money.charAt(money.length() - 1) != '.') {
                    String[] split = money.split("\\.");
                    if (split.length == 2 && split[1].length() > 2) {
                        money = split[0] + "." + split[1].substring(0, 2);
                        mMoneyText.setText(money);
                        mMoneyText.setSelection(money.length());
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    @OnClick({R.id.submit, R.id.show_bank_list, R.id.select_bank_card, R.id.select_wx, R.id.select_zfb})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.show_bank_list:
                Intent intent = new Intent(this, BankCarActivity.class);
                intent.putExtra("action", BankCarActivity.ACTION_SELECT_BANK_CARD);
                startActivityForResult(intent, BankCarActivity.ACTION_SELECT_BANK_CARD);
                break;
            case R.id.submit:
                submit();
                break;
            case R.id.select_bank_card:
                mSelectId = R.id.select_bank_card;
                mCbBk.setChecked(true);
                mCbZfb.setChecked(false);
                mCbWx.setChecked(false);
                break;
            case R.id.select_wx:
                mSelectId = R.id.select_wx;
                mCbBk.setChecked(false);
                mCbZfb.setChecked(false);
                mCbWx.setChecked(true);
                break;
            case R.id.select_zfb:
                mSelectId = R.id.select_zfb;
                mCbBk.setChecked(false);
                mCbZfb.setChecked(true);
                mCbWx.setChecked(false);
                break;
        }
    }

    private void submit() {
        String money = mMoneyText.getText().toString().trim();
        if (TextUtils.isEmpty(money) || Float.parseFloat(money) <= 0) {
            Toast.makeText(this, "请输入正确金额", Toast.LENGTH_SHORT).show();
            return;
        }
        //选择银行卡
        if (mSelectId == R.id.cb_bk && mBean == null) {
            Toast.makeText(this, "请先选择银行卡", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (currentType) {
            case RECHARGE://充值
                mPresenter.recharge(mSelectId == R.id.select_wx ? "wxpay" : "alipay", money);
                break;
            case WITHDRAWALS://提现
                if (mBean == null) {
                    Toast.makeText(this, "请先选择银行卡", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.submit(mBean.id, money);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case BankCarActivity.ACTION_SELECT_BANK_CARD:
                    mBean = (BankCarBean) data.getSerializableExtra("bean");
                    String bank_card = mBean.bank_card;
                    mBankInfo.setText(mBean.name + " (尾号" + bank_card.substring(bank_card.length() - 4, bank_card
                            .length()) + ")");
                    break;
            }
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_recharge_withdrawals;
    }

    @Override
    public void refreshUserInfo() {
        UserBean userBean = getUserBean();
        mMoney.setText(userBean.member_money + "");
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
            mPresenter.refreshUserInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
