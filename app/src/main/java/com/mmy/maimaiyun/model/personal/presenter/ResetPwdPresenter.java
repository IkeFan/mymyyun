package com.mmy.maimaiyun.model.personal.presenter;


import android.content.Intent;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.model.login.activity.LoginActivity;
import com.mmy.maimaiyun.model.personal.model.ResetPwdModel;
import com.mmy.maimaiyun.model.personal.view.ResetPwdView;

import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * @创建者 lucas
 * @创建时间 2017-11-08 02-14-06
 * @描述 修改密码控制器
 */

public class ResetPwdPresenter extends BasePresenter<ResetPwdView> {
    private ResetPwdView mV;

    @Inject
    ResetPwdModel mModel;
    private final BaseActivity mActivity;

    @Inject
    public ResetPwdPresenter(ResetPwdView v) {
        mV = v;
        mActivity = (BaseActivity) v;
    }

    public void resetPwd(String username, String pwd, String newpwd) {
        mModel.resetPwd(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data) {
                Toast.makeText(mActivity, data.info, Toast.LENGTH_SHORT).show();
                if (data.status==1){
                    mV.finishSelf();
                    mActivity.closeAllActivity();
                    mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>() {
                }.getType();
            }
        }, username, pwd, newpwd);
    }
}
