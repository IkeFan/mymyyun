package com.mmy.maimaiyun.model.login.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.helper.CountDownTask;
import com.mmy.maimaiyun.model.login.component.DaggerBindPhoneComponent;
import com.mmy.maimaiyun.model.login.module.BindPhoneModule;
import com.mmy.maimaiyun.model.login.preserter.BindPhonePresenter;
import com.mmy.maimaiyun.model.login.view.BindPhoneView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 绑定手机
 */
public class BindPhoneActivity extends BaseActivity<BindPhonePresenter> implements CountDownTask.OnCountDownTaskListener,BindPhoneView {

    @Bind(R.id.title_center_text)
    TextView  mTitleCenterText;
    @Bind(R.id.phone)
    EditText  mPhone;
    @Bind(R.id.phone_icon)
    ImageView mPhoneIcon;
    @Bind(R.id.code)
    EditText  mCode;
    @Bind(R.id.time)
    TextView  mTime;
    @Bind(R.id.submit)
    Button    mSubmit;
    private CountDownTask mCountDownTask;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerBindPhoneComponent.builder()
                .appComponent(appComponent)
                .bindPhoneModule(new BindPhoneModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mCountDownTask = new CountDownTask(mHandler, this);
        mTitleCenterText.setText("绑定手机");
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.submit, R.id.time})
    public void click(View view){
        String phone = mPhone.getText().toString().trim();
        switch (view.getId()) {
            case R.id.submit:
                submit();
                break;
            case R.id.time:
                mPresenter.sendCode(phone);
                mCountDownTask.start();
                break;
        }
    }

    private void submit() {
        String phone = mPhone.getText().toString().trim();
        String code = mCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(code)){
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.bindPhone(phone,code,getIntent());
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_bind_phone;
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
