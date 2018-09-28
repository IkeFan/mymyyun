package com.mmy.maimaiyun.model.login.preserter;

import android.content.Intent;
import android.widget.Toast;

import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.model.login.activity.BindPhoneActivity;
import com.mmy.maimaiyun.model.login.activity.ForgetActivity;
import com.mmy.maimaiyun.model.login.activity.LoginActivity;
import com.mmy.maimaiyun.model.login.model.LoginModel;
import com.mmy.maimaiyun.model.login.view.LoginView;
import com.mmy.maimaiyun.model.main.ui.activity.MainActivity;
import com.mmy.maimaiyun.utils.GlobalUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017/8/12 0012 15:55
 * @描述 TODO
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginView mV;

    @Inject
    LoginModel mModel;
    @Inject
    App        mApp;
    @Inject
    Gson       mGson;


    private final LoginActivity mLoginActivity;

    @Inject
    public LoginPresenter(LoginView v) {
        mV = v;
        mLoginActivity = (LoginActivity) mV;
    }

    public void login(String phone, final String pwd) {
        HashMap<String, String> data = new HashMap<>();
        data.put("username", phone);
        data.put("pwd", pwd);
        mModel.pushData(new OnLoadDataListener<BaseBean<UserBean>>() {
            @Override
            public void onResponse(String body, BaseBean<UserBean> data, IBean iBean) {
                if (iBean.status == 1) {
                    //缓存用户信息
                    GlobalUtil.getInstance().refreshUserInfo(data.data, mV);
                    GlobalUtil.getInstance().saveUserPwd(pwd);
                    mLoginActivity.startActivity(new Intent(mApp, MainActivity.class));
                    mLoginActivity.finish();
                }
                if (iBean.status == 2) {//三方账号登录
                    mLoginActivity.startActivity(new Intent(mLoginActivity, ForgetActivity.class));
                    Toast.makeText(mApp, "三方账号请设置新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                mV.hideLoading();
            }

            @Override
            public void onStart() {
                mV.showLoading();
            }

            @Override
            public void onCompleted() {
                mV.hideLoading();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<UserBean>>() {
                }.getType();
            }
        }, data);
    }

    public void thirdParty(String headimg, String openid, String nickname) {
        mModel.thirdParty(new OnLoadDataListener<BaseBean>() {
            @Override
            public void onResponse(String body, BaseBean data) {
                if (data.status == 0) {
                    //请求失败
                    Toast.makeText(mApp, "登录失败，请重新尝试", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (data.status == 1) {
                    //登录成功,缓存用户信息
                    Type type = new TypeToken<BaseBean<UserBean>>() {
                    }.getType();
                    BaseBean<UserBean> userBean = mGson.fromJson(body, type);
                    GlobalUtil.getInstance().refreshUserInfo(userBean.data, mV);
                    mLoginActivity.startActivity(new Intent(mApp, MainActivity.class));
                    mLoginActivity.finish();
                }
                if (data.status == 2) {
                    //绑定手机
                    Toast.makeText(mApp, "请绑定您的手机号", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mApp, BindPhoneActivity.class);
                    intent.putExtra("headimg", headimg);
                    intent.putExtra("openid", openid);
                    intent.putExtra("nickname", nickname);
                    mLoginActivity.startActivity(intent);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean>() {
                }.getType();
            }
        }, headimg, openid, nickname);
    }
}
