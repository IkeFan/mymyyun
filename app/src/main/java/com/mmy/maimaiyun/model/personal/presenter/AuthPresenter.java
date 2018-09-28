package com.mmy.maimaiyun.model.personal.presenter;

import android.widget.Toast;

import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.model.main.model.MainModel;
import com.mmy.maimaiyun.model.personal.model.AuthModel;
import com.mmy.maimaiyun.model.personal.view.AuthView;
import com.mmy.maimaiyun.utils.GlobalUtil;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import retrofit2.http.Part;


/**
 * @创建者 lucas
 * @创建时间 2017/12/11 0011 14:29
 * @描述 TODO
 */

public class AuthPresenter extends BasePresenter<AuthView> {
    private AuthView mView;

    @Inject
    AuthModel mModel;
    @Inject
    MainModel mMainModel;

    @Inject
    public AuthPresenter(AuthView view) {
        mView = view;
    }

    public void submitAuth(@Part List<MultipartBody.Part> data) {
        mModel.auth(new OnLoadDataListener<IBean>() {
            @Override
            public void onResponse(String body, IBean data, IBean iBean) {
                super.onResponse(body, data, iBean);
                Toast.makeText(mApp, iBean.info, Toast.LENGTH_SHORT).show();
                if (iBean.status==1){
                    refreshUserInfo();
                    mView.finishSelf();
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<IBean>(){}.getType();
            }
        }, data);
    }
    public void refreshUserInfo() {
        mMainModel.getUserInfo(new OnLoadDataListener<BaseBean<UserBean>>() {
            @Override
            public void onResponse(String body, BaseBean<UserBean> data, IBean iBean) {
                super.onResponse(body, data, iBean);
                GlobalUtil.getInstance().refreshUserInfo(data.data, mView);
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<UserBean>>() {
                }.getType();
            }
        }, mView.getUserBean().member_id);
    }

}
