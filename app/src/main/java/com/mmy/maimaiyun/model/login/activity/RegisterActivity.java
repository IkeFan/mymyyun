package com.mmy.maimaiyun.model.login.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
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
import com.mmy.maimaiyun.model.login.component.DaggerRegisterComponent;
import com.mmy.maimaiyun.model.login.module.RegisterModule;
import com.mmy.maimaiyun.model.login.preserter.RegisterPresenter;
import com.mmy.maimaiyun.model.login.view.RegisterView;
import com.mmy.maimaiyun.model.personal.ui.activity.AgreementActivity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView, CountDownTask
        .OnCountDownTaskListener {


    @Bind(R.id.phone)
    EditText  mPhone;
    @Bind(R.id.phone_icon)
    ImageView mPhoneIcon;
    @Bind(R.id.pwd)
    EditText  mPwd;
    @Bind(R.id.pwd_icon)
    ImageView mPwdIcon;
    @Bind(R.id.pwd2)
    EditText  mPwd2;
    @Bind(R.id.pwd2_icon)
    ImageView mPwd2Icon;
    @Bind(R.id.code)
    EditText  mCode;
    @Bind(R.id.time)
    TextView  mTime;
    @Bind(R.id.request_code)
    EditText  mRequestCode;
    @Bind(R.id.request_code_icon)
    ImageView mRequestCodeIcon;
    @Bind(R.id.logo)
    ImageView mLogo;
    private CountDownTask mCountDownTask;
    private boolean       mIsChecked;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerRegisterComponent.builder()
                .appComponent(appComponent)
                .registerModule(new RegisterModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mCountDownTask = new CountDownTask(mHandler, this);
        Picasso.with(this).load(BuildConfig.APP_LOGO).into(mLogo);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        super.initEvent();
        new EditTextHelper().setupEditTextAndIcon(mPhone, mPhoneIcon);
        new EditTextHelper().setupEditTextAndIcon(mPwd, mPwdIcon);
        new EditTextHelper().setupEditTextAndIcon(mPwd2, mPwd2Icon);
        new EditTextHelper().setupEditTextAndIcon(mRequestCode, mRequestCodeIcon);
    }

    @OnClick({ R.id.register, R.id.time, R.id.server_text})
    public void click(View view) {
        String phone = mPhone.getText().toString().trim();
        switch (view.getId()) {
            case R.id.server_text:
                startActivity(new Intent(this, AgreementActivity.class));
                break;
            case R.id.register:
                register(phone);
                break;
            case R.id.time:
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() != 11) {
                    Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.sendCode(phone);
                mCountDownTask.start();
                break;
        }
    }

    @OnCheckedChanged(R.id.server)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mIsChecked = isChecked;
    }

    private void register(String phone) {
        String pwd = mPwd.getText().toString().trim();
        String pwd2 = mPwd2.getText().toString().trim();
        String code = mCode.getText().toString().trim();
        String requestCode = mRequestCode.getText().toString().trim();
        mPresenter.register(phone, pwd, pwd2, code, requestCode, mIsChecked);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public void onRegisterSuccess() {
        //返回登录
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRegisterFailed(String msg) {
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