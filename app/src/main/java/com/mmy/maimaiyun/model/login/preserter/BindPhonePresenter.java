package com.mmy.maimaiyun.model.login.preserter;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.model.login.model.BindPhoneModel;
import com.mmy.maimaiyun.model.login.model.RegisterModel;
import com.mmy.maimaiyun.model.login.view.BindPhoneView;
import com.mmy.maimaiyun.model.main.ui.activity.MainActivity;
import com.mmy.maimaiyun.utils.GlobalUtil;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/11/2 0002 10:19
 * @描述
 */

public class BindPhonePresenter extends BasePresenter<BindPhoneView> {
    private BindPhoneView mView;
    @Inject
    BindPhoneModel mModel;
    @Inject
    RegisterModel mRegisterModel;
    @Inject
    Gson mGson;
    private final Activity mActivity;

    @Inject
    public BindPhonePresenter(BindPhoneView view) {
        mView = view;
        mActivity = (Activity) view;
    }

    public void sendCode(String phone) {
        mRegisterModel.sendCode(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                Toast.makeText(mApp, data.info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String body, Throwable t) {
                Toast.makeText(mApp, "短信发送失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>(){}.getType();
            }
        },phone);
    }

    public void bindPhone(String phone, String code, Intent intent) {
        String headimg = intent.getStringExtra("headimg");
        String openid = intent.getStringExtra("openid");
        String nickname = intent.getStringExtra("nickname");
        mModel.bindPhone(new OnLoadDataListener<BaseBean>() {
            @Override
            public void onResponse(String body, BaseBean data) {
                switch (data.status) {
                    case 0:
                        Toast.makeText(mApp, data.info, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //绑定成功,缓存用户信息
                        Toast.makeText(mActivity, "登录成功", Toast.LENGTH_SHORT).show();
                        Type type = new TypeToken<BaseBean<UserBean>>() {
                        }.getType();
                        BaseBean<UserBean> userBean = mGson.fromJson(body, type);
                        GlobalUtil.getInstance().refreshUserInfo(userBean.data, mView);
                        mActivity.startActivity(new Intent(mActivity, MainActivity.class));
                        mView.finishSelf();
                        break;
                    case 2:
                        //该手机号已被绑定
                        Toast.makeText(mActivity, "该手机号已绑定", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {
                Toast.makeText(mActivity, "登录失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean>(){}.getType();
            }
        },phone,code, headimg, openid, nickname);
    }
}
