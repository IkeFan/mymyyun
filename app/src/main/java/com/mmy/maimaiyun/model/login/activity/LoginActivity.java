package com.mmy.maimaiyun.model.login.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.BuildConfig;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.WXUserInfoBean;
import com.mmy.maimaiyun.helper.EditTextHelper;
import com.mmy.maimaiyun.model.login.component.DaggerLoginComponent;
import com.mmy.maimaiyun.model.login.module.LoginModule;
import com.mmy.maimaiyun.model.login.preserter.LoginPresenter;
import com.mmy.maimaiyun.model.login.view.LoginView;
import com.mmy.maimaiyun.utils.Constants;
import com.mmy.maimaiyun.wxapi.WXEntryActivity;
import com.squareup.picasso.Picasso;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Bind(R.id.register)
    TextView mRegister;
    @Bind(R.id.forget)
    TextView mForget;
    @Bind(R.id.username)
    EditText mUsername;
    @Bind(R.id.pwd)
    EditText mPwd;
    @Bind(R.id.username_icon)
    View mUsernameIcon;
    @Bind(R.id.pwd_icon)
    View mPwdIcon;
    @Bind(R.id.logo)
    ImageView mLogo;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerLoginComponent.builder().loginModule(new LoginModule(this))
                .appComponent(appComponent).build().inject(this);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        Picasso.with(this).load(BuildConfig.APP_LOGO).into(mLogo);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        new EditTextHelper().setupEditTextAndIcon(mUsername,mUsernameIcon);
        new EditTextHelper().setupEditTextAndIcon(mPwd,mPwdIcon);
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.submit, R.id.register, R.id.forget, R.id.weixin})
    public void click(View view){
        switch (view.getId()) {
            case R.id.submit:
                login();
                break;
            case R.id.register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.forget:
                startActivity(new Intent(this,ForgetActivity.class));
                break;
            case R.id.weixin:
                IWXAPI mApi = WXAPIFactory.createWXAPI(this,null);
                //使授权界面显示loading,并回调
                WXEntryActivity.ACTION = WXEntryActivity.WX_LOGIN;
                mApi.registerApp(Constants.WEIXIN_APPKEY);
                if (mApi != null && mApi.isWXAppInstalled()) {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_mmy_maimaiyun";
                    mApi.sendReq(req);
                } else
                    Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //登录
    private void login() {
        String username = mUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = mPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.login(username,pwd);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WXUserInfoBean event) {
        mPresenter.thirdParty(event.headimgurl,event.openid,event.nickname);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
