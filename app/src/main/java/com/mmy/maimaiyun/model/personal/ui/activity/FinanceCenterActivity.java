package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.DDCardActivity;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.EventBean;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.utils.GlobalUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 财务中心
 */
public class FinanceCenterActivity extends BaseActivity {

    @Bind(R.id.title_center_text)
    TextView mTitle;
    @Bind(R.id.Cumulative_money)
    TextView mCumulativeMoney;
    @Bind(R.id.balance)
    TextView mBalance;
    @Bind(R.id.frozen_money)
    TextView mFrozen_money;
    @Bind(R.id.pay_points)
    TextView mPay_points;
    @Bind(R.id.bank_car_manager)
    TextView mBankCarManager;
    @Bind(R.id.transaction_record)
    TextView mTransactionRecord;
    @Bind(R.id.recharge)
    TextView mRecharge;
    @Bind(R.id.withdrawals)
    TextView mWithdrawals;
    @Bind(R.id.mcdull)
    TextView mMcdull;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("财务中心");
        refreshUserInfoView();
    }

    @Override
    public boolean registerEventBus() {
        return true;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshUserInfoView();
    }

    private void refreshUserInfoView() {
        UserBean userBean = getUserBean();
        mCumulativeMoney.setText(userBean.distribut_money + "");
        mBalance.setText(userBean.member_money + "");
        mFrozen_money.setText("￥" + userBean.frozen_money);
        mPay_points.setText(userBean.pay_points + "");
        mMcdull.setText(userBean.mcdull + "");
    }

    @OnClick({R.id.bank_car_manager, R.id.recharge, R.id.withdrawals, R.id.transaction_record, R.id.duducard})
    public void click(View view) {
        Intent intent = new Intent(this, RechargeWithdrawalsActivity.class);
        switch (view.getId()) {
            case R.id.duducard:
                startActivity(new Intent(this, DDCardActivity.class));
                break;
            case R.id.transaction_record://交易记录
                startActivity(new Intent(this, TradeRecordActivity.class));
                break;
            case R.id.recharge://充值
                intent.putExtra("pageType", RechargeWithdrawalsActivity.RECHARGE);
                startActivityForResult(intent,0);
                break;
            case R.id.withdrawals://提现
                intent.putExtra("pageType", RechargeWithdrawalsActivity.WITHDRAWALS);
                startActivityForResult(intent,0);
                break;
            case R.id.bank_car_manager:
                startActivity(new Intent(this, BankCarActivity.class));
                break;
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_finance_center;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBean event) {
        if (GlobalUtil.REFRESH_USER_INFO.equals(event.getMeg()))
            refreshUserInfoView();
    }
}
