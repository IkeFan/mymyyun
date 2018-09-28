package com.mmy.maimaiyun.model.login.preserter;

import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.login.model.ForgetModel;
import com.mmy.maimaiyun.model.login.model.RegisterModel;
import com.mmy.maimaiyun.model.login.view.ForgetView;

import java.lang.reflect.Type;
import java.util.HashMap;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/8/24 0024 11:38
 * @描述 TODO
 */

public class ForgetPresenter extends BasePresenter<ForgetView> {
    private ForgetView mView;

    @Inject
    RegisterModel mRegisterModel;

    @Inject
    ForgetModel mModel;

    @Inject
    public ForgetPresenter(ForgetView view) {
        mView = view;
    }

    public void submit(String phone, String code, String pwd, String pwd2) {
        if (TextUtils.isEmpty(phone)) {
            mView.onFailed("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            mView.onFailed("验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            mView.onFailed("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd2)) {
            mView.onFailed("请再次输入密码");
            return;
        }
        if (!pwd.equals(pwd2)) {
            mView.onFailed("两次输入的密码不一致");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", pwd);
        map.put("code", code);
        mModel.pushData(new OnLoadDataListener() {
            @Override
            public void onResponse(String body, Object data, IBean iBean) {
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
                if (iBean.status == 1)
                    mView.finishSelf();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                Toast.makeText(mApp, "修改失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public Type getBeanType() {
                return null;
            }
        }, map);
    }

    public void sendCode(String phone) {
        mRegisterModel.sendCode(new OnLoadDataListener<BaseBean<String>>() {
            @Override
            public void onResponse(String body, BaseBean<String> data, IBean iBean) {
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                Toast.makeText(mApp, "发送失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<String>>() {
                }.getType();
            }
        }, phone);
    }
}
