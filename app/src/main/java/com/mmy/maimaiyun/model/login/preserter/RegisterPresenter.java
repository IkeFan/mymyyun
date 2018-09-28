package com.mmy.maimaiyun.model.login.preserter;

import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.login.model.RegisterModel;
import com.mmy.maimaiyun.model.login.view.RegisterView;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/8/24 0024 11:04
 * @描述
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {
    private RegisterView mView;
    @Inject
    RegisterModel mModel;
    @Inject
    Gson          mGson;

    @Inject
    public RegisterPresenter(RegisterView view) {
        mView = view;
    }

    public void register(String phone, String pwd, String pwd2, String code, String requestCode, boolean isChecked) {
        if (TextUtils.isEmpty(phone)) {
            mView.onRegisterFailed("手机号不能为空");
            return;
        }
        if (phone.length()!=11){
            Toast.makeText(mApp, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            mView.onRegisterFailed("密码不能为空");
            return;
        }
        if (pwd.length()<6){
            Toast.makeText(mApp, "请输入长度大于6的密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd2)) {
            mView.onRegisterFailed("请再次输入密码");
            return;
        }
        if (!pwd.equals(pwd2)) {
            mView.onRegisterFailed("两次输入密码不一致");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            mView.onRegisterFailed("验证码不能为空");
            return;
        }
        if (!isChecked){
            mView.onRegisterFailed("请阅读并同意 用户服务协议");
            return;
        }
        mModel.register(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                if (data.status == 1)
                    mView.onRegisterSuccess();
                else
                    mView.onRegisterFailed(data.info);
            }

            @Override
            public void onFailure(String body, Throwable t) {
                IBean iBean = mGson.fromJson(body, IBean.class);
                mView.onRegisterFailed(iBean.info);
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<IBean>>() {
                }.getType();
            }
        }, phone, pwd, code, 1 + "", requestCode);
    }

    public void sendCode(String phone) {
        mModel.sendCode(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                Toast.makeText(mApp, data.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {
//                Toast.makeText(mApp, body, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, phone);
    }
}
