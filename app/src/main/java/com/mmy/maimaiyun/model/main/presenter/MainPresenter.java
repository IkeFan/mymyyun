package com.mmy.maimaiyun.model.main.presenter;

import com.blankj.utilcode.util.AppUtils;
import com.mmy.maimaiyun.base.able.BaseBean;
import com.mmy.maimaiyun.base.able.IBean;
import com.mmy.maimaiyun.base.able.OnLoadDataListener;
import com.mmy.maimaiyun.base.mvp.BasePresenter;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.data.bean.VersionBean;
import com.mmy.maimaiyun.model.main.model.MainModel;
import com.mmy.maimaiyun.model.main.view.MainView;
import com.mmy.maimaiyun.utils.GlobalUtil;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;


/**
 * @创建者 lucas
 * @创建时间 2017/9/23 0023 16:21
 * @描述 TODO
 */

public class MainPresenter extends BasePresenter<MainView> {
    private MainView mView;

    @Inject
    MainModel mModel;

    @Inject
    public MainPresenter(MainView view) {
        mView = view;
    }

    public void checkVersion() {
        mModel.getVersion(new OnLoadDataListener<BaseBean<VersionBean>>() {
            @Override
            public void onResponse(String body, BaseBean<VersionBean> data) {
                int versionCode = AppUtils.getAppVersionCode();
                if (data.data != null)
                    GlobalUtil.downloadApkUrl = data.data.url;
                if (data.data != null && versionCode < data.data.version_code) {
                    mView.onVersionChange(data.data.url);
                }
            }

            @Override
            public void onFailure(String body, Throwable t) {

            }

            @Override
            public Type getBeanType() {
                return new TypeToken<BaseBean<VersionBean>>() {
                }.getType();
            }
        }, mView.getUserBean().token);
    }

    public void refreshUserInfo() {
        mModel.getUserInfo(new OnLoadDataListener<BaseBean<UserBean>>() {
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
