package com.mmy.maimaiyun.model.login.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.BuildConfig;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.helper.CountDownTask;
import com.mmy.maimaiyun.helper.EditTextHelper;
import com.mmy.maimaiyun.model.login.component.DaggerForgetComponent;
import com.mmy.maimaiyun.model.login.module.ForgetModule;
import com.mmy.maimaiyun.model.login.preserter.ForgetPresenter;
import com.mmy.maimaiyun.model.login.view.ForgetView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 忘记密码
 */
public class ForgetActivity extends BaseActivity<ForgetPresenter> implements ForgetView, CountDownTask
        .OnCountDownTaskListener {

    @Bind(R.id.phone)
    EditText  mPhone;
    @Bind(R.id.phone_icon)
    ImageView mPhoneIcon;
    @Bind(R.id.code)
    EditText  mCode;
    @Bind(R.id.time)
    TextView  mTime;
    @Bind(R.id.pwd)
    EditText  mPwd;
    @Bind(R.id.pwd_icon)
    ImageView mPwdIcon;
    @Bind(R.id.pwd2)
    EditText  mPwd2;
    @Bind(R.id.pwd2_icon)
    ImageView mPwd2Icon;
    @Bind(R.id.forget)
    Button    mForget;
    @Bind(R.id.logo)
    ImageView mLogo;
    private CountDownTask mCountDownTask;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerForgetComponent.builder()
                .appComponent(appComponent)
                .forgetModule(new ForgetModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        Picasso.with(this).load(BuildConfig.APP_LOGO).into(mLogo);
    }

    @Override
    public void initData() {
        mCountDownTask = new CountDownTask(mHandler, this);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        new EditTextHelper().setupEditTextAndIcon(mPhone, mPhoneIcon);
        new EditTextHelper().setupEditTextAndIcon(mPwd, mPwdIcon);
        new EditTextHelper().setupEditTextAndIcon(mPwd2, mPwd2Icon);
    }

    @OnClick({R.id.forget, R.id.time})
    public void click(View view) {
        String phone = mPhone.getText().toString().trim();
        switch (view.getId()) {
            case R.id.forget:
                String code = mCode.getText().toString().trim();
                String pwd = mPwd.getText().toString().trim();
                String pwd2 = mPwd2.getText().toString().trim();
                mPresenter.submit(phone, code, pwd, pwd2);
                break;
            case R.id.time:
                mPresenter.sendCode(phone);
                mCountDownTask.start();
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_forget;
    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void progress(int progress) {
        mTime.setClickable(false);
        mTime.setText(progress + "秒后重发");
    }

    @Override
    public void complete() {
        mTime.setClickable(true);
        mTime.setText("获取验证码");
    }

}
